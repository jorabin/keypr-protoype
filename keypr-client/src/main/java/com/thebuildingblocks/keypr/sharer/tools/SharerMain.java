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

package com.thebuildingblocks.keypr.sharer.tools;

import com.thebuildingblocks.keypr.common.TestIds;
import com.thebuildingblocks.keypr.sharer.Secret;
import com.thebuildingblocks.keypr.sharer.Sharer;
import org.derecalliance.derec.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.Scanner;

import static com.thebuildingblocks.keypr.common.Cryptography.keyPairGenerator;
import static com.thebuildingblocks.keypr.common.Cryptography.pemFrom;

/**
 * Illustration of use of classes
 */
public class SharerMain {
    static Logger logger = LoggerFactory.getLogger(SharerMain.class);

    public static void main(String[] args) {
        new SharerMain().run();
    }

    public void run() {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        String pem = pemFrom(keyPair.getPublic());
        // build a sharer
        Sharer me = Sharer.newBuilder()
                .id(new DeRecIdentity("Secret Sammy", "mailto:test@example.org", null, pem))
                .keyPair(keyPairGenerator.generateKeyPair())
                .notificationListener(Notifier::logNotification)
                .build();
        // get a secret
        logger.info("Building a secret, wait for it to be recoverable");
        Secret secret = me.newSecret("Martin Luther", "I have a dream".getBytes(StandardCharsets.UTF_8),
                TestIds.INSTANCE.defaultIds);
        // get last version shared - in this case the first version shared
        DeRecVersion v = secret.getVersions().lastEntry().getValue();
        logger.info("Secret version: {}, is protected: {}", v.getVersionNumber(), v.isProtected());

        // update the secret
        logger.info("Updating the secret");
        v = secret.update("I have another dream".getBytes(StandardCharsets.UTF_8));
        logger.info("Secret version: {}, is protected {}", v.getVersionNumber(), v.isProtected());

        logger.info("Closing secret {}", secret.getSecretIdAsUuid());
        // dispose of it
        secret.close();


/*        try {
            // should not be able to update after close
            secret.update("throw me an exception".getBytes(StandardCharsets.UTF_8));
            throw new AssertionError("can't update after close");
        } catch (IllegalStateException e) {
            // correctly throwing exception
            logger.info("[Expected] Exception on update secret", e);
        }*/

        DeRecSecret secret2 = me.newSecret("Genghis Khan", "Something".getBytes(StandardCharsets.UTF_8),
                TestIds.INSTANCE.defaultIds);


        System.out.println("Hit enter to exit");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        logger.info("Shutting down");
        me.close();
    }
}
