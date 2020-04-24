package org.catalytic.sdk.clients;

import org.catalytic.sdk.Client;
import org.catalytic.sdk.entities.DataTable;
import org.catalytic.sdk.entities.DataTablesPage;
import org.catalytic.sdk.search.Where;
import org.junit.Test;

import java.io.File;

import static com.google.common.truth.Truth.assertThat;

public class DataTablesTests {
    @Test
    public void itShouldGetADataTable() throws Exception {
        Client catalytic = new Client();
        DataTable dataTable = catalytic.dataTables().get("7e77254c-d2d6-4271-965a-98390aefa50a");
        assertThat(dataTable.getId().toString()).isEqualTo("7e77254c-d2d6-4271-965a-98390aefa50a");
    }

    @Test
    public void itShouldFindAllDataTables() throws Exception {
        Client catalytic = new Client();
        DataTablesPage results = catalytic.dataTables().find();
        assertThat(results.getDataTables()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindDataTablesByText() throws Exception {
        Client catalytic = new Client();
        DataTablesPage results = catalytic.dataTables().find(
                new Where().text().is("Testing PHP SDK")
        );
        assertThat(results.getDataTables()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCount()).isEqualTo(1);
    }

    @Test
    public void itShouldDownloadDataTableToTempDir() throws Exception {
        Client catalytic = new Client();
        File dataTable = catalytic.dataTables().download("7e77254c-d2d6-4271-965a-98390aefa50a");
        assertThat(dataTable).isNotNull();
    }

    @Test
    public void itShouldDownloadDataTableAsXlsxToTempDir() throws Exception {
        Client catalytic = new Client();
        File dataTable = catalytic.dataTables().download("7e77254c-d2d6-4271-965a-98390aefa50a", "excel");
        assertThat(dataTable).isNotNull();
    }

    @Test
    public void itShouldDownloadDataTableAsCsvToSpecificDirWithoutTrailingSlash() throws Exception {
        Client catalytic = new Client();
        File dataTable = catalytic.dataTables().download("7e77254c-d2d6-4271-965a-98390aefa50a", null, "/users/tomcaflisch/Downloads");
        assertThat(dataTable).isNotNull();
        assertThat(dataTable.getAbsolutePath()).isEqualTo("/users/tomcaflisch/Downloads/" + dataTable.getName());
    }

    @Test
    public void itShouldDownloadDataTableAsCsvToSpecificDirWithTrailingSlash() throws Exception {
        Client catalytic = new Client();
        File dataTable = catalytic.dataTables().download("7e77254c-d2d6-4271-965a-98390aefa50a", null, "/users/tomcaflisch/Downloads/");
        assertThat(dataTable).isNotNull();
        assertThat(dataTable.getAbsolutePath()).isEqualTo("/users/tomcaflisch/Downloads/" + dataTable.getName());
    }

    @Test
    public void itShouldUploadADataTable() throws Exception {
        Client catalytic = new Client();
        File file = new File("/Users/tomcaflisch/Downloads/mycsv.csv");
        DataTable dataTable = catalytic.dataTables().upload(file, "Toms test table");
        assertThat(dataTable).isNotNull();
        assertThat(dataTable.getName()).isEqualTo("Toms test table");
    }

    @Test
    public void itShouldReplaceADataTable() throws Exception {
        Client catalytic = new Client();
        File file = new File("/Users/tomcaflisch/Downloads/mycsv2.csv");
        DataTable dataTable = catalytic.dataTables().replace("7f0594eb-9e02-46f1-b897-a1fc0a9786ca", file);
        assertThat(dataTable).isNotNull();
    }
}
