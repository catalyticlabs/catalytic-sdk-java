package org.catalytic.sdk.entities;

import org.catalytic.sdk.generated.model.FieldVisibility;
import org.catalytic.sdk.generated.model.InstanceVisibility;

import java.time.OffsetDateTime;
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
    private String createdByEmail;
    private List<Field> inputFields;
    private Boolean isPublished;
    private Boolean isArchived;
    private FieldVisibility fieldVisibility;
    private InstanceVisibility instanceVisibility;
    private List<String> adminUserEmails;
    private List<String> standardUserEmails;
    private OffsetDateTime createdDate;

    public Workflow() {}

    public Workflow(UUID id, String name, String teamName, String description, String category, String owner, String createdByEmail, List<Field> inputFields, Boolean isPublished, Boolean isArchived, FieldVisibility fieldVisibility, InstanceVisibility instanceVisibility, List<String> adminUserEmails, List<String> standardUserEmails, OffsetDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.createdByEmail = createdByEmail;
        this.inputFields = inputFields;
        this.isPublished = isPublished;
        this.isArchived = isArchived;
        this.fieldVisibility = fieldVisibility;
        this.instanceVisibility = instanceVisibility;
        this.adminUserEmails = adminUserEmails;
        this.standardUserEmails = standardUserEmails;
        this.createdDate = createdDate;
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

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    public List<Field> getInputFields() {
        return inputFields;
    }

    public void setInputFields(List<Field> inputFields) {
        this.inputFields = inputFields;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean isPublished) {
        isPublished = isPublished;
    }

    public Boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Boolean isArchived) {
        isArchived = isArchived;
    }

    public FieldVisibility getFieldVisibility() {
        return fieldVisibility;
    }

    public void setFieldVisibility(FieldVisibility fieldVisibility) {
        this.fieldVisibility = fieldVisibility;
    }

    public InstanceVisibility getInstanceVisibility() {
        return instanceVisibility;
    }

    public void setInstanceVisibility(InstanceVisibility instanceVisibility) {
        this.instanceVisibility = instanceVisibility;
    }

    public List<String> getAdminUserEmails() {
        return adminUserEmails;
    }

    public void setAdminUserEmails(List<String> adminUserEmails) {
        this.adminUserEmails = adminUserEmails;
    }

    public List<String> getStandardUserEmails() {
        return standardUserEmails;
    }

    public void setStandardUserEmails(List<String> standardUserEmails) {
        this.standardUserEmails = standardUserEmails;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamName='" + teamName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", owner='" + owner + '\'' +
                ", createdByEmail='" + createdByEmail + '\'' +
                ", inputFields=" + inputFields +
                ", isPublished=" + isPublished +
                ", isArchived=" + isArchived +
                ", fieldVisibility=" + fieldVisibility +
                ", instanceVisibility=" + instanceVisibility +
                ", adminUserEmails=" + adminUserEmails +
                ", standardUserEmails=" + standardUserEmails +
                ", createdDate=" + createdDate +
                '}';
    }
}