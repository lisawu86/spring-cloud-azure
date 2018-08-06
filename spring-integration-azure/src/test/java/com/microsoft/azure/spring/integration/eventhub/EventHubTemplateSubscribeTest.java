/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package com.microsoft.azure.spring.integration.eventhub;

import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.EventProcessorHost;
import com.microsoft.azure.eventprocessorhost.IEventProcessorFactory;
import com.microsoft.azure.spring.integration.SubscribeByGroupOperationTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventHubTemplateSubscribeTest extends SubscribeByGroupOperationTest<EventData, EventHubOperation> {

    @Mock
    private EventHubClientFactory mockClientFactory;

    @Mock
    private EventProcessorHost host;

    @Before
    public void setUp() {
        this.subscribeByGroupOperation = new EventHubTemplate(mockClientFactory);
        when(this.mockClientFactory.getProcessorHostCreator()).thenReturn(t -> this.host);
        when(this.host.registerEventProcessorFactory(isA(IEventProcessorFactory.class)))
                .thenReturn(new CompletableFuture<>());
    }

    @Override
    protected void verifySubscriberCreatorCalled(int times) {
        verify(this.mockClientFactory, times(times)).getProcessorHostCreator();
    }

}