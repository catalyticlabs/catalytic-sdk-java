package org.catalytic.sdk.clients;

import org.catalytic.sdk.ApiClient;
import org.catalytic.sdk.ApiException;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.api.InstanceStepsApi;
import org.catalytic.sdk.api.InstancesApi;
import org.catalytic.sdk.entities.*;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.model.CompleteStepRequest;
import org.catalytic.sdk.model.FieldUpdateRequest;
import org.catalytic.sdk.model.StartInstanceRequest;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Instances client
 */
public class Instances {

    private InstancesApi instancesApi;
    private InstanceStepsApi instanceStepsApi;

    public Instances(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.instancesApi = new InstancesApi(apiClient);
        this.instanceStepsApi = new InstanceStepsApi(apiClient);
    }

    /**
     * Get a workflow instance by id
     *
     * @param id                            The id of the workflow instance to get
     * @return                              The Instance object
     * @throws InternalErrorException       If any error getting workflow
     * @throws InstanceNotFoundException    If instance with id does not exist
     */
    public Instance get(String id) throws Exception {
        org.catalytic.sdk.model.Instance internalInstance = null;
        try {
            internalInstance = this.instancesApi.getInstance(id);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                throw new InstanceNotFoundException("Instance with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get instance", e);
        }
        Instance instance = createInstance(internalInstance);
        return instance;
    }

    /**
     * Find all instances
     *
     * @return                          An InstancesPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public InstancesPage find() throws InternalErrorException {
        return this.find(null, null, null);
    }

    /**
     * Find all instances
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          An InstancesPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public InstancesPage find(String pageToken) throws InternalErrorException {
        return this.find(null, pageToken, null);
    }

    /**
     * Find instances by a variety of criteria
     *
     * @param filter                    The filter criteria to search instances by
     * @return                          An InstancesPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public InstancesPage find(Filter filter) throws InternalErrorException {
        return this.find(filter, null, null);
    }

    /**
     * Find instances by a variety of criteria
     *
     * @param filter                    The filter criteria to search instances by
     * @param pageToken                 The token of the page to fetch
     * @return                          An InstancesPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public InstancesPage find(Filter filter, String pageToken) throws InternalErrorException {
        return this.find(filter, pageToken, null);
    }

    /**
     * Find instances by a variety of criteria
     *
     * @param filter                    The filter criteria to search instances by
     * @param pageToken                 The token of the page to fetch
     * @param pageSize                  The number of workflows per page to fetch
     * @return                          An InstancesPage which contains the results
     * @throws InternalErrorException   If any error finding workflows
     */
    public InstancesPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException {
        String text = null;
        String owner = null;
        String status = null;
        String workflowId = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
            owner = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "owner");
            status = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "status");
            workflowId = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "workflowId");
        }

        org.catalytic.sdk.model.InstancesPage internalInstances = null;
        try {
            internalInstances = this.instancesApi.findInstances(text, status, workflowId, null, owner, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            throw new InternalErrorException("Unable to find instances", e);
        }
        List<Instance> instances = new ArrayList<>();

        for (org.catalytic.sdk.model.Instance internalInstance : internalInstances.getInstances()) {
            Instance instance = createInstance(internalInstance);
            instances.add(instance);
        }

        InstancesPage instancesPage = new InstancesPage(instances, internalInstances.getCount(), internalInstances.getNextPageToken());
        return instancesPage;
    }

    /**
     * Start an instance of a workflow for a given workflow id
     *
     * @param workflowId                    The id of the workflow to start an instance
     * @return                              The newly created instance
     * @throws InternalErrorException       If any errors starting an instance
     * @throws WorkflowNotFoundException    If workflow with workflowId does not exist
     */
    public Instance start(String workflowId) throws InternalErrorException, WorkflowNotFoundException {
        return this.start(UUID.fromString(workflowId), null, null, null);
    }

    /**
     * Start an instance of a workflow for a given workflow id
     *
     * @param workflowId                    The id of the workflow to start an instance
     * @return                              The newly created instance
     * @throws InternalErrorException       If any errors starting an instance
     * @throws WorkflowNotFoundException    If workflow with workflowId does not exist
     */
    public Instance start(UUID workflowId) throws InternalErrorException, WorkflowNotFoundException {
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
     * @throws InternalErrorException       If any errors starting an instance
     * @throws WorkflowNotFoundException    If workflow with workflowId does not exist
     */
    public Instance start(String workflowId, String name, String description, List<Field> fields) throws InternalErrorException, WorkflowNotFoundException {
        return this.start(UUID.fromString(workflowId), name, description, fields);
    }

    /**
     * Start an instance of a workflow for a given workflow id
     *
     * @param workflowId                    The id of the workflow to start an instance
     * @param name                          The name to give to the instance
     * @param description                   The description to give to the instance
     * @param fields                        The input fields to use when starting this instance
     * @return                              The newly created instance
     * @throws InternalErrorException       If any errors starting an instance
     * @throws WorkflowNotFoundException    If workflow with workflowId does not exist
     */
    public Instance start(UUID workflowId, String name, String description, List<Field> fields) throws InternalErrorException, WorkflowNotFoundException {
        StartInstanceRequest startInstanceRequest = createStartInstanceRequest(workflowId, name, description, fields);
        org.catalytic.sdk.model.Instance internalInstance = null;
        try {
            internalInstance = this.instancesApi.startInstance(startInstanceRequest);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
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
     * @throws InternalErrorException       If any errors stopping the instance
     * @throws InstanceNotFoundException    If instance with id does not exist
     */
    public Instance stop(UUID id) throws InstanceNotFoundException, InternalErrorException {
        return this.stop(id.toString());
    }

    /**
     * Stops an instance by instance id
     *
     * @param id                            The id of the instance to stop
     * @return                              The Instance that was stopped
     * @throws InternalErrorException       If any errors stopping the instance
     * @throws InstanceNotFoundException    If instance with id does not exist
     */
    public Instance stop(String id) throws InstanceNotFoundException, InternalErrorException {
        org.catalytic.sdk.model.Instance internalInstance = null;
        try {
            internalInstance = this.instancesApi.stopInstance(id);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
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
     * @throws InternalErrorException           If any errors getting the step
     * @throws InstanceStepNotFoundException    If instance with id does not exist
     */
    public InstanceStep getStep(UUID id) throws InternalErrorException, InstanceStepNotFoundException {
        return this.getStep(id.toString());
    }

    /**
     * Gets a step by step id
     *
     * @param id                                The id of the step to get
     * @return                                  The InstanceStep object
     * @throws InternalErrorException           If any errors getting the step
     * @throws InstanceStepNotFoundException    If instance with id does not exist
     */
    public InstanceStep getStep(String id) throws InternalErrorException, InstanceStepNotFoundException {
        org.catalytic.sdk.model.InstanceStep internalStep = null;
        try {
            internalStep = getStepById(id);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                throw new InstanceStepNotFoundException("Instance step with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get step with id " + id, e);
        }
        InstanceStep step = createInstanceStep(internalStep);
        return step;
    }

    /**
     * Gets all the steps for a specific instance id
     *
     * @param instanceId                The id of the instances to get steps for
     * @return                          The InstanceStepsPage which contains the results
     * @throws InternalErrorException   If any errors getting steps
     */
    public InstanceStepsPage getSteps(UUID instanceId) throws InternalErrorException {
        return this.getSteps(instanceId.toString());
    }

    /**
     * Gets all the steps for a specific instance id
     *
     * @param instanceId                The id of the instances to get steps for
     * @return                          The InstanceStepsPage which contains the results
     * @throws InternalErrorException   If any errors getting steps
     */
    public InstanceStepsPage getSteps(String instanceId) throws InternalErrorException {
        org.catalytic.sdk.model.InstanceStepsPage internalInstanceSteps;
        try {
            internalInstanceSteps = this.instanceStepsApi.findInstanceSteps(instanceId, null, null, null, null, null, null, null, null, null);
        } catch (ApiException e) {
            throw new InternalErrorException("Unable to get steps for instance " + instanceId, e);
        }

        List<InstanceStep> instanceSteps = new ArrayList<>();

        for (org.catalytic.sdk.model.InstanceStep internalInstanceStep : internalInstanceSteps.getSteps()) {
            InstanceStep instanceStep = createInstanceStep(internalInstanceStep);
            instanceSteps.add(instanceStep);
        }

        InstanceStepsPage instanceStepsPage = new InstanceStepsPage(instanceSteps, internalInstanceSteps.getCount(), internalInstanceSteps.getNextPageToken());
        return instanceStepsPage;
    }

    /**
     * Search for steps
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          An InstanceStepsPage which contains the results
     * @throws InternalErrorException   If any errors finding steps
     */
    public InstanceStepsPage findSteps(String pageToken) throws InternalErrorException {
        return this.findSteps(null, pageToken, null);
    }

    /**
     * Search for steps
     *
     * @param filter                    The filter criteria to search instance steps by
     * @return                          An InstanceStepsPage which contains the results
     * @throws InternalErrorException   If any errors finding steps
     */
    public InstanceStepsPage findSteps(Filter filter) throws InternalErrorException {
        return this.findSteps(filter, null, null);
    }

    /**
     * Search for steps
     *
     * @param filter                    The filter criteria to search instance steps by
     * @param pageToken                 The token of the page to fetch
     * @return                          An InstanceStepsPage which contains the results
     * @throws InternalErrorException   If any errors finding steps
     */
    public InstanceStepsPage findSteps(Filter filter, String pageToken) throws InternalErrorException {
        return this.findSteps(filter, pageToken, null);
    }

    /**
     * Search for steps
     *
     * @param filter                    The filter criteria to search instance steps by
     * @param pageToken                 The token of the page to fetch
     * @param pageSize                  The number of instance steps per page to fetch
     * @return                          An InstanceStepsPage which contains the results
     * @throws InternalErrorException   If any errors finding steps
     */
    public InstanceStepsPage findSteps(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException {
        // The REST api supports wildcard instance id when searching for instance steps
        // https://cloud.google.com/apis/design/design_patterns#list_sub-collections
        String wildcardInstanceId = "-";
        String text = null;
        String workflowId = null;
        String assignee = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
            workflowId = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "workflowId");
            assignee = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "assignee");
        }

        org.catalytic.sdk.model.InstanceStepsPage internalInstanceStepsPage = null;
        try {
            internalInstanceStepsPage = this.instanceStepsApi.findInstanceSteps(wildcardInstanceId, text, null, workflowId, null, null, null, assignee, pageToken, pageSize);
        } catch (ApiException e) {
            throw new InternalErrorException("Unable to find steps", e);
        }
        List<InstanceStep> steps = new ArrayList<>();

        for (org.catalytic.sdk.model.InstanceStep internalStep : internalInstanceStepsPage.getSteps()) {
            InstanceStep step = createInstanceStep(internalStep);
            steps.add(step);
        }

        InstanceStepsPage instanceStepsPage = new InstanceStepsPage(steps, internalInstanceStepsPage.getCount(), internalInstanceStepsPage.getNextPageToken());
        return instanceStepsPage;
    }

    /**
     * Completes a specific step
     *
     * @param id                        The id of the step to complete
     * @param fields                    Fields and the values to use when completing a step
     * @return                          The completed InstanceStep
     * @throws InternalErrorException   If any errors completing the step
     */
    public InstanceStep completeStep(UUID id, List<Field> fields) throws InternalErrorException {
        return this.completeStep(id.toString(), fields);
    }

    /**
     * Completes a specific step
     *
     * @param id                        The id of the step to complete
     * @param fields                    Fields and the values to use when completing a step
     * @return                          The completed InstanceStep
     * @throws InternalErrorException   If any errors completing the step
     */
    public InstanceStep completeStep(String id, List<Field> fields) throws InternalErrorException {
        List<FieldUpdateRequest> fieldUpdateRequests = createFieldUpdateRequests(fields);
        CompleteStepRequest completeStepRequest = new CompleteStepRequest();
        completeStepRequest.setId(UUID.fromString(id));
        completeStepRequest.setStepOutputFields(fieldUpdateRequests);
        org.catalytic.sdk.model.InstanceStep internalStep;
        try {
            org.catalytic.sdk.model.InstanceStep step = getStepById(id);
            internalStep = this.instanceStepsApi.completeStep(id, step.getInstanceId().toString(), completeStepRequest);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                throw new InternalErrorException("Step with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to complete step with id " + id, e);
        }

        InstanceStep step = createInstanceStep(internalStep);
        return step;
    }

    /**
     * Creates a StartInstanceRequest object with the passed in params
     *
     * @param workflowId     The id to create the CompleteStepRequest with
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
     * Create FieldUpdateRequest's from Fields
     *
     * @param fields    The fields to create FieldUpdateRequest's from
     * @return          FieldUpdateRequest's created from Field's
     */
    private List<FieldUpdateRequest> createFieldUpdateRequests (List<Field> fields) {
        List<FieldUpdateRequest> fieldUpdateRequests = new ArrayList<>();

        if (fields != null) {
            for (Field field : fields) {
                FieldUpdateRequest fieldUpdateRequest = new FieldUpdateRequest();
                fieldUpdateRequest.setName(field.getName());
                fieldUpdateRequest.setReferenceName(field.getReferenceName());
                fieldUpdateRequest.setValue(field.getValue());
                fieldUpdateRequests.add(fieldUpdateRequest);
            }
        }
        return fieldUpdateRequests;
    }

    /**
     * Get an instance step by its id
     *
     * @param id            The id of the step to get
     * @return              The InstanceStep object
     * @throws ApiException If any errors getting the instance step
     */
    private org.catalytic.sdk.model.InstanceStep getStepById(String id) throws ApiException {
        // The REST api supports wildcard instance id when searching for instance steps
        // https://cloud.google.com/apis/design/design_patterns#list_sub-collections
        String wildcardInstanceId = "-";
        org.catalytic.sdk.model.InstanceStep step = this.instanceStepsApi.getInstanceStep(id, wildcardInstanceId);
        return step;
    }

    /**
     * Create an Instance object from an internal Instance object
     *
     * @param internalInstance  The internal instance to create an Instance object from
     * @return                  The created Instance object
     */
    private Instance createInstance(org.catalytic.sdk.model.Instance internalInstance)
    {
        List<InstanceStep> steps = new ArrayList<>();
        List<Field> fields = new ArrayList<>();

        // Create external steps from internal steps if any exist
        if (internalInstance.getSteps() != null) {

            for (org.catalytic.sdk.model.InstanceStep internalInstanceStep : internalInstance.getSteps()) {

                // Create external outputFields from internal outputFields
                List<Field> outputFields = createFields(internalInstanceStep.getOutputFields());

                org.catalytic.sdk.entities.InstanceStep step = new org.catalytic.sdk.entities.InstanceStep(
                        internalInstanceStep.getId(),
                        internalInstanceStep.getInstanceId(),
                        internalInstanceStep.getWorkflowId(),
                        internalInstanceStep.getName(),
                        internalInstanceStep.getTeamName(),
                        internalInstanceStep.getPosition(),
                        internalInstanceStep.getDescription(),
                        internalInstanceStep.getStatus().getValue(),
                        internalInstanceStep.getAssignedTo(),
                        outputFields
                );
                steps.add(step);
            }
        }

        // Create external fields from internal fields
        for (org.catalytic.sdk.model.Field internalField : internalInstance.getFields()) {
            Field field = createField(internalField);
            fields.add(field);
        }

        // Create an external Instance
        Instance instance = new Instance(
                internalInstance.getId(),
                internalInstance.getWorkflowId(),
                internalInstance.getName(),
                internalInstance.getTeamName(),
                internalInstance.getDescription(),
                internalInstance.getCategory(),
                internalInstance.getOwner(),
                internalInstance.getCreatedBy(),
                steps,
                fields,
                internalInstance.getStatus().getValue(),
                internalInstance.getFieldVisibility(),
                internalInstance.getVisibility(),
                internalInstance.getVisibleToUsers()
        );
        return instance;
    }

    /**
     * Create an InstanceStep object from an internal InstanceStep object
     *
     * @param internalInstanceStep  The internal instance step to create an InstanceStep object from
     * @return                      The created InstanceStep object
     */
    private InstanceStep createInstanceStep(org.catalytic.sdk.model.InstanceStep internalInstanceStep) {
        // Create external outputFields from internal outputFields
        List<Field> outputFields = createFields(internalInstanceStep.getOutputFields());

        InstanceStep instanceStep = new InstanceStep(
                internalInstanceStep.getId(),
                internalInstanceStep.getInstanceId(),
                internalInstanceStep.getWorkflowId(),
                internalInstanceStep.getName(),
                internalInstanceStep.getTeamName(),
                internalInstanceStep.getPosition(),
                internalInstanceStep.getDescription(),
                internalInstanceStep.getStatus().getValue(),
                internalInstanceStep.getAssignedTo(),
                outputFields
        );
        return instanceStep;
    }

    /**
     * Create external Fields from internal Fields
     *
     * @param internalFields    The internal fields to create external fields from
     * @return                  External fields
     */
    private List<Field> createFields (List<org.catalytic.sdk.model.Field> internalFields) {
        List<Field> fields = new ArrayList<>();

        // Create external outputFields from internal outputFields
        if (internalFields != null) {
            for (org.catalytic.sdk.model.Field internalField : internalFields) {
                Field field = createField(internalField);
                fields.add(field);
            }
        }

        return fields;
    }

    /**
     * Create an external Field from an internal Field
     *
     * @param internalField The internal field to create an external field from
     * @return              An external field
     */
    private Field createField(org.catalytic.sdk.model.Field internalField) {
        Field field = new Field(
                internalField.getId(),
                internalField.getName(),
                internalField.getReferenceName(),
                internalField.getDescription(),
                internalField.getPosition(),
                internalField.getRestrictions(),
                internalField.getFieldType().getValue(),
                internalField.getValue(),
                internalField.getDefaultValue()
        );
        return field;
    }
}
