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
import java.util.ArrayList;
import java.util.List;
import org.catalytic.sdk.generated.model.BoolSearchExpression;
import org.catalytic.sdk.generated.model.DateTimeSearchExpression;
import org.catalytic.sdk.generated.model.ExactStringSearchExpression;
import org.catalytic.sdk.generated.model.GuidSearchExpression;
import org.catalytic.sdk.generated.model.StringSearchExpression;

/**
 * DataTableSearchClause
 */

public class DataTableSearchClause {
  public static final String SERIALIZED_NAME_AND = "and";
  @SerializedName(SERIALIZED_NAME_AND)
  private List<DataTableSearchClause> and = null;

  public static final String SERIALIZED_NAME_OR = "or";
  @SerializedName(SERIALIZED_NAME_OR)
  private List<DataTableSearchClause> or = null;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private GuidSearchExpression id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private StringSearchExpression name;

  public static final String SERIALIZED_NAME_IS_ARCHIVED = "isArchived";
  @SerializedName(SERIALIZED_NAME_IS_ARCHIVED)
  private BoolSearchExpression isArchived;

  public static final String SERIALIZED_NAME_CREATED_BY_EMAIL = "createdByEmail";
  @SerializedName(SERIALIZED_NAME_CREATED_BY_EMAIL)
  private ExactStringSearchExpression createdByEmail;

  public static final String SERIALIZED_NAME_CREATED_DATE = "createdDate";
  @SerializedName(SERIALIZED_NAME_CREATED_DATE)
  private DateTimeSearchExpression createdDate;


  public DataTableSearchClause and(List<DataTableSearchClause> and) {
    
    this.and = and;
    return this;
  }

  public DataTableSearchClause addAndItem(DataTableSearchClause andItem) {
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

  public List<DataTableSearchClause> getAnd() {
    return and;
  }


  public void setAnd(List<DataTableSearchClause> and) {
    this.and = and;
  }


  public DataTableSearchClause or(List<DataTableSearchClause> or) {
    
    this.or = or;
    return this;
  }

  public DataTableSearchClause addOrItem(DataTableSearchClause orItem) {
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

  public List<DataTableSearchClause> getOr() {
    return or;
  }


  public void setOr(List<DataTableSearchClause> or) {
    this.or = or;
  }


  public DataTableSearchClause id(GuidSearchExpression id) {
    
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


  public DataTableSearchClause name(StringSearchExpression name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public StringSearchExpression getName() {
    return name;
  }


  public void setName(StringSearchExpression name) {
    this.name = name;
  }


  public DataTableSearchClause isArchived(BoolSearchExpression isArchived) {
    
    this.isArchived = isArchived;
    return this;
  }

   /**
   * Get isArchived
   * @return isArchived
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public BoolSearchExpression getIsArchived() {
    return isArchived;
  }


  public void setIsArchived(BoolSearchExpression isArchived) {
    this.isArchived = isArchived;
  }


  public DataTableSearchClause createdByEmail(ExactStringSearchExpression createdByEmail) {
    
    this.createdByEmail = createdByEmail;
    return this;
  }

   /**
   * Get createdByEmail
   * @return createdByEmail
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ExactStringSearchExpression getCreatedByEmail() {
    return createdByEmail;
  }


  public void setCreatedByEmail(ExactStringSearchExpression createdByEmail) {
    this.createdByEmail = createdByEmail;
  }


  public DataTableSearchClause createdDate(DateTimeSearchExpression createdDate) {
    
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
    DataTableSearchClause dataTableSearchClause = (DataTableSearchClause) o;
    return Objects.equals(this.and, dataTableSearchClause.and) &&
        Objects.equals(this.or, dataTableSearchClause.or) &&
        Objects.equals(this.id, dataTableSearchClause.id) &&
        Objects.equals(this.name, dataTableSearchClause.name) &&
        Objects.equals(this.isArchived, dataTableSearchClause.isArchived) &&
        Objects.equals(this.createdByEmail, dataTableSearchClause.createdByEmail) &&
        Objects.equals(this.createdDate, dataTableSearchClause.createdDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(and, or, id, name, isArchived, createdByEmail, createdDate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataTableSearchClause {\n");
    sb.append("    and: ").append(toIndentedString(and)).append("\n");
    sb.append("    or: ").append(toIndentedString(or)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    isArchived: ").append(toIndentedString(isArchived)).append("\n");
    sb.append("    createdByEmail: ").append(toIndentedString(createdByEmail)).append("\n");
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

