package org.catalytic.sdk.entities;

/**
 * A dataTable column object
 */
public class DataTableColumn {

    private String name;
    private String type;
    private String referenceName;
    private FieldRestrictions restrictions;

    public DataTableColumn(String name, String type, String referenceName, FieldRestrictions restrictions) {
        this.name = name;
        this.type = type;
        this.referenceName = referenceName;
        this.restrictions = restrictions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
                ", type='" + type + '\'' +
                ", referenceName='" + referenceName + '\'' +
                ", restrictions=" + restrictions +
                '}';
    }
}