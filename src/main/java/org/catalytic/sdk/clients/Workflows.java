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
import org.catalytic.sdk.search.WorkflowSearchClause;

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
     * Get all Workflows
     *
     * @return                              A WorkflowsPage object which contains the all the Workflows from all pages of results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage list() throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        // Get the first page of Workflows
        WorkflowsPage workflowsPage = search(null, null);
        WorkflowsPage results = workflowsPage;

        // Loop through the rest of the pages and add the workflows to results
        while(!workflowsPage.getNextPageToken().isEmpty()) {
            workflowsPage = search(null, workflowsPage.getNextPageToken());
            results.addWorkflows(workflowsPage.getWorkflows(), workflowsPage.getNextPageToken());
        }

        return results;
    }

    /**
     * Finds Workflows by a variety of filters
     *
     * @param workflowSearchClause          The search criteria to search Workflows by
     * @return                              A WorkflowsPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage search(WorkflowSearchClause workflowSearchClause) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(workflowSearchClause, null, null);
    }

    /**
     * Finds Workflows by a variety of filters
     *
     * @param workflowSearchClause          The search criteria to search Workflows by
     * @param pageToken                     The token of the page to fetch
     * @return                              A WorkflowsPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage search(WorkflowSearchClause workflowSearchClause, String pageToken) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(workflowSearchClause, pageToken, null);
    }

    /**
     * Finds Workflows by a variety of filters
     *
     * @param workflowSearchClause          The search criteria to search Workflows by
     * @param pageToken                     The token of the page to fetch
     * @param pageSize                      The number of Workflows to fetch per page
     * @return                              A WorkflowsPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public WorkflowsPage search(WorkflowSearchClause workflowSearchClause, String pageToken, Integer pageSize) throws AccessTokenNotFoundException, InternalErrorException, UnauthorizedException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.WorkflowsPage results;
        List<Workflow> workflows = new ArrayList<>();

        if (workflowSearchClause == null) {
            workflowSearchClause = new WorkflowSearchClause();
        }

        org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(workflowSearchClause.getId());
        org.catalytic.sdk.generated.model.StringSearchExpression internalName = createInternalStringSearchExpression(workflowSearchClause.getName());
        org.catalytic.sdk.generated.model.StringSearchExpression internalDescription = createInternalStringSearchExpression(workflowSearchClause.getDescription());
        org.catalytic.sdk.generated.model.ExactStringSearchExpression internalOwnerEmail = createInternalExactStringSearchExpression(workflowSearchClause.getOwnerEmail());
        org.catalytic.sdk.generated.model.StringSearchExpression internalCategory = createInternalStringSearchExpression(workflowSearchClause.getCategory());
        org.catalytic.sdk.generated.model.DateTimeSearchExpression internalCreatedDate = createInternalDateTimeSearchExpression(workflowSearchClause.getCreatedDate());

        List<org.catalytic.sdk.generated.model.WorkflowSearchClause> internalAnd = createInternalWorkflowSearchClause(workflowSearchClause.getAnd());
        List<org.catalytic.sdk.generated.model.WorkflowSearchClause> internalOr = createInternalWorkflowSearchClause(workflowSearchClause.getOr());

        org.catalytic.sdk.generated.model.WorkflowSearchClause workflowSearchRequest = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
        workflowSearchRequest.setAnd(internalAnd);
        workflowSearchRequest.setOr(internalOr);
        workflowSearchRequest.setId(internalId);
        workflowSearchRequest.setName(internalName);
        workflowSearchRequest.setDescription(internalDescription);
        workflowSearchRequest.setOwnerEmail(internalOwnerEmail);
        workflowSearchRequest.setCategory(internalCategory);
        workflowSearchRequest.setCreatedDate(internalCreatedDate);

        try {
            log.debug("Finding Workflows with criteria {}", () -> workflowSearchRequest);
            results = this.workflowsApi.searchWorkflows(pageToken, pageSize, workflowSearchRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find Workflows", e);
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
                internalWorkflow.getOwnerEmail(),
                internalWorkflow.getCreatedByEmail(),
                newInputFields,
                internalWorkflow.getIsPublished(),
                internalWorkflow.getIsArchived(),
                internalWorkflow.getFieldVisibility(),
                internalWorkflow.getInstanceVisibility(),
                internalWorkflow.getAdminUserEmails(),
                internalWorkflow.getStandardUserEmails(),
                internalWorkflow.getCreatedDate()
        );
        return workflow;
    }

    /**
     * Create an internal WorkflowSearchClause from an external WorkflowSearchClause
     *
     * @param workflowSearchClause  The external WorkflowSearchClause to create an internal one from
     * @return                      An internal WorkflowSearchClause
     */
    private List<org.catalytic.sdk.generated.model.WorkflowSearchClause> createInternalWorkflowSearchClause(List<WorkflowSearchClause> workflowSearchClause) {
        List<org.catalytic.sdk.generated.model.WorkflowSearchClause> internalWorkflowSearchClauses = null;

        if (workflowSearchClause != null) {

            internalWorkflowSearchClauses = new ArrayList<>();

            for (WorkflowSearchClause searchClause : workflowSearchClause) {
                org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(searchClause.getId());
                org.catalytic.sdk.generated.model.StringSearchExpression internalName = createInternalStringSearchExpression(searchClause.getName());
                org.catalytic.sdk.generated.model.StringSearchExpression internalDescription = createInternalStringSearchExpression(searchClause.getDescription());
                org.catalytic.sdk.generated.model.ExactStringSearchExpression internalOwnerEmail = createInternalExactStringSearchExpression(searchClause.getOwnerEmail());
                org.catalytic.sdk.generated.model.StringSearchExpression internalCategory = createInternalStringSearchExpression(searchClause.getCategory());
                org.catalytic.sdk.generated.model.DateTimeSearchExpression internalCreatedDate = createInternalDateTimeSearchExpression(searchClause.getCreatedDate());

                org.catalytic.sdk.generated.model.WorkflowSearchClause internalWorkflowSearchClause = new org.catalytic.sdk.generated.model.WorkflowSearchClause();
                internalWorkflowSearchClause.setId(internalId);
                internalWorkflowSearchClause.setName(internalName);
                internalWorkflowSearchClause.setDescription(internalDescription);
                internalWorkflowSearchClause.setOwnerEmail(internalOwnerEmail);
                internalWorkflowSearchClause.setCategory(internalCategory);
                internalWorkflowSearchClause.setCreatedDate(internalCreatedDate);
                List<org.catalytic.sdk.generated.model.WorkflowSearchClause> internalAnd = createInternalWorkflowSearchClause(searchClause.getAnd());
                List<org.catalytic.sdk.generated.model.WorkflowSearchClause> internalOr = createInternalWorkflowSearchClause(searchClause.getOr());
                internalWorkflowSearchClause.setAnd(internalAnd);
                internalWorkflowSearchClause.setOr(internalOr);
                internalWorkflowSearchClauses.add(internalWorkflowSearchClause);
            }
        }
        return internalWorkflowSearchClauses;
    }
}
