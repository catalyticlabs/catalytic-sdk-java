package org.catalytic.sdk.entities;

import java.util.List;
import java.util.UUID;

/**
 * The Invoke Action Request object
 */
public class InvokeActionRequest {

    private UUID actionId;
    private List<ActionInput> inputs;
    private List<String> requiredWorkerTags;
    private Integer lifetimeSeconds;
    private String metadata;
    private String integrationConnectionId;

    public InvokeActionRequest(UUID actionId, List<ActionInput> inputs, List<String> requiredWorkerTags, Integer lifetimeSeconds, String metadata, String integrationConnectionId) {
        this.actionId = actionId;
        this.inputs = inputs;
        this.requiredWorkerTags = requiredWorkerTags;
        this.lifetimeSeconds = lifetimeSeconds;
        this.metadata = metadata;
        this.integrationConnectionId = integrationConnectionId;
    }

    public UUID getActionId() {
        return actionId;
    }

    public void setActionId(UUID actionId) {
        this.actionId = actionId;
    }

    public List<ActionInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<ActionInput> inputs) {
        this.inputs = inputs;
    }

    public List<String> getRequiredWorkerTags() {
        return requiredWorkerTags;
    }

    public void setRequiredWorkerTags(List<String> requiredWorkerTags) {
        this.requiredWorkerTags = requiredWorkerTags;
    }

    public Integer getLifetimeSeconds() {
        return lifetimeSeconds;
    }

    public void setLifetimeSeconds(Integer lifetimeSeconds) {
        this.lifetimeSeconds = lifetimeSeconds;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getIntegrationConnectionId() {
        return integrationConnectionId;
    }

    public void setIntegrationConnectionId(String integrationConnectionId) {
        this.integrationConnectionId = integrationConnectionId;
    }

    @Override
    public String toString() {
        return "InvokeActionRequest{" +
                "actionId=" + actionId +
                ", inputs=" + inputs +
                ", requiredWorkerTags=" + requiredWorkerTags +
                ", lifetimeSeconds=" + lifetimeSeconds +
                ", metadata='" + metadata + '\'' +
                ", integrationConnectionId='" + integrationConnectionId + '\'' +
                '}';
    }
}
