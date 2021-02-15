package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.DataTable;
import org.catalytic.sdk.entities.DataTablesPage;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.DataTableNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.DataTablesApi;
import org.catalytic.sdk.search.DataTableSearchClause;
import org.catalytic.sdk.search.DataTablesWhere;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Test(expected = AccessTokenNotFoundException.class)
    public void get_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        DataTables dataTablesClient = new DataTables(nullString);
        dataTablesClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getDataTable_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(dataTablesApi.getDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = DataTableNotFoundException.class)
    public void get_itShouldThrowDataTableNotFoundExceptionIfDataTableDoesNotExist() throws Exception {
        when(dataTablesApi.getDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(404, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = InternalErrorException.class)
    public void get_itShouldThrowInternalErrorException() throws Exception {
        when(dataTablesApi.getDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test
    public void get_itShouldGetADataTable() throws Exception {
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setId(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
        when(dataTablesApi.getDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd")).thenReturn(internalDataTable);

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        DataTable dataTable = dataTablesClient.get("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
        assertThat(dataTable).isNotNull();
        assertThat(dataTable.getId()).isEqualTo(UUID.fromString("f98b3a70-a461-46b1-b43a-3eb1cced3efd"));
    }

    @Test(expected = UnauthorizedException.class)
    public void search_itShouldReturnUnauthorizedException() throws Exception {
        when(dataTablesApi.searchDataTables(null, null, new org.catalytic.sdk.generated.model.DataTableSearchClause()))
                .thenThrow(new ApiException(401, null));

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTablesClient.search(null);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void search_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        DataTables DataTablesClient = new DataTables(null);
        DataTablesClient.search(null);
    }

    @Test(expected = InternalErrorException.class)
    public void search_itShouldReturnInternalErrorException() throws Exception {
        when(dataTablesApi.searchDataTables(null, null, new org.catalytic.sdk.generated.model.DataTableSearchClause()))
                .thenThrow(new ApiException(500, null));

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTablesClient.search(null);
    }

    @Test
    public void search_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setName("My dataTable");
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");
        when(dataTablesApi.searchDataTables("abc123", null, new org.catalytic.sdk.generated.model.DataTableSearchClause()))
                .thenReturn(DataTablesPage);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTablesPage results = DataTablesClient.search(null, "abc123", null);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getName()).isEqualTo("My dataTable");
    }

    @Test
    public void search_itShouldFindDataTableById() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression id = new org.catalytic.sdk.generated.model.GuidSearchExpression();
        id.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        DataTableSearchClause.setId(id);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        org.catalytic.sdk.search.DataTableSearchClause searchCriteria = DataTablesWhere.id(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTablesPage results = DataTablesClient.search(searchCriteria);
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
    }

    @Test
    public void search_itShouldFindDataTableByName() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setName("My dataTable");
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setIsEqualTo("My dataTable");
        DataTableSearchClause.setName(name);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTableSearchClause searchCriteria = DataTablesWhere.name("My dataTable");

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getName()).isEqualTo("My dataTable");
    }

    @Test
    public void search_itShouldFindDataTableByNameContains() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setName("My dataTable");
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setContains("my");
        DataTableSearchClause.setName(name);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTableSearchClause searchCriteria = DataTablesWhere.nameContains("my");
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getName()).isEqualTo("My dataTable");
    }

    @Test
    public void search_itShouldFindDataTableBetweenNames() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setName("My dataTable");
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange nameRange = new org.catalytic.sdk.generated.model.StringRange();
        nameRange.setLowerBoundInclusive("Ma");
        nameRange.setUpperBoundInclusive("Mz");
        name.setBetween(nameRange);
        DataTableSearchClause.setName(name);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTableSearchClause searchCriteria = DataTablesWhere.nameBetween("Ma", "Mz");
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getName()).isEqualTo("My dataTable");
    }

    @Test
    public void search_itShouldFindDataTableByIsArchived() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setIsArchived(true);
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.BoolSearchExpression isArchived = new org.catalytic.sdk.generated.model.BoolSearchExpression();
        isArchived.setIsEqualTo(true);
        DataTableSearchClause.setIsArchived(isArchived);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTableSearchClause searchCriteria = DataTablesWhere.isArchived(true);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getIsArchived()).isEqualTo(true);
    }

    @Test
    public void search_itShouldFindDataTableByCreatedByEmail() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setCreatedByEmail("bob@aol.com");
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.ExactStringSearchExpression createdByEmail = new org.catalytic.sdk.generated.model.ExactStringSearchExpression();
        createdByEmail.setIsEqualTo("bob@aol.com");
        DataTableSearchClause.setCreatedByEmail(createdByEmail);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTableSearchClause searchCriteria = DataTablesWhere.createdByEmail("bob@aol.com");

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getCreatedByEmail()).isEqualTo("bob@aol.com");
    }

    @Test
    public void search_itShouldFindDataTableByCreatedDateAsString() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setCreatedDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        createdDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        DataTableSearchClause.setCreatedDate(createdDate);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTableSearchClause searchCriteria = DataTablesWhere.createdDate("2020-01-01T00:00:00.000-06:00");
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindDataTableByCreatedDate() throws Exception {
        org.catalytic.sdk.generated.model.DataTable dataTable = new org.catalytic.sdk.generated.model.DataTable();
        dataTable.setCreatedDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(dataTable));
        DataTablesPage.setCount(Arrays.asList(dataTable).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        createdDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        DataTableSearchClause.setCreatedDate(createdDate);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTableSearchClause searchCriteria = DataTablesWhere.createdDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindDataTablesByCreatedDateBetween() throws Exception {
        org.catalytic.sdk.generated.model.DataTable alice = new org.catalytic.sdk.generated.model.DataTable();
        alice.setCreatedDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(alice));
        DataTablesPage.setCount(Arrays.asList(alice).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        DataTableSearchClause.setCreatedDate(createdDate);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTableSearchClause searchCriteria = DataTablesWhere.createdDateBetween(
                "2020-01-01T00:00:00.000-06:00",
                "2020-12-01T00:00:00.000-06:00"
        );
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindDataTablesByCreatedDateAsStringBetweenAsString() throws Exception {
        org.catalytic.sdk.generated.model.DataTable alice = new org.catalytic.sdk.generated.model.DataTable();
        alice.setCreatedDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(alice));
        DataTablesPage.setCount(Arrays.asList(alice).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        DataTableSearchClause.setCreatedDate(createdDate);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTableSearchClause searchCriteria = DataTablesWhere.createdDateBetween(
                OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
                OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
        );
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void search_itShouldFindDataTableByNameAndDescription() throws Exception {
        org.catalytic.sdk.generated.model.DataTable alice = new org.catalytic.sdk.generated.model.DataTable();
        alice.setName("alice@example.com");
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        DataTablesPage.setDataTables(Arrays.asList(alice));
        DataTablesPage.setCount(Arrays.asList(alice).size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();

        List<org.catalytic.sdk.generated.model.DataTableSearchClause> and = new ArrayList<>();
        org.catalytic.sdk.generated.model.DataTableSearchClause nameClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.DataTableSearchClause fullNameClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setIsEqualTo("alice@example.com");
        org.catalytic.sdk.generated.model.StringSearchExpression fullName = new org.catalytic.sdk.generated.model.StringSearchExpression();
        fullName.setIsEqualTo("alice example");
        nameClause.setName(name);
        fullNameClause.setDescription(fullName);
        and.add(nameClause);
        and.add(fullNameClause);

        DataTableSearchClause.setAnd(and);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTableSearchClause nameSearchClause = DataTablesWhere.name("alice@example.com");
        DataTableSearchClause fullNameSearchClause = DataTablesWhere.description("alice example");
        DataTableSearchClause searchCriteria = DataTablesWhere.and(nameSearchClause, fullNameSearchClause);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getName()).isEqualTo("alice@example.com");
    }

    @Test
    public void search_itShouldFindDataTableByNameOrName() throws Exception {
        org.catalytic.sdk.generated.model.DataTable alice = new org.catalytic.sdk.generated.model.DataTable();
        org.catalytic.sdk.generated.model.DataTable marvin = new org.catalytic.sdk.generated.model.DataTable();
        alice.setName("alice@example.com");
        marvin.setName("marvin@aol.com");
        org.catalytic.sdk.generated.model.DataTablesPage DataTablesPage = new org.catalytic.sdk.generated.model.DataTablesPage();
        List<org.catalytic.sdk.generated.model.DataTable> DataTables = Arrays.asList(alice, marvin);
        DataTablesPage.setDataTables(DataTables);
        DataTablesPage.setCount(DataTables.size());
        DataTablesPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.DataTableSearchClause DataTableSearchClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();

        List<org.catalytic.sdk.generated.model.DataTableSearchClause> or = new ArrayList<>();
        org.catalytic.sdk.generated.model.DataTableSearchClause nameAliceClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.DataTableSearchClause nameMarvinClause = new org.catalytic.sdk.generated.model.DataTableSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression nameAlice = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringSearchExpression nameMarvin = new org.catalytic.sdk.generated.model.StringSearchExpression();
        nameAlice.setIsEqualTo("alice@example.com");
        nameMarvin.setIsEqualTo("marvin@aol.com");
        nameAliceClause.setName(nameAlice);
        nameMarvinClause.setName(nameMarvin);
        or.add(nameAliceClause);
        or.add(nameMarvinClause);

        DataTableSearchClause.setOr(or);
        when(dataTablesApi.searchDataTables(null, null, DataTableSearchClause))
                .thenReturn(DataTablesPage);

        DataTableSearchClause aliceEmail = DataTablesWhere.name("alice@example.com");
        DataTableSearchClause marvinEmail = DataTablesWhere.name("marvin@aol.com");
        DataTableSearchClause searchCriteria = DataTablesWhere.or(aliceEmail, marvinEmail);

        DataTables DataTablesClient = new DataTables("1234", dataTablesApi);
        org.catalytic.sdk.entities.DataTablesPage results = DataTablesClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(2);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getDataTables().get(0).getName()).isEqualTo("alice@example.com");
        assertThat(results.getDataTables().get(1).getName()).isEqualTo("marvin@aol.com");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void downloadDataTable_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        DataTables dataTablesClient = new DataTables(nullString);
        dataTablesClient.download("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void downloadDataTable_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = DataTableNotFoundException.class)
    public void downloadDataTable_itShouldThrowDataTableNotFoundExceptionIfDataTableDoesNotExist() throws Exception {
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenThrow(new ApiException(404, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test(expected = InternalErrorException.class)
    public void downloadDataTable_itShouldThrowInternalErrorException() throws Exception {
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
    }

    @Test
    public void downloadDataTable_itShouldReturnTheDownloadedFile() throws Exception {
        java.io.File internalFile = new java.io.File("foobar");
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenReturn(internalFile);

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        File file = dataTablesClient.download("f98b3a70-a461-46b1-b43a-3eb1cced3efd");
        assertThat(file).isNotNull();
    }

    @Test
    public void downloadDataTable_itShouldReturnTheDownloadedFileAsXlsx() throws Exception {
        java.io.File file = new java.io.File("foobar.xlsx");
        when(dataTablesApi.downloadDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null)).thenReturn(file);

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
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

    @Test(expected = AccessTokenNotFoundException.class)
    public void uploadDataTable_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        DataTables dataTablesClient = new DataTables(nullString);
        dataTablesClient.upload(null, null);
    }

    @Test(expected = UnauthorizedException.class)
    public void uploadDataTable_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.uploadDataTable(null, null, null, Arrays.asList(file))).thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.upload(file, null);
    }

    @Test(expected = InternalErrorException.class)
    public void uploadDataTable_itShouldThrowInternalErrorException() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.uploadDataTable(null, null, null, Arrays.asList(file))).thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.upload(file, null);
    }

    @Test
    public void uploadDataTable_itShouldUploadDataTable() throws Exception {
        java.io.File file = new java.io.File("foobar");
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setName("My Table");
        when(dataTablesApi.uploadDataTable(null, null, null, Arrays.asList(file))).thenReturn(internalDataTable);

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        DataTable dataTable = dataTablesClient.upload(file, null);
        assertThat(dataTable.getName()).isEqualTo("My Table");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void replaceDataTable_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        String nullString = null;
        DataTables dataTablesClient = new DataTables(nullString);
        dataTablesClient.replace("1234", null);
    }

    @Test(expected = UnauthorizedException.class)
    public void replaceDataTable_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.replaceDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, null, Arrays.asList(file))).thenThrow(new ApiException(401, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.replace("f98b3a70-a461-46b1-b43a-3eb1cced3efd", file);
    }

    @Test(expected = DataTableNotFoundException.class)
    public void replaceDataTable_itShouldThrowDataTableNotFoundExceptionIfDataTableDoesNotExist() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.replaceDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, null, Arrays.asList(file))).thenThrow(new ApiException(404, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.replace("f98b3a70-a461-46b1-b43a-3eb1cced3efd", file);
    }

    @Test(expected = InternalErrorException.class)
    public void replaceDataTable_itShouldThrowInternalErrorException() throws Exception {
        java.io.File file = new java.io.File("foobar");
        when(dataTablesApi.replaceDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, null, Arrays.asList(file))).thenThrow(new ApiException(500, null));

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        dataTablesClient.replace("f98b3a70-a461-46b1-b43a-3eb1cced3efd", file);
    }

    @Test
    public void replaceDataTable_itShouldReplaceDataTable() throws Exception {
        java.io.File file = new java.io.File("foobar");
        org.catalytic.sdk.generated.model.DataTable internalDataTable = new org.catalytic.sdk.generated.model.DataTable();
        internalDataTable.setName("My New Table");
        when(dataTablesApi.replaceDataTable("f98b3a70-a461-46b1-b43a-3eb1cced3efd", null, null, Arrays.asList(file))).thenReturn(internalDataTable);

        DataTables dataTablesClient = new DataTables("1234", dataTablesApi);
        DataTable dataTable = dataTablesClient.replace("f98b3a70-a461-46b1-b43a-3eb1cced3efd", file);
        assertThat(dataTable.getName()).isEqualTo("My New Table");
    }
}
