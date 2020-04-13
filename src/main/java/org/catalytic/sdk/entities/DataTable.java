package org.catalytic.sdk.entities;

/**
 * A DataTable object
 */
public class DataTable {

    private String id;
    private String referenceName;
    private String name;
    private String teamName;
    private String description;
    private DataTableColumn[] columns;
    private boolean isArchived;
    private String type;
    private String visibility;
    private String[] visibleToUsers;
    private int rowLimit;
    private int columnLimit;
    private int cellLimit;

    public DataTable(String id, String referenceName, String name, String teamName, String description, DataTableColumn[] columns, boolean isArchived, String type, String visibility, String[] visibleToUsers, int rowLimit, int columnLimit, int cellLimit) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public DataTableColumn[] getColumns() {
        return columns;
    }

    public void setColumns(DataTableColumn[] columns) {
        columns = columns;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
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

    public String[] getVisibleToUsers() {
        return visibleToUsers;
    }

    public void setVisibleToUsers(String[] visibleToUsers) {
        this.visibleToUsers = visibleToUsers;
    }

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public int getColumnLimit() {
        return columnLimit;
    }

    public void setColumnLimit(int columnLimit) {
        this.columnLimit = columnLimit;
    }

    public int getCellLimit() {
        return cellLimit;
    }

    public void setCellLimit(int cellLimit) {
        this.cellLimit = cellLimit;
    }
}