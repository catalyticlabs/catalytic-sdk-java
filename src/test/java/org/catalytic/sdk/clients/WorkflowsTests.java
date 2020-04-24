package org.catalytic.sdk.clients;

import org.catalytic.sdk.Client;
import org.catalytic.sdk.entities.Workflow;
import org.catalytic.sdk.entities.WorkflowExport;
import org.catalytic.sdk.entities.WorkflowsPage;
import org.catalytic.sdk.search.Where;
import org.junit.Test;

import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;

public class WorkflowsTests {

    @Test
    public void itShouldGetAWorkflow() throws Exception {
        Client catalytic = new Client();
        Workflow workflow = catalytic.workflows().get("7e77254c-d2d6-4271-965a-98390aefa50a");
        assertThat(workflow).isNotNull();
        assertThat(workflow.getName()).containsMatch("Testing PHP SDK");
    }

    @Test
    public void itShouldFindAllWorkflows() throws Exception {
        Client catalytic = new Client();
        WorkflowsPage results = catalytic.workflows().find();
        assertThat(results.getWorkflows()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindWorkflowsByFuzzyName() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        WorkflowsPage results = catalytic.workflows().find(
                where.text().matches("php sdk")
        );
        assertThat(results.getWorkflows()).hasSize(1);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCount()).isEqualTo(1);
    }

    @Test
    public void itShouldFindWorkflowsByOwner() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        WorkflowsPage results = catalytic.workflows().find(
                where.owner().is("tcaflisch@catalytic.com")
        );
        assertThat(results.getWorkflows()).hasSize(2);
        assertThat(results.getNextPageToken()).isNull();
        assertThat(results.getCount()).isEqualTo(2);
    }

    @Test
    public void itShouldFindWorkflowsByCategory() throws Exception {
        Client catalytic = new Client();
        Where where = new Where();
        WorkflowsPage results = catalytic.workflows().find(
                where.category().is("general")
        );
        assertThat(results.getWorkflows()).isNotEmpty();
        assertThat(results.getNextPageToken()).isNotNull();
        assertThat(results.getCount()).isGreaterThan(0);
    }

    @Test
    public void itShouldExportAWorkflowFromStringNoPassword() throws Exception {
        Client catalytic = new Client();
        WorkflowExport workflowExport = catalytic.workflows().export("7e77254c-d2d6-4271-965a-98390aefa50a");
        assertThat(workflowExport.getId()).isNotNull();
        assertThat(workflowExport.getErrorMessage()).isNull();
    }

    @Test
    public void itShouldExportAWorkflowFromUUIDNoPassword() throws Exception {
        Client catalytic = new Client();
        WorkflowExport workflowExport = catalytic.workflows().export(UUID.fromString("7e77254c-d2d6-4271-965a-98390aefa50a"));
        assertThat(workflowExport.getId()).isNotNull();
        assertThat(workflowExport.getErrorMessage()).isNull();
    }

    @Test
    public void itShouldExportAWorkflowUUIDWithPassword() throws Exception {
        Client catalytic = new Client();
        WorkflowExport workflowExport = catalytic.workflows().export(
                UUID.fromString("7e77254c-d2d6-4271-965a-98390aefa50a"),
                "my-super-secret-password"
        );
        assertThat(workflowExport.getId()).isNotNull();
        assertThat(workflowExport.getErrorMessage()).isNull();
    }

//    @Test
//    public void itShouldImportAWorkflowNoPassword() throws Exception {
//        Client catalytic = new Client();
//        Workflow workflow = catalytic.workflows().importWorkflow();
//        assertThat(workflow).isNotNull();
//        assertThat(workflow.getName()).containsMatch("Testing PHP SDK");
//    }
//
//    @Test
//    public void itShouldImportAWorkflowNoPassword() throws Exception {
//        Client catalytic = new Client();
//        Workflow workflow = catalytic.workflows().importWorkflow(, "my-super-secret-password");
//        assertThat(workflow).isNotNull();
//        assertThat(workflow.getName()).containsMatch("Testing PHP SDK");
//    }
}
