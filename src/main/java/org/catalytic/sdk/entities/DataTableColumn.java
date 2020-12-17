package org.catalytic.sdk.entities;

import org.catalytic.sdk.generated.model.FieldType;

/**
 * A dataTable column object
 */
public class DataTableColumn {

    private String name;
    private org.catalytic.sdk.generated.model.FieldType fieldType;
    private String referenceName;
    private FieldRestrictions restrictions;

    public DataTableColumn(String name, org.catalytic.sdk.generated.model.FieldType fieldType, String referenceName, FieldRestrictions restrictions) {
        this.name = name;
        this.fieldType = fieldType;
        this.referenceName = referenceName;
        this.restrictions = restrictions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public org.catalytic.sdk.generated.model.FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public FieldRestrictions getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(FieldRestrictions restrictions) {
        this.restrictions = restrictions;
    }

    @Override
    public String toString() {
        return "DataTableColumn{" +
                "name='" + name + '\'' +
                ", fieldType=" + fieldType +
                ", referenceName='" + referenceName + '\'' +
                ", restrictions=" + restrictions +
                '}';
    }
}