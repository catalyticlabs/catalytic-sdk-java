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
import java.util.ArrayList;
import java.util.List;
import org.catalytic.sdk.generated.model.BoolSearchExpression;
import org.catalytic.sdk.generated.model.DateTimeSearchExpression;
import org.catalytic.sdk.generated.model.GuidSearchExpression;
import org.catalytic.sdk.generated.model.StringSearchExpression;

/**
 * UserSearchClause
 */

public class UserSearchClause {
  public static final String SERIALIZED_NAME_AND = "and";
  @SerializedName(SERIALIZED_NAME_AND)
  private List<UserSearchClause> and = null;

  public static final String SERIALIZED_NAME_OR = "or";
  @SerializedName(SERIALIZED_NAME_OR)
  private List<UserSearchClause> or = null;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private GuidSearchExpression id;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private StringSearchExpression email;

  public static final String SERIALIZED_NAME_FULL_NAME = "fullName";
  @SerializedName(SERIALIZED_NAME_FULL_NAME)
  private StringSearchExpression fullName;

  public static final String SERIALIZED_NAME_IS_TEAM_ADMIN = "isTeamAdmin";
  @SerializedName(SERIALIZED_NAME_IS_TEAM_ADMIN)
  private BoolSearchExpression isTeamAdmin;

  public static final String SERIALIZED_NAME_IS_DEACTIVATED = "isDeactivated";
  @SerializedName(SERIALIZED_NAME_IS_DEACTIVATED)
  private BoolSearchExpression isDeactivated;

  public static final String SERIALIZED_NAME_IS_LOCKED_OUT = "isLockedOut";
  @SerializedName(SERIALIZED_NAME_IS_LOCKED_OUT)
  private BoolSearchExpression isLockedOut;

  public static final String SERIALIZED_NAME_CREATED_DATE = "createdDate";
  @SerializedName(SERIALIZED_NAME_CREATED_DATE)
  private DateTimeSearchExpression createdDate;


  public UserSearchClause and(List<UserSearchClause> and) {
    
    this.and = and;
    return this;
  }

  public UserSearchClause addAndItem(UserSearchClause andItem) {
    if (this.and == null) {
      this.and = new ArrayList<>();
    }
    this.and.add(andItem);
    return this;
  }

   /**
   * Get and
   * @return and
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<UserSearchClause> getAnd() {
    return and;
  }


  public void setAnd(List<UserSearchClause> and) {
    this.and = and;
  }


  public UserSearchClause or(List<UserSearchClause> or) {
    
    this.or = or;
    return this;
  }

  public UserSearchClause addOrItem(UserSearchClause orItem) {
    if (this.or == null) {
      this.or = new ArrayList<>();
    }
    this.or.add(orItem);
    return this;
  }

   /**
   * Get or
   * @return or
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<UserSearchClause> getOr() {
    return or;
  }


  public void setOr(List<UserSearchClause> or) {
    this.or = or;
  }


  public UserSearchClause id(GuidSearchExpression id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public GuidSearchExpression getId() {
    return id;
  }


  public void setId(GuidSearchExpression id) {
    this.id = id;
  }


  public UserSearchClause email(StringSearchExpression email) {
    
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StringSearchExpression getEmail() {
    return email;
  }


  public void setEmail(StringSearchExpression email) {
    this.email = email;
  }


  public UserSearchClause fullName(StringSearchExpression fullName) {
    
    this.fullName = fullName;
    return this;
  }

   /**
   * Get fullName
   * @return fullName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StringSearchExpression getFullName() {
    return fullName;
  }


  public void setFullName(StringSearchExpression fullName) {
    this.fullName = fullName;
  }


  public UserSearchClause isTeamAdmin(BoolSearchExpression isTeamAdmin) {
    
    this.isTeamAdmin = isTeamAdmin;
    return this;
  }

   /**
   * Get isTeamAdmin
   * @return isTeamAdmin
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public BoolSearchExpression getIsTeamAdmin() {
    return isTeamAdmin;
  }


  public void setIsTeamAdmin(BoolSearchExpression isTeamAdmin) {
    this.isTeamAdmin = isTeamAdmin;
  }


  public UserSearchClause isDeactivated(BoolSearchExpression isDeactivated) {
    
    this.isDeactivated = isDeactivated;
    return this;
  }

   /**
   * Get isDeactivated
   * @return isDeactivated
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public BoolSearchExpression getIsDeactivated() {
    return isDeactivated;
  }


  public void setIsDeactivated(BoolSearchExpression isDeactivated) {
    this.isDeactivated = isDeactivated;
  }


  public UserSearchClause isLockedOut(BoolSearchExpression isLockedOut) {
    
    this.isLockedOut = isLockedOut;
    return this;
  }

   /**
   * Get isLockedOut
   * @return isLockedOut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public BoolSearchExpression getIsLockedOut() {
    return isLockedOut;
  }


  public void setIsLockedOut(BoolSearchExpression isLockedOut) {
    this.isLockedOut = isLockedOut;
  }


  public UserSearchClause createdDate(DateTimeSearchExpression createdDate) {
    
    this.createdDate = createdDate;
    return this;
  }

   /**
   * Get createdDate
   * @return createdDate
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public DateTimeSearchExpression getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(DateTimeSearchExpression createdDate) {
    this.createdDate = createdDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSearchClause userSearchClause = (UserSearchClause) o;
    return Objects.equals(this.and, userSearchClause.and) &&
        Objects.equals(this.or, userSearchClause.or) &&
        Objects.equals(this.id, userSearchClause.id) &&
        Objects.equals(this.email, userSearchClause.email) &&
        Objects.equals(this.fullName, userSearchClause.fullName) &&
        Objects.equals(this.isTeamAdmin, userSearchClause.isTeamAdmin) &&
        Objects.equals(this.isDeactivated, userSearchClause.isDeactivated) &&
        Objects.equals(this.isLockedOut, userSearchClause.isLockedOut) &&
        Objects.equals(this.createdDate, userSearchClause.createdDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(and, or, id, email, fullName, isTeamAdmin, isDeactivated, isLockedOut, createdDate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserSearchClause {\n");
    sb.append("    and: ").append(toIndentedString(and)).append("\n");
    sb.append("    or: ").append(toIndentedString(or)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    isTeamAdmin: ").append(toIndentedString(isTeamAdmin)).append("\n");
    sb.append("    isDeactivated: ").append(toIndentedString(isDeactivated)).append("\n");
    sb.append("    isLockedOut: ").append(toIndentedString(isLockedOut)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
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

