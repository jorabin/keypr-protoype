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

import org.derecalliance.derec.api.DeRecIdentity;

import java.net.URI;
import java.security.KeyPair;
import java.util.*;

import static com.thebuildingblocks.keypr.common.Cryptography.keyPairGenerator;
import static com.thebuildingblocks.keypr.common.Cryptography.pemFrom;

public enum TestIds {
    INSTANCE();

    public record Counterparty(String name, String email, URI address) {
        public Counterparty(String name, String email){
            this(name, email, URI.create("https://example.com"));
        }
        static KeyPair bogusKeyPair = keyPairGenerator.generateKeyPair();
        DeRecIdentity asDeRecIdentity(){
            return new DeRecIdentity(name, email, address.toString(), pemFrom(bogusKeyPair.getPublic()));
        }
    }

    public final List<DeRecIdentity> defaultIds = new ArrayList<>();
    public final List<DeRecIdentity> invalidIds = new ArrayList<>();
    public final Map<String, Counterparty> helpers = new HashMap<>();
    public final Map<String, Counterparty> sharers = new HashMap<>();
    String helperHost = "http://localhost:8080";

    private TestIds() {
        this.helperHost = System.getenv("HELPER_HOST");
        if (this.helperHost == null) {
            this.helperHost = "http://localhost:8080";
        }

        counterpartyBuilder("leemon", "mailto:leemon@swirldslabs.com");
        counterpartyBuilder("rohit", "mailto:rohit@swirldslabs.com");
        counterpartyBuilder("dipti", "mailto:dipti@swirldslabs.com");
        counterpartyBuilder("cate", "mailto:cate@swirldslabs.com");
        counterpartyBuilder("jo", "mailto:jo@thebuildingblocks.com");
        counterpartyBuilder("niall", "mailto:niall@thebuildingblocks.com");
        counterpartyBuilder("daniel", "mailto:daniel@thebuildingblocks.com");

        sharers.put("tigger", new Counterparty("tigger", "mailto:tigger@houseatpoohcorner.com"));
        sharers.put("eeyore", new Counterparty("eeyore", "mailto:eeyore@houseatpoohcorner.com"));
        sharers.put("piglet", new Counterparty("piglet", "mailto:piglet@houseatpoohcorner.com"));

        // create a map of encryption contexts for each sharer for each helper
        Map<Counterparty, Map<Counterparty, EncryptionContext>> sharersByHelper = new HashMap<>();
        for (Counterparty helper: helpers.values()) {
            for (Counterparty sharer: sharers.values()) {
                Map<Counterparty, EncryptionContext> sharers = sharersByHelper.computeIfAbsent(helper, s-> new HashMap<>());
                sharers.put(sharer, EncryptionContext.forHelper(helper.asDeRecIdentity(), sharer.asDeRecIdentity()));
            }
        }

        // create a map of encryption contexts for each helper for each sharer
        Map<Counterparty, Map<Counterparty, EncryptionContext>> helpersBySharer = new HashMap<>();
        for (Counterparty sharer: sharers.values()) {
            for (Counterparty helper: helpers.values()) {
                Map<Counterparty, EncryptionContext> helpers = helpersBySharer.computeIfAbsent(sharer, s-> new HashMap<>());
                helpers.put(helper, EncryptionContext.forSharer(sharersByHelper.get(helper).get(sharer).getContactInfo()));
            }
        }

        for (Counterparty helper: helpers.values()) {
            defaultIds.add(helper.asDeRecIdentity());
        }

        invalidIds.add(new DeRecIdentity("nohelper", "", helperHost + "/" + "noone", ""));
        invalidIds.add(new DeRecIdentity("notanaddress", "", "http://10.0.0.0:8080", ""));
    }

    private void counterpartyBuilder(String name, String email) {
        helpers.put(name, new Counterparty(name, email, URI.create(helperHost + "/" + name)));
    }
}
