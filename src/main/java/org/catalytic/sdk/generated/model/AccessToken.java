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
import java.util.UUID;
import org.catalytic.sdk.generated.model.TokenType;

/**
 * An AccessToken used for authentication via the SDK
 */
@ApiModel(description = "An AccessToken used for authentication via the SDK")

public class AccessToken {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_DOMAIN = "domain";
  @SerializedName(SERIALIZED_NAME_DOMAIN)
  private String domain;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TokenType type;

  public static final String SERIALIZED_NAME_TOKEN = "token";
  @SerializedName(SERIALIZED_NAME_TOKEN)
  private String token;

  public static final String SERIALIZED_NAME_SECRET = "secret";
  @SerializedName(SERIALIZED_NAME_SECRET)
  private String secret;

  public static final String SERIALIZED_NAME_ENVIRONMENT = "environment";
  @SerializedName(SERIALIZED_NAME_ENVIRONMENT)
  private String environment;

  public static final String SERIALIZED_NAME_OWNER = "owner";
  @SerializedName(SERIALIZED_NAME_OWNER)
  private String owner;


  public AccessToken id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * The public Id of the AccessToken
   * @return id
  **/
  @ApiModelProperty(required = true, value = "The public Id of the AccessToken")

  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public AccessToken domain(String domain) {
    
    this.domain = domain;
    return this;
  }

   /**
   * The Domain of the Catalytic team with which these AccessToken are associated
   * @return domain
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "The Domain of the Catalytic team with which these AccessToken are associated")

  public String getDomain() {
    return domain;
  }


  public void setDomain(String domain) {
    this.domain = domain;
  }


  public AccessToken name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * The name associated with the AccessToken
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The name associated with the AccessToken")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public AccessToken type(TokenType type) {
    
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public TokenType getType() {
    return type;
  }


  public void setType(TokenType type) {
    this.type = type;
  }


  public AccessToken token(String token) {
    
    this.token = token;
    return this;
  }

   /**
   * The serialized AccessToken Token
   * @return token
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The serialized AccessToken Token")

  public String getToken() {
    return token;
  }


  public void setToken(String token) {
    this.token = token;
  }


  public AccessToken secret(String secret) {
    
    this.secret = secret;
    return this;
  }

   /**
   * The confidential Secret of the AccessToken
   * @return secret
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The confidential Secret of the AccessToken")

  public String getSecret() {
    return secret;
  }


  public void setSecret(String secret) {
    this.secret = secret;
  }


  public AccessToken environment(String environment) {
    
    this.environment = environment;
    return this;
  }

   /**
   * The environment of the Catalytic team associated with the AccessToken
   * @return environment
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The environment of the Catalytic team associated with the AccessToken")

  public String getEnvironment() {
    return environment;
  }


  public void setEnvironment(String environment) {
    this.environment = environment;
  }


  public AccessToken owner(String owner) {
    
    this.owner = owner;
    return this;
  }

   /**
   * The email address of the user who these AccessToken belong to
   * @return owner
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The email address of the user who these AccessToken belong to")

  public String getOwner() {
    return owner;
  }


  public void setOwner(String owner) {
    this.owner = owner;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccessToken accessToken = (AccessToken) o;
    return Objects.equals(this.id, accessToken.id) &&
        Objects.equals(this.domain, accessToken.domain) &&
        Objects.equals(this.name, accessToken.name) &&
        Objects.equals(this.type, accessToken.type) &&
        Objects.equals(this.token, accessToken.token) &&
        Objects.equals(this.secret, accessToken.secret) &&
        Objects.equals(this.environment, accessToken.environment) &&
        Objects.equals(this.owner, accessToken.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, domain, name, type, token, secret, environment, owner);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccessToken {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    domain: ").append(toIndentedString(domain)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    secret: ").append(toIndentedString(secret)).append("\n");
    sb.append("    environment: ").append(toIndentedString(environment)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
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

