package org.catalytic.sdk.entities;

import org.catalytic.sdk.generated.model.FieldVisibility;
import org.catalytic.sdk.generated.model.InstanceVisibilty;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * An Instance object
 */
public class Instance {

    private UUID id;
    private UUID workflowId;
    private String name;
    private String teamName;
    private String description;
    private String category;
    private String owner;
    private String createdBy;
    private List<InstanceStep> steps;
    private List<Field> fields;
    private String status;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private FieldVisibility fieldVisibility;
    private InstanceVisibilty visibility;
    private List<String> visibleToUsers;

    public Instance() {}

    public Instance(UUID id, UUID workflowId, String name, String teamName, String description, String category, String owner, String createdBy, List<InstanceStep> steps, List<Field> fields, String status, OffsetDateTime startDate, OffsetDateTime endDate, FieldVisibility fieldVisibility, InstanceVisibilty visibility, List<String> visibleToUsers) {
        this.id = id;
        this.workflowId = workflowId;
        this.name = name;
        this.teamName = teamName;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.createdBy = createdBy;
        this.steps = steps;
        this.fields = fields;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fieldVisibility = fieldVisibility;
        this.visibility = visibility;
        this.visibleToUsers = visibleToUsers;
    }

    public Instance(UUID id, UUID workflowId, String name, String teamName, String description, String category, String owner, String createdBy, List<Field> fields, String status, OffsetDateTime startDate, OffsetDateTime endDate, FieldVisibility fieldVisibility, InstanceVisibilty visibility, List<String> visibleToUsers) {
        this.id = id;
        this.workflowId = workflowId;
        this.name = name;
        this.teamName = teamName;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.createdBy = createdBy;
//        this.steps = steps;
        this.fields = fields;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fieldVisibility = fieldVisibility;
        this.visibility = visibility;
        this.visibleToUsers = visibleToUsers;
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

    @Deprecated
    public List<InstanceStep> getSteps() {
        return steps;
    }

    @Deprecated
    public void setSteps(List<InstanceStep> steps) {
        this.steps = steps;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public InstanceVisibilty getVisibility() {
        return visibility;
    }

    public void setVisibility(InstanceVisibilty visibility) {
        this.visibility = visibility;
    }

    public List<String> getVisibleToUsers() {
        return visibleToUsers;
    }

    public void setVisibleToUsers(List<String> visibleToUsers) {
        this.visibleToUsers = visibleToUsers;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "id=" + id +
                ", workflowId=" + workflowId +
                ", name='" + name + '\'' +
                ", teamName='" + teamName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", owner='" + owner + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", steps=" + steps +
                ", fields=" + fields +
                ", status='" + status + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fieldVisibility=" + fieldVisibility +
                ", visibility=" + visibility +
                ", visibleToUsers=" + visibleToUsers +
                '}';
    }
}