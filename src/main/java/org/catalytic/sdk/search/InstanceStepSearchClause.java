package org.catalytic.sdk.search;

import org.catalytic.sdk.entities.InstanceStepStatus;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Class used to create search criteria for searching InstanceSteps
 */
public class InstanceStepSearchClause {

    private List<InstanceStepSearchClause> and;
    private List<InstanceStepSearchClause> or;
    private GuidSearchExpression id;
    private GuidSearchExpression instanceId;
    private GuidSearchExpression workflowId;
    private StringSearchExpression name;
    private StringSearchExpression description;
    private InstanceStepStatusSearchExpression status;
    private ExactStringSearchExpression assignedToEmail;
    private ExactStringSearchExpression completedByEmail;
    private DateTimeSearchExpression startDate;
    private DateTimeSearchExpression endDate;
    private StringSearchExpression actionTypeId;

    public List<InstanceStepSearchClause> getAnd() {
        return and;
    }

    public void setAnd(InstanceStepSearchClause... and) {
        List<InstanceStepSearchClause> andExpression = new ArrayList<>();
        andExpression.addAll(Arrays.asList(and));
        this.and = andExpression;
    }

    public List<InstanceStepSearchClause> getOr() {
        return or;
    }

    public void setOr(InstanceStepSearchClause... or) {
        List<InstanceStepSearchClause> orExpression = new ArrayList<>();
        orExpression.addAll(Arrays.asList(or));
        this.or = orExpression;
    }

    public GuidSearchExpression getId() {
        return id;
    }

    public void setId(UUID id) {
        GuidSearchExpression idExpression = new GuidSearchExpression();
        idExpression.setIsEqualTo(id);
        this.id = idExpression;
    }

    public GuidSearchExpression getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(UUID instanceId) {
        GuidSearchExpression instanceIdExpression = new GuidSearchExpression();
        instanceIdExpression.setIsEqualTo(instanceId);
        this.instanceId = instanceIdExpression;
    }

    public GuidSearchExpression getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(UUID workflowId) {
        GuidSearchExpression workflowIdExpression = new GuidSearchExpression();
        workflowIdExpression.setIsEqualTo(workflowId);
        this.workflowId = workflowIdExpression;
    }

    public StringSearchExpression getName() {
        return name;
    }

    public void setName(String name) {
        StringSearchExpression nameExpression = new StringSearchExpression();
        nameExpression.setIsEqualTo(name);
        this.name = nameExpression;
    }

    public void setNameContains(String name) {
        StringSearchExpression nameExpression = new StringSearchExpression();
        nameExpression.setContains(name);
        this.name = nameExpression;
    }

    public void setNameBetween(String start, String end) {
        StringSearchExpression nameExpression = new StringSearchExpression();
        StringRange nameRange = new StringRange(start, end);
        nameExpression.setBetween(nameRange);
        this.name = nameExpression;
    }

    public StringSearchExpression getDescription() {
        return description;
    }

    public void setDescription(String description) {
        StringSearchExpression descriptionExpression = new StringSearchExpression();
        descriptionExpression.setIsEqualTo(description);
        this.description = descriptionExpression;
    }

    public void setDescriptionContains(String description) {
        StringSearchExpression descriptionExpression = new StringSearchExpression();
        descriptionExpression.setContains(description);
        this.description = descriptionExpression;
    }

    public void setDescriptionBetween(String start, String end) {
        StringSearchExpression descriptionExpression = new StringSearchExpression();
        StringRange descriptionRange = new StringRange(start, end);
        descriptionExpression.setBetween(descriptionRange);
        this.description = descriptionExpression;
    }

    public InstanceStepStatusSearchExpression getStatus() {
        return status;
    }

    public void setStatus(InstanceStepStatus status) {
        InstanceStepStatusSearchExpression statusExpression = new InstanceStepStatusSearchExpression();
        statusExpression.setIsEqualTo(status);
        this.status = statusExpression;
    }

    public ExactStringSearchExpression getAssignedToEmail() {
        return assignedToEmail;
    }

    public void setAssignedToEmail(String assignedToEmail) {
        ExactStringSearchExpression assignedToEmailExpression = new ExactStringSearchExpression();
        assignedToEmailExpression.setIsEqualTo(assignedToEmail);
        this.assignedToEmail = assignedToEmailExpression;
    }

    public ExactStringSearchExpression getCompletedByEmail() {
        return completedByEmail;
    }

    public void setCompletedByEmail(String completedByEmail) {
        ExactStringSearchExpression completedByEmailExpression = new ExactStringSearchExpression();
        completedByEmailExpression.setIsEqualTo(completedByEmail);
        this.completedByEmail = completedByEmailExpression;
    }

    public DateTimeSearchExpression getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        DateTimeSearchExpression startDateExpression = new DateTimeSearchExpression();
        startDateExpression.setIsEqualTo(startDate);
        this.startDate = startDateExpression;
    }

    public void setStartDateBetween(OffsetDateTime start, OffsetDateTime end) {
        DateTimeSearchExpression startDateExpression = new DateTimeSearchExpression();
        DateTimeOffsetRange between = new DateTimeOffsetRange(start, end);
        startDateExpression.setBetween(between);
        this.startDate = startDateExpression;
    }

    public DateTimeSearchExpression getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        DateTimeSearchExpression endDateExpression = new DateTimeSearchExpression();
        endDateExpression.setIsEqualTo(endDate);
        this.endDate = endDateExpression;
    }

    public void setEndDateBetween(OffsetDateTime start, OffsetDateTime end) {
        DateTimeSearchExpression endDateExpression = new DateTimeSearchExpression();
        DateTimeOffsetRange between = new DateTimeOffsetRange(start, end);
        endDateExpression.setBetween(between);
        this.endDate = endDateExpression;
    }

    public StringSearchExpression getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(String actionTypeId) {
        StringSearchExpression actionTypeIdExpression = new StringSearchExpression();
        actionTypeIdExpression.setIsEqualTo(actionTypeId);
        this.actionTypeId = actionTypeIdExpression;
    }

    public void setActionTypeIdContains(String actionTypeId) {
        StringSearchExpression actionTypeIdExpression = new StringSearchExpression();
        actionTypeIdExpression.setContains(actionTypeId);
        this.actionTypeId = actionTypeIdExpression;
    }

    public void setActionTypeIdBetween(String start, String end) {
        StringSearchExpression actionTypeIdExpression = new StringSearchExpression();
        StringRange actionTypeIdRange = new StringRange(start, end);
        actionTypeIdExpression.setBetween(actionTypeIdRange);
        this.actionTypeId = actionTypeIdExpression;
    }

    @Override
    public String toString() {
        return "InstanceStepSearchClause{" +
                "and=" + and +
                ", or=" + or +
                ", id=" + id +
                ", instanceId=" + instanceId +
                ", workflowId=" + workflowId +
                ", name=" + name +
                ", description=" + description +
                ", status=" + status +
                ", assignedToEmail=" + assignedToEmail +
                ", completedByEmail=" + completedByEmail +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", actionTypeId=" + actionTypeId +
                '}';
    }
}
