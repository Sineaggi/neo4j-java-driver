/*
 * Copyright (c) "Neo4j"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
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
package org.neo4j.driver.stress;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.neo4j.driver.internal.util.Matchers.syntaxError;

import org.neo4j.driver.AccessMode;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;

public class BlockingWrongQueryWithRetries<C extends AbstractContext> extends AbstractBlockingQuery<C> {
    public BlockingWrongQueryWithRetries(Driver driver) {
        super(driver, false);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void execute(C context) {
        try (Session session = newSession(AccessMode.READ, context)) {
            Exception e = assertThrows(
                    Exception.class,
                    () -> session.readTransaction(tx -> tx.run("RETURN").consume()));
            assertThat(e, is(syntaxError()));
        }
    }
}
