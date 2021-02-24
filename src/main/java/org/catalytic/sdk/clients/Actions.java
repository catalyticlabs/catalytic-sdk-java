package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.*;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.ActionsApi;
import org.catalytic.sdk.search.ActionSearchClause;

import java.util.ArrayList;
import java.util.List;

/**
 * Actions client
 */
public class Actions extends BaseClient {

    private String token;
    private static final Logger log = CatalyticLogger.getLogger(Actions.class);
    private ActionsApi actionsApi;

    public Actions(String token) {
        this.token = token;
        ApiClient apiClient = ConfigurationUtils.getApiClient(this.token);
        this.actionsApi = new ActionsApi(apiClient);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in a mock ActionsApi
     *
     * @param token         The token to be used
     * @param actionsApi    The mocked ActionsApi
     */
    public Actions(String token, ActionsApi actionsApi) {
        this.token = token;
        this.actionsApi = actionsApi;
    }

    /**
     * Get an Action Defintion by id
     *
     * @param id                            The id of the Action to get
     * @return                              The Action object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws ActionNotFoundException      If Action is not found
     * @throws InternalErrorException       If any error getting an Action
     * @throws UnauthorizedException        If unauthorized
     */
    public Action get(String id) throws InternalErrorException, ActionNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.Action internalAction;
        try {
            log.debug("Getting Action with id {}", () -> id);
            internalAction = this.actionsApi.getAction(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new ActionNotFoundException("Action with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get Action", e);
        }
        Action action = createAction(internalAction);
        return action;
    }

    /**
     * Get all Actions
     *
     * @return                              An ActionsPage object which contains the all the Actions from all pages of results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Actions
     * @throws UnauthorizedException        If unauthorized
     */
    public ActionsPage list() throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        // Get the first page of users
        ActionsPage actionsPage = search(null, null);
        ActionsPage results = actionsPage;

        // Loop through the rest of the pages and add the users to results
        while(!actionsPage.getNextPageToken().isEmpty()) {
            actionsPage = search(null, actionsPage.getNextPageToken());
            results.addActions(actionsPage.getActions(), actionsPage.getNextPageToken());
        }

        return results;
    }

    /**
     * Finds actions by a variety of filters
     *
     * @param actionSearchClause            The search criteria to search actions by
     * @return                              A ActionsPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding actions
     * @throws UnauthorizedException        If unauthorized
     */
    public ActionsPage search(ActionSearchClause actionSearchClause) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(actionSearchClause, null, null);
    }

    /**
     * Finds actions by a variety of filters
     *
     * @param actionSearchClause            The search criteria to search actions by
     * @param pageToken                     The token of the page to fetch
     * @return                              A ActionsPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Actions
     * @throws UnauthorizedException        If unauthorized
     */
    public ActionsPage search(ActionSearchClause actionSearchClause, String pageToken) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(actionSearchClause, pageToken, null);
    }

    /**
     * Finds actions by a variety of filters
     *
     * @param actionSearchClause            The search criteria to search actions by
     * @param pageToken                     The token of the page to fetch
     * @param pageSize                      The number of actions to fetch per page
     * @return                              A ActionsPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Actions
     * @throws UnauthorizedException        If unauthorized
     */
    public ActionsPage search(ActionSearchClause actionSearchClause, String pageToken, Integer pageSize) throws AccessTokenNotFoundException, InternalErrorException, UnauthorizedException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.ActionsPage results;
        List<Action> actions = new ArrayList<>();

        if (actionSearchClause == null) {
            actionSearchClause = new ActionSearchClause();
        }

        org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(actionSearchClause.getId());
        org.catalytic.sdk.generated.model.StringSearchExpression internalName = createInternalStringSearchExpression(actionSearchClause.getName());
        org.catalytic.sdk.generated.model.StringSearchExpression internalDescription = createInternalStringSearchExpression(actionSearchClause.getDescription());
        org.catalytic.sdk.generated.model.GuidSearchExpression internalOriginalActionId = createInternalGuidSearchExpression(actionSearchClause.getOriginalActionId());
        org.catalytic.sdk.generated.model.ExactStringSearchExpression internalCreatedByEmail = createInternalExactStringSearchExpression(actionSearchClause.getCreatedByEmail());
        org.catalytic.sdk.generated.model.DateTimeSearchExpression internalCreatedDate = createInternalDateTimeSearchExpression(actionSearchClause.getCreatedDate());

        List<org.catalytic.sdk.generated.model.ActionSearchClause> internalAnd = createInternalActionSearchClause(actionSearchClause.getAnd());
        List<org.catalytic.sdk.generated.model.ActionSearchClause> internalOr = createInternalActionSearchClause(actionSearchClause.getOr());

        org.catalytic.sdk.generated.model.ActionSearchClause actionSearchRequest = new org.catalytic.sdk.generated.model.ActionSearchClause();
        actionSearchRequest.setAnd(internalAnd);
        actionSearchRequest.setOr(internalOr);
        actionSearchRequest.setId(internalId);
        actionSearchRequest.setName(internalName);
        actionSearchRequest.setDescription(internalDescription);
        actionSearchRequest.setOriginalActionId(internalOriginalActionId);
        actionSearchRequest.setCreatedByEmail(internalCreatedByEmail);
        actionSearchRequest.setCreatedDate(internalCreatedDate);

        try {
            log.debug("Finding Actions with criteria {}", () -> actionSearchRequest);
            results = this.actionsApi.searchActions(pageToken, pageSize, actionSearchRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find Actions", e);
        }

        for (org.catalytic.sdk.generated.model.Action internalAction : results.getActions()) {
            Action action = createAction(internalAction);
            actions.add(action);
        }

        ActionsPage actionsPage = new ActionsPage(actions, results.getCount(), results.getNextPageToken());
        return actionsPage;
    }

    /**
     * Invoke an Action by id
     *
     * @param id                            The id of the Action to invoke
     * @param invokeActionRequest           The request to invoke the Action with
     * @return                              The invoked Action
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws ActionNotFoundException      If Action is not found
     * @throws InternalErrorException       If any error getting an Action
     * @throws UnauthorizedException        If unauthorized
     */
    public ActionInvocation invoke(String id, InvokeActionRequest invokeActionRequest) throws InternalErrorException, ActionNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.ActionInvocation internalActionInvocation;
        List<org.catalytic.sdk.generated.model.ActionInput> inputs = createInternalActionInputs(invokeActionRequest.getInputs());

        org.catalytic.sdk.generated.model.InvokeActionRequest internalInvokeActionRequest = new org.catalytic.sdk.generated.model.InvokeActionRequest();
        internalInvokeActionRequest.setActionId(invokeActionRequest.getActionId());
        internalInvokeActionRequest.setInputs(inputs);
        internalInvokeActionRequest.setRequiredWorkerTags(invokeActionRequest.getRequiredWorkerTags());
        internalInvokeActionRequest.setLifetimeSeconds(invokeActionRequest.getLifetimeSeconds());
        internalInvokeActionRequest.setMetadata(invokeActionRequest.getMetadata());
        internalInvokeActionRequest.setIntegrationConnectionId(invokeActionRequest.getIntegrationConnectionId());

        try {
            log.debug("Invoking Action with id {}", () -> id);
            internalActionInvocation = this.actionsApi.invokeAction(id, internalInvokeActionRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new ActionNotFoundException("Action with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to invoke Action", e);
        }
        ActionInvocation actionInvocation = createActionInvocation(internalActionInvocation);
        return actionInvocation;
    }

    /**
     * Get an Action Invocation by id
     *
     * @param id                                    The id of the Action Invocation to get
     * @return                                      The Action object
     * @throws AccessTokenNotFoundException         If Access Token is not found or if the client was instantiated without an Access Token
     * @throws ActionInvocationNotFoundException    If Action is not found
     * @throws InternalErrorException               If any error getting an Action
     * @throws UnauthorizedException                If unauthorized
     */
    public ActionInvocation getActionInvocation(String id) throws InternalErrorException, ActionInvocationNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.ActionInvocation internalActionInvocation;
        try {
            log.debug("Getting Action Invocation with id {}", () -> id);
            // The REST api supports wildcard instance id when searching for Integration Connections
            // https://cloud.google.com/apis/design/design_patterns#list_sub-collections
            String wildcardActionId = "-";
            internalActionInvocation = this.actionsApi.getActionInvocation(wildcardActionId, id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new ActionInvocationNotFoundException("Action Invocation with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get Action Invocation", e);
        }
        ActionInvocation actionInvocation = createActionInvocation(internalActionInvocation);
        return actionInvocation;
    }

    /**
     * Create an ActionInvocation object from an internal ActionInvocation object
     *
     * @param internalActionInvocation  The internal ActionInvocation to create an ActionInvocation from
     * @return                          The created ActionInvocation object
     */
    private ActionInvocation createActionInvocation(org.catalytic.sdk.generated.model.ActionInvocation internalActionInvocation) {
        List<ActionInput> inputs = createActionInputs(internalActionInvocation.getInputs());
        List<ActionOutput> outputs = createActionOutputs(internalActionInvocation.getOutputs());

        ActionInvocation actionInvocation = new ActionInvocation(
                internalActionInvocation.getId(),
                internalActionInvocation.getTeamName(),
                internalActionInvocation.getActionId(),
                internalActionInvocation.getReferenceName(),
                internalActionInvocation.getIntegrationConnectionId(),
                inputs,
                outputs,
                internalActionInvocation.getRequiredWorkerTags(),
                internalActionInvocation.getCompletedByWorkerId(),
                internalActionInvocation.getExpirationTime(),
                internalActionInvocation.getIsCompleted(),
                internalActionInvocation.getCompletedTime(),
                internalActionInvocation.getMetadata()
        );

        return actionInvocation;
    }

    /**
     * Create an Action object from an internal Action object
     *
     * @param internalAction    The internal Action to create a Action object from
     * @return                  The created Action object
     */
    private Action createAction(org.catalytic.sdk.generated.model.Action internalAction) {
        List<ActionInputDefinition> actionInputDefinitions = createActionInputDefintions(internalAction.getInputs());
        List<ActionOutputDefinition> actionOutputDefinitions = createActionOutputDefinitions(internalAction.getOutputs());

        Action action = new Action(
                internalAction.getId(),
                internalAction.getTeamName(),
                internalAction.getReferenceName(),
                internalAction.getVersion(),
                internalAction.getAppId(),
                internalAction.getCreatedByEmail(),
                internalAction.getName(),
                internalAction.getDescription(),
                internalAction.getTags(),
                internalAction.getRequiredWorkerTags(),
                actionInputDefinitions,
                actionOutputDefinitions,
                internalAction.getIsPublished(),
                internalAction.getMetadata(),
                internalAction.getOriginalActionId(),
                internalAction.getHelpDocumentPath(),
                internalAction.getIconSvg(),
                internalAction.getIntegrationIds(),
                internalAction.getIntegrationRequired()
        );
        return action;
    }

    /**
     * Create a list of ActionInputDefinitions from a list of internal ActionInputDefinition
     *
     * @param internalActionInputDefinitions The internal list of ActionInputDefinitions to create a list of external ActionInputDefintion objects from
     * @return                              The created list of ActionInputDefintions
     */
    private List<ActionInputDefinition> createActionInputDefintions(List<org.catalytic.sdk.generated.model.ActionInputDefinition> internalActionInputDefinitions) {
        List<ActionInputDefinition> inputs = null;

        if (internalActionInputDefinitions != null) {
            inputs = new ArrayList<>();

            for (org.catalytic.sdk.generated.model.ActionInputDefinition internalInput : internalActionInputDefinitions) {
                FieldDisplayOptions display = new FieldDisplayOptions(internalInput.getDisplay().getChoices(), internalInput.getDisplay().getValueRequired());
                ActionParameterType type = ActionParameterType.valueOf(internalInput.getType().getValue());

                ActionInputDefinition input = new ActionInputDefinition(
                        internalInput.getName(),
                        internalInput.getDescription(),
                        internalInput.getIsRequired(),
                        internalInput.getDefaultValueSerialized(),
                        display,
                        type,
                        internalInput.getAdvanced(),
                        internalInput.getMetadata()
                );
                inputs.add(input);
            }
        }

        return inputs;
    }

    /**
     * Create a list of ActionOutputDefinitions from a list of internal ActionOutputDefinition
     *
     * @param internalActionOutputDefinitions   The internal list of ActionOutputDefinitions to create a list of external ActionOutputDefintion objects from
     * @return                                  The created list of ActionOutputDefintions
     */
    private List<ActionOutputDefinition> createActionOutputDefinitions(List<org.catalytic.sdk.generated.model.ActionOutputDefinition> internalActionOutputDefinitions) {
        List<ActionOutputDefinition> outputs = null;

        if (internalActionOutputDefinitions != null) {
            outputs = new ArrayList<>();

            for (org.catalytic.sdk.generated.model.ActionOutputDefinition internalOutput : internalActionOutputDefinitions) {
                ActionParameterType type = ActionParameterType.valueOf(internalOutput.getType().getValue());

                ActionOutputDefinition output = new ActionOutputDefinition(
                        internalOutput.getName(),
                        internalOutput.getDescription(),
                        type,
                        internalOutput.getAdvanced(),
                        internalOutput.getMetadata()
                );
                outputs.add(output);
            }
        }

        return outputs;
    }

    /**
     * Create a list of internal ActionInputs from a list of external ActionInputs
     *
     * @param internalActionInputs  The external list of ActionInput to create a list of internal ActionInput objects from
     * @return                      The created list of ActionInputs
     */
    private List<org.catalytic.sdk.generated.model.ActionInput> createInternalActionInputs(List<ActionInput> internalActionInputs) {
        List<org.catalytic.sdk.generated.model.ActionInput> inputs = null;

        if (internalActionInputs != null) {
            inputs = new ArrayList<>();

            for (ActionInput internalInput : internalActionInputs) {
                org.catalytic.sdk.generated.model.ActionParameterType type = org.catalytic.sdk.generated.model.ActionParameterType.valueOf(internalInput.getType().getValue());
                org.catalytic.sdk.generated.model.FieldDisplayOptions display = new org.catalytic.sdk.generated.model.FieldDisplayOptions();
                display.setChoices(internalInput.getDisplay().getChoices());
                display.setValueRequired(internalInput.getDisplay().getValueRequired());

                org.catalytic.sdk.generated.model.ActionInput input = new org.catalytic.sdk.generated.model.ActionInput();
                input.setName(internalInput.getName());
                input.setDescription(internalInput.getDescription());
                input.setSerializedValue(internalInput.getSerializedValue());
                input.setIsRequired(internalInput.getIsRequired());
                input.setDefaultValueSerialized(internalInput.getDefaultValueSerialized());
                input.setDisplay(display);
                input.setType(type);
                input.setAdvanced(internalInput.getAdvanced());
                input.setMetadata(internalInput.getMetadata());

                inputs.add(input);
            }
        }

        return inputs;
    }

    /**
     * Create a list of external ActionInputs from a list of internal ActionInputs
     *
     * @param internalActionInputs  The internal list of ActionInput to create a list of external ActionInput objects from
     * @return                      The created list of ActionInputs
     */
    private List<ActionInput> createActionInputs(List<org.catalytic.sdk.generated.model.ActionInput> internalActionInputs) {
        List<ActionInput> inputs = null;

        if (internalActionInputs != null) {
            inputs = new ArrayList<>();

            for (org.catalytic.sdk.generated.model.ActionInput internalInput : internalActionInputs) {
                ActionParameterType type = ActionParameterType.valueOf(internalInput.getType().getValue());
                FieldDisplayOptions display = new FieldDisplayOptions(
                        internalInput.getDisplay().getChoices(),
                        internalInput.getDisplay().getValueRequired()
                );

                ActionInput input = new ActionInput(
                    internalInput.getName(),
                    internalInput.getDescription(),
                    internalInput.getSerializedValue(),
                    internalInput.getIsRequired(),
                    internalInput.getDefaultValueSerialized(),
                    display,
                    type,
                    internalInput.getAdvanced(),
                    internalInput.getMetadata()
                );

                inputs.add(input);
            }
        }

        return inputs;
    }

    /**
     * Create a list of external ActionOutputs from a list of internal ActionOutputs
     *
     * @param internalActionOutputs The internal list of ActionOutputs to create a list of external ActionOutputs objects from
     * @return                      The created list of ActionOutputs
     */
    private List<ActionOutput> createActionOutputs(List<org.catalytic.sdk.generated.model.ActionOutput> internalActionOutputs) {
        List<ActionOutput> outputs = null;

        if (internalActionOutputs != null) {
            outputs = new ArrayList<>();

            for (org.catalytic.sdk.generated.model.ActionOutput internalOutput : internalActionOutputs) {
                ActionParameterType type = ActionParameterType.valueOf(internalOutput.getType().getValue());

                ActionOutput output = new ActionOutput(
                    internalOutput.getName(),
                    internalOutput.getDescription(),
                    internalOutput.getSerializedValue(),
                    type,
                    internalOutput.getAdvanced(),
                    internalOutput.getMetadata()
                );

                outputs.add(output);
            }
        }

        return outputs;
    }

    /**
     * Create an internal ActionSearchClause from an external ActionSearchClause
     *
     * @param actionSearchClause    The external ActionSearchClause to create an internal one from
     * @return                      An internal ActionSearchClause
     */
    private List<org.catalytic.sdk.generated.model.ActionSearchClause> createInternalActionSearchClause(List<ActionSearchClause> actionSearchClause) {
        List<org.catalytic.sdk.generated.model.ActionSearchClause> internalActionSearchClauses = null;

        if (actionSearchClause != null) {

            internalActionSearchClauses = new ArrayList<>();

            for (ActionSearchClause searchClause : actionSearchClause) {
                org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(searchClause.getId());
                org.catalytic.sdk.generated.model.StringSearchExpression internalName = createInternalStringSearchExpression(searchClause.getName());
                org.catalytic.sdk.generated.model.StringSearchExpression internalDescription = createInternalStringSearchExpression(searchClause.getDescription());
                org.catalytic.sdk.generated.model.GuidSearchExpression internalOriginalActionId = createInternalGuidSearchExpression(searchClause.getOriginalActionId());
                org.catalytic.sdk.generated.model.ExactStringSearchExpression internalCreatedbyEmail = createInternalExactStringSearchExpression(searchClause.getCreatedByEmail());
                org.catalytic.sdk.generated.model.DateTimeSearchExpression internalCreatedDate = createInternalDateTimeSearchExpression(searchClause.getCreatedDate());

                org.catalytic.sdk.generated.model.ActionSearchClause internalActionSearchClause = new org.catalytic.sdk.generated.model.ActionSearchClause();
                internalActionSearchClause.setId(internalId);
                internalActionSearchClause.setName(internalName);
                internalActionSearchClause.setDescription(internalDescription);
                internalActionSearchClause.setOriginalActionId(internalOriginalActionId);
                internalActionSearchClause.setCreatedByEmail(internalCreatedbyEmail);
                internalActionSearchClause.setCreatedDate(internalCreatedDate);
                List<org.catalytic.sdk.generated.model.ActionSearchClause> internalAnd = createInternalActionSearchClause(searchClause.getAnd());
                List<org.catalytic.sdk.generated.model.ActionSearchClause> internalOr = createInternalActionSearchClause(searchClause.getOr());
                internalActionSearchClause.setAnd(internalAnd);
                internalActionSearchClause.setOr(internalOr);
                internalActionSearchClauses.add(internalActionSearchClause);
            }
        }
        return internalActionSearchClauses;
    }
}
