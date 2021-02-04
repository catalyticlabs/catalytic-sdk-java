package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.*;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.InstanceStepsApi;
import org.catalytic.sdk.generated.api.InstancesApi;
import org.catalytic.sdk.generated.model.CompleteStepRequest;
import org.catalytic.sdk.generated.model.FieldUpdateRequest;
import org.catalytic.sdk.generated.model.StartInstanceRequest;
import org.catalytic.sdk.search.InstanceSearchClause;
import org.catalytic.sdk.search.InstanceStatusSearchExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Instances client
 */
public class Instances extends BaseClient {

    private String token;
    private static final Logger log = CatalyticLogger.getLogger(Instances.class);
    private InstancesApi instancesApi;
    private InstanceStepsApi instanceStepsApi;

    public Instances(String token) {
        this.token = token;
        ApiClient apiClient = ConfigurationUtils.getApiClient(this.token);
        this.instancesApi = new InstancesApi(apiClient);
        this.instanceStepsApi = new InstanceStepsApi(apiClient);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in mocks for InstancesApi and InstanceStepsApi
     *
     * @param token             The token to be used
     * @param instancesApi      The mocked InstancesApi
     * @param instanceStepsApi  The mocked InstanceStepsApi
     */
    public Instances(String token, InstancesApi instancesApi, InstanceStepsApi instanceStepsApi) {
        this.token = token;
        this.instancesApi = instancesApi;
        this.instanceStepsApi = instanceStepsApi;
    }

    /**
     * Get a workflow instance by id
     *
     * @param id                            The id of the workflow instance to get
     * @return                              The Instance object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error getting workflow
     * @throws InstanceNotFoundException    If instance with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public Instance get(String id) throws AccessTokenNotFoundException, UnauthorizedException, InstanceNotFoundException, InternalErrorException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.Instance internalInstance = null;
        try {
            log.debug("Getting instance with id {}", () -> id);
            internalInstance = this.instancesApi.getInstance(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new InstanceNotFoundException("Instance with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get instance", e);
        }
        Instance instance = createInstance(internalInstance);
        return instance;
    }

    /**
     * Get all Instances
     *
     * @return                              An InstancesPage object which contains the all the Instances from all pages of results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public InstancesPage list() throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        // Get the first page of Instances
        InstancesPage instancesPage = search(null, null);
        InstancesPage results = instancesPage;

        // Loop through the rest of the pages and add the Instances to results
        while(!instancesPage.getNextPageToken().isEmpty()) {
            instancesPage = search(null, instancesPage.getNextPageToken());
            results.addInstances(instancesPage.getInstances(), instancesPage.getNextPageToken());
        }

        return results;
    }

    /**
     * Finds Instances by a variety of filters
     *
     * @param instanceSearchClause         The search criteria to search Instances by
     * @return                              An InstancesPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public InstancesPage search(InstanceSearchClause instanceSearchClause) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(instanceSearchClause, null, null);
    }

    /**
     * Finds Instances by a variety of filters
     *
     * @param instanceSearchClause          The search criteria to search Instances by
     * @param pageToken                     The token of the page to fetch
     * @return                              An InstancesPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public InstancesPage search(InstanceSearchClause instanceSearchClause, String pageToken) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(instanceSearchClause, pageToken, null);
    }

    /**
     * Finds Instances by a variety of filters
     *
     * @param instanceSearchClause          The search criteria to search Instances by
     * @param pageToken                     The token of the page to fetch
     * @param pageSize                      The number of Workflows to fetch per page
     * @return                              A WorkflowsPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public InstancesPage search(InstanceSearchClause instanceSearchClause, String pageToken, Integer pageSize) throws AccessTokenNotFoundException, InternalErrorException, UnauthorizedException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.InstancesPage results;
        List<Instance> instances = new ArrayList<>();

        if (instanceSearchClause == null) {
            instanceSearchClause = new InstanceSearchClause();
        }

        org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(instanceSearchClause.getId());
        org.catalytic.sdk.generated.model.GuidSearchExpression workflowId = createInternalGuidSearchExpression(instanceSearchClause.getWorkflowId());
        org.catalytic.sdk.generated.model.GuidSearchExpression rootInstanceId = createInternalGuidSearchExpression(instanceSearchClause.getRootInstanceId());
        org.catalytic.sdk.generated.model.ExactStringSearchExpression internalOwnerEmail = createInternalExactStringSearchExpression(instanceSearchClause.getOwnerEmail());
        org.catalytic.sdk.generated.model.BoolSearchExpression internalIsTest = createInternalBooleanSearchExpression(instanceSearchClause.getIsTest());
        org.catalytic.sdk.generated.model.StringSearchExpression internalName = createInternalStringSearchExpression(instanceSearchClause.getName());
        org.catalytic.sdk.generated.model.InstanceStatusSearchExpression internalStatus = createInternalInstanceStatusSearchExpression(instanceSearchClause.getStatus());
        org.catalytic.sdk.generated.model.StringSearchExpression internalDescription = createInternalStringSearchExpression(instanceSearchClause.getDescription());
        org.catalytic.sdk.generated.model.StringSearchExpression internalCategory = createInternalStringSearchExpression(instanceSearchClause.getCategory());
        org.catalytic.sdk.generated.model.DateTimeSearchExpression internalStartDate = createInternalDateTimeSearchExpression(instanceSearchClause.getStartDate());
        org.catalytic.sdk.generated.model.DateTimeSearchExpression internalEndDate = createInternalDateTimeSearchExpression(instanceSearchClause.getEndDate());

        List<org.catalytic.sdk.generated.model.InstanceSearchClause> internalAnd = createInternalInstanceSearchClause(instanceSearchClause.getAnd());
        List<org.catalytic.sdk.generated.model.InstanceSearchClause> internalOr = createInternalInstanceSearchClause(instanceSearchClause.getOr());

        org.catalytic.sdk.generated.model.InstanceSearchClause instanceSearchRequest = new org.catalytic.sdk.generated.model.InstanceSearchClause();
        instanceSearchRequest.setAnd(internalAnd);
        instanceSearchRequest.setOr(internalOr);
        instanceSearchRequest.setId(internalId);
        instanceSearchRequest.setWorkflowId(workflowId);
        instanceSearchRequest.setRootInstanceId(rootInstanceId);
        instanceSearchRequest.setOwnerEmail(internalOwnerEmail);
        instanceSearchRequest.setIsTest(internalIsTest);
        instanceSearchRequest.setName(internalName);
        instanceSearchRequest.setStatus(internalStatus);
        instanceSearchRequest.setDescription(internalDescription);
        instanceSearchRequest.setCategory(internalCategory);
        instanceSearchRequest.setStartDate(internalStartDate);
        instanceSearchRequest.setEndDate(internalEndDate);

        try {
            log.debug("Finding Instances with criteria {}", () -> instanceSearchRequest);
            results = this.instancesApi.searchInstances(pageToken, pageSize, instanceSearchRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find Instances", e);
        }

        for (org.catalytic.sdk.generated.model.Instance internalInstance : results.getInstances()) {
            Instance instance = createInstance(internalInstance);
            instances.add(instance);
        }

        InstancesPage instancesPage = new InstancesPage(instances, results.getCount(), results.getNextPageToken());
        return instancesPage;
    }

    /**
     * Start an instance of a workflow for a given workflow id
     *
     * @param workflowId                    The id of the workflow to start an instance
     * @return                              The newly created instance
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors starting an instance
     * @throws WorkflowNotFoundException    If workflow with workflowId does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public Instance start(String workflowId) throws InternalErrorException, WorkflowNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        return this.start(workflowId, null, null, null);
    }

    /**
     * Start an instance of a workflow for a given workflow id
     *
     * @param workflowId                    The id of the workflow to start an instance
     * @param name                          The name to give to the instance
     * @param description                   The description to give to the instance
     * @param fields                        The input fields to use when starting this instance
     * @return                              The newly created instance
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors starting an instance
     * @throws WorkflowNotFoundException    If workflow with workflowId does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public Instance start(String workflowId, String name, String description, List<Field> fields) throws InternalErrorException, WorkflowNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        StartInstanceRequest startInstanceRequest = createStartInstanceRequest(UUID.fromString(workflowId), name, description, fields);
        org.catalytic.sdk.generated.model.Instance internalInstance = null;
        try {
            log.debug("Starting instance with workflowId {}", () -> workflowId);
            internalInstance = this.instancesApi.startInstance(startInstanceRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new WorkflowNotFoundException("Workflow with id " + workflowId + " not found");
            }
            throw new InternalErrorException("Unable to start workflow instance", e);
        }
        Instance instance = createInstance(internalInstance);
        return instance;
    }

    /**
     * Stops an instance by instance id
     *
     * @param id                            The id of the instance to stop
     * @return                              The Instance that was stopped
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors stopping the instance
     * @throws InstanceNotFoundException    If instance with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public Instance stop(String id) throws InstanceNotFoundException, InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.Instance internalInstance;
        try {
            log.debug("Stopping instance with id {}", () -> id);
            internalInstance = this.instancesApi.stopInstance(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new InstanceNotFoundException("Instance with id " + id + " not found");
            }
            throw new InternalErrorException("Unable to stop workflow instance with id " + id, e);
        }
        Instance instance = createInstance(internalInstance);
        return instance;
    }

    /**
     * Gets a step by step id
     *
     * @param id                                The id of the step to get
     * @return                                  The InstanceStep object
     * @throws AccessTokenNotFoundException     If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException           If any errors getting the step
     * @throws InstanceStepNotFoundException    If instance with id does not exist
     * @throws UnauthorizedException            If unauthorized
     */
    public InstanceStep getStep(String id) throws InternalErrorException, InstanceStepNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.InstanceStep internalStep;
        try {
            log.debug("Getting step with id {}", () -> id);
            internalStep = getStepById(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new InstanceStepNotFoundException("Instance step with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get step with id " + id, e);
        }
        InstanceStep step = createInstanceStep(internalStep);
        return step;
    }

//    /**
//     * Gets all the steps for a specific instance id
//     *
//     * @param instanceId                    The id of the instances to get steps for
//     * @return                              The InstanceStepsPage which contains the results
//     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
//     * @throws InternalErrorException       If any errors getting steps
//     * @throws UnauthorizedException        If unauthorized
//     */
//    public InstanceStepsPage getSteps(String instanceId) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
//        ClientHelpers.verifyAccessTokenExists(this.token);
//
//        org.catalytic.sdk.generated.model.InstanceStepsPage internalInstanceSteps;
//        try {
//            log.debug("Getting all the steps for instance {}", () -> instanceId);
//            internalInstanceSteps = this.instanceStepsApi.findInstanceSteps(instanceId, null, null, null, null, null, null, null, null, null, null, null, null, null);
//        } catch (ApiException e) {
//            if (e.getCode() == 401) {
//                throw new UnauthorizedException(e);
//            }
//            throw new InternalErrorException("Unable to get steps for instance " + instanceId, e);
//        }
//
//        List<InstanceStep> instanceSteps = new ArrayList<>();
//
//        for (org.catalytic.sdk.generated.model.InstanceStep internalInstanceStep : internalInstanceSteps.getSteps()) {
//            InstanceStep instanceStep = createInstanceStep(internalInstanceStep);
//            instanceSteps.add(instanceStep);
//        }
//
//        InstanceStepsPage instanceStepsPage = new InstanceStepsPage(instanceSteps, internalInstanceSteps.getCount(), internalInstanceSteps.getNextPageToken());
//        return instanceStepsPage;
//    }

    /**
     * Completes a specific step
     *
     * @param id                            The id of the step to complete
     * @param fields                        Fields and the values to use when completing a step
     * @return                              The completed InstanceStep
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any errors completing the step
     * @throws UnauthorizedException        If unauthorized
     */
    public InstanceStep completeStep(String id, List<Field> fields) throws InternalErrorException, UnauthorizedException, InstanceStepNotFoundException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        List<FieldUpdateRequest> fieldUpdateRequests = createFieldUpdateRequests(fields);
        CompleteStepRequest completeStepRequest = new CompleteStepRequest();
        completeStepRequest.setId(UUID.fromString(id));
        completeStepRequest.setStepOutputFields(fieldUpdateRequests);
        org.catalytic.sdk.generated.model.InstanceStep internalStep;
        try {
            log.debug("Completing step with id {}", () -> id);
            org.catalytic.sdk.generated.model.InstanceStep step = getStepById(id);
            internalStep = this.instanceStepsApi.completeStep(id, step.getInstanceId().toString(), completeStepRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new InstanceStepNotFoundException("Step with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to complete step with id " + id, e);
        }

        InstanceStep step = createInstanceStep(internalStep);
        return step;
    }

    /**
     * Creates a StartInstanceRequest object with the passed in params
     *
     * @param workflowId    The id to create the CompleteStepRequest with
     * @param name          The name to create the CompleteStepRequest with
     * @param description   The description to create the CompleteStepRequest with
     * @param fields        The fields to create the CompleteStepRequest with
     * @return              The created StartInstanceRequest object
     */
    private StartInstanceRequest createStartInstanceRequest(UUID workflowId, String name, String description, List<Field> fields) {
        StartInstanceRequest startInstanceRequest = new StartInstanceRequest();
        List<FieldUpdateRequest> inputFields = createFieldUpdateRequests(fields);
        startInstanceRequest.setWorkflowId(workflowId);
        startInstanceRequest.setName(name);
        startInstanceRequest.setDescription(description);
        if (fields != null) {
            startInstanceRequest.setInputFields(inputFields);
        }
        return startInstanceRequest;
    }

    /**
     * Get an instance step by its id
     *
     * @param id            The id of the step to get
     * @return              The InstanceStep object
     * @throws ApiException If any errors getting the instance step
     */
    private org.catalytic.sdk.generated.model.InstanceStep getStepById(String id) throws ApiException {
        // The REST api supports wildcard instance id when searching for instance steps
        // https://cloud.google.com/apis/design/design_patterns#list_sub-collections
        String wildcardInstanceId = "-";
        org.catalytic.sdk.generated.model.InstanceStep step = this.instanceStepsApi.getInstanceStep(id, wildcardInstanceId);
        return step;
    }

    /**
     * Create an Instance object from an internal Instance object
     *
     * @param internalInstance  The internal instance to create an Instance object from
     * @return                  The created Instance object
     */
    private Instance createInstance(org.catalytic.sdk.generated.model.Instance internalInstance)
    {
        List<Field> fields = createFields(internalInstance.getFields());
        InstanceStatus status = InstanceStatus.valueOf(internalInstance.getStatus().name());
        FieldVisibility fieldVisibility = FieldVisibility.valueOf(internalInstance.getFieldVisibility().name());
        InstanceVisibility visibility = InstanceVisibility.valueOf(internalInstance.getVisibility().name());

        // Create an external Instance
        Instance instance = new Instance(
                internalInstance.getId(),
                internalInstance.getWorkflowId(),
                internalInstance.getRootInstanceId(),
                internalInstance.getName(),
                internalInstance.getTeamName(),
                internalInstance.getDescription(),
                internalInstance.getCategory(),
                internalInstance.getIsTest(),
                internalInstance.getOwnerEmail(),
                internalInstance.getCreatedByEmail(),
                fields,
                status,
                internalInstance.getStartDate(),
                internalInstance.getEndDate(),
                fieldVisibility,
                visibility,
                internalInstance.getVisibleToUserEmails()
        );

        return instance;
    }

    /**
     * Create an InstanceStep object from an internal InstanceStep object
     *
     * @param internalInstanceStep  The internal instance step to create an InstanceStep object from
     * @return                      The created InstanceStep object
     */
    private InstanceStep createInstanceStep(org.catalytic.sdk.generated.model.InstanceStep internalInstanceStep) {
        List<Field> outputFields = new ArrayList<>();
        String status = null;

        if (internalInstanceStep.getOutputFields() != null) {
            // Create external outputFields from internal outputFields
            outputFields = createFields(internalInstanceStep.getOutputFields());
        }

        if (internalInstanceStep.getStatus() != null) {
            status = internalInstanceStep.getStatus().getValue();
        }

        InstanceStep instanceStep = new InstanceStep(
            internalInstanceStep.getId(),
            internalInstanceStep.getInstanceId(),
            internalInstanceStep.getWorkflowId(),
            internalInstanceStep.getName(),
            internalInstanceStep.getTeamName(),
            internalInstanceStep.getPosition(),
            internalInstanceStep.getDescription(),
            status,
            internalInstanceStep.getAssignedToEmail(),
            internalInstanceStep.getStartDate(),
            internalInstanceStep.getEndDate(),
            outputFields
        );
        return instanceStep;
    }

    /**
     * Create external InstanceStep from internal InstanceSteps
     *
     * @param internalSteps The internal steps to create external steps from
     * @return              External steps
     */
    private List<InstanceStep> createInstanceSteps(List<org.catalytic.sdk.generated.model.InstanceStep> internalSteps) {
        List<InstanceStep> steps = new ArrayList<>();

        // Create external steps from internal steps
        if (internalSteps != null) {
            for (org.catalytic.sdk.generated.model.InstanceStep internalStep : internalSteps) {
                InstanceStep step = createInstanceStep(internalStep);
                steps.add(step);
            }
        }

        return steps;
    }

    /**
     * Create an internal InstanceStatusSearchExpression from an external InstanceStatusSearchExpression
     *
     * @param instanceStatusSearchExpression    The external InstanceStatusSearchExpression to create an internal one from
     * @return                                  An internal InstanceStatusSearchExpression
     */
    org.catalytic.sdk.generated.model.InstanceStatusSearchExpression createInternalInstanceStatusSearchExpression(InstanceStatusSearchExpression instanceStatusSearchExpression) {
        org.catalytic.sdk.generated.model.InstanceStatusSearchExpression internalInstanceStatusSearchExpression = null;

        if (instanceStatusSearchExpression != null) {
            org.catalytic.sdk.generated.model.InstanceStatus status = org.catalytic.sdk.generated.model.InstanceStatus.fromValue(instanceStatusSearchExpression.getIsEqualTo().getValue());
            internalInstanceStatusSearchExpression = new org.catalytic.sdk.generated.model.InstanceStatusSearchExpression();
            internalInstanceStatusSearchExpression.setIsEqualTo(status);
        }
        return internalInstanceStatusSearchExpression;
    }

    /**
     * Create an internal WorkflowSearchClause from an external WorkflowSearchClause
     *
     * @param instanceSearchClause  The external InstanceSearchClause to create an internal one from
     * @return                      An internal InstanceSearchClause
     */
    private List<org.catalytic.sdk.generated.model.InstanceSearchClause> createInternalInstanceSearchClause(List<InstanceSearchClause> instanceSearchClause) {
        List<org.catalytic.sdk.generated.model.InstanceSearchClause> internalInstanceSearchClauses = null;

        if (instanceSearchClause != null) {

            internalInstanceSearchClauses = new ArrayList<>();

            for (InstanceSearchClause searchClause : instanceSearchClause) {
                org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(searchClause.getId());
                org.catalytic.sdk.generated.model.GuidSearchExpression internalWorkflowId = createInternalGuidSearchExpression(searchClause.getWorkflowId());
                org.catalytic.sdk.generated.model.GuidSearchExpression internalRootInstanceId = createInternalGuidSearchExpression(searchClause.getRootInstanceId());
                org.catalytic.sdk.generated.model.ExactStringSearchExpression internalOwnerEmail = createInternalExactStringSearchExpression(searchClause.getOwnerEmail());
                org.catalytic.sdk.generated.model.BoolSearchExpression internalIsTest = createInternalBooleanSearchExpression(searchClause.getIsTest());
                org.catalytic.sdk.generated.model.StringSearchExpression internalName = createInternalStringSearchExpression(searchClause.getName());
                org.catalytic.sdk.generated.model.InstanceStatusSearchExpression internalStatus = createInternalInstanceStatusSearchExpression(searchClause.getStatus());
                org.catalytic.sdk.generated.model.StringSearchExpression internalDescription = createInternalStringSearchExpression(searchClause.getDescription());
                org.catalytic.sdk.generated.model.StringSearchExpression internalCategory = createInternalStringSearchExpression(searchClause.getCategory());
                org.catalytic.sdk.generated.model.DateTimeSearchExpression internalStartDate = createInternalDateTimeSearchExpression(searchClause.getStartDate());
                org.catalytic.sdk.generated.model.DateTimeSearchExpression internalEndDate = createInternalDateTimeSearchExpression(searchClause.getEndDate());

                org.catalytic.sdk.generated.model.InstanceSearchClause internalInstanceSearchClause = new org.catalytic.sdk.generated.model.InstanceSearchClause();
                internalInstanceSearchClause.setId(internalId);
                internalInstanceSearchClause.setWorkflowId(internalWorkflowId);
                internalInstanceSearchClause.setRootInstanceId(internalRootInstanceId);
                internalInstanceSearchClause.setOwnerEmail(internalOwnerEmail);
                internalInstanceSearchClause.setIsTest(internalIsTest);
                internalInstanceSearchClause.setName(internalName);
                internalInstanceSearchClause.setStatus(internalStatus);
                internalInstanceSearchClause.setDescription(internalDescription);
                internalInstanceSearchClause.setCategory(internalCategory);
                internalInstanceSearchClause.setStartDate(internalStartDate);
                internalInstanceSearchClause.setEndDate(internalEndDate);
                List<org.catalytic.sdk.generated.model.InstanceSearchClause> internalAnd = createInternalInstanceSearchClause(searchClause.getAnd());
                List<org.catalytic.sdk.generated.model.InstanceSearchClause> internalOr = createInternalInstanceSearchClause(searchClause.getOr());
                internalInstanceSearchClause.setAnd(internalAnd);
                internalInstanceSearchClause.setOr(internalOr);
                internalInstanceSearchClauses.add(internalInstanceSearchClause);
            }
        }
        return internalInstanceSearchClauses;
    }
}
