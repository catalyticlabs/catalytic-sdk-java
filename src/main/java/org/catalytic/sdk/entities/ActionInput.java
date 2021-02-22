package org.catalytic.sdk.entities;

/**
 * The Action Input object
 */
public class ActionInput {

    private String name;
    private String description;
    private String serializedValue;
    private Boolean isRequired;
    private String defaultValueSerialized;
    private FieldDisplayOptions display;
    private ActionParameterType type;
    private Boolean advanced;
    private String metadata;

    public ActionInput(String name, String description, String serializedValue, Boolean isRequired, String defaultValueSerialized, FieldDisplayOptions display, ActionParameterType type, Boolean advanced, String metadata) {
        this.name = name;
        this.description = description;
        this.serializedValue = serializedValue;
        this.isRequired = isRequired;
        this.defaultValueSerialized = defaultValueSerialized;
        this.display = display;
        this.type = type;
        this.advanced = advanced;
        this.metadata = metadata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerializedValue() {
        return serializedValue;
    }

    public void setSerializedValue(String serializedValue) {
        this.serializedValue = serializedValue;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean required) {
        isRequired = required;
    }

    public String getDefaultValueSerialized() {
        return defaultValueSerialized;
    }

    public void setDefaultValueSerialized(String defaultValueSerialized) {
        this.defaultValueSerialized = defaultValueSerialized;
    }

    public FieldDisplayOptions getDisplay() {
        return display;
    }

    public void setDisplay(FieldDisplayOptions display) {
        this.display = display;
    }

    public ActionParameterType getType() {
        return type;
    }

    public void setType(ActionParameterType type) {
        this.type = type;
    }

    public Boolean getAdvanced() {
        return advanced;
    }

    public void setAdvanced(Boolean advanced) {
        this.advanced = advanced;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "ActionInput{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", serializedValue='" + serializedValue + '\'' +
                ", isRequired=" + isRequired +
                ", defaultValueSerialized='" + defaultValueSerialized + '\'' +
                ", display=" + display +
                ", type=" + type +
                ", advanced=" + advanced +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
