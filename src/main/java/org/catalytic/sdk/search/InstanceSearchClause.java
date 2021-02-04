package org.catalytic.sdk.search;

import org.catalytic.sdk.entities.InstanceStatus;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Class used to create search criteria for searching Instances
 */
public class InstanceSearchClause {

    private List<InstanceSearchClause> and;
    private List<InstanceSearchClause> or;
    private GuidSearchExpression id;
    private GuidSearchExpression workflowId;
    private GuidSearchExpression rootInstanceId;
    private ExactStringSearchExpression ownerEmail;
    private BooleanSearchExpression isTest;
    private StringSearchExpression name;
    private InstanceStatusSearchExpression status;
    private StringSearchExpression description;
    private StringSearchExpression category;
    private DateTimeSearchExpression startDate;
    private DateTimeSearchExpression endDate;

    public List<InstanceSearchClause> getAnd() {
        return and;
    }

    public void setAnd(InstanceSearchClause... and) {
        List<InstanceSearchClause> andExpression = new ArrayList<>();
        andExpression.addAll(Arrays.asList(and));
        this.and = andExpression;
    }

    public List<InstanceSearchClause> getOr() {
        return or;
    }

    public void setOr(InstanceSearchClause... or) {
        List<InstanceSearchClause> orExpression = new ArrayList<>();
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

    public GuidSearchExpression getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(UUID id) {
        GuidSearchExpression idExpression = new GuidSearchExpression();
        idExpression.setIsEqualTo(id);
        this.workflowId = idExpression;
    }

    public GuidSearchExpression getRootInstanceId() {
        return rootInstanceId;
    }

    public void setRootInstanceId(UUID id) {
        GuidSearchExpression idExpression = new GuidSearchExpression();
        idExpression.setIsEqualTo(id);
        this.rootInstanceId = idExpression;
    }

    public ExactStringSearchExpression getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        ExactStringSearchExpression ownerEmailExpression = new ExactStringSearchExpression();
        ownerEmailExpression.setIsEqualTo(ownerEmail);
        this.ownerEmail = ownerEmailExpression;
    }

    public BooleanSearchExpression getIsTest() {
        return this.isTest;
    }

    public void setIsTest(Boolean isTest) {
        BooleanSearchExpression isTestExpression = new BooleanSearchExpression();
        isTestExpression.setIsEqualTo(isTest);
        this.isTest = isTestExpression;
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

    public InstanceStatusSearchExpression getStatus() {
        return status;
    }

    public void setStatus(InstanceStatus status) {
        InstanceStatusSearchExpression statusExpression = new InstanceStatusSearchExpression();
        statusExpression.setIsEqualTo(status);
        this.status = statusExpression;
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

    public StringSearchExpression getCategory() {
        return category;
    }

    public void setCategory(String category) {
        StringSearchExpression categoryExpression = new StringSearchExpression();
        categoryExpression.setIsEqualTo(category);
        this.category = categoryExpression;
    }

    public void setCategoryContains(String category) {
        StringSearchExpression categoryExpression = new StringSearchExpression();
        categoryExpression.setContains(category);
        this.category = categoryExpression;
    }

    public void setCategoryBetween(String start, String end) {
        StringSearchExpression categoryExpression = new StringSearchExpression();
        StringRange categoryRange = new StringRange(start, end);
        categoryExpression.setBetween(categoryRange);
        this.category = categoryExpression;
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

    @Override
    public String toString() {
        return "InstanceSearchClause{" +
                "and=" + and +
                ", or=" + or +
                ", id=" + id +
                ", workflowId=" + workflowId +
                ", rootInstanceId=" + rootInstanceId +
                ", owneEmail=" + ownerEmail +
                ", isTest=" + isTest +
                ", name=" + name +
                ", status=" + status +
                ", description=" + description +
                ", category=" + category +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
