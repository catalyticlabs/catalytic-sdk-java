package org.catalytic.sdk.entities;

/**
 * A object which represents a step on a particular instance of a pushbot
 */
public class InstanceStep {

    private String id;
    private String instanceId;
    private String pushbotId;
    private String name;
    private String teamName;
    private String position;
    private String description;
    private String status;
    private String assignedTo;
    private Field[] outputFields;

    public InstanceStep(String id, String instanceId, String pushbotId, String name, String teamName, String position, String description, String status, String assignedTo, Field[] outputFields) {
        this.id = id;
        this.instanceId = instanceId;
        this.pushbotId = pushbotId;
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.outputFields = outputFields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getPushbotId() {
        return pushbotId;
    }

    public void setPushbotId(String pushbotId) {
        this.pushbotId = pushbotId;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
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

    public Field[] getOutputFields() {
        return outputFields;
    }

    public void setOutputFields(Field[] outputFields) {
        this.outputFields = outputFields;
    }
}