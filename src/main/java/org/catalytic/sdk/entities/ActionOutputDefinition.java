package org.catalytic.sdk.entities;

/**
 * An Action Output Definition object
 */
public class ActionOutputDefinition {

    private String name;
    private String description;
    private ActionParameterType type;
    private Boolean advanced;
    private String metadata;

    public ActionOutputDefinition(String name, String description, ActionParameterType type, Boolean advanced, String metadata) {
        this.name = name;
        this.description = description;
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
        return "ActionOutputDefinition{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", advanced=" + advanced +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
