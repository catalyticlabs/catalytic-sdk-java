/*
 * Catalytic SDK API
 *  ## API for the Catalytic SDK 
 *
 * The version of the OpenAPI document: v1.0.0
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
import org.catalytic.sdk.generated.model.Field;
import org.catalytic.sdk.generated.model.InstanceStepStatus;

/**
 * Represents a single Step of an Instance
 */
@ApiModel(description = "Represents a single Step of an Instance")

public class InstanceStep {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_INSTANCE_ID = "instanceId";
  @SerializedName(SERIALIZED_NAME_INSTANCE_ID)
  private UUID instanceId;

  public static final String SERIALIZED_NAME_WORKFLOW_ID = "workflowId";
  @SerializedName(SERIALIZED_NAME_WORKFLOW_ID)
  private UUID workflowId;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_TEAM_NAME = "teamName";
  @SerializedName(SERIALIZED_NAME_TEAM_NAME)
  private String teamName;

  public static final String SERIALIZED_NAME_POSITION = "position";
  @SerializedName(SERIALIZED_NAME_POSITION)
  private Integer position;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private InstanceStepStatus status;

  public static final String SERIALIZED_NAME_ASSIGNED_TO = "assignedTo";
  @SerializedName(SERIALIZED_NAME_ASSIGNED_TO)
  private String assignedTo;

  public static final String SERIALIZED_NAME_OUTPUT_FIELDS = "outputFields";
  @SerializedName(SERIALIZED_NAME_OUTPUT_FIELDS)
  private List<Field> outputFields = null;


  public InstanceStep id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * Unique ID of this Task
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique ID of this Task")

  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public InstanceStep instanceId(UUID instanceId) {
    
    this.instanceId = instanceId;
    return this;
  }

   /**
   * Unique ID of this Instance to which this Step belongs
   * @return instanceId
  **/
  @ApiModelProperty(required = true, value = "Unique ID of this Instance to which this Step belongs")

  public UUID getInstanceId() {
    return instanceId;
  }


  public void setInstanceId(UUID instanceId) {
    this.instanceId = instanceId;
  }


  public InstanceStep workflowId(UUID workflowId) {
    
    this.workflowId = workflowId;
    return this;
  }

   /**
   * Unique ID of the Workflow to which this Step belongs
   * @return workflowId
  **/
  @ApiModelProperty(required = true, value = "Unique ID of the Workflow to which this Step belongs")

  public UUID getWorkflowId() {
    return workflowId;
  }


  public void setWorkflowId(UUID workflowId) {
    this.workflowId = workflowId;
  }


  public InstanceStep name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Display name of this Task
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "Display name of this Task")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public InstanceStep teamName(String teamName) {
    
    this.teamName = teamName;
    return this;
  }

   /**
   * The name of the Catalytic team in which this Task exists
   * @return teamName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "The name of the Catalytic team in which this Task exists")

  public String getTeamName() {
    return teamName;
  }


  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }


  public InstanceStep position(Integer position) {
    
    this.position = position;
    return this;
  }

   /**
   * The position of this Task amongst the other Tasks in the Instance
   * @return position
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The position of this Task amongst the other Tasks in the Instance")

  public Integer getPosition() {
    return position;
  }


  public void setPosition(Integer position) {
    this.position = position;
  }


  public InstanceStep description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * A description or instructions of the Task
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A description or instructions of the Task")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public InstanceStep status(InstanceStepStatus status) {
    
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InstanceStepStatus getStatus() {
    return status;
  }


  public void setStatus(InstanceStepStatus status) {
    this.status = status;
  }


  public InstanceStep assignedTo(String assignedTo) {
    
    this.assignedTo = assignedTo;
    return this;
  }

   /**
   * The email of the user (if any) that this InstanceStep is assigned to
   * @return assignedTo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The email of the user (if any) that this InstanceStep is assigned to")

  public String getAssignedTo() {
    return assignedTo;
  }


  public void setAssignedTo(String assignedTo) {
    this.assignedTo = assignedTo;
  }


  public InstanceStep outputFields(List<Field> outputFields) {
    
    this.outputFields = outputFields;
    return this;
  }

  public InstanceStep addOutputFieldsItem(Field outputFieldsItem) {
    if (this.outputFields == null) {
      this.outputFields = new ArrayList<>();
    }
    this.outputFields.add(outputFieldsItem);
    return this;
  }

   /**
   * A collection of the required and optional output fields  that can be set by this InstanceStep when completing it.
   * @return outputFields
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A collection of the required and optional output fields  that can be set by this InstanceStep when completing it.")

  public List<Field> getOutputFields() {
    return outputFields;
  }


  public void setOutputFields(List<Field> outputFields) {
    this.outputFields = outputFields;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstanceStep instanceStep = (InstanceStep) o;
    return Objects.equals(this.id, instanceStep.id) &&
        Objects.equals(this.instanceId, instanceStep.instanceId) &&
        Objects.equals(this.workflowId, instanceStep.workflowId) &&
        Objects.equals(this.name, instanceStep.name) &&
        Objects.equals(this.teamName, instanceStep.teamName) &&
        Objects.equals(this.position, instanceStep.position) &&
        Objects.equals(this.description, instanceStep.description) &&
        Objects.equals(this.status, instanceStep.status) &&
        Objects.equals(this.assignedTo, instanceStep.assignedTo) &&
        Objects.equals(this.outputFields, instanceStep.outputFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, instanceId, workflowId, name, teamName, position, description, status, assignedTo, outputFields);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InstanceStep {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    instanceId: ").append(toIndentedString(instanceId)).append("\n");
    sb.append("    workflowId: ").append(toIndentedString(workflowId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    teamName: ").append(toIndentedString(teamName)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    assignedTo: ").append(toIndentedString(assignedTo)).append("\n");
    sb.append("    outputFields: ").append(toIndentedString(outputFields)).append("\n");
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

