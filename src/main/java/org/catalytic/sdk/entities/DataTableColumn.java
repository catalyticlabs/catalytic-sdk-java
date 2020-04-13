package org.catalytic.sdk.entities;

/**
 * A dataTable column object
 */
public class DataTableColumn {

    private String name;
    private String type;
    private String referenceName;
    private String restrictions;

    public DataTableColumn(String name, String type, String referenceName, String restrictions) {
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

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }
}