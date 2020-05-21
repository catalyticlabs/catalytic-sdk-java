package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.DataTable;
import org.catalytic.sdk.entities.DataTablesPage;
import org.catalytic.sdk.exceptions.DataTableNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.DataTablesApi;
import org.catalytic.sdk.search.Where;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataTablesTests {

    DataTablesApi dataTablesApi;

    @Before
    public void before() {
        dataTablesApi = mock(DataTablesApi.class);
    }

    @Test
    public void getDataTable_itShouldGetADataTable() throws Exception {
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setId(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
        when(dataTablesApi.getDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenReturn(internalDataTable);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        DataTable dataTable = dataTablesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
        assertThat(dataTable).isNotNull();
        assertThat(dataTable.getId()).isEqualTo(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
    }

    @Test(expected = UnauthorizedException.class)
    public void getDataTable_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(dataTablesApi.getDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = DataTableNotFoundException.class)
    public void getDataTable_itShouldThrowDataTableNotFoundExceptionIfDataTableDoesNotExist() throws Exception {
        when(dataTablesApi.getDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(404, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = InternalErrorException.class)
    public void getDataTable_itShouldThrowInternalErrorException() throws Exception {
        when(dataTablesApi.getDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = UnauthorizedException.class)
    public void findDataTables_itShouldReturnUnauthorizedException() throws Exception {
        when(dataTablesApi.findDataTables(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.find();
    }

    @Test(expected = InternalErrorException.class)
    public void findDataTables_itShouldReturnInternalErrorException() throws Exception {
        when(dataTablesApi.findDataTables(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.find();
    }

    @Test
    public void findDataTables_itShouldReturnAllDataTables() throws Exception {
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setId(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
        org.catalytic.sdk.generated.model.DataTablesPage dataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        dataTablesPage.setDataTables(Arrays.asList(internalDataTable));
        dataTablesPage.setCount(Arrays.asList(internalDataTable).size());
        dataTablesPage.setNextPageToken(null);
        when(dataTablesApi.findDataTables(null, null, null, null, null, null, null, null, null))
                .thenReturn(dataTablesPage);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        DataTablesPage results = dataTablesClient.find();
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getDataTables().get(0).getId()).isEqualTo(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
    }

    @Test
    public void findDataTables_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setId(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
        org.catalytic.sdk.generated.model.DataTablesPage dataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        dataTablesPage.setDataTables(Arrays.asList(internalDataTable));
        dataTablesPage.setCount(Arrays.asList(internalDataTable).size());
        dataTablesPage.setNextPageToken(null);
        when(dataTablesApi.findDataTables(null, null, null, null, null, null, null, "25", null))
                .thenReturn(dataTablesPage);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        DataTablesPage results = dataTablesClient.find("25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getDataTables().get(0).getId()).isEqualTo(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
    }

    @Test
    public void findDataTables_itShouldFindDataTableByText() throws Exception {
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setId(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
        internalDataTable.setName("example");
        org.catalytic.sdk.generated.model.DataTablesPage dataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        dataTablesPage.setDataTables(Arrays.asList(internalDataTable));
        dataTablesPage.setCount(Arrays.asList(internalDataTable).size());
        dataTablesPage.setNextPageToken(null);
        when(dataTablesApi.findDataTables("example", null, null, null, null, null, null, null, null))
                .thenReturn(dataTablesPage);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        Where where = new Where();
        DataTablesPage results = dataTablesClient.find(where.text().is("example"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getDataTables().get(0).getId()).isEqualTo(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
    }

    @Test
    public void findDataTables_itShouldFindDataTableByTextAndPage() throws Exception {
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setId(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
        org.catalytic.sdk.generated.model.DataTablesPage dataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        dataTablesPage.setDataTables(Arrays.asList(internalDataTable));
        dataTablesPage.setCount(Arrays.asList(internalDataTable).size());
        dataTablesPage.setNextPageToken(null);
        when(dataTablesApi.findDataTables("example", null, null, null, null, null, null, "25", null))
                .thenReturn(dataTablesPage);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        Where where = new Where();
        DataTablesPage results = dataTablesClient.find(where.text().is("example"), "25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getDataTables().get(0).getId()).isEqualTo(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
    }

    @Test(expected = UnauthorizedException.class)
    public void downloadDataTable_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = DataTableNotFoundException.class)
    public void downloadDataTable_itShouldThrowDataTableNotFoundExceptionIfDataTableDoesNotExist() throws Exception {
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenThrow(new ApiException(404, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = InternalErrorException.class)
    public void downloadDataTable_itShouldThrowInternalErrorException() throws Exception {
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test
    public void downloadDataTable_itShouldReturnTheDownloadedFile() throws Exception {
        java.io.File internalFile = new java.io.File("foobar");
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenReturn(internalFile);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        File file = dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
        assertThat(file).isNotNull();
    }

    @Test
    public void downloadDataTable_itShouldReturnTheDownloadedFileAsXlsx() throws Exception {
        java.io.File file = new java.io.File("foobar.xlsx");
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenReturn(file);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd", "xlsx");
        assertThat(file).isNotNull();
        assertThat(file.getName()).endsWith(".xlsx");
    }

//    @Test
//    public void downloadDataTable_itShouldReturnTheDownloadedFileToDir() throws Exception {
//        java.io.File file = new java.io.File("foobar");
//        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenReturn(file);
//
//        DataTables dataTablesClient = new DataTables(dataTablesApi);
//        dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, "/my/path");
//        assertThat(file).isNotNull();
//    }

    @Test(expected = UnauthorizedException.class)
    public void uploadDataTable_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.uploadDataTable(null, null, null, Arrays.asList(file))).thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.upload(file, null);
    }

    @Test(expected = InternalErrorException.class)
    public void uploadDataTable_itShouldThrowInternalErrorException() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.uploadDataTable(null, null, null, Arrays.asList(file))).thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.upload(file, null);
    }

    @Test
    public void uploadDataTable_itShouldUploadDataTable() throws Exception {
        java.io.File file = new java.io.File("foobar");
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setName("My Table");
        when(dataTablesApi.uploadDataTable(null, null, null, Arrays.asList(file))).thenReturn(internalDataTable);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        DataTable dataTable = dataTablesClient.upload(file, null);
        assertThat(dataTable.getName()).isEqualTo("My Table");
    }

    @Test(expected = UnauthorizedException.class)
    public void replaceDataTable_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.replaceDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, null, Arrays.asList(file))).thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.replace("f98b3a70-a461-46b1-b43a-3eb1cced3efd", file);
    }

    @Test(expected = DataTableNotFoundException.class)
    public void replaceDataTable_itShouldThrowDataTableNotFoundExceptionIfDataTableDoesNotExist() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.replaceDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, null, Arrays.asList(file))).thenThrow(new ApiException(404, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.replace("f98b3a70-a461-46b1-b43a-3eb1cced3efd", file);
    }

    @Test(expected = InternalErrorException.class)
    public void replaceDataTable_itShouldThrowInternalErrorException() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.replaceDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, null, Arrays.asList(file))).thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        dataTablesClient.replace("f98b3a70-a461-46b1-b43a-3eb1cced3efd", file);
    }

    @Test
    public void replaceDataTable_itShouldReplaceDataTable() throws Exception {
        java.io.File file = new java.io.File("foobar");
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setName("My New Table");
        when(dataTablesApi.replaceDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, null, Arrays.asList(file))).thenReturn(internalDataTable);

        DataTables dataTablesClient = new DataTables(dataTablesApi);
        DataTable dataTable = dataTablesClient.replace("f98b3a70-a461-46b1-b43a-3eb1cced3efd", file);
        assertThat(dataTable.getName()).isEqualTo("My New Table");
    }
}
