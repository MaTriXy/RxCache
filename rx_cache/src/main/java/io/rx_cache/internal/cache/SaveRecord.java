/*
 * Copyright 2016 Victor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rx_cache.internal.cache;


import javax.inject.Inject;

import io.rx_cache.Persistence;
import io.rx_cache.Record;
import io.rx_cache.internal.Memory;

public final class SaveRecord extends Action {

    @Inject public SaveRecord(Memory memory, Persistence persistence) {
        super(memory, persistence);
    }

    void save(String providerKey, String dynamicKey, String dynamicKeyGroup, Object data, long lifeTime) {
        String composedKey = composeKey(providerKey, dynamicKey, dynamicKeyGroup);

        Record record = new Record(data, lifeTime);
        memory.put(composedKey, record);
        persistence.saveRecord(composedKey, record);
    }
}
