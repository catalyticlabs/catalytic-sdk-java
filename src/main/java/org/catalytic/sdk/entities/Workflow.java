package org.catalytic.sdk.entities;

import org.catalytic.sdk.generated.model.FieldVisibility;
import org.catalytic.sdk.generated.model.InstanceVisibilty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A Workflow object
 */
public class Workflow {

    private UUID id;
    private String name;
    private String teamName;
    private String description;
    private String category;
    private String owner;
    private String createdBy;
    private List<Field> inputFields;
    private boolean isPublished;
    private boolean isArchived;
    private FieldVisibility fieldVisibility;
    private InstanceVisibilty instanceVisibility;
    private List<String> adminUsers;
    private List<String> standardUsers;

    public Workflow() {}

    public Workflow(UUID id, String name, String teamName, String description, String category, String owner, String createdBy, List<org.catalytic.sdk.generated.model.Field> inputFields, boolean isPublished, boolean isArchived, FieldVisibility fieldVisibility, InstanceVisibilty instanceVisibility, List<String> adminUsers, List<String> standardUsers) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.createdBy = createdBy;

        List<Field> newInputFields = new ArrayList<>();
        for (org.catalytic.sdk.generated.model.Field internalField : inputFields) {
            Field field = new Field(
                    internalField.getId(),
                    internalField.getName(),
                    internalField.getReferenceName(),
                    internalField.getDescription(),
                    internalField.getPosition(),
                    internalField.getRestrictions(),
                    internalField.getFieldType().getValue(),
                    internalField.getValue(),
                    internalField.getDefaultValue()
            );
            newInputFields.add(field);
        }

        this.inputFields = newInputFields;
        this.isPublished = isPublished;
        this.isArchived = isArchived;
        this.fieldVisibility = fieldVisibility;
        this.instanceVisibility = instanceVisibility;
        this.adminUsers = adminUsers;
        this.standardUsers = standardUsers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public List<Field> getInputFields() {
        return inputFields;
    }

    public void setInputFields(List<Field> inputFields) {
        this.inputFields = inputFields;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public FieldVisibility getFieldVisibility() {
        return fieldVisibility;
    }

    public void setFieldVisibility(FieldVisibility fieldVisibility) {
        this.fieldVisibility = fieldVisibility;
    }

    public InstanceVisibilty getInstanceVisibility() {
        return instanceVisibility;
    }

    public void setInstanceVisibility(InstanceVisibilty instanceVisibility) {
        this.instanceVisibility = instanceVisibility;
    }

    public List<String> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(List<String> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public List<String> getStandardUsers() {
        return standardUsers;
    }

    public void setStandardUsers(List<String> standardUsers) {
        this.standardUsers = standardUsers;
    }
}