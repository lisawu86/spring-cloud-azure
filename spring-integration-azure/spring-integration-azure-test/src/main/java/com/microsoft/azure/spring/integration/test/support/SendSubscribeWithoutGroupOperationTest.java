/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package com.microsoft.azure.spring.integration.test.support;

import com.microsoft.azure.spring.integration.core.api.CheckpointMode;
import com.microsoft.azure.spring.integration.core.api.SendOperation;
import com.microsoft.azure.spring.integration.core.api.SubscribeOperation;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

public abstract class SendSubscribeWithoutGroupOperationTest<T extends SendOperation & SubscribeOperation>
        extends SendSubscribeOperationTest<T> {

    @Override
    protected void subscribe(String destination, Consumer<Message<?>> consumer, Class<?> payloadType) {
        sendSubscribeOperation.subscribe(destination, consumer, payloadType);
    }

    @Override
    protected void setCheckpointMode(CheckpointMode checkpointMode) {
        sendSubscribeOperation.setCheckpointMode(checkpointMode);
    }
}
