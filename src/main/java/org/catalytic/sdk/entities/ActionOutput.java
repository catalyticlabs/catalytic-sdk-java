package org.catalytic.sdk.entities;

/**
 * An Action Output object
 */
public class ActionOutput {

    private String name;
    private String description;
    private String serializedValue;
    private ActionParameterType type;
    private Boolean advanced;
    private String metadata;

    public ActionOutput(String name, String description, String serializedValue, ActionParameterType type, Boolean advanced, String metadata) {
        this.name = name;
        this.description = description;
        this.serializedValue = serializedValue;
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
        return "ActionOutput{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", serializedValue='" + serializedValue + '\'' +
                ", type=" + type +
                ", advanced=" + advanced +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
