package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of dataTables
 */
public class DataTablesPage extends Page {

    private List<DataTable> dataTables;

    public DataTablesPage(List<DataTable> dataTables, int count, String nextPageToken) {
        this.dataTables = dataTables;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<DataTable> getDataTables() {
        return dataTables;
    }

    public void setDataTables(List<DataTable> dataTables) {
        this.dataTables = dataTables;
    }
}
