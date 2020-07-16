package org.catalytic.sdk.clients;


import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.Field;
import org.catalytic.sdk.entities.File;
import org.catalytic.sdk.entities.Workflow;
import org.catalytic.sdk.entities.WorkflowsPage;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.WorkflowsApi;
import org.catalytic.sdk.generated.model.WorkflowExportRequest;
import org.catalytic.sdk.generated.model.WorkflowImport;
import org.catalytic.sdk.generated.model.WorkflowImportRequest;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Workflows client
 */
public class Workflows extends BaseClient {

    private String token;
    private static final Logger log = CatalyticLogger.getLogger(Workflows.class);
    private WorkflowsApi workflowsApi;
    private Files filesClient;

    public Workflows(String token) {
        this.token = token;
        ApiClient apiClient = ConfigurationUtils.getApiClient(token);
        this.workflowsApi = new WorkflowsApi(apiClient);
        this.filesClient = new Files(this.token);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in mocks for WorkflowsApi and Files
     *
     * @param token         The token to be used
     * @param workflowsApi  The mocked WorkflowsApi
     * @param filesClient   The mocked Files client
     */
    public Workflows(String token, WorkflowsApi workflowsApi, Files filesClient) {
        this.token = token;
        this.workflowsApi = workflowsApi;
        this.filesClient = filesClient;
    }

    /**
     * Get a workflow by id
     *
     * @param id                            The id of the workflow to get
     * @return                              The Workflow object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws WorkflowNotFoundException    If workflow not found
     * @throws InternalErrorException       If any error getting a workflow
     * @throws UnauthorizedException        If unauthorized
     */
    public Workflow get(String id) throws InternalErrorException, WorkflowNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.Workflow internalWorkflow;
        try {
            log.debug("Getting workflow with id {}", () -> id);
            internalWorkflow = this.workflowsApi.getWorkflow(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new WorkflowNotFoundException("Workflow with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get workflow", e);
        }
        Workflow workflow = createWorkflow(internalWorkflow);
        return workflow;
    }

    /**
     * Find all workflows
     *
     * @return                              A WorkflowsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage find() throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return this.find(null, null, null);
    }

    /**
     * Find all workflows
     *
     * @param pageToken                     The token of the page to fetch
     * @return                              A WorkflowsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage find(String pageToken) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return this.find(null, pageToken, null);
    }

    /**
     * Find workflows by a variety of filters
     *
     * @param filter                        The filter criteria to search workflows by
     * @return                              A WorkflowsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage find(Filter filter) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return this.find(filter, null, null);
    }

    /**
     * Find workflows by a variety of filters
     *
     * @param filter                        The filter criteria to search workflows by
     * @param pageToken                     The token of the page to fetch
     * @return                              A WorkflowsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage find(Filter filter, String pageToken) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return this.find(filter, pageToken, null);
    }

    /**
     * Find workflows by a variety of filters
     *
     * @param filter                        The filter criteria to search workflows by
     * @param pageToken                     The token of the page to fetch
     * @param pageSize                      The number of workflows per page to fetch
     * @return                              A WorkflowsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        String text = null;
        String owner = null;
        String category = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
            owner = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "owner");
            category = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "category");
        }

        org.catalytic.sdk.generated.model.WorkflowsPage results = null;
        List<Workflow> workflows = new ArrayList<>();

        try {
            log.debug("Finding workflows with text: {} owner: {} category: {}", text, owner, category);
            results = this.workflowsApi.findWorkflows(text, null, null, null, owner, category, null, null, null, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find workflows", e);
        }

        for (org.catalytic.sdk.generated.model.Workflow internalWorkflow : results.getWorkflows()) {
            Workflow workflow = createWorkflow(internalWorkflow);
            workflows.add(workflow);
        }

        WorkflowsPage workflowsPage = new WorkflowsPage(workflows, results.getCount(), results.getNextPageToken());
        return workflowsPage;
    }

    /**
     * Export a workflow by id
     *
     * @param id                            The id of the workflow to export
     * @return                              The exported workflow object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws WorkflowNotFoundException    If Workflow not found
     * @throws InternalErrorException       If any error exporting the workflow
     * @throws UnauthorizedException        If unauthorized
     */
    public File export(String id) throws InternalErrorException, WorkflowNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        return this.export(id, null);
    }

    /**
     * Export a workflow by id
     *
     * @param id                            The id of the workflow to export
     * @param password                      The password for the workflow
     * @return                              The exported workflow object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws WorkflowNotFoundException    If Workflow not found
     * @throws InternalErrorException       If any error exporting the workflow
     * @throws UnauthorizedException        If unauthorized
     */
    public File export(String id, String password) throws InternalErrorException, WorkflowNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        File file;
        WorkflowExportRequest workflowExportRequest = new WorkflowExportRequest();
        workflowExportRequest.setWorkflowId(UUID.fromString(id));
        workflowExportRequest.setPassword(password);
        org.catalytic.sdk.generated.model.WorkflowExport internalWorkflowExport;

        try {
            log.debug("Exporting workflow with id {}", () -> id);
            // Submit a request to export a workflow
            internalWorkflowExport = this.workflowsApi.exportWorkflow(id, workflowExportRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new WorkflowNotFoundException("Workflow with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to export workflow with id " + id, e);
        }

        // Poll another request every second until the export is ready
        while (internalWorkflowExport.getFileId() == null) {
            UUID exportId = internalWorkflowExport.getId();
            try {
                log.debug("Getting export workflow with id {}", () -> exportId.toString());
                internalWorkflowExport = this.workflowsApi.getWorkflowExport(exportId.toString());
            } catch (ApiException e) {
                throw new InternalErrorException("Unable to export workflow with id " + id, e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new InternalErrorException("Unexpected error exporting workflow with id " + id, e);
            }
        }

        try {
            log.debug("Getting file with id {}", internalWorkflowExport.getId().toString());
            file = this.filesClient.get(internalWorkflowExport.getId().toString());
        } catch (FileNotFoundException e) {
            throw new InternalErrorException("Unable to export workflow with id " + id, e);
        }

        return file;
    }

    /**
     * Import a .catalytic file as a workflow
     *
     * @param importFile                    The file to import
     * @return                              The imported Workflow object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws WorkflowNotFoundException    If Workflow not found
     * @throws InternalErrorException       If any errors importing the workflow
     * @throws UnauthorizedException        If unauthorized
     */
    public Workflow importWorkflow(java.io.File importFile) throws InternalErrorException, WorkflowNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        return this.importWorkflow(importFile, null);
    }

    /**
     * Import a .catalytic file as a workflow
     *
     * @param importFile                    The file to import
     * @param password                      The password for the workflow
     * @return                              The imported Workflow object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws WorkflowNotFoundException    If Workflow not found
     * @throws InternalErrorException       If any errors importing the workflow
     * @throws UnauthorizedException        If unauthorized
     */
    public Workflow importWorkflow(java.io.File importFile, String password) throws InternalErrorException, WorkflowNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        File file = this.filesClient.upload(importFile);

        WorkflowImportRequest workflowImportRequest = new WorkflowImportRequest();
        workflowImportRequest.setFileId(file.getId());
        workflowImportRequest.setPassword(password);

        WorkflowImport internalWorkflowImport;
        try {
            log.debug("Importing workflow");
            // Submit a request to import a workflow
            internalWorkflowImport = this.workflowsApi.importWorkflow(workflowImportRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to import workflow", e);
        }

        // Poll another request every second until the import has completed
        while (internalWorkflowImport.getWorkflowId() == null) {
            try {
                log.debug("Getting workflow import with id {}", internalWorkflowImport.getId().toString());
                internalWorkflowImport = this.workflowsApi.getWorkflowImport(internalWorkflowImport.getId().toString());
            } catch (ApiException e) {
                throw new InternalErrorException("Unable to import workflow", e);
            }
        }

        Workflow workflow = get(internalWorkflowImport.getWorkflowId().toString());
        return workflow;
    }

    /**
     * Create a Workflow object from an internal Workflow object
     *
     * @param internalWorkflow  The internal workflow to create a Workflow object from
     * @return                  The created Workflow object
     */
    private Workflow createWorkflow(org.catalytic.sdk.generated.model.Workflow internalWorkflow)
    {
        List<Field> newInputFields = createFields(internalWorkflow.getInputFields());

        Workflow workflow = new Workflow(
                internalWorkflow.getId(),
                internalWorkflow.getName(),
                internalWorkflow.getTeamName(),
                internalWorkflow.getDescription(),
                internalWorkflow.getCategory(),
                internalWorkflow.getOwner(),
                internalWorkflow.getCreatedBy(),
                newInputFields,
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
