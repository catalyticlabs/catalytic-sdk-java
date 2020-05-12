package org.catalytic.sdk.integration;

import org.catalytic.sdk.CatalyticClient;
import org.catalytic.sdk.entities.Workflow;
import org.catalytic.sdk.entities.WorkflowsPage;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class WorkflowsTests {

//    @Test
//    public void itShouldGetAWorkflow() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        Workflow workflow = catalytic.workflows().get("7e77254c-d2d6-4271-965a-98390aefa50a");
//        assertThat(workflow).isNotNull();
//        assertThat(workflow.getName()).containsMatch("Testing PHP SDK");
//    }
//
//    @Test
//    public void itShouldFindAllWorkflows() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        WorkflowsPage results = catalytic.workflows().find();
//        assertThat(results.getWorkflows()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//    }

//    @Test
//    public void itShouldFindWorkflowsByFuzzyName() throws Exception {
//        Client catalytic = new Client();
//        Where where = new Where();
//        WorkflowsPage results = catalytic.workflows().find(
//                where.text().matches("php sdk")
//        );
//        assertThat(results.getWorkflows()).hasSize(5);
//        assertThat(results.getNextPageToken()).isNull();
//        assertThat(results.getCount()).isEqualTo(5);
//    }

//    @Test
//    public void itShouldFindWorkflowsByOwner() throws Exception {
//        Client catalytic = new Client();
//        Where where = new Where();
//        WorkflowsPage results = catalytic.workflows().find(
//                where.owner().is("tcaflisch@catalytic.com")
//        );
//        assertThat(results.getWorkflows()).hasSize(6);
//        assertThat(results.getNextPageToken()).isNull();
//        assertThat(results.getCount()).isEqualTo(6);
//    }
//
//    @Test
//    public void itShouldFindWorkflowsByCategory() throws Exception {
//        Client catalytic = new Client();
//        Where where = new Where();
//        WorkflowsPage results = catalytic.workflows().find(
//                where.category().is("general")
//        );
//        assertThat(results.getWorkflows()).isNotEmpty();
//        assertThat(results.getNextPageToken()).isNotNull();
//        assertThat(results.getCount()).isGreaterThan(0);
//    }

//    @Test
//    public void itShouldExportAWorkflowFromStringNoPassword() throws Exception {
//        Client catalytic = new Client();
//        WorkflowExport workflowExport = catalytic.workflows().export("7e77254c-d2d6-4271-965a-98390aefa50a");
//        assertThat(workflowExport.getId()).isNotNull();
//        assertThat(workflowExport.getErrorMessage()).isNull();
//    }

//    @Test
//    public void itShouldExportAWorkflowFromUUIDNoPassword() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        WorkflowExport workflowExport = catalytic.workflows().export(UUID.fromString("7e77254c-d2d6-4271-965a-98390aefa50a"));
//        assertThat(workflowExport.getId()).isNotNull();
//        assertThat(workflowExport.getErrorMessage()).isNull();
//    }
//
//    @Test
//    public void itShouldExportAWorkflowUUIDWithPassword() throws Exception {
//        CatalyticClient catalytic = new CatalyticClient();
//        WorkflowExport workflowExport = catalytic.workflows().export(
//                UUID.fromString("7e77254c-d2d6-4271-965a-98390aefa50a"),
//                "my-super-secret-password"
//        );
//        assertThat(workflowExport.getId()).isNotNull();
//        assertThat(workflowExport.getErrorMessage()).isNull();
//    }

//    @Test
//    public void itShouldImportAWorkflowNoPassword() throws Exception {
//        Client catalytic = new Client();
//        File importFile = new File("/Users/tomcaflisch/Downloads/testing-php-sdk-export.catalytic");
//        Workflow workflow = catalytic.workflows().importWorkflow(importFile);
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
