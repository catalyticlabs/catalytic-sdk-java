package org.catalytic.sdk.entities;

import java.util.List;
import java.util.UUID;

/**
 * An Action object
 */
public class Action {

    private UUID id;
    private String teamName;
    private String referenceName;
    private Integer version;
    private String appId;
    private String createdByEmail;
    private String name;
    private String description;
    private List<String> tags;
    private List<String> requiredWorkerTags;
    private List<ActionInputDefinition> inputs;
    private List<ActionOutputDefinition> outputs;
    private Boolean isPublished;
    private String metadata;
    private UUID originalActionId;
    private String helpDocumentPath;
    private String iconSvg;
    private List<String> integrationIds;
    private Boolean integrationRequired;

    public Action(UUID id, String teamName, String referenceName, Integer version, String appId, String createdByEmail, String name, String description, List<String> tags, List<String> requiredWorkerTags, List<ActionInputDefinition> inputs, List<ActionOutputDefinition> outputs, Boolean isPublished, String metadata, UUID originalActionId, String helpDocumentPath, String iconSvg, List<String> integrationIds, Boolean integrationRequired) {
        this.id = id;
        this.teamName = teamName;
        this.referenceName = referenceName;
        this.version = version;
        this.appId = appId;
        this.createdByEmail = createdByEmail;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.requiredWorkerTags = requiredWorkerTags;
        this.inputs = inputs;
        this.outputs = outputs;
        this.isPublished = isPublished;
        this.metadata = metadata;
        this.originalActionId = originalActionId;
        this.helpDocumentPath = helpDocumentPath;
        this.iconSvg = iconSvg;
        this.integrationIds = integrationIds;
        this.integrationRequired = integrationRequired;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCreatedByEmail() {
        return createdByEmail;
    }

    public void setCreatedByEmail(String createdByEmail) {
        this.createdByEmail = createdByEmail;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getRequiredWorkerTags() {
        return requiredWorkerTags;
    }

    public void setRequiredWorkerTags(List<String> requiredWorkerTags) {
        this.requiredWorkerTags = requiredWorkerTags;
    }

    public List<ActionInputDefinition> getInputs() {
        return inputs;
    }

    public void setInputs(List<ActionInputDefinition> inputs) {
        this.inputs = inputs;
    }

    public List<ActionOutputDefinition> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<ActionOutputDefinition> outputs) {
        this.outputs = outputs;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public UUID getOriginalActionId() {
        return originalActionId;
    }

    public void setOriginalActionId(UUID originalActionId) {
        this.originalActionId = originalActionId;
    }

    public String getHelpDocumentPath() {
        return helpDocumentPath;
    }

    public void setHelpDocumentPath(String helpDocumentPath) {
        this.helpDocumentPath = helpDocumentPath;
    }

    public String getIconSvg() {
        return iconSvg;
    }

    public void setIconSvg(String iconSvg) {
        this.iconSvg = iconSvg;
    }

    public List<String> getIntegrationIds() {
        return integrationIds;
    }

    public void setIntegrationIds(List<String> integrationIds) {
        this.integrationIds = integrationIds;
    }

    public Boolean getIntegrationRequired() {
        return integrationRequired;
    }

    public void setIntegrationRequired(Boolean integrationRequired) {
        this.integrationRequired = integrationRequired;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", referenceName='" + referenceName + '\'' +
                ", version=" + version +
                ", appId='" + appId + '\'' +
                ", createdByEmail='" + createdByEmail + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", requiredWorkerTags=" + requiredWorkerTags +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                ", isPublished=" + isPublished +
                ", metadata='" + metadata + '\'' +
                ", originalActionId=" + originalActionId +
                ", helpDocumentPath='" + helpDocumentPath + '\'' +
                ", iconSvg='" + iconSvg + '\'' +
                ", integrationIds=" + integrationIds +
                ", integrationRequired=" + integrationRequired +
                '}';
    }
}
