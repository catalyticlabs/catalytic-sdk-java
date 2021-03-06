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

/**
 * StringRange
 */

public class StringRange {
  public static final String SERIALIZED_NAME_LOWER_BOUND_INCLUSIVE = "lowerBoundInclusive";
  @SerializedName(SERIALIZED_NAME_LOWER_BOUND_INCLUSIVE)
  private String lowerBoundInclusive;

  public static final String SERIALIZED_NAME_UPPER_BOUND_INCLUSIVE = "upperBoundInclusive";
  @SerializedName(SERIALIZED_NAME_UPPER_BOUND_INCLUSIVE)
  private String upperBoundInclusive;


  public StringRange lowerBoundInclusive(String lowerBoundInclusive) {
    
    this.lowerBoundInclusive = lowerBoundInclusive;
    return this;
  }

   /**
   * Get lowerBoundInclusive
   * @return lowerBoundInclusive
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getLowerBoundInclusive() {
    return lowerBoundInclusive;
  }


  public void setLowerBoundInclusive(String lowerBoundInclusive) {
    this.lowerBoundInclusive = lowerBoundInclusive;
  }


  public StringRange upperBoundInclusive(String upperBoundInclusive) {
    
    this.upperBoundInclusive = upperBoundInclusive;
    return this;
  }

   /**
   * Get upperBoundInclusive
   * @return upperBoundInclusive
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getUpperBoundInclusive() {
    return upperBoundInclusive;
  }


  public void setUpperBoundInclusive(String upperBoundInclusive) {
    this.upperBoundInclusive = upperBoundInclusive;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StringRange stringRange = (StringRange) o;
    return Objects.equals(this.lowerBoundInclusive, stringRange.lowerBoundInclusive) &&
        Objects.equals(this.upperBoundInclusive, stringRange.upperBoundInclusive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lowerBoundInclusive, upperBoundInclusive);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StringRange {\n");
    sb.append("    lowerBoundInclusive: ").append(toIndentedString(lowerBoundInclusive)).append("\n");
    sb.append("    upperBoundInclusive: ").append(toIndentedString(upperBoundInclusive)).append("\n");
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

