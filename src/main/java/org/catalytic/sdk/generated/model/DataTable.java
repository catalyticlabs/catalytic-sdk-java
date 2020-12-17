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
import java.util.UUID;
import org.catalytic.sdk.generated.model.DataTableColumn;
import org.catalytic.sdk.generated.model.DataTableType;
import org.catalytic.sdk.generated.model.TableVisibility;

/**
 * DataTable
 */

public class DataTable {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_DATA_TABLE_ID = "dataTableId";
  @SerializedName(SERIALIZED_NAME_DATA_TABLE_ID)
  private UUID dataTableId;

  public static final String SERIALIZED_NAME_REFERENCE_NAME = "referenceName";
  @SerializedName(SERIALIZED_NAME_REFERENCE_NAME)
  private String referenceName;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_TEAM_NAME = "teamName";
  @SerializedName(SERIALIZED_NAME_TEAM_NAME)
  private String teamName;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_COLUMNS = "columns";
  @SerializedName(SERIALIZED_NAME_COLUMNS)
  private List<DataTableColumn> columns = null;

  public static final String SERIALIZED_NAME_IS_ARCHIVED = "isArchived";
  @SerializedName(SERIALIZED_NAME_IS_ARCHIVED)
  private Boolean isArchived;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private DataTableType type;

  public static final String SERIALIZED_NAME_VISIBILITY = "visibility";
  @SerializedName(SERIALIZED_NAME_VISIBILITY)
  private TableVisibility visibility;

  public static final String SERIALIZED_NAME_VISIBLE_TO_USERS = "visibleToUsers";
  @SerializedName(SERIALIZED_NAME_VISIBLE_TO_USERS)
  private List<String> visibleToUsers = null;

  public static final String SERIALIZED_NAME_ROW_LIMIT = "rowLimit";
  @SerializedName(SERIALIZED_NAME_ROW_LIMIT)
  private Integer rowLimit;

  public static final String SERIALIZED_NAME_COLUMN_LIMIT = "columnLimit";
  @SerializedName(SERIALIZED_NAME_COLUMN_LIMIT)
  private Integer columnLimit;

  public static final String SERIALIZED_NAME_CELL_LIMIT = "cellLimit";
  @SerializedName(SERIALIZED_NAME_CELL_LIMIT)
  private Integer cellLimit;


  public DataTable id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * The unique ID of the Data Table
   * @return id
  **/
  @ApiModelProperty(required = true, value = "The unique ID of the Data Table")

  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public DataTable dataTableId(UUID dataTableId) {
    
    this.dataTableId = dataTableId;
    return this;
  }

   /**
   * Alias for &#x60;Id&#x60;
   * @return dataTableId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Alias for `Id`")

  public UUID getDataTableId() {
    return dataTableId;
  }


  public void setDataTableId(UUID dataTableId) {
    this.dataTableId = dataTableId;
  }


  public DataTable referenceName(String referenceName) {
    
    this.referenceName = referenceName;
    return this;
  }

   /**
   * Gets a unique reference name for this Data Table
   * @return referenceName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Gets a unique reference name for this Data Table")

  public String getReferenceName() {
    return referenceName;
  }


  public void setReferenceName(String referenceName) {
    this.referenceName = referenceName;
  }


  public DataTable name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * The descriptive name of the Data Table
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "The descriptive name of the Data Table")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public DataTable teamName(String teamName) {
    
    this.teamName = teamName;
    return this;
  }

   /**
   * The name of the team in which the Data Table is defined
   * @return teamName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(required = true, value = "The name of the team in which the Data Table is defined")

  public String getTeamName() {
    return teamName;
  }


  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }


  public DataTable description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * A description of what kind of data is stored in the Table
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "A description of what kind of data is stored in the Table")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public DataTable columns(List<DataTableColumn> columns) {
    
    this.columns = columns;
    return this;
  }

  public DataTable addColumnsItem(DataTableColumn columnsItem) {
    if (this.columns == null) {
      this.columns = new ArrayList<>();
    }
    this.columns.add(columnsItem);
    return this;
  }

   /**
   * The ordered names of the columns in this Data Table
   * @return columns
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The ordered names of the columns in this Data Table")

  public List<DataTableColumn> getColumns() {
    return columns;
  }


  public void setColumns(List<DataTableColumn> columns) {
    this.columns = columns;
  }


  public DataTable isArchived(Boolean isArchived) {
    
    this.isArchived = isArchived;
    return this;
  }

   /**
   * Whether or not this table is archived and excluded from default searches
   * @return isArchived
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Whether or not this table is archived and excluded from default searches")

  public Boolean getIsArchived() {
    return isArchived;
  }


  public void setIsArchived(Boolean isArchived) {
    this.isArchived = isArchived;
  }


  public DataTable type(DataTableType type) {
    
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public DataTableType getType() {
    return type;
  }


  public void setType(DataTableType type) {
    this.type = type;
  }


  public DataTable visibility(TableVisibility visibility) {
    
    this.visibility = visibility;
    return this;
  }

   /**
   * Get visibility
   * @return visibility
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public TableVisibility getVisibility() {
    return visibility;
  }


  public void setVisibility(TableVisibility visibility) {
    this.visibility = visibility;
  }


  public DataTable visibleToUsers(List<String> visibleToUsers) {
    
    this.visibleToUsers = visibleToUsers;
    return this;
  }

  public DataTable addVisibleToUsersItem(String visibleToUsersItem) {
    if (this.visibleToUsers == null) {
      this.visibleToUsers = new ArrayList<>();
    }
    this.visibleToUsers.add(visibleToUsersItem);
    return this;
  }

   /**
   * The users who can find and modify this Data Table
   * @return visibleToUsers
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The users who can find and modify this Data Table")

  public List<String> getVisibleToUsers() {
    return visibleToUsers;
  }


  public void setVisibleToUsers(List<String> visibleToUsers) {
    this.visibleToUsers = visibleToUsers;
  }


  public DataTable rowLimit(Integer rowLimit) {
    
    this.rowLimit = rowLimit;
    return this;
  }

   /**
   * The maximum number of rows in this Data Table
   * @return rowLimit
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The maximum number of rows in this Data Table")

  public Integer getRowLimit() {
    return rowLimit;
  }


  public void setRowLimit(Integer rowLimit) {
    this.rowLimit = rowLimit;
  }


  public DataTable columnLimit(Integer columnLimit) {
    
    this.columnLimit = columnLimit;
    return this;
  }

   /**
   * The maximum number of columns in this Data Table
   * @return columnLimit
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The maximum number of columns in this Data Table")

  public Integer getColumnLimit() {
    return columnLimit;
  }


  public void setColumnLimit(Integer columnLimit) {
    this.columnLimit = columnLimit;
  }


  public DataTable cellLimit(Integer cellLimit) {
    
    this.cellLimit = cellLimit;
    return this;
  }

   /**
   * The maximum number of cells in this Data Table
   * @return cellLimit
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The maximum number of cells in this Data Table")

  public Integer getCellLimit() {
    return cellLimit;
  }


  public void setCellLimit(Integer cellLimit) {
    this.cellLimit = cellLimit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataTable dataTable = (DataTable) o;
    return Objects.equals(this.id, dataTable.id) &&
        Objects.equals(this.dataTableId, dataTable.dataTableId) &&
        Objects.equals(this.referenceName, dataTable.referenceName) &&
        Objects.equals(this.name, dataTable.name) &&
        Objects.equals(this.teamName, dataTable.teamName) &&
        Objects.equals(this.description, dataTable.description) &&
        Objects.equals(this.columns, dataTable.columns) &&
        Objects.equals(this.isArchived, dataTable.isArchived) &&
        Objects.equals(this.type, dataTable.type) &&
        Objects.equals(this.visibility, dataTable.visibility) &&
        Objects.equals(this.visibleToUsers, dataTable.visibleToUsers) &&
        Objects.equals(this.rowLimit, dataTable.rowLimit) &&
        Objects.equals(this.columnLimit, dataTable.columnLimit) &&
        Objects.equals(this.cellLimit, dataTable.cellLimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dataTableId, referenceName, name, teamName, description, columns, isArchived, type, visibility, visibleToUsers, rowLimit, columnLimit, cellLimit);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataTable {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dataTableId: ").append(toIndentedString(dataTableId)).append("\n");
    sb.append("    referenceName: ").append(toIndentedString(referenceName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    teamName: ").append(toIndentedString(teamName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    columns: ").append(toIndentedString(columns)).append("\n");
    sb.append("    isArchived: ").append(toIndentedString(isArchived)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    visibility: ").append(toIndentedString(visibility)).append("\n");
    sb.append("    visibleToUsers: ").append(toIndentedString(visibleToUsers)).append("\n");
    sb.append("    rowLimit: ").append(toIndentedString(rowLimit)).append("\n");
    sb.append("    columnLimit: ").append(toIndentedString(columnLimit)).append("\n");
    sb.append("    cellLimit: ").append(toIndentedString(cellLimit)).append("\n");
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

