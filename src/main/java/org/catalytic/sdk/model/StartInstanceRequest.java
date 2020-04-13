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


package org.catalytic.sdk.model;

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
import org.catalytic.sdk.model.FieldUpdateRequest;

/**
 * StartInstanceRequest
 */

public class StartInstanceRequest {
  public static final String SERIALIZED_NAME_PUSHBOT_ID = "pushbotId";
  @SerializedName(SERIALIZED_NAME_PUSHBOT_ID)
  private UUID pushbotId;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_INPUT_FIELDS = "inputFields";
  @SerializedName(SERIALIZED_NAME_INPUT_FIELDS)
  private List<FieldUpdateRequest> inputFields = null;


  public StartInstanceRequest pushbotId(UUID pushbotId) {
    
    this.pushbotId = pushbotId;
    return this;
  }

   /**
   * Get pushbotId
   * @return pushbotId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public UUID getPushbotId() {
    return pushbotId;
  }


  public void setPushbotId(UUID pushbotId) {
    this.pushbotId = pushbotId;
  }


  public StartInstanceRequest name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public StartInstanceRequest description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public StartInstanceRequest inputFields(List<FieldUpdateRequest> inputFields) {
    
    this.inputFields = inputFields;
    return this;
  }

  public StartInstanceRequest addInputFieldsItem(FieldUpdateRequest inputFieldsItem) {
    if (this.inputFields == null) {
      this.inputFields = new ArrayList<>();
    }
    this.inputFields.add(inputFieldsItem);
    return this;
  }

   /**
   * Get inputFields
   * @return inputFields
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<FieldUpdateRequest> getInputFields() {
    return inputFields;
  }


  public void setInputFields(List<FieldUpdateRequest> inputFields) {
    this.inputFields = inputFields;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartInstanceRequest startInstanceRequest = (StartInstanceRequest) o;
    return Objects.equals(this.pushbotId, startInstanceRequest.pushbotId) &&
        Objects.equals(this.name, startInstanceRequest.name) &&
        Objects.equals(this.description, startInstanceRequest.description) &&
        Objects.equals(this.inputFields, startInstanceRequest.inputFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pushbotId, name, description, inputFields);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartInstanceRequest {\n");
    sb.append("    pushbotId: ").append(toIndentedString(pushbotId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    inputFields: ").append(toIndentedString(inputFields)).append("\n");
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

