package org.catalytic.sdk.entities;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * An Instance object
 */
public class Instance {

    private UUID id;
    private UUID workflowId;
    private UUID rootInstanceId;
    private String name;
    private String teamName;
    private String description;
    private String category;
    private Boolean isTest;
    private String ownerEmail;
    private String createdByEmail;
    private List<Field> fields;
    private InstanceStatus status;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private FieldVisibility fieldVisibility;
    private InstanceVisibility visibility;
    private List<String> visibleToUserEmails;

    public Instance() {}

    public Instance(UUID id, UUID workflowId, UUID rootInstanceId, String name, String teamName, String description, String category, Boolean isTest, String ownerEmail, String createdByEmail, List<Field> fields, InstanceStatus status, OffsetDateTime startDate, OffsetDateTime endDate, FieldVisibility fieldVisibility, InstanceVisibility visibility, List<String> visibleToUserEmails) {
        this.id = id;
        this.workflowId = workflowId;
        this.rootInstanceId = rootInstanceId;
        this.name = name;
        this.teamName = teamName;
        this.description = description;
        this.category = category;
        this.isTest = isTest;
        this.ownerEmail = ownerEmail;
        this.createdByEmail = createdByEmail;
        this.fields = fields;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fieldVisibility = fieldVisibility;
        this.visibility = visibility;
        this.visibleToUserEmails = visibleToUserEmails;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(UUID workflowId) {
        this.workflowId = workflowId;
    }

    public UUID getRootInstanceId() {
        return rootInstanceId;
    }

    public void setRootInstanceId(UUID rootInstanceId) {
        this.rootInstanceId = rootInstanceId;
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

    public Boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(Boolean test) {
        isTest = test;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public InstanceStatus getStatus() {
        return status;
    }

    public void setStatus(InstanceStatus status) {
        this.status = status;
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

    public FieldVisibility getFieldVisibility() {
        return fieldVisibility;
    }

    public void setFieldVisibility(FieldVisibility fieldVisibility) {
        this.fieldVisibility = fieldVisibility;
    }

    public InstanceVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(InstanceVisibility visibility) {
        this.visibility = visibility;
    }

    public List<String> getVisibleToUserEmails() {
        return visibleToUserEmails;
    }

    public void setVisibleToUserEmails(List<String> visibleToUsers) {
        this.visibleToUserEmails = visibleToUserEmails;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "id=" + id +
                ", workflowId=" + workflowId +
                ", rootInstanceId=" + rootInstanceId +
                ", name='" + name + '\'' +
                ", teamName='" + teamName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", isTest=" + isTest +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", createdByEmail='" + createdByEmail + '\'' +
                ", fields=" + fields +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fieldVisibility=" + fieldVisibility +
                ", visibility=" + visibility +
                ", visibleToUserEmails=" + visibleToUserEmails +
                '}';
    }
}