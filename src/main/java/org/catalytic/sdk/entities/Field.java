package org.catalytic.sdk.entities;

import org.catalytic.sdk.generated.model.FieldDisplayOptions;

import java.util.UUID;

/**
 * A Field object
 */
public class Field {

    private UUID id;
    private String name;
    private String referenceName;
    private String description;
    private Integer position;
    private FieldDisplayOptions display;
    private String fieldType;
    private String value;
    private String defaultValue;

    /**
     * Create a Field with a name and a value
     *
     * @param name  The name of the field
     * @param value The value to give to the field
     */
    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Field(UUID id, String name, String referenceName, String description, Integer position, FieldDisplayOptions display, String fieldType, String value, String defaultValue) {
        this.id = id;
        this.name = name;
        this.referenceName = referenceName;
        this.description = description;
        this.position = position;
        this.display = display;
        this.fieldType = fieldType;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public FieldDisplayOptions getDisplay() {
        return display;
    }

    public void setDisplay(FieldDisplayOptions display) {
        this.display = display;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", referenceName='" + referenceName + '\'' +
                ", description='" + description + '\'' +
                ", position=" + position +
                ", display=" + display +
                ", fieldType='" + fieldType + '\'' +
                ", value='" + value + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                '}';
    }
}
