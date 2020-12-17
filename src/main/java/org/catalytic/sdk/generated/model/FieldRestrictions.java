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
import java.util.ArrayList;
import java.util.List;

/**
 * Contains validation rules for field values
 */
@ApiModel(description = "Contains validation rules for field values")

public class FieldRestrictions {
  public static final String SERIALIZED_NAME_CHOICES = "choices";
  @SerializedName(SERIALIZED_NAME_CHOICES)
  private List<String> choices = null;

  public static final String SERIALIZED_NAME_VALUE_REQUIRED = "valueRequired";
  @SerializedName(SERIALIZED_NAME_VALUE_REQUIRED)
  private Boolean valueRequired;


  public FieldRestrictions choices(List<String> choices) {
    
    this.choices = choices;
    return this;
  }

  public FieldRestrictions addChoicesItem(String choicesItem) {
    if (this.choices == null) {
      this.choices = new ArrayList<>();
    }
    this.choices.add(choicesItem);
    return this;
  }

   /**
   * A set of valid choices for this field. If set, FieldType must be  SingleChoice or MultipleChoice
   * @return choices
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A set of valid choices for this field. If set, FieldType must be  SingleChoice or MultipleChoice")

  public List<String> getChoices() {
    return choices;
  }


  public void setChoices(List<String> choices) {
    this.choices = choices;
  }


  public FieldRestrictions valueRequired(Boolean valueRequired) {
    
    this.valueRequired = valueRequired;
    return this;
  }

   /**
   * Indicates whether null or empty values will be rejected
   * @return valueRequired
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Indicates whether null or empty values will be rejected")

  public Boolean getValueRequired() {
    return valueRequired;
  }


  public void setValueRequired(Boolean valueRequired) {
    this.valueRequired = valueRequired;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FieldRestrictions fieldRestrictions = (FieldRestrictions) o;
    return Objects.equals(this.choices, fieldRestrictions.choices) &&
        Objects.equals(this.valueRequired, fieldRestrictions.valueRequired);
  }

  @Override
  public int hashCode() {
    return Objects.hash(choices, valueRequired);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldRestrictions {\n");
    sb.append("    choices: ").append(toIndentedString(choices)).append("\n");
    sb.append("    valueRequired: ").append(toIndentedString(valueRequired)).append("\n");
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

