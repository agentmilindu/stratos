/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.stratos.messaging.event.instance.notifier;

import java.io.Serializable;

/**
 * This event is fired by SM when unsubscribe from  cartridge to notify all the instances
 * for the termination. So that instance which receives this event will perform
 * the clean up task before the actual termination.
 */
public class InstanceCleanupClusterEvent extends InstanceNotifierEvent implements Serializable {
    private String clusterId;
    private String clusterInstanceId;

    public InstanceCleanupClusterEvent(String clusterId, String instanceId) {
        this.clusterId = clusterId;
        this.clusterInstanceId = instanceId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public String getClusterInstanceId() {
        return clusterInstanceId;
    }
}
