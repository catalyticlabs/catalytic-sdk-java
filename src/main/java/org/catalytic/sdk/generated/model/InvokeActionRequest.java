/*
 * Catalytic SDK API
 *  ## API for the Catalytic SDK 
 *
 * The version of the OpenAPI document: 2.0.0
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.catalytic.sdk.generated.model.ActionInput;

/**
 * InvokeActionRequest
 */

public class InvokeActionRequest {
  public static final String SERIALIZED_NAME_ACTION_ID = "actionId";
  @SerializedName(SERIALIZED_NAME_ACTION_ID)
  private UUID actionId;

  public static final String SERIALIZED_NAME_INPUTS = "inputs";
  @SerializedName(SERIALIZED_NAME_INPUTS)
  private List<ActionInput> inputs = null;

  public static final String SERIALIZED_NAME_REQUIRED_WORKER_TAGS = "requiredWorkerTags";
  @SerializedName(SERIALIZED_NAME_REQUIRED_WORKER_TAGS)
  private List<String> requiredWorkerTags = null;

  public static final String SERIALIZED_NAME_LIFETIME_SECONDS = "lifetimeSeconds";
  @SerializedName(SERIALIZED_NAME_LIFETIME_SECONDS)
  private Integer lifetimeSeconds;

  public static final String SERIALIZED_NAME_METADATA = "metadata";
  @SerializedName(SERIALIZED_NAME_METADATA)
  private String metadata;

  public static final String SERIALIZED_NAME_INTEGRATION_CONNECTION_ID = "integrationConnectionId";
  @SerializedName(SERIALIZED_NAME_INTEGRATION_CONNECTION_ID)
  private String integrationConnectionId;


  public InvokeActionRequest actionId(UUID actionId) {
    
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


  public InvokeActionRequest inputs(List<ActionInput> inputs) {
    
    this.inputs = inputs;
    return this;
  }

  public InvokeActionRequest addInputsItem(ActionInput inputsItem) {
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


  public InvokeActionRequest requiredWorkerTags(List<String> requiredWorkerTags) {
    
    this.requiredWorkerTags = requiredWorkerTags;
    return this;
  }

  public InvokeActionRequest addRequiredWorkerTagsItem(String requiredWorkerTagsItem) {
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


  public InvokeActionRequest lifetimeSeconds(Integer lifetimeSeconds) {
    
    this.lifetimeSeconds = lifetimeSeconds;
    return this;
  }

   /**
   * Get lifetimeSeconds
   * @return lifetimeSeconds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getLifetimeSeconds() {
    return lifetimeSeconds;
  }


  public void setLifetimeSeconds(Integer lifetimeSeconds) {
    this.lifetimeSeconds = lifetimeSeconds;
  }


  public InvokeActionRequest metadata(String metadata) {
    
    this.metadata = metadata;
    return this;
  }

   /**
   * A JSON-formatted meta data. This meta data is passed along unmodified to the   action implementation when an action task is executed
   * @return metadata
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A JSON-formatted meta data. This meta data is passed along unmodified to the   action implementation when an action task is executed")

  public String getMetadata() {
    return metadata;
  }


  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }


  public InvokeActionRequest integrationConnectionId(String integrationConnectionId) {
    
    this.integrationConnectionId = integrationConnectionId;
    return this;
  }

   /**
   * Get integrationConnectionId
   * @return integrationConnectionId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

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
    InvokeActionRequest invokeActionRequest = (InvokeActionRequest) o;
    return Objects.equals(this.actionId, invokeActionRequest.actionId) &&
        Objects.equals(this.inputs, invokeActionRequest.inputs) &&
        Objects.equals(this.requiredWorkerTags, invokeActionRequest.requiredWorkerTags) &&
        Objects.equals(this.lifetimeSeconds, invokeActionRequest.lifetimeSeconds) &&
        Objects.equals(this.metadata, invokeActionRequest.metadata) &&
        Objects.equals(this.integrationConnectionId, invokeActionRequest.integrationConnectionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actionId, inputs, requiredWorkerTags, lifetimeSeconds, metadata, integrationConnectionId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InvokeActionRequest {\n");
    sb.append("    actionId: ").append(toIndentedString(actionId)).append("\n");
    sb.append("    inputs: ").append(toIndentedString(inputs)).append("\n");
    sb.append("    requiredWorkerTags: ").append(toIndentedString(requiredWorkerTags)).append("\n");
    sb.append("    lifetimeSeconds: ").append(toIndentedString(lifetimeSeconds)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

