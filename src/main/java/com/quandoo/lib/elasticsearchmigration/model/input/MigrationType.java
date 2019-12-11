/**
 * Copyright (C) 2019 Quandoo GmbH (account.oss@quandoo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.quandoo.lib.elasticsearchmigration.model.input;

/**
 * @author Emir Dizdarevic
 * @since 1.0.0
 */
public enum MigrationType {
    CREATE_INDEX,
    DELETE_INDEX,
    CREATE_OR_UPDATE_INDEX_TEMPLATE,
    DELETE_INDEX_TEMPLATE,
    UPDATE_MAPPING,
    INDEX_DOCUMENT,
    DELETE_DOCUMENT,
    UPDATE_DOCUMENT,
    ALIASES,
    CREATE_INGEST_PIPELINE,
    DELETE_INGEST_PIPELINE,
    REINDEX,
    UPDATE_INDEX_SETTINGS
}
