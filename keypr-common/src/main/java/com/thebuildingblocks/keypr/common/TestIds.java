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

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thebuildingblocks.keypr.common.Cryptography.keyPairGenerator;
import static com.thebuildingblocks.keypr.common.Cryptography.pemFrom;

public enum TestIds {
    INSTANCE();

    public record Person(String name, String email, KeyPair keyPair) {
        public Person(String name, String email) {
            this(name, email, keyPairGenerator.generateKeyPair());
        }
    }
    public final List<DeRecIdentity> defaultIds = new ArrayList<>();
    public final List<DeRecIdentity> invalidIds = new ArrayList<>();
    public final Map<String, Person> helpers = new HashMap<>();
    public final Map<String, Person> sharers = new HashMap<>();
    String helperHost = "http://localhost:8080";

    private TestIds() {
        this.helperHost = System.getenv("HELPER_HOST");
        if (this.helperHost == null) {
            this.helperHost = "http://localhost:8080";
        }

        helpers.put("leemon", new Person("leemon", "mailto:leemon@swirldslabs.com"));
        helpers.put("rohit", new Person("rohit", "mailto:rohit@swirldslabs.com"));
        helpers.put("dipti", new Person("dipti", "mailto:dipti@swirldslabs.com"));
        helpers.put("cate", new Person("cate", "mailto:cate@swirldslabs.com"));
        helpers.put("jo", new Person("jo", "mailto:jo@thebuildingblocks.com"));
        helpers.put("niall", new Person("niall", "mailto:niall@thebuildingblocks.com"));
        helpers.put("daniel", new Person("daniel", "mailto:daniel@thebuildingblocks.com"));


        sharers.put("tigger", new Person("tigger", "mailto:tigger@houseatpoohcorner.com"));
        sharers.put("eeyore", new Person("eeyore", "mailto:eeyore@houseatpoohcorner.com"));
        sharers.put("piglet", new Person("piglet", "mailto:piglet@houseatpoohcorner.com"));

        for (Person person : helpers.values()) {
            defaultIds.add(new DeRecIdentity(person.name, person.email, helperHost + "/" + person.name,
                    pemFrom(person.keyPair.getPublic())));
        }

        invalidIds.add(new DeRecIdentity("nohelper", "", helperHost + "/" + "noone", ""));
        invalidIds.add(new DeRecIdentity("notanaddress", "", "http://10.0.0.0:8080", ""));
    }
}
