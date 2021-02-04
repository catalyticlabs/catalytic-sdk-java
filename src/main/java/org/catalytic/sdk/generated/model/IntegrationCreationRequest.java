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
import org.catalytic.sdk.generated.model.IntegrationConfiguration;
import org.catalytic.sdk.generated.model.IntegrationType;

/**
 * IntegrationCreationRequest
 */

public class IntegrationCreationRequest {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private IntegrationType type;

  public static final String SERIALIZED_NAME_CONFIG = "config";
  @SerializedName(SERIALIZED_NAME_CONFIG)
  private IntegrationConfiguration config;


  public IntegrationCreationRequest name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * The display Name to apply to the  Integration
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "The display Name to apply to the  Integration")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public IntegrationCreationRequest type(IntegrationType type) {
    
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")

  public IntegrationType getType() {
    return type;
  }


  public void setType(IntegrationType type) {
    this.type = type;
  }


  public IntegrationCreationRequest config(IntegrationConfiguration config) {
    
    this.config = config;
    return this;
  }

   /**
   * Get config
   * @return config
  **/
  @ApiModelProperty(required = true, value = "")

  public IntegrationConfiguration getConfig() {
    return config;
  }


  public void setConfig(IntegrationConfiguration config) {
    this.config = config;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegrationCreationRequest integrationCreationRequest = (IntegrationCreationRequest) o;
    return Objects.equals(this.name, integrationCreationRequest.name) &&
        Objects.equals(this.type, integrationCreationRequest.type) &&
        Objects.equals(this.config, integrationCreationRequest.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, config);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntegrationCreationRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    config: ").append(toIndentedString(config)).append("\n");
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

