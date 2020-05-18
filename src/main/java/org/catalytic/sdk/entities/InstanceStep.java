package org.catalytic.sdk.entities;

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
    private String status;
    private String assignedTo;
    private List<Field> outputFields;

    public InstanceStep() {}

    public InstanceStep(UUID id, UUID instanceId, UUID workflowId, String name, String teamName, Integer position, String description, String status, String assignedTo, List<Field> outputFields) {
        this.id = id;
        this.instanceId = instanceId;
        this.workflowId = workflowId;
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.outputFields = outputFields;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<Field> getOutputFields() {
        return outputFields;
    }

    public void setOutputFields(List<Field> outputFields) {
        this.outputFields = outputFields;
    }
}