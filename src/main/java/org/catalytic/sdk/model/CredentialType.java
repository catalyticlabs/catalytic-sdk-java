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
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Gets or Sets CredentialType
 */
@JsonAdapter(CredentialType.Adapter.class)
public enum CredentialType {
  
  USER("user"),
  
  ACTIONWORKER("actionWorker");

  private String value;

  CredentialType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static CredentialType fromValue(String value) {
    for (CredentialType b : CredentialType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<CredentialType> {
    @Override
    public void write(final JsonWriter jsonWriter, final CredentialType enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public CredentialType read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return CredentialType.fromValue(value);
    }
  }
}

