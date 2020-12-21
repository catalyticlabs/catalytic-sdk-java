/*
 * Catalytic SDK API
 *  ## API for the Catalytic SDK 
 *
 * The version of the OpenAPI document: 1.0.6
 * Contact: developers@catalytic.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.catalytic.sdk.generated.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.catalytic.sdk.generated.model.ActionInput;
import org.catalytic.sdk.generated.model.ActionOutput;

/**
 * ActionInvocation
 */

public class ActionInvocation {
  public static final String SERIALIZED_NAME_INPUTS = "inputs";
  @SerializedName(SERIALIZED_NAME_INPUTS)
  private List<ActionInput> inputs = null;

  public static final String SERIALIZED_NAME_OUTPUTS = "outputs";
  @SerializedName(SERIALIZED_NAME_OUTPUTS)
  private List<ActionOutput> outputs = null;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_TEAM_NAME = "teamName";
  @SerializedName(SERIALIZED_NAME_TEAM_NAME)
  private String teamName;

  public static final String SERIALIZED_NAME_REQUIRED_WORKER_TAGS = "requiredWorkerTags";
  @SerializedName(SERIALIZED_NAME_REQUIRED_WORKER_TAGS)
  private List<String> requiredWorkerTags = null;

  public static final String SERIALIZED_NAME_ACTION_ID = "actionId";
  @SerializedName(SERIALIZED_NAME_ACTION_ID)
  private UUID actionId;

  public static final String SERIALIZED_NAME_COMPLETED_BY_WORKER_ID = "completedByWorkerId";
  @SerializedName(SERIALIZED_NAME_COMPLETED_BY_WORKER_ID)
  private UUID completedByWorkerId;

  public static final String SERIALIZED_NAME_EXPIRATION_TIME = "expirationTime";
  @SerializedName(SERIALIZED_NAME_EXPIRATION_TIME)
  private OffsetDateTime expirationTime;

  public static final String SERIALIZED_NAME_COMPLETED_TIME = "completedTime";
  @SerializedName(SERIALIZED_NAME_COMPLETED_TIME)
  private OffsetDateTime completedTime;

  public static final String SERIALIZED_NAME_IS_COMPLETED = "isCompleted";
  @SerializedName(SERIALIZED_NAME_IS_COMPLETED)
  private Boolean isCompleted;

  public static final String SERIALIZED_NAME_META_DATA = "metaData";
  @SerializedName(SERIALIZED_NAME_META_DATA)
  private String metaData;

  public static final String SERIALIZED_NAME_REFERENCE_NAME = "referenceName";
  @SerializedName(SERIALIZED_NAME_REFERENCE_NAME)
  private String referenceName;

  public static final String SERIALIZED_NAME_INTEGRATION_CONNECTION_ID = "integrationConnectionId";
  @SerializedName(SERIALIZED_NAME_INTEGRATION_CONNECTION_ID)
  private String integrationConnectionId;


  public ActionInvocation inputs(List<ActionInput> inputs) {
    
    this.inputs = inputs;
    return this;
  }

  public ActionInvocation addInputsItem(ActionInput inputsItem) {
    if (this.inputs == null) {
      this.inputs = new ArrayList<>();
    }
    this.inputs.add(inputsItem);
    return this;
  }

   /**
   * Get inputs
   * @return inputs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<ActionInput> getInputs() {
    return inputs;
  }


  public void setInputs(List<ActionInput> inputs) {
    this.inputs = inputs;
  }


  public ActionInvocation outputs(List<ActionOutput> outputs) {
    
    this.outputs = outputs;
    return this;
  }

  public ActionInvocation addOutputsItem(ActionOutput outputsItem) {
    if (this.outputs == null) {
      this.outputs = new ArrayList<>();
    }
    this.outputs.add(outputsItem);
    return this;
  }

   /**
   * Get outputs
   * @return outputs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<ActionOutput> getOutputs() {
    return outputs;
  }


  public void setOutputs(List<ActionOutput> outputs) {
    this.outputs = outputs;
  }


  public ActionInvocation id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public ActionInvocation teamName(String teamName) {
    
    this.teamName = teamName;
    return this;
  }

   /**
   * Get teamName
   * @return teamName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getTeamName() {
    return teamName;
  }


  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }


  public ActionInvocation requiredWorkerTags(List<String> requiredWorkerTags) {
    
    this.requiredWorkerTags = requiredWorkerTags;
    return this;
  }

  public ActionInvocation addRequiredWorkerTagsItem(String requiredWorkerTagsItem) {
    if (this.requiredWorkerTags == null) {
      this.requiredWorkerTags = new ArrayList<>();
    }
    this.requiredWorkerTags.add(requiredWorkerTagsItem);
    return this;
  }

   /**
   * Get requiredWorkerTags
   * @return requiredWorkerTags
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<String> getRequiredWorkerTags() {
    return requiredWorkerTags;
  }


  public void setRequiredWorkerTags(List<String> requiredWorkerTags) {
    this.requiredWorkerTags = requiredWorkerTags;
  }


  public ActionInvocation actionId(UUID actionId) {
    
    this.actionId = actionId;
    return this;
  }

   /**
   * Get actionId
   * @return actionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public UUID getActionId() {
    return actionId;
  }


  public void setActionId(UUID actionId) {
    this.actionId = actionId;
  }


  public ActionInvocation completedByWorkerId(UUID completedByWorkerId) {
    
    this.completedByWorkerId = completedByWorkerId;
    return this;
  }

   /**
   * Get completedByWorkerId
   * @return completedByWorkerId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public UUID getCompletedByWorkerId() {
    return completedByWorkerId;
  }


  public void setCompletedByWorkerId(UUID completedByWorkerId) {
    this.completedByWorkerId = completedByWorkerId;
  }


  public ActionInvocation expirationTime(OffsetDateTime expirationTime) {
    
    this.expirationTime = expirationTime;
    return this;
  }

   /**
   * Get expirationTime
   * @return expirationTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OffsetDateTime getExpirationTime() {
    return expirationTime;
  }


  public void setExpirationTime(OffsetDateTime expirationTime) {
    this.expirationTime = expirationTime;
  }


  public ActionInvocation completedTime(OffsetDateTime completedTime) {
    
    this.completedTime = completedTime;
    return this;
  }

   /**
   * Get completedTime
   * @return completedTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OffsetDateTime getCompletedTime() {
    return completedTime;
  }


  public void setCompletedTime(OffsetDateTime completedTime) {
    this.completedTime = completedTime;
  }


  public ActionInvocation isCompleted(Boolean isCompleted) {
    
    this.isCompleted = isCompleted;
    return this;
  }

   /**
   * Get isCompleted
   * @return isCompleted
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getIsCompleted() {
    return isCompleted;
  }


  public void setIsCompleted(Boolean isCompleted) {
    this.isCompleted = isCompleted;
  }


  public ActionInvocation metaData(String metaData) {
    
    this.metaData = metaData;
    return this;
  }

   /**
   * A JSON-formatted meta data. This meta data is passed along unmodified to the   action implementation when an action task is executed
   * @return metaData
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A JSON-formatted meta data. This meta data is passed along unmodified to the   action implementation when an action task is executed")

  public String getMetaData() {
    return metaData;
  }


  public void setMetaData(String metaData) {
    this.metaData = metaData;
  }


  public ActionInvocation referenceName(String referenceName) {
    
    this.referenceName = referenceName;
    return this;
  }

   /**
   * Get referenceName
   * @return referenceName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getReferenceName() {
    return referenceName;
  }


  public void setReferenceName(String referenceName) {
    this.referenceName = referenceName;
  }


  public ActionInvocation integrationConnectionId(String integrationConnectionId) {
    
    this.integrationConnectionId = integrationConnectionId;
    return this;
  }

   /**
   * The Id IntegrationConnection attached to this Invocation
   * @return integrationConnectionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The Id IntegrationConnection attached to this Invocation")

  public String getIntegrationConnectionId() {
    return integrationConnectionId;
  }


  public void setIntegrationConnectionId(String integrationConnectionId) {
    this.integrationConnectionId = integrationConnectionId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActionInvocation actionInvocation = (ActionInvocation) o;
    return Objects.equals(this.inputs, actionInvocation.inputs) &&
        Objects.equals(this.outputs, actionInvocation.outputs) &&
        Objects.equals(this.id, actionInvocation.id) &&
        Objects.equals(this.teamName, actionInvocation.teamName) &&
        Objects.equals(this.requiredWorkerTags, actionInvocation.requiredWorkerTags) &&
        Objects.equals(this.actionId, actionInvocation.actionId) &&
        Objects.equals(this.completedByWorkerId, actionInvocation.completedByWorkerId) &&
        Objects.equals(this.expirationTime, actionInvocation.expirationTime) &&
        Objects.equals(this.completedTime, actionInvocation.completedTime) &&
        Objects.equals(this.isCompleted, actionInvocation.isCompleted) &&
        Objects.equals(this.metaData, actionInvocation.metaData) &&
        Objects.equals(this.referenceName, actionInvocation.referenceName) &&
        Objects.equals(this.integrationConnectionId, actionInvocation.integrationConnectionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputs, outputs, id, teamName, requiredWorkerTags, actionId, completedByWorkerId, expirationTime, completedTime, isCompleted, metaData, referenceName, integrationConnectionId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActionInvocation {\n");
    sb.append("    inputs: ").append(toIndentedString(inputs)).append("\n");
    sb.append("    outputs: ").append(toIndentedString(outputs)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    teamName: ").append(toIndentedString(teamName)).append("\n");
    sb.append("    requiredWorkerTags: ").append(toIndentedString(requiredWorkerTags)).append("\n");
    sb.append("    actionId: ").append(toIndentedString(actionId)).append("\n");
    sb.append("    completedByWorkerId: ").append(toIndentedString(completedByWorkerId)).append("\n");
    sb.append("    expirationTime: ").append(toIndentedString(expirationTime)).append("\n");
    sb.append("    completedTime: ").append(toIndentedString(completedTime)).append("\n");
    sb.append("    isCompleted: ").append(toIndentedString(isCompleted)).append("\n");
    sb.append("    metaData: ").append(toIndentedString(metaData)).append("\n");
    sb.append("    referenceName: ").append(toIndentedString(referenceName)).append("\n");
    sb.append("    integrationConnectionId: ").append(toIndentedString(integrationConnectionId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
