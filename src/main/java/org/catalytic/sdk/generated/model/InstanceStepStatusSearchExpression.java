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
import org.catalytic.sdk.generated.model.InstanceStepStatus;

/**
 * InstanceStepStatusSearchExpression
 */

public class InstanceStepStatusSearchExpression {
  public static final String SERIALIZED_NAME_IS_EQUAL_TO = "isEqualTo";
  @SerializedName(SERIALIZED_NAME_IS_EQUAL_TO)
  private InstanceStepStatus isEqualTo;


  public InstanceStepStatusSearchExpression isEqualTo(InstanceStepStatus isEqualTo) {
    
    this.isEqualTo = isEqualTo;
    return this;
  }

   /**
   * Get isEqualTo
   * @return isEqualTo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InstanceStepStatus getIsEqualTo() {
    return isEqualTo;
  }


  public void setIsEqualTo(InstanceStepStatus isEqualTo) {
    this.isEqualTo = isEqualTo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InstanceStepStatusSearchExpression instanceStepStatusSearchExpression = (InstanceStepStatusSearchExpression) o;
    return Objects.equals(this.isEqualTo, instanceStepStatusSearchExpression.isEqualTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isEqualTo);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InstanceStepStatusSearchExpression {\n");
    sb.append("    isEqualTo: ").append(toIndentedString(isEqualTo)).append("\n");
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

