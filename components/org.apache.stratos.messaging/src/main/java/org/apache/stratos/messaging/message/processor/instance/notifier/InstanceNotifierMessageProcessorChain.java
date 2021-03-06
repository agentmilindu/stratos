/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.stratos.messaging.message.processor.instance.notifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.stratos.messaging.listener.EventListener;
import org.apache.stratos.messaging.listener.instance.notifier.ArtifactUpdateEventListener;
import org.apache.stratos.messaging.listener.instance.notifier.InstanceCleanupClusterEventListener;
import org.apache.stratos.messaging.listener.instance.notifier.InstanceCleanupMemberEventListener;
import org.apache.stratos.messaging.message.processor.MessageProcessorChain;

/**
 * Defines default instance notifier message processor chain.
 */
public class InstanceNotifierMessageProcessorChain extends MessageProcessorChain {
    private static final Log log = LogFactory.getLog(InstanceNotifierMessageProcessorChain.class);

    private ArtifactUpdateMessageProcessor artifactUpdateMessageProcessor;
    private InstanceCleanupMemberNotifierMessageProcessor instanceCleanupMemberNotifierMessageProcessor;
    private InstanceCleanupClusterNotifierMessageProcessor instanceCleanupClusterNotifierMessageProcessor;

    public void initialize() {
        // Add instance notifier event processors
        artifactUpdateMessageProcessor = new ArtifactUpdateMessageProcessor();
        add(artifactUpdateMessageProcessor);
        instanceCleanupMemberNotifierMessageProcessor = new InstanceCleanupMemberNotifierMessageProcessor();
        add(instanceCleanupMemberNotifierMessageProcessor);
        instanceCleanupClusterNotifierMessageProcessor = new InstanceCleanupClusterNotifierMessageProcessor();
        add(instanceCleanupClusterNotifierMessageProcessor);


        if (log.isDebugEnabled()) {
            log.debug("Instance notifier message processor chain initialized");
        }
    }

    public void addEventListener(EventListener eventListener) {
        if (eventListener instanceof ArtifactUpdateEventListener) {
            artifactUpdateMessageProcessor.addEventListener(eventListener);
        } else if (eventListener instanceof InstanceCleanupMemberEventListener) {
            instanceCleanupMemberNotifierMessageProcessor.addEventListener(eventListener);
        } else if (eventListener instanceof InstanceCleanupClusterEventListener) {
            instanceCleanupClusterNotifierMessageProcessor.addEventListener(eventListener);
        } else {
            throw new RuntimeException("Unknown event listener");
        }
    }
}
