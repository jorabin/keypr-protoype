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

// map of the shares of the secretIds of the sharers

import com.google.protobuf.ByteString;
import org.derecalliance.derec.api.DeRecSecret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelperServerShare {
    DeRecSecret.Id secretId;
    int version;
    byte[] payload;

    public HelperServerShare(DeRecSecret.Id secretId, int version, byte[] payload) {
        this.secretId = secretId;
        this.version = version;
        this.payload = payload;
    }

    public interface Storage {
        void putShare(ByteString id, HelperServerShare share);

        List<HelperServerShare> getShares (ByteString id);
    }

    public static class SimpleStorage implements Storage {
        final Map<ByteString, List<HelperServerShare>> shares = new HashMap<>();

        @Override
        public void putShare(ByteString id, HelperServerShare share) {
            List<HelperServerShare> list = shares.computeIfAbsent(id, k -> new ArrayList<>());
            list.add(share);
        }

        @Override
        public List<HelperServerShare> getShares(ByteString id) {
            return shares.get(id);
        }
    }
}
