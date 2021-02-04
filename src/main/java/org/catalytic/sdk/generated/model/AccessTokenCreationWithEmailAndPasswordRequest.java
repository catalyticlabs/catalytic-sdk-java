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
 * Represents a request to create and approve new AccessToken for authentication into a Catalytic team  with passed username and password
 */
@ApiModel(description = "Represents a request to create and approve new AccessToken for authentication into a Catalytic team  with passed username and password")

public class AccessTokenCreationWithEmailAndPasswordRequest {
  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_PASSWORD = "password";
  @SerializedName(SERIALIZED_NAME_PASSWORD)
  private String password;

  public static final String SERIALIZED_NAME_DOMAIN = "domain";
  @SerializedName(SERIALIZED_NAME_DOMAIN)
  private String domain;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;


  public AccessTokenCreationWithEmailAndPasswordRequest email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * Optional email address of the Catalytic user for whom the AccessToken should be created
   * @return email
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "Optional email address of the Catalytic user for whom the AccessToken should be created")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public AccessTokenCreationWithEmailAndPasswordRequest password(String password) {
    
    this.password = password;
    return this;
  }

   /**
   * Optional password of the Catalytic user for whom the AccessToken should be created
   * @return password
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "Optional password of the Catalytic user for whom the AccessToken should be created")

  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public AccessTokenCreationWithEmailAndPasswordRequest domain(String domain) {
    
    this.domain = domain;
    return this;
  }

   /**
   * Catalytic team domain to authenticate in to (ex: \&quot;myteam.pushbot.com\&quot;)
   * @return domain
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "Catalytic team domain to authenticate in to (ex: \"myteam.pushbot.com\")")

  public String getDomain() {
    return domain;
  }


  public void setDomain(String domain) {
    this.domain = domain;
  }


  public AccessTokenCreationWithEmailAndPasswordRequest name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Optional Name to assign to AccessToken; visible in Catalytic UI
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Optional Name to assign to AccessToken; visible in Catalytic UI")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccessTokenCreationWithEmailAndPasswordRequest accessTokenCreationWithEmailAndPasswordRequest = (AccessTokenCreationWithEmailAndPasswordRequest) o;
    return Objects.equals(this.email, accessTokenCreationWithEmailAndPasswordRequest.email) &&
        Objects.equals(this.password, accessTokenCreationWithEmailAndPasswordRequest.password) &&
        Objects.equals(this.domain, accessTokenCreationWithEmailAndPasswordRequest.domain) &&
        Objects.equals(this.name, accessTokenCreationWithEmailAndPasswordRequest.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, domain, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccessTokenCreationWithEmailAndPasswordRequest {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    domain: ").append(toIndentedString(domain)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

