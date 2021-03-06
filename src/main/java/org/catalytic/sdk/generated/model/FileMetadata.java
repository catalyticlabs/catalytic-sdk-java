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
 * FileMetadata
 */

public class FileMetadata {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_TEAM_NAME = "teamName";
  @SerializedName(SERIALIZED_NAME_TEAM_NAME)
  private String teamName;

  public static final String SERIALIZED_NAME_CONTENT_TYPE = "contentType";
  @SerializedName(SERIALIZED_NAME_CONTENT_TYPE)
  private String contentType;

  public static final String SERIALIZED_NAME_SIZE_IN_BYTES = "sizeInBytes";
  @SerializedName(SERIALIZED_NAME_SIZE_IN_BYTES)
  private Integer sizeInBytes;

  public static final String SERIALIZED_NAME_DISPLAY_SIZE = "displaySize";
  @SerializedName(SERIALIZED_NAME_DISPLAY_SIZE)
  private String displaySize;

  public static final String SERIALIZED_NAME_IS_PUBLIC = "isPublic";
  @SerializedName(SERIALIZED_NAME_IS_PUBLIC)
  private Boolean isPublic;

  public static final String SERIALIZED_NAME_MD5_HASH = "md5Hash";
  @SerializedName(SERIALIZED_NAME_MD5_HASH)
  private String md5Hash;

  public static final String SERIALIZED_NAME_REFERENCE_NAME = "referenceName";
  @SerializedName(SERIALIZED_NAME_REFERENCE_NAME)
  private String referenceName;


  public FileMetadata id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * The unique ID of the File in Catalytic
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The unique ID of the File in Catalytic")

  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public FileMetadata name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * The name of the File
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of the File")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public FileMetadata teamName(String teamName) {
    
    this.teamName = teamName;
    return this;
  }

   /**
   * The name of the Catalytic team with which the File is associated
   * @return teamName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name of the Catalytic team with which the File is associated")

  public String getTeamName() {
    return teamName;
  }


  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }


  public FileMetadata contentType(String contentType) {
    
    this.contentType = contentType;
    return this;
  }

   /**
   * The content-type of the File
   * @return contentType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The content-type of the File")

  public String getContentType() {
    return contentType;
  }


  public void setContentType(String contentType) {
    this.contentType = contentType;
  }


  public FileMetadata sizeInBytes(Integer sizeInBytes) {
    
    this.sizeInBytes = sizeInBytes;
    return this;
  }

   /**
   * The size of the File, in bytes
   * @return sizeInBytes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The size of the File, in bytes")

  public Integer getSizeInBytes() {
    return sizeInBytes;
  }


  public void setSizeInBytes(Integer sizeInBytes) {
    this.sizeInBytes = sizeInBytes;
  }


  public FileMetadata displaySize(String displaySize) {
    
    this.displaySize = displaySize;
    return this;
  }

   /**
   * The human-readable size of the File
   * @return displaySize
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The human-readable size of the File")

  public String getDisplaySize() {
    return displaySize;
  }


  public void setDisplaySize(String displaySize) {
    this.displaySize = displaySize;
  }


  public FileMetadata isPublic(Boolean isPublic) {
    
    this.isPublic = isPublic;
    return this;
  }

   /**
   * Boolean indicating whether the File can be downloaded by unauthenticated users
   * @return isPublic
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Boolean indicating whether the File can be downloaded by unauthenticated users")

  public Boolean getIsPublic() {
    return isPublic;
  }


  public void setIsPublic(Boolean isPublic) {
    this.isPublic = isPublic;
  }


  public FileMetadata md5Hash(String md5Hash) {
    
    this.md5Hash = md5Hash;
    return this;
  }

   /**
   * The MD5 hash of the File
   * @return md5Hash
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The MD5 hash of the File")

  public String getMd5Hash() {
    return md5Hash;
  }


  public void setMd5Hash(String md5Hash) {
    this.md5Hash = md5Hash;
  }


  public FileMetadata referenceName(String referenceName) {
    
    this.referenceName = referenceName;
    return this;
  }

   /**
   * The stringified ID of the File, used for reference in a !:FilesPage
   * @return referenceName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The stringified ID of the File, used for reference in a !:FilesPage")

  public String getReferenceName() {
    return referenceName;
  }


  public void setReferenceName(String referenceName) {
    this.referenceName = referenceName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileMetadata fileMetadata = (FileMetadata) o;
    return Objects.equals(this.id, fileMetadata.id) &&
        Objects.equals(this.name, fileMetadata.name) &&
        Objects.equals(this.teamName, fileMetadata.teamName) &&
        Objects.equals(this.contentType, fileMetadata.contentType) &&
        Objects.equals(this.sizeInBytes, fileMetadata.sizeInBytes) &&
        Objects.equals(this.displaySize, fileMetadata.displaySize) &&
        Objects.equals(this.isPublic, fileMetadata.isPublic) &&
        Objects.equals(this.md5Hash, fileMetadata.md5Hash) &&
        Objects.equals(this.referenceName, fileMetadata.referenceName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, teamName, contentType, sizeInBytes, displaySize, isPublic, md5Hash, referenceName);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileMetadata {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    teamName: ").append(toIndentedString(teamName)).append("\n");
    sb.append("    contentType: ").append(toIndentedString(contentType)).append("\n");
    sb.append("    sizeInBytes: ").append(toIndentedString(sizeInBytes)).append("\n");
    sb.append("    displaySize: ").append(toIndentedString(displaySize)).append("\n");
    sb.append("    isPublic: ").append(toIndentedString(isPublic)).append("\n");
    sb.append("    md5Hash: ").append(toIndentedString(md5Hash)).append("\n");
    sb.append("    referenceName: ").append(toIndentedString(referenceName)).append("\n");
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

