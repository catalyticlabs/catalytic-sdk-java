package org.catalytic.sdk.entities;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * A object which represents a step on a particular instance of a workflow
 */
public class InstanceStep {

    private UUID id;
    private UUID instanceId;
    private UUID workflowId;
    private String name;
    private String teamName;
    private Integer position;
    private String description;
    private InstanceStepStatus status;
    private String assignedToEmail;
    private String completedByEmail;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private List<Field> outputFields;
    private String actionTypeId;
    private Boolean isAutomated;

    public InstanceStep() {}

    public InstanceStep(UUID id, UUID instanceId, UUID workflowId, String name, String teamName, Integer position, String description, InstanceStepStatus status, String assignedToEmail, String completedByEmail, OffsetDateTime startDate, OffsetDateTime endDate, List<Field> outputFields, String actionTypeId, Boolean isAutomated) {
        this.id = id;
        this.instanceId = instanceId;
        this.workflowId = workflowId;
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.description = description;
        this.status = status;
        this.assignedToEmail = assignedToEmail;
        this.completedByEmail = completedByEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.outputFields = outputFields;
        this.actionTypeId = actionTypeId;
        this.isAutomated = isAutomated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(UUID instanceId) {
        this.instanceId = instanceId;
    }

    public UUID getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(UUID workflowId) {
        this.workflowId = workflowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InstanceStepStatus getStatus() {
        return status;
    }

    public void setStatus(InstanceStepStatus status) {
        this.status = status;
    }

    public String getAssignedToEmail() {
        return assignedToEmail;
    }

    public void setAssignedToEmail(String assignedToEmail) {
        this.assignedToEmail = assignedToEmail;
    }

    public String getCompletedByEmail() {
        return completedByEmail;
    }

    public void setCompletedByEmail(String completedByEmail) {
        this.completedByEmail = completedByEmail;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Field> getOutputFields() {
        return outputFields;
    }

    public void setOutputFields(List<Field> outputFields) {
        this.outputFields = outputFields;
    }

    public String getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(String actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public Boolean getIsAutomated() {
        return isAutomated;
    }

    public void setIsAutomated(Boolean automated) {
        isAutomated = automated;
    }

    @Override
    public String toString() {
        return "InstanceStep{" +
                "id=" + id +
                ", instanceId=" + instanceId +
                ", workflowId=" + workflowId +
                ", name='" + name + '\'' +
                ", teamName='" + teamName + '\'' +
                ", position=" + position +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assignedToEmail='" + assignedToEmail + '\'' +
                ", completedByEmail='" + completedByEmail + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", outputFields=" + outputFields +
                ", actionTypeId='" + actionTypeId + '\'' +
                ", isAutomated=" + isAutomated +
                '}';
    }
}