package org.catalytic.sdk.search;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Class used to create search criteria for searching Workflows
 */
public class WorkflowSearchClause {

    private List<WorkflowSearchClause> and;
    private List<WorkflowSearchClause> or;
    private GuidSearchExpression id;
    private StringSearchExpression name;
    private StringSearchExpression description;
    private StringSearchExpression owner;
    private StringSearchExpression category;
    private DateTimeSearchExpression createdDate;

    public List<WorkflowSearchClause> getAnd() {
        return and;
    }

    public void setAnd(WorkflowSearchClause... and) {
        List<WorkflowSearchClause> andExpression = new ArrayList<>();
        andExpression.addAll(Arrays.asList(and));
        this.and = andExpression;
    }

    public List<WorkflowSearchClause> getOr() {
        return or;
    }

    public void setOr(WorkflowSearchClause... or) {
        List<WorkflowSearchClause> orExpression = new ArrayList<>();
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

    public StringSearchExpression getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        StringSearchExpression ownerExpression = new StringSearchExpression();
        ownerExpression.setIsEqualTo(owner);
        this.owner = ownerExpression;
    }

    public void setOwnerContains(String owner) {
        StringSearchExpression ownerExpression = new StringSearchExpression();
        ownerExpression.setContains(owner);
        this.owner = ownerExpression;
    }

    public void setOwnerBetween(String start, String end) {
        StringSearchExpression ownerExpression = new StringSearchExpression();
        StringRange ownerRange = new StringRange(start, end);
        ownerExpression.setBetween(ownerRange);
        this.owner = ownerExpression;
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
        return "WorkflowSearchClause{" +
                "and=" + and +
                ", or=" + or +
                ", id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", owner=" + owner +
                ", category=" + category +
                ", createdDate=" + createdDate +
                '}';
    }
}
