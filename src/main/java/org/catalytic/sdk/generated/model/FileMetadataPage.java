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
import org.catalytic.sdk.generated.model.FileMetadata;
import org.catalytic.sdk.generated.model.PagingOptions;

/**
 * FileMetadataPage
 */

public class FileMetadataPage {
  public static final String SERIALIZED_NAME_FILES = "files";
  @SerializedName(SERIALIZED_NAME_FILES)
  private List<FileMetadata> files = null;

  public static final String SERIALIZED_NAME_NEXT_PAGE_OPTIONS = "nextPageOptions";
  @SerializedName(SERIALIZED_NAME_NEXT_PAGE_OPTIONS)
  private PagingOptions nextPageOptions;

  public static final String SERIALIZED_NAME_NEXT_PAGE_TOKEN = "nextPageToken";
  @SerializedName(SERIALIZED_NAME_NEXT_PAGE_TOKEN)
  private String nextPageToken;

  public static final String SERIALIZED_NAME_COUNT = "count";
  @SerializedName(SERIALIZED_NAME_COUNT)
  private Integer count;


  public FileMetadataPage files(List<FileMetadata> files) {
    
    this.files = files;
    return this;
  }

  public FileMetadataPage addFilesItem(FileMetadata filesItem) {
    if (this.files == null) {
      this.files = new ArrayList<>();
    }
    this.files.add(filesItem);
    return this;
  }

   /**
   * A Collection of items with Dictionaries keyed by both ID and ReferenceName
   * @return files
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A Collection of items with Dictionaries keyed by both ID and ReferenceName")

  public List<FileMetadata> getFiles() {
    return files;
  }


  public void setFiles(List<FileMetadata> files) {
    this.files = files;
  }


  public FileMetadataPage nextPageOptions(PagingOptions nextPageOptions) {
    
    this.nextPageOptions = nextPageOptions;
    return this;
  }

   /**
   * Get nextPageOptions
   * @return nextPageOptions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public PagingOptions getNextPageOptions() {
    return nextPageOptions;
  }


  public void setNextPageOptions(PagingOptions nextPageOptions) {
    this.nextPageOptions = nextPageOptions;
  }


  public FileMetadataPage nextPageToken(String nextPageToken) {
    
    this.nextPageToken = nextPageToken;
    return this;
  }

   /**
   * Get nextPageToken
   * @return nextPageToken
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getNextPageToken() {
    return nextPageToken;
  }


  public void setNextPageToken(String nextPageToken) {
    this.nextPageToken = nextPageToken;
  }


  public FileMetadataPage count(Integer count) {
    
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getCount() {
    return count;
  }


  public void setCount(Integer count) {
    this.count = count;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileMetadataPage fileMetadataPage = (FileMetadataPage) o;
    return Objects.equals(this.files, fileMetadataPage.files) &&
        Objects.equals(this.nextPageOptions, fileMetadataPage.nextPageOptions) &&
        Objects.equals(this.nextPageToken, fileMetadataPage.nextPageToken) &&
        Objects.equals(this.count, fileMetadataPage.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(files, nextPageOptions, nextPageToken, count);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileMetadataPage {\n");
    sb.append("    files: ").append(toIndentedString(files)).append("\n");
    sb.append("    nextPageOptions: ").append(toIndentedString(nextPageOptions)).append("\n");
    sb.append("    nextPageToken: ").append(toIndentedString(nextPageToken)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
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

