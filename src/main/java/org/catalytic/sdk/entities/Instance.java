package org.catalytic.sdk.entities;

/**
 * An Instance object
 */
public class Instance {

    private String id;
    private String pushbotId;
    private String name;
    private String teamName;
    private String description;
    private String category;
    private String owner;
    private String createdBy;
    private Step[] steps;
    private Field[] fields;
    private String status;
    private String fieldVisibility;
    private String visibility;
    private String visibleToUsers;

    public Instance(String id, String pushbotId, String name, String teamName, String description, String category, String owner, String createdBy, Step[] steps, Field[] fields, String status, String fieldVisibility, String visibility, String visibleToUsers) {
        this.id = id;
        this.pushbotId = pushbotId;
        this.name = name;
        this.teamName = teamName;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.createdBy = createdBy;
        this.steps = steps;
        this.fields = fields;
        this.status = status;
        this.fieldVisibility = fieldVisibility;
        this.visibility = visibility;
        this.visibleToUsers = visibleToUsers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Step[] getSteps() {
        return steps;
    }

    public void setSteps(Step[] steps) {
        this.steps = steps;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFieldVisibility() {
        return fieldVisibility;
    }

    public void setFieldVisibility(String fieldVisibility) {
        this.fieldVisibility = fieldVisibility;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getVisibleToUsers() {
        return visibleToUsers;
    }

    public void setVisibleToUsers(String visibleToUsers) {
        this.visibleToUsers = visibleToUsers;
    }
}