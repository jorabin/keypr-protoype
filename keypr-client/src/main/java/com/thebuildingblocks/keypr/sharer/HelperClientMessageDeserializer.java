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

package com.thebuildingblocks.keypr.sharer;

import org.derecalliance.derec.protobuf.Derecmessage.DeRecMessage;
import org.derecalliance.derec.protobuf.Derecmessage.DeRecMessage.HelperMessageBody;

import org.derecalliance.derec.protobuf.ResultOuterClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.stream.Collectors;

public class HelperClientMessageDeserializer {

    private final Map<HelperMessageBody.BodyCase, HelperMessageBody> bodyMessages;
    private final DeRecMessage message;
    private HelperMessageBody body;

    private HelperClientMessageDeserializer(InputStream inputStream) throws IOException {
        message = DeRecMessage.parseFrom(inputStream);
        bodyMessages = message.getMessageBodies()
                .getHelperMessageBodies()
                .getHelperMessageBodyList().stream().collect(Collectors.toMap(HelperMessageBody::getBodyCase, b -> b));

    }

    public <C> C getBodyMessage(HelperMessageBody.BodyCase bodyCase, Class<C> messageClass) {
        return getMessageBody(bodyMessages.get(bodyCase), messageClass);

    }

    public static <C> C getMessageBody(HelperMessageBody body, Class<C> klass) {
        return switch (body.getBodyCase()) {
            case PAIRRESPONSEMESSAGE -> klass.cast(body.getPairResponseMessage());
            case UNPAIRRESPONSEMESSAGE -> klass.cast(body.getUnpairResponseMessage());
            case STORESHARERESPONSEMESSAGE -> klass.cast(body.getStoreShareResponseMessage());
            case VERIFYSHARERESPONSEMESSAGE -> klass.cast(body.getVerifyShareResponseMessage());
            case GETSECRETIDSVERSIONSRESPONSEMESSAGE -> klass.cast(body.getGetSecretIdsVersionsResponseMessage());
            case GETSHARERESPONSEMESSAGE -> klass.cast(body.getGetShareResponseMessage());
            case ERRORRESPONSEMESSAGE -> klass.cast(body.getErrorResponseMessage());
            case BODY_NOT_SET -> throw new IllegalArgumentException("Body is not set for " + body.getBodyCase());
        };
    }

    /**
     * Check for one and only one response of the type indicated and make available via {@link #getBody()}
     * @param inputStream input stream containing the protobuf serialized messages
     * @param bodyCase type of message needed
     * @return a new instance containing the parsed message
     */
    public static HelperClientMessageDeserializer newInstance(InputStream inputStream,
                                                              HelperMessageBody.BodyCase bodyCase) {
        HelperClientMessageDeserializer instance = null;
        try {
            instance = new HelperClientMessageDeserializer(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        if (instance.bodyMessages.isEmpty()) {
            throw new IllegalArgumentException("There are no bodies in the message");
        }
        if (instance.bodyMessages.size() > 1) {
            throw new IllegalArgumentException("There is more than one body in the message");
        }
        if (!instance.bodyMessages.containsKey(bodyCase)) {
            throw new IllegalArgumentException("The response is not a " + bodyCase);
        }
        instance.body = instance.bodyMessages.get(bodyCase);
        return instance;
    }

    public DeRecMessage getMessage() {
        return message;
    }

    public HelperMessageBody getBody() {
        return body;
    }

    public Map<HelperMessageBody.BodyCase, HelperMessageBody> getBodyMessages() {
        return bodyMessages;
    }
}
