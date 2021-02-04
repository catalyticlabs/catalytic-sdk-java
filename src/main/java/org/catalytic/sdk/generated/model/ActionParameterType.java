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
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Gets or Sets ActionParameterType
 */
@JsonAdapter(ActionParameterType.Adapter.class)
public enum ActionParameterType {
  
  UNDEFINED("undefined"),
  
  TEXT("text"),
  
  INTEGER("integer"),
  
  DECIMAL("decimal"),
  
  DATE("date"),
  
  DATETIME("dateTime"),
  
  BOOLEAN("boolean"),
  
  SINGLECHOICE("singleChoice"),
  
  MULTIPLECHOICE("multipleChoice"),
  
  JSON("json"),
  
  FILE("file"),
  
  FILES("files"),
  
  TABLE("table"),
  
  WORKFLOW("workflow"),
  
  INSTANCE("instance"),
  
  USER("user"),
  
  ARRAY("array"),
  
  FIELDS("fields"),
  
  METADATA("metadata");

  private String value;

  ActionParameterType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static ActionParameterType fromValue(String value) {
    for (ActionParameterType b : ActionParameterType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<ActionParameterType> {
    @Override
    public void write(final JsonWriter jsonWriter, final ActionParameterType enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public ActionParameterType read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return ActionParameterType.fromValue(value);
    }
  }
}

