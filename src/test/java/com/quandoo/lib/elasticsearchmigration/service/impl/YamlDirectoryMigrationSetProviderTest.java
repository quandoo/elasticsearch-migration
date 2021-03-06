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
package com.quandoo.lib.elasticsearchmigration.service.impl;

import com.quandoo.lib.elasticsearchmigration.model.migration.*;
import com.google.common.collect.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.*;
import java.util.*;
import java.util.stream.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Emir Dizdarevic
 * @since 1.0.0
 */
public class YamlDirectoryMigrationSetProviderTest {

    private YamlDirectoryMigrationSetProvider yamlDirectoryMigrationSetProvider;

    @BeforeEach
    public void setUp() throws Exception {
        yamlDirectoryMigrationSetProvider = new YamlDirectoryMigrationSetProvider();
    }

    @Test
    public void getMigrationSet() throws URISyntaxException {
        final MigrationSet migrationSet = yamlDirectoryMigrationSetProvider.getMigrationSet("com.quandoo.lib.elasticsearchmigration.service.impl");

        assertThat(migrationSet.getMigrations(), hasSize(3));
        assertThat(migrationSet.getMigrations().stream().map(e -> e.getMigrationMeta().getVersion()).collect(Collectors.toList()), contains(
                "1.0.0",
                "1.2.0",
                "1.10.0"
        ));
        assertThat(migrationSet.getMigrations().stream().map(e -> e.getMigrationMeta().getName()).collect(Collectors.toList()), contains(
                "migration_one",
                "migration_two",
                "migration_three"
        ));
        assertThat(migrationSet.getMigrations().stream().map(e -> e.getMigrationMeta().getSha256Checksum()).collect(Collectors.toList()), contains(
                "de6e1367a5bad35d63931ea9fc9ef2e4f53b0a6e2d3e4b5ecbea5e918d3e3917",
                "c02446548fa38297c926ddaf755774fcb595b1cb765f4c21f96b2131d28831a0",
                "b87fd41e28149029486b0a5c78d92bdc749469e0531dd6be954196ae664841e4"
        ));
        assertThat(migrationSet.getMigrations().stream().flatMap(e -> e.getMigration().stream()).collect(Collectors.toList()), contains(
                new CreateIndexMigration("test_index_1", "{}"),
                new CreateIndexMigration("test_index_2", "{}"),
                new CreateOrUpdateIndexTemplateMigration("test_template", "{}"),
                new CreateIngestPipelineMigration("test_pipeline", "{}"),
                new UpdateIndexSettingsMigration("test_index_1", "{}"),
                new UpdateMappingMigration(ImmutableSet.of("test_index_1", "test_index_2"), "{}"),
                new AliasesMigration("{}"),
                new ReindexMigration("{}"),
                new IndexDocumentMigration("test_index_1", Optional.of("1"), Optional.empty(), "{}"),
                new UpdateDocumentMigration("test_index_1", "1", "{}"),
                new DeleteDocumentMigration("test_index_1", "1"),
                new DeleteIndexTemplateMigration("test_template"),
                new DeleteIndexMigration("test_index_1"),
                new DeleteIndexMigration("test_index_2"),
                new DeleteIngestPipelineMigration("test_pipeline")
        ));

    }
}
