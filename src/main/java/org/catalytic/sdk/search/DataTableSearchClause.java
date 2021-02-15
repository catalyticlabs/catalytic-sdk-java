package org.catalytic.sdk.search;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DataTableSearchClause {

    private List<DataTableSearchClause> and;
    private List<DataTableSearchClause> or;
    private GuidSearchExpression id;
    private StringSearchExpression name;
    private BooleanSearchExpression isArchived;
    private ExactStringSearchExpression createdByEmail;
    private DateTimeSearchExpression createdDate;

    public List<DataTableSearchClause> getAnd() {
        return and;
    }

    public void setAnd(DataTableSearchClause... and) {
        List<DataTableSearchClause> andExpression = new ArrayList<>();
        andExpression.addAll(Arrays.asList(and));
        this.and = andExpression;
    }

    public List<DataTableSearchClause> getOr() {
        return or;
    }

    public void setOr(DataTableSearchClause... or) {
        List<DataTableSearchClause> orExpression = new ArrayList<>();
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

    public BooleanSearchExpression getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(Boolean isArchived) {
        BooleanSearchExpression isArchivedExpression = new BooleanSearchExpression();
        isArchivedExpression.setIsEqualTo(isArchived);
        this.isArchived = isArchivedExpression;
    }

    public ExactStringSearchExpression getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        ExactStringSearchExpression createdByEmailExpression = new ExactStringSearchExpression();
        createdByEmailExpression.setIsEqualTo(createdByEmail);
        this.createdByEmail = createdByEmailExpression;
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
        return "DataTableSearchClause{" +
                "and=" + and +
                ", or=" + or +
                ", id=" + id +
                ", name=" + name +
                ", isArchived=" + isArchived +
                ", createdByEmail=" + createdByEmail +
                ", createdDate=" + createdDate +
                '}';
    }
}
