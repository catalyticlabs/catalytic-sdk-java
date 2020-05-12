package org.catalytic.sdk.entities;

import java.util.List;
import java.util.UUID;

/**
 * A DataTable object
 */
public class DataTable {

    private UUID id;
    private String referenceName;
    private String name;
    private String teamName;
    private String description;
    private List<DataTableColumn> columns;
    private Boolean isArchived;
    private String type;
    private String visibility;
    private List<String> visibleToUsers;
    private Integer rowLimit;
    private Integer columnLimit;
    private Integer cellLimit;

    public DataTable(UUID id, String referenceName, String name, String teamName, String description, List<DataTableColumn> columns, Boolean isArchived, String type, String visibility, List<String> visibleToUsers, Integer rowLimit, Integer columnLimit, Integer cellLimit) {
        this.id = id;
        this.referenceName = referenceName;
        this.name = name;
        this.teamName = teamName;
        this.description = description;
        this.columns = columns;
        this.isArchived = isArchived;
        this.type = type;
        this.visibility = visibility;
        this.visibleToUsers = visibleToUsers;
        this.rowLimit = rowLimit;
        this.columnLimit = columnLimit;
        this.cellLimit = cellLimit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DataTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataTableColumn> columns) {
        columns = columns;
    }

    public Boolean isArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<String> getVisibleToUsers() {
        return visibleToUsers;
    }

    public void setVisibleToUsers(List<String> visibleToUsers) {
        this.visibleToUsers = visibleToUsers;
    }

    public Integer getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(Integer rowLimit) {
        this.rowLimit = rowLimit;
    }

    public Integer getColumnLimit() {
        return columnLimit;
    }

    public void setColumnLimit(Integer columnLimit) {
        this.columnLimit = columnLimit;
    }

    public Integer getCellLimit() {
        return cellLimit;
    }

    public void setCellLimit(Integer cellLimit) {
        this.cellLimit = cellLimit;
    }
}