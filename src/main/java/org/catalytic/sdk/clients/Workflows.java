package org.catalytic.sdk.clients;

import org.catalytic.sdk.ApiClient;
import org.catalytic.sdk.ApiException;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.api.WorkflowsApi;
import org.catalytic.sdk.entities.Workflow;
import org.catalytic.sdk.entities.WorkflowExport;
import org.catalytic.sdk.entities.WorkflowsPage;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.model.WorkflowExportRequest;
import org.catalytic.sdk.model.WorkflowImport;
import org.catalytic.sdk.model.WorkflowImportRequest;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Workflows client
 */
public class Workflows {

    private WorkflowsApi workflowsApi;

    public Workflows(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.workflowsApi = new WorkflowsApi(apiClient);
    }

    /**
     * Get a workflow by id
     *
     * @param id                        The id of the workflow to get
     * @return                          The Workflow object
     * @throws InternalErrorException   If any error getting a workflow
     */
    public Workflow get(String id) throws InternalErrorException {
        org.catalytic.sdk.model.Workflow internalWorkflow = null;
        try {
            internalWorkflow = this.workflowsApi.getWorkflow(id);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                throw new InternalErrorException("Workflow with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get workflow", e);
        }
        Workflow workflow = createWorkflow(internalWorkflow);
        return workflow;
    }

    /**
     * Find all workflows
     *
     * @return                          A WorkflowsPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public WorkflowsPage find() throws InternalErrorException {
        return this.find(null, null, null);
    }

    /**
     * Find all workflows
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          A WorkflowsPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public WorkflowsPage find(String pageToken) throws InternalErrorException {
        return this.find(null, pageToken, null);
    }

    /**
     * Find workflows by a variety of filters
     *
     * @param filter                    The filter criteria to search workflows by
     * @return                          A WorkflowsPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public WorkflowsPage find(Filter filter) throws InternalErrorException {
        return this.find(filter, null, null);
    }

    /**
     * Find workflows by a variety of filters
     *
     * @param filter                    The filter criteria to search workflows by
     * @param pageToken                 The token of the page to fetch
     * @return                          A WorkflowsPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public WorkflowsPage find(Filter filter, String pageToken) throws InternalErrorException {
        return this.find(filter, pageToken, null);
    }

    /**
     * Find workflows by a variety of filters
     *
     * @param filter                    The filter criteria to search workflows by
     * @param pageToken                 The token of the page to fetch
     * @param pageSize                  The number of workflows per page to fetch
     * @return                          A WorkflowsPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public WorkflowsPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException {
        String text = null;
        String owner = null;
        String category = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
            owner = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "owner");
            category = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "category");
        }

        org.catalytic.sdk.model.WorkflowsPage results = null;
        List<Workflow> workflows = new ArrayList<>();

        try {
            results = this.workflowsApi.findWorkflows(text, null, null, null, owner, category, null, pageToken, pageSize);
        } catch (ApiException e) {
            throw new InternalErrorException("Unable to find workflows", e);
        }

        for (org.catalytic.sdk.model.Workflow internalWorkflow : results.getWorkflows()) {
            Workflow workflow = createWorkflow(internalWorkflow);
            workflows.add(workflow);
        }

        WorkflowsPage workflowsPage = new WorkflowsPage(workflows, results.getCount(), results.getNextPageToken());
        return workflowsPage;
    }

    /**
     * Export a workflow by id
     *
     * @param id                        The id of the workflow to export
     * @return                          The exported workflow object
     * @throws InternalErrorException   If any error exporting the workflow
     */
    public WorkflowExport export(String id) throws InternalErrorException {
        return this.export(UUID.fromString(id), null);
    }

    /**
     * Export a workflow by id
     *
     * @param id                        The id of the workflow to export
     * @return                          The exported workflow object
     * @throws InternalErrorException   If any error exporting the workflow
     */
    public WorkflowExport export(UUID id) throws InternalErrorException {
        return this.export(id, null);
    }

    /**
     * Export a workflow by id
     *
     * @param id                        The id of the workflow to export
     * @param password                  The password for the workflow
     * @return                          The exported workflow object
     * @throws InternalErrorException   If any error exporting the workflow
     */
    public WorkflowExport export(String id, String password) throws InternalErrorException {
        return this.export(UUID.fromString(id), password);
    }

    /**
     * Export a workflow by id
     *
     * @param id                        The id of the workflow to export
     * @param password                  The password for the workflow
     * @return                          The exported workflow object
     * @throws InternalErrorException   If any error exporting the workflow
     */
    public WorkflowExport export(UUID id, String password) throws InternalErrorException {

        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(id);
        workflowExportRequest.setPassword(password);
        org.catalytic.sdk.model.WorkflowExport internalWorkflowExport;
        try {
            // Submit a request to export a workflow
            internalWorkflowExport = this.workflowsApi.exportWorkflow(id.toString(), workflowExportRequest);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                throw new InternalErrorException("Workflow with id " + id.toString() + " not found", e);
            }
            throw new InternalErrorException("Unable to export workflow with id " + id.toString(), e);
        }

        // Poll another request every second until the export is ready
        while (internalWorkflowExport.getFileId() == null) {
            UUID exportId = internalWorkflowExport.getId();
            internalWorkflowExport = this.workflowsApi.getWorkflowExport(exportId.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new InternalErrorException("Unexpected error exporting workflow with id " + id.toString(), e);
            }
        }

        WorkflowExport workflowExport = new WorkflowExport(
                internalWorkflowExport.getId(),
                internalWorkflowExport.getName(),
                internalWorkflowExport.getFileId(),
                internalWorkflowExport.getErrorMessage()
        );
        return workflowExport;
    }

    /**
     * Import a .catalytic file as a workflow
     *
     * @param importFile                The file to import
     * @return                          The imported Workflow object
     * @throws InternalErrorException   If any errors importing the workflow
     */
    public Workflow importWorkflow(File importFile) throws InternalErrorException {
        return this.importWorkflow(importFile, null);
    }

    /**
     * Import a .catalytic file as a workflow
     *
     * @param importFile                    The file to import
     * @param password                      The password for the workflow
     * @return                              The imported Workflow object
     * @throws InternalErrorException    If any errors importing the workflow
     */
    public Workflow importWorkflow(File importFile, String password) throws InternalErrorException {
        // TODO: First need to upload the file. Similar to https://github.com/catalyticlabs/CatalyticSDKAPI/blob/master/CatalyticSDKClient/src/Clients/WorkflowClient.cs#L71
        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
//        workflowImportRequest.setFileId(importFile);
        workflowImportRequest.setPassword(password);

        WorkflowImport internalWorkflowImport;
        try {
            // Submit a request to import a workflow
            internalWorkflowImport = this.workflowsApi.importWorkflow(workflowImportRequest);
        } catch (ApiException e) {
            throw new InternalErrorException("Unable to import workflow", e);
        }

        // Poll another request every second until the import has completed
        while (internalWorkflowImport.getWorkflowId() == null) {
            internalWorkflowImport = this.workflowsApi.getWorkflowImport(internalWorkflowImport.getId().toString());
        }

        Workflow workflow = get(internalWorkflowImport.getWorkflowId().toString());
        return workflow;
    }

    /**
     * Create a Workflow object from an internal Workflow object
     *
     * @param internalWorkflow   The internal workflow to create a Workflow object from
     * @return                  The created Workflow object
     */
    private Workflow createWorkflow(org.catalytic.sdk.model.Workflow internalWorkflow)
    {
        Workflow workflow = new Workflow(
                internalWorkflow.getId(),
                internalWorkflow.getName(),
                internalWorkflow.getTeamName(),
                internalWorkflow.getDescription(),
                internalWorkflow.getCategory(),
                internalWorkflow.getOwner(),
                internalWorkflow.getCreatedBy(),
                internalWorkflow.getInputFields(),
                internalWorkflow.getIsPublished(),
                internalWorkflow.getIsArchived(),
                internalWorkflow.getFieldVisibility(),
                internalWorkflow.getInstanceVisibility(),
                internalWorkflow.getAdminUsers(),
                internalWorkflow.getStandardUsers()
        );
        return workflow;
    }
}
