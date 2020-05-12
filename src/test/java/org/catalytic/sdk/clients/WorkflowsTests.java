package org.catalytic.sdk.clients;

import org.catalytic.sdk.entities.File;
import org.catalytic.sdk.entities.Workflow;
import org.catalytic.sdk.entities.WorkflowsPage;
import org.catalytic.sdk.exceptions.FileNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.exceptions.WorkflowNotFoundException;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.WorkflowsApi;
import org.catalytic.sdk.generated.model.WorkflowExport;
import org.catalytic.sdk.generated.model.WorkflowExportRequest;
import org.catalytic.sdk.generated.model.WorkflowImport;
import org.catalytic.sdk.generated.model.WorkflowImportRequest;
import org.catalytic.sdk.search.Where;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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

    @Test
    public void getWorkflow_itShouldGetAWorkflow() throws Exception {
        org.catalytic.sdk.generated.model.Workflow sdkWorkflow = new org.catalytic.sdk.generated.model.Workflow();
        sdkWorkflow.setId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(workflowsApi.getWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a")).thenReturn(sdkWorkflow);

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        Workflow workflow = workflowsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
        assertThat(workflow).isNotNull();
        assertThat(workflow.getId()).isEqualTo(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
    }

    @Test(expected = UnauthorizedException.class)
    public void getWorkflow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        when(workflowsApi.getWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(401, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = WorkflowNotFoundException.class)
    public void getWorkflow_itShouldThrowWorkflowNotFoundExceptionIfWorkflowDoesNotExist() throws Exception {
        when(workflowsApi.getWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(404, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void getWorkflow_itShouldThrowInternalErrorException() throws Exception {
        when(workflowsApi.getWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a")).thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.get("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = UnauthorizedException.class)
    public void findWorkflows_itShouldReturnUnauthorizedException() throws Exception {
        when(workflowsApi.findWorkflows(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(401, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.find();
    }

    @Test(expected = InternalErrorException.class)
    public void findWorkflows_itShouldReturnInternalErrorException() throws Exception {
        when(workflowsApi.findWorkflows(null, null, null, null, null, null, null, null, null))
                .thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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
        when(workflowsApi.findWorkflows(null, null, null, null, null, null, null, null, null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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
        when(workflowsApi.findWorkflows(null, null, null, null, null, null, null, "25", null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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
        when(workflowsApi.findWorkflows("My Workflow", null, null, null, null, null, null, null, null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        Where where = new Where();
        WorkflowsPage results = workflowsClient.find(where.text().is("My Workflow"));
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
        when(workflowsApi.findWorkflows(null, null, null, null, "alice@example.com", null, null, null, null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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
        when(workflowsApi.findWorkflows(null, null, null, null, null, "general", null, null, null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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
        when(workflowsApi.findWorkflows("My Workflow", null, null, null, null, null, null, "25", null))
                .thenReturn(workflowsPage);

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        Where where = new Where();
        WorkflowsPage results = workflowsClient.find(where.text().is("My Workflow"), "25");
        assertThat(results.getCount()).isEqualTo(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getWorkflows().get(0).getName()).isEqualTo("My Workflow");
    }

    @Test(expected = UnauthorizedException.class)
    public void exportWorkflow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenThrow(new ApiException(401, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = WorkflowNotFoundException.class)
    public void exportWorkflow_itShouldThrowWorkflowNotFoundExceptionIfWorkflowDoesNotExist() throws Exception {
        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenThrow(new ApiException(404, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a");
    }

    @Test(expected = InternalErrorException.class)
    public void exportWorkflow_itShouldThrowInternalErrorException() throws Exception {
        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString("ac14952a-a331-457c-ac7d-9a284258b65a"));
        when(workflowsApi.exportWorkflow("ac14952a-a331-457c-ac7d-9a284258b65a", workflowExportRequest)).thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
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

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        File file = workflowsClient.export("ac14952a-a331-457c-ac7d-9a284258b65a", "my-password");
        assertThat(file.getName()).isEqualTo("My Workflow");
    }

    @Test(expected = UnauthorizedException.class)
    public void importWorkflow_itShouldThrowUnauthorizedExceptionIfUnauthorized() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId("ac14952a-a331-457c-ac7d-9a284258b65a");
        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(UUID.fromString(file.getId()));
        when(filesClient.upload(importFile)).thenReturn(file);
        when(workflowsApi.importWorkflow(workflowImportRequest)).thenThrow(new ApiException(401, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.importWorkflow(importFile);
    }

    @Test(expected = WorkflowNotFoundException.class)
    public void importWorkflow_itShouldThrowWorkflowNotFoundExceptionIfWorkflowDoesNotExist() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId("ac14952a-a331-457c-ac7d-9a284258b65a");
        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(UUID.fromString(file.getId()));
        when(filesClient.upload(importFile)).thenReturn(file);
        when(workflowsApi.importWorkflow(workflowImportRequest)).thenThrow(new ApiException(404, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.importWorkflow(importFile);
    }

    @Test(expected = InternalErrorException.class)
    public void importWorkflow_itShouldThrowInternalErrorException() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId("ac14952a-a331-457c-ac7d-9a284258b65a");
        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(UUID.fromString(file.getId()));
        when(filesClient.upload(importFile)).thenReturn(file);
        when(workflowsApi.importWorkflow(workflowImportRequest)).thenThrow(new ApiException(500, null));

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.importWorkflow(importFile);
    }

    @Test(expected = InternalErrorException.class)
    public void importWorkflow_itShouldThrowInternalErrorExceptionWhenGettingWorkflowImport() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId("ac14952a-a331-457c-ac7d-9a284258b65a");

        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(UUID.fromString(file.getId()));

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

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        workflowsClient.importWorkflow(importFile);
    }

    @Test
    public void importWorkflow_itShouldExportAWorkflowWithNoPassword() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId("ac14952a-a331-457c-ac7d-9a284258b65a");

        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(UUID.fromString(file.getId()));

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

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        Workflow workflow = workflowsClient.importWorkflow(importFile);
        assertThat(workflow.getId()).isEqualTo(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));
    }

    @Test
    public void importWorkflow_itShouldExportAWorkflowWithWithPassword() throws Exception {
        java.io.File importFile = new java.io.File("/foo/bar");
        File file = new File();
        file.setId("ac14952a-a331-457c-ac7d-9a284258b65a");

        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(UUID.fromString(file.getId()));
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

        Workflows workflowsClient = new Workflows(workflowsApi, filesClient);
        Workflow workflow = workflowsClient.importWorkflow(importFile, "my-password");
        assertThat(workflow.getId()).isEqualTo(UUID.fromString("41669347-e810-4e08-9183-b70d001ab2f5"));
    }
}
