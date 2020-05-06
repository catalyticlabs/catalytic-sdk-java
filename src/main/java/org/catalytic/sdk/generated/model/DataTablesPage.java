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
import org.catalytic.sdk.generated.model.DataTable;
import org.catalytic.sdk.generated.model.PagingOptions;

/**
 * DataTablesPage
 */

public class DataTablesPage {
  public static final String SERIALIZED_NAME_DATA_TABLES = "dataTables";
  @SerializedName(SERIALIZED_NAME_DATA_TABLES)
  private List<DataTable> dataTables = null;

  public static final String SERIALIZED_NAME_NEXT_PAGE_OPTIONS = "nextPageOptions";
  @SerializedName(SERIALIZED_NAME_NEXT_PAGE_OPTIONS)
  private PagingOptions nextPageOptions;

  public static final String SERIALIZED_NAME_NEXT_PAGE_TOKEN = "nextPageToken";
  @SerializedName(SERIALIZED_NAME_NEXT_PAGE_TOKEN)
  private String nextPageToken;

  public static final String SERIALIZED_NAME_COUNT = "count";
  @SerializedName(SERIALIZED_NAME_COUNT)
  private Integer count;


  public DataTablesPage dataTables(List<DataTable> dataTables) {
    
    this.dataTables = dataTables;
    return this;
  }

  public DataTablesPage addDataTablesItem(DataTable dataTablesItem) {
    if (this.dataTables == null) {
      this.dataTables = new ArrayList<>();
    }
    this.dataTables.add(dataTablesItem);
    return this;
  }

   /**
   * Get dataTables
   * @return dataTables
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<DataTable> getDataTables() {
    return dataTables;
  }


  public void setDataTables(List<DataTable> dataTables) {
    this.dataTables = dataTables;
  }


  public DataTablesPage nextPageOptions(PagingOptions nextPageOptions) {
    
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


  public DataTablesPage nextPageToken(String nextPageToken) {
    
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


  public DataTablesPage count(Integer count) {
    
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
    DataTablesPage dataTablesPage = (DataTablesPage) o;
    return Objects.equals(this.dataTables, dataTablesPage.dataTables) &&
        Objects.equals(this.nextPageOptions, dataTablesPage.nextPageOptions) &&
        Objects.equals(this.nextPageToken, dataTablesPage.nextPageToken) &&
        Objects.equals(this.count, dataTablesPage.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataTables, nextPageOptions, nextPageToken, count);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataTablesPage {\n");
    sb.append("    dataTables: ").append(toIndentedString(dataTables)).append("\n");
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

