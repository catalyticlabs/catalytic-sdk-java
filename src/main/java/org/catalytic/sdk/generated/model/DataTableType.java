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
import io.swagger.annotations.ApiModel;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * The type of data table
 */
@JsonAdapter(DataTableType.Adapter.class)
public enum DataTableType {
  
  IMPORTED("imported"),
  
  MASTER("master"),
  
  APPLICATION("application"),
  
  INSTANCE("instance"),
  
  BATCH("batch");

  private String value;

  DataTableType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static DataTableType fromValue(String value) {
    for (DataTableType b : DataTableType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<DataTableType> {
    @Override
    public void write(final JsonWriter jsonWriter, final DataTableType enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public DataTableType read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return DataTableType.fromValue(value);
    }
  }
}

