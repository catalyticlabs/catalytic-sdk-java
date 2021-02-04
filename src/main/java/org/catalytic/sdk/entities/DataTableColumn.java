package org.catalytic.sdk.entities;

import org.catalytic.sdk.generated.model.FieldType;

/**
 * A dataTable column object
 */
public class DataTableColumn {

    private String name;
    private org.catalytic.sdk.generated.model.FieldType fieldType;
    private String referenceName;
    private FieldDisplayOptions display;

    public DataTableColumn(String name, org.catalytic.sdk.generated.model.FieldType fieldType, String referenceName, FieldDisplayOptions display) {
        this.name = name;
        this.fieldType = fieldType;
        this.referenceName = referenceName;
        this.display = display;
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

    public FieldDisplayOptions getDisplay() {
        return display;
    }

    public void setDisplay(FieldDisplayOptions display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "DataTableColumn{" +
                "name='" + name + '\'' +
                ", fieldType=" + fieldType +
                ", referenceName='" + referenceName + '\'' +
                ", display=" + display +
                '}';
    }
}