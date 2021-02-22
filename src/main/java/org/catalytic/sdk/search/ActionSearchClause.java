package org.catalytic.sdk.search;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Class used to create search criteria for searching Actions
 */
public class ActionSearchClause {

    private List<ActionSearchClause> and;
    private List<ActionSearchClause> or;
    private GuidSearchExpression id;
    private StringSearchExpression name;
    private StringSearchExpression description;
    private GuidSearchExpression originalActionId;
    private ExactStringSearchExpression createdByEmail;
    private DateTimeSearchExpression createdDate;

    public List<ActionSearchClause> getAnd() {
        return and;
    }

    public void setAnd(ActionSearchClause... and) {
        List<ActionSearchClause> andExpression = new ArrayList<>();
        andExpression.addAll(Arrays.asList(and));
        this.and = andExpression;
    }

    public List<ActionSearchClause> getOr() {
        return or;
    }

    public void setOr(ActionSearchClause... or) {
        List<ActionSearchClause> orExpression = new ArrayList<>();
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

    public GuidSearchExpression getOriginalActionId() {
        return originalActionId;
    }

    public void setOriginalActionId(UUID originalActionId) {
        GuidSearchExpression originalActionIdExpression = new GuidSearchExpression();
        originalActionIdExpression.setIsEqualTo(originalActionId);
        this.originalActionId = originalActionIdExpression;
    }

    public ExactStringSearchExpression getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        ExactStringSearchExpression ownerEmailExpression = new ExactStringSearchExpression();
        ownerEmailExpression.setIsEqualTo(createdByEmail);
        this.createdByEmail = ownerEmailExpression;
    }

    public DateTimeSearchExpression getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        DateTimeSearchExpression createdDateExpression = new DateTimeSearchExpression();
        createdDateExpression.setIsEqualTo(createdDate);
        this.createdDate = createdDateExpression;
    }

    public void setCreatedDateBetween(OffsetDateTime start, OffsetDateTime end) {
        DateTimeSearchExpression createdDateExpression = new DateTimeSearchExpression();
        DateTimeOffsetRange between = new DateTimeOffsetRange(start, end);
        createdDateExpression.setBetween(between);
        this.createdDate = createdDateExpression;
    }

    @Override
    public String toString() {
        return "ActionSearchClause{" +
                "and=" + and +
                ", or=" + or +
                ", id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", originalActionId=" + originalActionId +
                ", createdByEmail=" + createdByEmail +
                ", createdDate=" + createdDate +
                '}';
    }
}
