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
import java.util.UUID;

/**
 * ReassignStepRequest
 */

public class ReassignStepRequest {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_ASSIGN_TO = "assignTo";
  @SerializedName(SERIALIZED_NAME_ASSIGN_TO)
  private String assignTo;


  public ReassignStepRequest id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * ID of the step to reassign
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "ID of the step to reassign")

  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public ReassignStepRequest assignTo(String assignTo) {
    
    this.assignTo = assignTo;
    return this;
  }

   /**
   * The email address of the user to reassign the task to
   * @return assignTo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The email address of the user to reassign the task to")

  public String getAssignTo() {
    return assignTo;
  }


  public void setAssignTo(String assignTo) {
    this.assignTo = assignTo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReassignStepRequest reassignStepRequest = (ReassignStepRequest) o;
    return Objects.equals(this.id, reassignStepRequest.id) &&
        Objects.equals(this.assignTo, reassignStepRequest.assignTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, assignTo);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReassignStepRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    assignTo: ").append(toIndentedString(assignTo)).append("\n");
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

