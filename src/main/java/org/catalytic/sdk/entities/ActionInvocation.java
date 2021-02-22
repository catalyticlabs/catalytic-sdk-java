package org.catalytic.sdk.entities;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * An Action Invocation object
 */
public class ActionInvocation {

    private UUID id;
    private String teamName;
    private UUID actionId;
    private String referenceName;
    private String integrationConnectionId;
    private List<ActionInput> inputs;
    private List<ActionOutput> outputs;
    private List<String> requiredWorkerTags;
    private UUID completedByWorkerId;
    private OffsetDateTime expirationTime;
    private Boolean isCompleted;
    private OffsetDateTime completedTime;
    private String metadata;

    public ActionInvocation(UUID id, String teamName, UUID actionId, String referenceName, String integrationConnectionId, List<ActionInput> inputs, List<ActionOutput> outputs, List<String> requiredWorkerTags, UUID completedByWorkerId, OffsetDateTime expirationTime, Boolean isCompleted, OffsetDateTime completedTime, String metadata) {
        this.id = id;
        this.teamName = teamName;
        this.actionId = actionId;
        this.referenceName = referenceName;
        this.integrationConnectionId = integrationConnectionId;
        this.inputs = inputs;
        this.outputs = outputs;
        this.requiredWorkerTags = requiredWorkerTags;
        this.completedByWorkerId = completedByWorkerId;
        this.expirationTime = expirationTime;
        this.isCompleted = isCompleted;
        this.completedTime = completedTime;
        this.metadata = metadata;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public UUID getActionId() {
        return actionId;
    }

    public void setActionId(UUID actionId) {
        this.actionId = actionId;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getIntegrationConnectionId() {
        return integrationConnectionId;
    }

    public void setIntegrationConnectionId(String integrationConnectionId) {
        this.integrationConnectionId = integrationConnectionId;
    }

    public List<ActionInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<ActionInput> inputs) {
        this.inputs = inputs;
    }

    public List<ActionOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<ActionOutput> outputs) {
        this.outputs = outputs;
    }

    public List<String> getRequiredWorkerTags() {
        return requiredWorkerTags;
    }

    public void setRequiredWorkerTags(List<String> requiredWorkerTags) {
        this.requiredWorkerTags = requiredWorkerTags;
    }

    public UUID getCompletedByWorkerId() {
        return completedByWorkerId;
    }

    public void setCompletedByWorkerId(UUID completedByWorkerId) {
        this.completedByWorkerId = completedByWorkerId;
    }

    public OffsetDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(OffsetDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public OffsetDateTime getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(OffsetDateTime completedTime) {
        this.completedTime = completedTime;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "ActionInvocation{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", actionId=" + actionId +
                ", referenceName='" + referenceName + '\'' +
                ", integrationConnectionId='" + integrationConnectionId + '\'' +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                ", requiredWorkerTags=" + requiredWorkerTags +
                ", completedByWorkerId=" + completedByWorkerId +
                ", expirationTime=" + expirationTime +
                ", isCompleted=" + isCompleted +
                ", completedTime=" + completedTime +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
