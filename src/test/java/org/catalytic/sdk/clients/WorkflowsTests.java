package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.File;
import org.catalytic.sdk.entities.Workflow;
import org.catalytic.sdk.entities.WorkflowsPage;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.WorkflowsApi;
import org.catalytic.sdk.generated.model.WorkflowExport;
import org.catalytic.sdk.generated.model.WorkflowExportRequest;
import org.catalytic.sdk.generated.model.WorkflowImport;
import org.catalytic.sdk.generated.model.WorkflowImportRequest;
import org.catalytic.sdk.search.Where;
import org.catalytic.sdk.search.WorkflowSearchClause;
import org.catalytic.sdk.search.WorkflowsWhere;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorkflowsTests {

    WorkflowsApi workflowsApi;
    Files filesClient;

    @Before
    public void before() {
        workflowsApi = mock(WorkflowsApi.class);
        filesClient = mock(Files.class);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void getWorkflow_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Workflows workflowsClient = new Workflows(null);
        workflowsClient.get("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void getWorkflow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(workflowsApi.getWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(401, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = WorkflowNotFoundException.class)
    public void getWorkflow_itShouldThrowWorkflowNotFoundExceptionIfWorkflowDoesNotExist() throws Exception {
        when(workflowsApi.getWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(404, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void getWorkflow_itShouldThrowInternalErrorException() throws Exception {
        when(workflowsApi.getWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test
    public void getWorkflow_itShouldGetAWorkflow() throws Exception {
        org.catalytic.sdk.generated.model.Workflow sdkWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        sdkWorkflow.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(workflowsApi.getWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a")).thenReturn(sdkWorkflow);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        Workflow workflow = workflowsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
        assertThat(workflow).isNotNull();
        assertThat(workflow.getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void findWorkflows_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Workflows workflowsClient = new Workflows(null);
        workflowsClient.find();
    }

    @Test(expected = UnauthorizedException.class)
    public void findWorkflows_itShouldReturnUnauthorizedException() throws Exception {
        when(workflowsApi.findWorkflows(null, null, null, null, null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(401, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.find();
    }

    @Test(expected = InternalErrorException.class)
    public void findWorkflows_itShouldReturnInternalErrorException() throws Exception {
        when(workflowsApi.findWorkflows(null, null, null, null, null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.find();
    }

    @Test
    public void findWorkflows_itShouldReturnAllWorkflows() throws Exception {
        org.catalytic.sdk.generated.model.Workflow myWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        myWorkflow.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        org.catalytic.sdk.generated.model.WorkflowsPage workflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        workflowsPage.setWorkflows(Arrays.asList(myWorkflow));
        workflowsPage.setCount(Arrays.asList(myWorkflow).size());
        workflowsPage.setNextPageToken(null);
        when(workflowsApi.findWorkflows(null, null, null, null, null, null, null, null, null, null, null, null, null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        WorkflowsPage results = workflowsClient.find();
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getWorkflows().get(0).getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void findWorkflows_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.Workflow myWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        myWorkflow.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        org.catalytic.sdk.generated.model.WorkflowsPage workflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        workflowsPage.setWorkflows(Arrays.asList(myWorkflow));
        workflowsPage.setCount(Arrays.asList(myWorkflow).size());
        workflowsPage.setNextPageToken(null);
        when(workflowsApi.findWorkflows(null, null, null, null, null, null, null, null, null, null, null, "25", null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        WorkflowsPage results = workflowsClient.find("25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getWorkflows().get(0).getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test
    public void findWorkflows_itShouldFindWorkflowByName() throws Exception {
        org.catalytic.sdk.generated.model.Workflow myWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        myWorkflow.setName("My Workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage workflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        workflowsPage.setWorkflows(Arrays.asList(myWorkflow));
        workflowsPage.setCount(Arrays.asList(myWorkflow).size());
        workflowsPage.setNextPageToken(null);
        when(workflowsApi.findWorkflows("My Workflow", null, null, null, null, null, null, null, null, null, null, null, null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        Where where = new Where();
        WorkflowsPage results = workflowsClient.find(where.text().matches("My Workflow"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("My Workflow");
    }

    @Test
    public void findWorkflows_itShouldFindWorkflowByOwner() throws Exception {
        org.catalytic.sdk.generated.model.Workflow myWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        myWorkflow.setOwner("alice@example.com");
        org.catalytic.sdk.generated.model.WorkflowsPage workflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        workflowsPage.setWorkflows(Arrays.asList(myWorkflow));
        workflowsPage.setCount(Arrays.asList(myWorkflow).size());
        workflowsPage.setNextPageToken(null);
        when(workflowsApi.findWorkflows(null, null, null, null, "alice@example.com", null, null, null, null, null, null, null, null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        Where where = new Where();
        WorkflowsPage results = workflowsClient.find(where.owner().is("alice@example.com"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getWorkflows().get(0).getOwner()).isEqualTo("alice@example.com");
    }

    @Test
    public void findWorkflows_itShouldFindWorkflowByCategory() throws Exception {
        org.catalytic.sdk.generated.model.Workflow myWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        myWorkflow.setCategory("general");
        org.catalytic.sdk.generated.model.WorkflowsPage workflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        workflowsPage.setWorkflows(Arrays.asList(myWorkflow));
        workflowsPage.setCount(Arrays.asList(myWorkflow).size());
        workflowsPage.setNextPageToken(null);
        when(workflowsApi.findWorkflows(null, null, null, null, null, "general", null, null, null, null, null, null, null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        Where where = new Where();
        WorkflowsPage results = workflowsClient.find(where.category().is("general"));
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getWorkflows().get(0).getCategory()).isEqualTo("general");
    }

    @Test
    public void findWorkflows_itShouldFindWorkflowByNameAndPage() throws Exception {
        org.catalytic.sdk.generated.model.Workflow myWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        myWorkflow.setName("My Workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage workflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        workflowsPage.setWorkflows(Arrays.asList(myWorkflow));
        workflowsPage.setCount(Arrays.asList(myWorkflow).size());
        workflowsPage.setNextPageToken(null);
        when(workflowsApi.findWorkflows("My Workflow", null, null, null, null, null, null, null, null, null, null, "25", null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        Where where = new Where();
        WorkflowsPage results = workflowsClient.find(where.text().is("My Workflow"), "25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("My Workflow");
    }

    @Test(expected = UnauthorizedException.class)
    public void search_itShouldReturnUnauthorizedException() throws Exception {
        when(workflowsApi.searchWorkflows(null, null, new org.catalytic.sdk.generated.model.WorkflowSearchClause()))
                .thenThrow(new ApiException(401, null));

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsClient.search(null);
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void search_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Workflows WorkflowsClient = new Workflows(null);
        WorkflowsClient.search(null);
    }

    @Test(expected = InternalErrorException.class)
    public void search_itShouldReturnInternalErrorException() throws Exception {
        when(workflowsApi.searchWorkflows(null, null, new org.catalytic.sdk.generated.model.WorkflowSearchClause()))
                .thenThrow(new ApiException(500, null));

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsClient.search(null);
    }

    @Test
    public void list_itShouldReturnAllWorkflows() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow1 = new org.catalytic.sdk.generated.model.Workflow();
        org.catalytic.sdk.generated.model.Workflow workflow2 = new org.catalytic.sdk.generated.model.Workflow();
        workflow1.setName("Workflow one");
        workflow2.setName("Workflow two");
        org.catalytic.sdk.generated.model.WorkflowsPage firstPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        org.catalytic.sdk.generated.model.WorkflowsPage secondPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        firstPage.setWorkflows(Arrays.asList(workflow1));
        firstPage.setCount(Arrays.asList(workflow1).size());
        firstPage.setNextPageToken("123");
        secondPage.setWorkflows(Arrays.asList(workflow2));
        secondPage.setCount(Arrays.asList(workflow2).size());
        secondPage.setNextPageToken("123");
        secondPage.setNextPageToken("");
        when(workflowsApi.searchWorkflows(null, null, new org.catalytic.sdk.generated.model.WorkflowSearchClause()))
                .thenReturn(firstPage);
        when(workflowsApi.searchWorkflows("123", null, new org.catalytic.sdk.generated.model.WorkflowSearchClause()))
                .thenReturn(secondPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.list();

        assertThat(results.getCount()).isEqualTo(2);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("Workflow one");
        assertThat(results.getWorkflows().get(1).getName()).isEqualTo("Workflow two");
    }

    @Test
    public void searchWorkflows_itShouldFindNextPage() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setName("My workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");
        when(workflowsApi.searchWorkflows("abc123", null, new org.catalytic.sdk.generated.model.WorkflowSearchClause()))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.search(null, "abc123", null);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("My workflow");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowById() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setId(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.GuidSearchExpression id = new org.catalytic.sdk.generated.model.GuidSearchExpression();
        id.setIsEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        WorkflowSearchClause.setId(id);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        WorkflowSearchClause searchCriteria = WorkflowsWhere.id(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getId()).isEqualTo(UUID.fromString("00f0e0af-7029-47b5-b38b-bb636fc1bd63"));
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByName() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setName("My workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setIsEqualTo("My workflow");
        WorkflowSearchClause.setName(name);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        WorkflowSearchClause searchCriteria = WorkflowsWhere.name("My workflow");

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("My workflow");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByPartialName() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setName("My workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setContains("my");
        WorkflowSearchClause.setName(name);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.nameContains("my");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("My workflow");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowBetweenNames() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setName("My workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange nameRange = new org.catalytic.sdk.generated.model.StringRange();
        nameRange.setLowerBoundInclusive("Ma");
        nameRange.setUpperBoundInclusive("Mz");
        name.setBetween(nameRange);
        WorkflowSearchClause.setName(name);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.nameBetween("Ma", "Mz");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("My workflow");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByDescription() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setDescription("My first workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new org.catalytic.sdk.generated.model.StringSearchExpression();
        description.setIsEqualTo("My first workflow");
        WorkflowSearchClause.setDescription(description);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        WorkflowSearchClause searchCriteria = WorkflowsWhere.description("My first workflow");

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getDescription()).isEqualTo("My first workflow");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByPartialDescription() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setDescription("My first workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression description = new org.catalytic.sdk.generated.model.StringSearchExpression();
        description.setContains("first");
        WorkflowSearchClause.setDescription(description);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.descriptionContains("first");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getDescription()).isEqualTo("My first workflow");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowBetweenDescriptions() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setDescription("My first workflow");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression fullName = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange fullNameRange = new org.catalytic.sdk.generated.model.StringRange();
        fullNameRange.setLowerBoundInclusive("Ma");
        fullNameRange.setUpperBoundInclusive("Mz");
        fullName.setBetween(fullNameRange);
        WorkflowSearchClause.setDescription(fullName);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.descriptionBetween("Ma", "Mz");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getDescription()).isEqualTo("My first workflow");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByOwner() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setOwner("Bob Marley");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression owner = new org.catalytic.sdk.generated.model.StringSearchExpression();
        owner.setIsEqualTo("Bob Marley");
        WorkflowSearchClause.setOwner(owner);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        WorkflowSearchClause searchCriteria = WorkflowsWhere.owner("Bob Marley");

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getOwner()).isEqualTo("Bob Marley");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByPartialOwner() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setOwner("Bob Marley");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression owner = new org.catalytic.sdk.generated.model.StringSearchExpression();
        owner.setContains("bob");
        WorkflowSearchClause.setOwner(owner);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.ownerContains("bob");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getOwner()).isEqualTo("Bob Marley");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowBetweenOwners() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setOwner("Bob Marley");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause workflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression owner = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange ownerRange = new org.catalytic.sdk.generated.model.StringRange();
        ownerRange.setLowerBoundInclusive("Ba");
        ownerRange.setUpperBoundInclusive("Bz");
        owner.setBetween(ownerRange);
        workflowSearchClause.setOwner(owner);
        when(workflowsApi.searchWorkflows(null, null, workflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.ownerBetween("Ba", "Bz");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getOwner()).isEqualTo("Bob Marley");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByCategory() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setCategory("General");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression category = new org.catalytic.sdk.generated.model.StringSearchExpression();
        category.setIsEqualTo("General");
        WorkflowSearchClause.setCategory(category);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        WorkflowSearchClause searchCriteria = WorkflowsWhere.category("General");

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getCategory()).isEqualTo("General");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByPartialCategory() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setCategory("General");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression category = new org.catalytic.sdk.generated.model.StringSearchExpression();
        category.setContains("ener");
        WorkflowSearchClause.setCategory(category);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.categoryContains("ener");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getCategory()).isEqualTo("General");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowBetweenCategories() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setCategory("General");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression category = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringRange categoryRange = new org.catalytic.sdk.generated.model.StringRange();
        categoryRange.setLowerBoundInclusive("Ga");
        categoryRange.setUpperBoundInclusive("Gz");
        category.setBetween(categoryRange);
        WorkflowSearchClause.setCategory(category);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.categoryBetween("Ga", "Gz");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getCategory()).isEqualTo("General");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByCreatedDateAsString() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setCreatedDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        createdDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        WorkflowSearchClause.setCreatedDate(createdDate);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.createdDate("2020-01-01T00:00:00.000-06:00");
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByCreatedDate() throws Exception {
        org.catalytic.sdk.generated.model.Workflow workflow = new org.catalytic.sdk.generated.model.Workflow();
        workflow.setCreatedDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(workflow));
        WorkflowsPage.setCount(Arrays.asList(workflow).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        createdDate.isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        WorkflowSearchClause.setCreatedDate(createdDate);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.createdDate(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowsByCreatedDateBetween() throws Exception {
        org.catalytic.sdk.generated.model.Workflow alice = new org.catalytic.sdk.generated.model.Workflow();
        alice.setCreatedDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(alice));
        WorkflowsPage.setCount(Arrays.asList(alice).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        WorkflowSearchClause.setCreatedDate(createdDate);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.createdDateBetween(
                "2020-01-01T00:00:00.000-06:00",
                "2020-12-01T00:00:00.000-06:00"
        );
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowsByCreatedDateAsStringBetweenAsString() throws Exception {
        org.catalytic.sdk.generated.model.Workflow alice = new org.catalytic.sdk.generated.model.Workflow();
        alice.setCreatedDate(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(alice));
        WorkflowsPage.setCount(Arrays.asList(alice).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.DateTimeSearchExpression createdDate = new org.catalytic.sdk.generated.model.DateTimeSearchExpression();
        org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange createdDateRange = new org.catalytic.sdk.generated.model.DateTimeOffsetNullableRange();
        createdDateRange.setLowerBoundInclusive(OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDateRange.setUpperBoundInclusive(OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
        createdDate.setBetween(createdDateRange);
        WorkflowSearchClause.setCreatedDate(createdDate);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowSearchClause searchCriteria = WorkflowsWhere.createdDateBetween(
                OffsetDateTime.of(2020, 01, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")),
                OffsetDateTime.of(2020, 12, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00"))
        );
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getCreatedDate()).isEqualTo(OffsetDateTime.of(2020, 06, 01, 0, 0, 0, 0, ZoneOffset.of("-06:00")));
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByNameAndDescription() throws Exception {
        org.catalytic.sdk.generated.model.Workflow alice = new org.catalytic.sdk.generated.model.Workflow();
        alice.setName("alice@example.com");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        WorkflowsPage.setWorkflows(Arrays.asList(alice));
        WorkflowsPage.setCount(Arrays.asList(alice).size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();

        List<org.catalytic.sdk.generated.model.WorkflowSearchClause> and = new ArrayList<>();
        org.catalytic.sdk.generated.model.WorkflowSearchClause nameClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.WorkflowSearchClause fullNameClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression name = new org.catalytic.sdk.generated.model.StringSearchExpression();
        name.setIsEqualTo("alice@example.com");
        org.catalytic.sdk.generated.model.StringSearchExpression fullName = new org.catalytic.sdk.generated.model.StringSearchExpression();
        fullName.setIsEqualTo("alice example");
        nameClause.setName(name);
        fullNameClause.setDescription(fullName);
        and.add(nameClause);
        and.add(fullNameClause);

        WorkflowSearchClause.setAnd(and);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        WorkflowSearchClause nameSearchClause = WorkflowsWhere.name("alice@example.com");
        WorkflowSearchClause fullNameSearchClause = WorkflowsWhere.description("alice example");
        WorkflowSearchClause searchCriteria = WorkflowsWhere.and(nameSearchClause, fullNameSearchClause);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("alice@example.com");
    }

    @Test
    public void searchWorkflows_itShouldFindWorkflowByNameOrName() throws Exception {
        org.catalytic.sdk.generated.model.Workflow alice = new org.catalytic.sdk.generated.model.Workflow();
        org.catalytic.sdk.generated.model.Workflow marvin = new org.catalytic.sdk.generated.model.Workflow();
        alice.setName("alice@example.com");
        marvin.setName("marvin@aol.com");
        org.catalytic.sdk.generated.model.WorkflowsPage WorkflowsPage = new org.catalytic.sdk.generated.model.WorkflowsPage();
        List<org.catalytic.sdk.generated.model.Workflow> Workflows = Arrays.asList(alice, marvin);
        WorkflowsPage.setWorkflows(Workflows);
        WorkflowsPage.setCount(Workflows.size());
        WorkflowsPage.setNextPageToken("");

        org.catalytic.sdk.generated.model.WorkflowSearchClause WorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();

        List<org.catalytic.sdk.generated.model.WorkflowSearchClause> or = new ArrayList<>();
        org.catalytic.sdk.generated.model.WorkflowSearchClause nameAliceClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.WorkflowSearchClause nameMarvinClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        org.catalytic.sdk.generated.model.StringSearchExpression nameAlice = new org.catalytic.sdk.generated.model.StringSearchExpression();
        org.catalytic.sdk.generated.model.StringSearchExpression nameMarvin = new org.catalytic.sdk.generated.model.StringSearchExpression();
        nameAlice.setIsEqualTo("alice@example.com");
        nameMarvin.setIsEqualTo("marvin@aol.com");
        nameAliceClause.setName(nameAlice);
        nameMarvinClause.setName(nameMarvin);
        or.add(nameAliceClause);
        or.add(nameMarvinClause);

        WorkflowSearchClause.setOr(or);
        when(workflowsApi.searchWorkflows(null, null, WorkflowSearchClause))
                .thenReturn(WorkflowsPage);

        WorkflowSearchClause aliceEmail = WorkflowsWhere.name("alice@example.com");
        WorkflowSearchClause marvinEmail = WorkflowsWhere.name("marvin@aol.com");
        WorkflowSearchClause searchCriteria = WorkflowsWhere.or(aliceEmail, marvinEmail);

        Workflows WorkflowsClient = new Workflows("1234", workflowsApi, null);
        WorkflowsPage results = WorkflowsClient.search(searchCriteria);

        assertThat(results.getCount()).isEqualTo(2);
        assertThat(results.getNextPageToken()).isEmpty();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("alice@example.com");
        assertThat(results.getWorkflows().get(1).getName()).isEqualTo("marvin@aol.com");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void exportWorkflow_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Workflows workflowsClient = new Workflows(null);
        workflowsClient.export("1234");
    }

    @Test(expected = UnauthorizedException.class)
    public void exportWorkflow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenThrow(new ApiException(401, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = WorkflowNotFoundException.class)
    public void exportWorkflow_itShouldThrowWorkflowNotFoundExceptionIfWorkflowDoesNotExist() throws Exception {
        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenThrow(new ApiException(404, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void exportWorkflow_itShouldThrowInternalErrorException() throws Exception {
        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void exportWorkflow_itShouldThrowInternalErrorExceptionWhenGettingWorkflowExport() throws Exception {
        WorkflowExport initialWorkflowExport = new WorkflowExport();
        initialWorkflowExport.setName("My Workflow");
        initialWorkflowExport.setId(UUID.fromString("3e8470de-6474-44ec-b9bb-f0b5262ab9dd"));

        WorkflowExport subsequentWorkflowExport = new WorkflowExport();
        subsequentWorkflowExport.setName("My Workflow");
        subsequentWorkflowExport.setFileId(UUID.fromString("96d9cd1d-5200-4b4f-840e-a98cd0936149"));
        subsequentWorkflowExport.setId(UUID.fromString("3e8470de-6474-44ec-b9bb-f0b5262ab9dd"));

        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

        File myFile = new File();
        myFile.setName("My Workflow");

        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenReturn(initialWorkflowExport);
        when(workflowsApi.getWorkflowExport("3e8470de-6474-44ec-b9bb-f0b5262ab9dd")).thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void exportWorkflow_itShouldThrowInternalErrorExceptionWhenGettingFile() throws Exception {
        WorkflowExport initialWorkflowExport = new WorkflowExport();
        initialWorkflowExport.setName("My Workflow");
        initialWorkflowExport.setId(UUID.fromString("3e8470de-6474-44ec-b9bb-f0b5262ab9dd"));

        WorkflowExport subsequentWorkflowExport = new WorkflowExport();
        subsequentWorkflowExport.setName("My Workflow");
        subsequentWorkflowExport.setFileId(UUID.fromString("96d9cd1d-5200-4b4f-840e-a98cd0936149"));
        subsequentWorkflowExport.setId(UUID.fromString("3e8470de-6474-44ec-b9bb-f0b5262ab9dd"));

        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

        File myFile = new File();
        myFile.setName("My Workflow");

        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenReturn(initialWorkflowExport);
        when(workflowsApi.getWorkflowExport("3e8470de-6474-44ec-b9bb-f0b5262ab9dd")).thenReturn(subsequentWorkflowExport);
        when(filesClient.get("3e8470de-6474-44ec-b9bb-f0b5262ab9dd")).thenThrow(new FileNotFoundException());

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test
    public void exportWorkflow_itShouldExportAWorkflowWithNoPassword() throws Exception {
        WorkflowExport initialWorkflowExport = new WorkflowExport();
        initialWorkflowExport.setName("My Workflow");
        initialWorkflowExport.setId(UUID.fromString("3e8470de-6474-44ec-b9bb-f0b5262ab9dd"));

        WorkflowExport subsequentWorkflowExport = new WorkflowExport();
        subsequentWorkflowExport.setName("My Workflow");
        subsequentWorkflowExport.setFileId(UUID.fromString("96d9cd1d-5200-4b4f-840e-a98cd0936149"));
        subsequentWorkflowExport.setId(UUID.fromString("3e8470de-6474-44ec-b9bb-f0b5262ab9dd"));

        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

        File myFile = new File();
        myFile.setName("My Workflow");

        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenReturn(initialWorkflowExport);
        when(workflowsApi.getWorkflowExport("3e8470de-6474-44ec-b9bb-f0b5262ab9dd")).thenReturn(subsequentWorkflowExport);
        when(filesClient.get("3e8470de-6474-44ec-b9bb-f0b5262ab9dd")).thenReturn(myFile);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        File file = workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a");
        assertThat(file.getName()).isEqualTo("My Workflow");
    }

    @Test
    public void exportWorkflow_itShouldExportAWorkflowWithWithPassword() throws Exception {
        WorkflowExport initialWorkflowExport = new WorkflowExport();
        initialWorkflowExport.setName("My Workflow");
        initialWorkflowExport.setId(UUID.fromString("3e8470de-6474-44ec-b9bb-f0b5262ab9dd"));

        WorkflowExport subsequentWorkflowExport = new WorkflowExport();
        subsequentWorkflowExport.setName("My Workflow");
        subsequentWorkflowExport.setFileId(UUID.fromString("96d9cd1d-5200-4b4f-840e-a98cd0936149"));
        subsequentWorkflowExport.setId(UUID.fromString("3e8470de-6474-44ec-b9bb-f0b5262ab9dd"));

        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        workflowExportRequest.setPassword("my-password");

        File myFile = new File();
        myFile.setName("My Workflow");

        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenReturn(initialWorkflowExport);
        when(workflowsApi.getWorkflowExport("3e8470de-6474-44ec-b9bb-f0b5262ab9dd")).thenReturn(subsequentWorkflowExport);
        when(filesClient.get("3e8470de-6474-44ec-b9bb-f0b5262ab9dd")).thenReturn(myFile);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        File file = workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a", "my-password");
        assertThat(file.getName()).isEqualTo("My Workflow");
    }

    @Test(expected = AccessTokenNotFoundException.class)
    public void importWorkflow_itShouldReturnAccessTokenNotFoundExceptionIfClientInstantiatedWithoutToken() throws Exception {
        Workflows workflowsClient = new Workflows(null);
        workflowsClient.importWorkflow(null);
    }

    @Test(expected = UnauthorizedException.class)
    public void importWorkflow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(file.getId());
        when(filesClient.upload(importFile)).thenReturn(file);
        when(workflowsApi.importWorkflow(workflowImportRequest)).thenThrow(new ApiException(401, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.importWorkflow(importFile);
    }

    @Test(expected = InternalErrorException.class)
    public void importWorkflow_itShouldThrowInternalErrorException() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(file.getId());
        when(filesClient.upload(importFile)).thenReturn(file);
        when(workflowsApi.importWorkflow(workflowImportRequest)).thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.importWorkflow(importFile);
    }

    @Test(expected = InternalErrorException.class)
    public void importWorkflow_itShouldThrowInternalErrorExceptionWhenGettingWorkflowImport() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(file.getId());

        WorkflowImport initialWorkflowImport = new WorkflowImport();
        initialWorkflowImport.setId(UUID.fromString("31b490e8-e52d-41fb-ad72-f7e45edcdd92"));

        WorkflowImport subsequentWorkflowImport = new WorkflowImport();
        subsequentWorkflowImport.setId(UUID.fromString("31b490e8-e52d-41fb-ad72-f7e45edcdd92"));
        subsequentWorkflowImport.setWorkflowId(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));

        org.catalytic.sdk.generated.model.Workflow sdkWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        sdkWorkflow.setId(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));

        when(filesClient.upload(importFile)).thenReturn(file);
        when(workflowsApi.importWorkflow(workflowImportRequest)).thenReturn(initialWorkflowImport);
        when(workflowsApi.getWorkflowImport(initialWorkflowImport.getId().toString())).thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        workflowsClient.importWorkflow(importFile);
    }

    @Test
    public void importWorkflow_itShouldExportAWorkflowWithNoPassword() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(file.getId());

        WorkflowImport initialWorkflowImport = new WorkflowImport();
        initialWorkflowImport.setId(UUID.fromString("31b490e8-e52d-41fb-ad72-f7e45edcdd92"));

        WorkflowImport subsequentWorkflowImport = new WorkflowImport();
        subsequentWorkflowImport.setId(UUID.fromString("31b490e8-e52d-41fb-ad72-f7e45edcdd92"));
        subsequentWorkflowImport.setWorkflowId(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));

        org.catalytic.sdk.generated.model.Workflow sdkWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        sdkWorkflow.setId(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));

        when(filesClient.upload(importFile)).thenReturn(file);
        when(workflowsApi.importWorkflow(workflowImportRequest)).thenReturn(initialWorkflowImport);
        when(workflowsApi.getWorkflowImport(initialWorkflowImport.getId().toString())).thenReturn(subsequentWorkflowImport);
        when(workflowsApi.getWorkflow("41669347-e810-4e08-9183-b70d001ab2f5")).thenReturn(sdkWorkflow);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        Workflow workflow = workflowsClient.importWorkflow(importFile);
        assertThat(workflow.getId()).isEqualTo(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));
    }

    @Test
    public void importWorkflow_itShouldExportAWorkflowWithWithPassword() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));

        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(file.getId());
        workflowImportRequest.setPassword("my-password");

        WorkflowImport initialWorkflowImport = new WorkflowImport();
        initialWorkflowImport.setId(UUID.fromString("31b490e8-e52d-41fb-ad72-f7e45edcdd92"));

        WorkflowImport subsequentWorkflowImport = new WorkflowImport();
        subsequentWorkflowImport.setId(UUID.fromString("31b490e8-e52d-41fb-ad72-f7e45edcdd92"));
        subsequentWorkflowImport.setWorkflowId(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));

        org.catalytic.sdk.generated.model.Workflow sdkWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        sdkWorkflow.setId(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));

        when(filesClient.upload(importFile)).thenReturn(file);
        when(workflowsApi.importWorkflow(workflowImportRequest)).thenReturn(initialWorkflowImport);
        when(workflowsApi.getWorkflowImport(initialWorkflowImport.getId().toString())).thenReturn(subsequentWorkflowImport);
        when(workflowsApi.getWorkflow("41669347-e810-4e08-9183-b70d001ab2f5")).thenReturn(sdkWorkflow);

        Workflows workflowsClient = new Workflows("1234", workflowsApi, filesClient);
        Workflow workflow = workflowsClient.importWorkflow(importFile, "my-password");
        assertThat(workflow.getId()).isEqualTo(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));
    }
}
