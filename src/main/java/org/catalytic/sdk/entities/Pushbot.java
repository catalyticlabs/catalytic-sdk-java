package org.catalytic.sdk.entities;

/**
 * A Pushbot object
 */
public class Pushbot {

    private String id;
    private String name;
    private String teamName;
    private String description;
    private String category;
    private String owner;
    private String createdBy;
    private Field[] inputFields;
    private boolean isPublished;
    private boolean isArchived;
    private String fieldVisibility;
    private String instanceVisibility;
    private User[] adminUsers;
    private User[] standardUsers;

    public Pushbot() {}

    public Pushbot(String id, String name, String teamName, String description, String category, String owner, String createdBy, Field[] inputFields, boolean isPublished, boolean isArchived, String fieldVisibility, String instanceVisibility, User[] adminUsers, User[] standardUsers) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.createdBy = createdBy;
        this.inputFields = inputFields;
        this.isPublished = isPublished;
        this.isArchived = isArchived;
        this.fieldVisibility = fieldVisibility;
        this.instanceVisibility = instanceVisibility;
        this.adminUsers = adminUsers;
        this.standardUsers = standardUsers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Field[] getInputFields() {
        return inputFields;
    }

    public void setInputFields(Field[] inputFields) {
        this.inputFields = inputFields;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String getFieldVisibility() {
        return fieldVisibility;
    }

    public void setFieldVisibility(String fieldVisibility) {
        this.fieldVisibility = fieldVisibility;
    }

    public String getInstanceVisibility() {
        return instanceVisibility;
    }

    public void setInstanceVisibility(String instanceVisibility) {
        this.instanceVisibility = instanceVisibility;
    }

    public User[] getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(User[] adminUsers) {
        this.adminUsers = adminUsers;
    }

    public User[] getStandardUsers() {
        return standardUsers;
    }

    public void setStandardUsers(User[] standardUsers) {
        this.standardUsers = standardUsers;
    }
}