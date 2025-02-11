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
package org.neo4j.driver.internal.messaging.response;

import static java.lang.String.format;

import org.neo4j.driver.internal.messaging.Message;

/**
 * FAILURE response message
 * <p>
 * Sent by the server to signal a failed operation.
 * Terminates response sequence.
 */
public class FailureMessage implements Message {
    public static final byte SIGNATURE = 0x7F;

    private final String code;
    private final String message;

    public FailureMessage(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public byte signature() {
        return SIGNATURE;
    }

    @Override
    public String toString() {
        return format("FAILURE %s \"%s\"", code, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FailureMessage that = (FailureMessage) o;

        return !(code != null ? !code.equals(that.code) : that.code != null)
                && !(message != null ? !message.equals(that.message) : that.message != null);
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
