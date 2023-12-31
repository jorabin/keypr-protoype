/*
 * Copyright (c) 2023 The Building Blocks Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thebuildingblocks.keypr.helper;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.derecalliance.derec.api.DeRecIdentity;
import org.derecalliance.derec.api.DeRecSecret;
import org.derecalliance.derec.protobuf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.thebuildingblocks.keypr.helper.HelperServerMessageFactory.*;

public class HelperServerResponseProcessing {
    private final static Logger logger = LoggerFactory.getLogger(HelperServer.class);
    private final HelperServerShare.Storage storage;
    private final DeRecIdentity myId;

    public HelperServerResponseProcessing(DeRecIdentity myId, HelperServerShare.Storage storage) {
        this.storage = storage;
        this.myId = myId;
    }

    /**
     * Interface to allow setting of response headers prior to writing to OutputStream
     */
    @FunctionalInterface
    public interface Processor {
        void process() throws IOException;
    }

    /**
     * Process an incoming request and produce a response
     * @param requestBody signed and encrypted request stream
     * @param responseBody signed and encrypted response stream
     * @param preProcessor action to take before writing to responseBody e.g. set headers
     */
    public void process(InputStream requestBody, OutputStream responseBody, Processor preProcessor) throws IOException {
        // deserialise as protobuf
        Derecmessage.DeRecMessage message = Derecmessage.DeRecMessage.parseFrom(requestBody);
        if (!message.hasMessageBodies()) {
            throw new IllegalStateException("Received message has no message body");
        }

        // process the incoming messages
        List<Derecmessage.DeRecMessage.HelperMessageBody> responses = processMessages(message);

        // serialise the responses
        Derecmessage.DeRecMessage reply = Derecmessage.DeRecMessage.newBuilder()
                .setMessageBodies(Derecmessage.DeRecMessage.MessageBodies.newBuilder()
                        .setHelperMessageBodies(Derecmessage.DeRecMessage.HelperMessageBodies.newBuilder()
                                .addAllHelperMessageBody(responses)
                                .build())
                        .build())
                .build();

        preProcessor.process();
        try (OutputStream os = responseBody) {
            reply.writeTo(os);
        }
    }

    public List<Derecmessage.DeRecMessage.HelperMessageBody> processMessages(Derecmessage.DeRecMessage message) throws IOException {
        Derecmessage.DeRecMessage.SharerMessageBodies messageBodies = message.getMessageBodies().getSharerMessageBodies();
        List<Derecmessage.DeRecMessage.HelperMessageBody> responses = new ArrayList<>();
        for (Derecmessage.DeRecMessage.SharerMessageBody messageBody : messageBodies.getSharerMessageBodyList()) {
            Derecmessage.DeRecMessage.HelperMessageBody response = switch (messageBody.getBodyCase()) {
                case PAIRREQUESTMESSAGE -> processPairRequest(message, messageBody.getPairRequestMessage());
                case UNPAIRREQUESTMESSAGE -> processUnPairRequest(message, messageBody.getUnpairRequestMessage());
                case STORESHAREREQUESTMESSAGE -> processShareRequest(message, messageBody.getStoreShareRequestMessage());
                case VERIFYSHAREREQUESTMESSAGE -> processVerifyShareRequestMessage(message, messageBody.getVerifyShareRequestMessage());
                case GETSECRETIDSVERSIONSREQUESTMESSAGE -> processGetSecretIdsVersionsRequestMessage(message, messageBody.getGetSecretIdsVersionsRequestMessage());
                default -> throw new IllegalStateException("Unhandled message received " + messageBody.getBodyCase());
            };
            if (Objects.nonNull(response)) {
                responses.add(response);
            }
        }
        return responses;
    }

    private Derecmessage.DeRecMessage.HelperMessageBody processVerifyShareRequestMessage(Derecmessage.DeRecMessage message,
                                                                                         Verify.VerifyShareRequestMessage verifyShareRequestMessage) {
        logger.info("{} received {}", myId.getName(), verifyShareRequestMessage.getClass().getSimpleName());
        return getVerifyShareResponseMessageBody(verifyShareRequestMessage.getNonce().toByteArray());
    }

    private Derecmessage.DeRecMessage.HelperMessageBody processShareRequest(Derecmessage.DeRecMessage message,
                                                                            Storeshare.StoreShareRequestMessage storeShareRequestMessage) throws InvalidProtocolBufferException {
        logger.info("{} received {}", myId.getName(), storeShareRequestMessage.getClass().getSimpleName());
        ByteString shareBytes = storeShareRequestMessage.getCommittedDeRecShare().getDeRecShare();

        // parse the raw bytes of the incoming message
        Storeshare.DeRecShare share = Storeshare.DeRecShare.parseFrom(shareBytes);
        logger.info("Share version was {}", share.getVersion());
        // and save it
        HelperServerShare hss = new HelperServerShare(new DeRecSecret.Id(share.getSecretId().toByteArray()),
                share.getVersion(), share.getEncryptedSecret().toByteArray());
        storage.putShare(message.getSender(), hss);

        return getShareResponseMessageBody(ResultOuterClass.StatusEnum.OK, "Share stored", share.getVersion());
    }

    private Derecmessage.DeRecMessage.HelperMessageBody processUnPairRequest(Derecmessage.DeRecMessage message,
                                                                             Unpair.UnpairRequestMessage unpairRequestMessage) {
        logger.info("{} received {}", myId.getName(), unpairRequestMessage.getClass().getSimpleName());
        return getUnpairResponseMessageBody();
    }

    private Derecmessage.DeRecMessage.HelperMessageBody processPairRequest(Derecmessage.DeRecMessage message, Pair.PairRequestMessage pairRequestMessage) {
        Map<String, Object> communicationInfo =
                pairRequestMessage.getCommunicationInfo().getCommunicationInfoEntriesList()
                        .stream()
                        .collect(Collectors.toMap(Communicationinfo.CommunicationInfoKeyValue::getKey,
                                kv -> kv.hasStringValue() ? kv.getStringValue() : kv.getBytesValue()));
        logger.info("{} received {} \"{}\"", myId.getName(), pairRequestMessage.getClass().getSimpleName(),
                communicationInfo.get("name"));
        return getPairResponseMessageBody();
    }

    private Derecmessage.DeRecMessage.HelperMessageBody processGetSecretIdsVersionsRequestMessage(Derecmessage.DeRecMessage message, Secretidsversions.GetSecretIdsVersionsRequestMessage getSecretIdsVersionsRequestMessage) {
        return getGetSecretIdsVersionsResponseMessageBody(storage.getShares(message.getSender()));
    }
}
