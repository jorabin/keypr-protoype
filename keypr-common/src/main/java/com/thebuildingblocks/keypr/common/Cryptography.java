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

package com.thebuildingblocks.keypr.common;

import java.security.*;
import java.util.Base64;

public class Cryptography {

    public static MessageDigest messageDigest;
    public static KeyPairGenerator keyPairGenerator;
    public static SecureRandom secureRandom;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-384");
            keyPairGenerator = KeyPairGenerator.getInstance("EC");
            secureRandom = new SecureRandom();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static String pemFrom(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
    public static PublicKey fromPem(String pemEncodedPublicKey) {
        return null;
    }
}
