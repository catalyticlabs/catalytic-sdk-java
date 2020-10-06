package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.Field;
import org.catalytic.sdk.entities.Integration;
import org.catalytic.sdk.entities.IntegrationConfiguration;
import org.catalytic.sdk.entities.IntegrationConnection;
import org.catalytic.sdk.entities.IntegrationsPage;
import org.catalytic.sdk.exceptions.*;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.IntegrationsApi;
import org.catalytic.sdk.generated.model.*;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Integrations client
 */
public class Integrations extends BaseClient {

    private String token;
    private static final Logger log = CatalyticLogger.getLogger(Integrations.class);
    private IntegrationsApi integrationsApi;

    public Integrations(String token) {
        this.token = token;
        ApiClient apiClient = ConfigurationUtils.getApiClient(this.token);
        this.integrationsApi = new IntegrationsApi(apiClient);
    }

    /**
     * Constructor used for unit testing
     *
     * Allows you to pass in mocks for IntegrationsApi
     *
     * @param token             The token to be used
     * @param integrationsApi   The mocked IntegrationsApi
     */
    public Integrations(String token, IntegrationsApi integrationsApi) {
        this.token = token;
        this.integrationsApi = integrationsApi;
    }

    /**
     * Get an Integration by id
     *
     * @param id                            The id of the Integration to get
     * @return                              The Integration object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws UnauthorizedException        If unauthorized
     * @throws IntegrationNotFoundException If an Integration with the id does not exist
     * @throws InternalErrorException       If any error getting Integration
     */
    public Integration get(String id) throws AccessTokenNotFoundException, UnauthorizedException, IntegrationNotFoundException, InternalErrorException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.Integration internalIntegration = null;
        try {
            log.debug("Getting Integration with id {}", () -> id);
            internalIntegration = this.integrationsApi.getIntegration(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new IntegrationNotFoundException("Integration with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get Integration", e);
        }
        Integration integration = createIntegration(internalIntegration);
        return integration;
    }

    /**
     * Find all Integrations
     *
     * @return                              An IntegrationsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Integrations
     * @throws UnauthorizedException        If unauthorized
     */
    public IntegrationsPage find () throws UnauthorizedException, InternalErrorException, AccessTokenNotFoundException {
        return this.find(null, null, null);
    }

    /**
     * Find Integrations by a variety of criteria
     *
     * @param pageToken                     The token of the page to fetch
     * @return                              An IntegrationsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Integrations
     * @throws UnauthorizedException        If unauthorized
     */
    public IntegrationsPage find (String pageToken) throws UnauthorizedException, InternalErrorException, AccessTokenNotFoundException {
        return this.find(null, pageToken, null);
    }

    /**
     * Find Integrations by a variety of criteria
     *
     * @param filter                        The filter criteria to search Integrations by
     * @return                              An IntegrationsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Integrations
     * @throws UnauthorizedException        If unauthorized
     */
    public IntegrationsPage find(Filter filter) throws AccessTokenNotFoundException, UnauthorizedException, InternalErrorException {
        return this.find(filter, null, null);
    }

    /**
     * Find Integrations by a variety of criteria
     *
     * @param filter                        The filter criteria to search Integrations by
     * @param pageToken                     The token of the page to fetch
     * @return                              An IntegrationsPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding Integrations
     * @throws UnauthorizedException        If unauthorized
     */
    public IntegrationsPage find(Filter filter, String pageToken) throws AccessTokenNotFoundException, UnauthorizedException, InternalErrorException {
        return this.find(filter, pageToken, null);
    }

    /**
     * Find Integrations by a variety of criteria
     *
     * @param filter                        The filter criteria to search instances by
     * @param pageToken                     The token of the page to fetch
     * @param pageSize                      The number of workflows per page to fetch
     * @return                              An InstancesPage which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding workflows
     * @throws UnauthorizedException        If unauthorized
     */
    public IntegrationsPage find(Filter filter, String pageToken, Integer pageSize) throws AccessTokenNotFoundException, UnauthorizedException, InternalErrorException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        String text = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
        }

        org.catalytic.sdk.generated.model.IntegrationsPage internalIntegrations = null;

        try {
            log.debug("Finding Integrtaions with text: {}", text);
            internalIntegrations = this.integrationsApi.findIntegrations(text, null, null, null, null, null, null, null, null, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find Integrations", e);
        }

        List<Integration> integrations = new ArrayList<>();

        for (org.catalytic.sdk.generated.model.Integration internalIntegration : internalIntegrations.getIntegrations()) {
            Integration integration = createIntegration(internalIntegration);
            integrations.add(integration);
        }

        IntegrationsPage integrationsPage = new IntegrationsPage(integrations, internalIntegrations.getCount(), internalIntegrations.getNextPageToken());
        return integrationsPage;
    }

    /**
     * Create an Integration
     *
     * @param name                          The name to give to the Integration
     * @param oauthConfig                   The OAuth2 Configuration to use when creating the Integration
     * @return                              The newly created Integration
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error creating Integrations
     * @throws UnauthorizedException        If unauthorized
     */
    public Integration create(String name, IntegrationConfiguration oauthConfig) throws AccessTokenNotFoundException, UnauthorizedException, InternalErrorException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.IntegrationConfiguration internalConfig = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        internalConfig.setAuthorizeBaseUrl(oauthConfig.getAuthorizedBaseUrl());
        internalConfig.setClientId(oauthConfig.getClientId());
        internalConfig.setClientSecret(oauthConfig.getClientSecret());
        internalConfig.setRevokePath(oauthConfig.getRevokePath());
        internalConfig.setScopes(oauthConfig.getScopes());
        internalConfig.setSite(oauthConfig.getSite());
        internalConfig.setTokenPath(oauthConfig.getTokenPath());
        internalConfig.setUseBodyAuth(oauthConfig.getUseBodyAuth());

        IntegrationCreationRequest request = new IntegrationCreationRequest();
        request.setName(name);
        request.setConfig(internalConfig);
        request.setType(IntegrationType.OAUTH2);

        org.catalytic.sdk.generated.model.Integration internalIntegration;

        try {
            log.debug("Creating Integration with name {}", () -> name);
            internalIntegration = this.integrationsApi.createIntegration(request);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to create Integration", e);
        }

        Integration integration = createIntegration(internalIntegration);
        return integration;
    }

    /**
     * Update an Integration by id
     *
     * @param id                            The id of the Integration to update
     * @param name                          The name to give to the Integration
     * @param oauthConfig                   The connection params to use when creating the Integration
     * @return                              The newly created Integration
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error updating the Integration
     * @throws UnauthorizedException        If unauthorized
     * @throws IntegrationNotFoundException If an Integration with the id is not found
     */
    public Integration update(String id, String name, IntegrationConfiguration oauthConfig) throws AccessTokenNotFoundException, UnauthorizedException, InternalErrorException, IntegrationNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.IntegrationConfiguration internalConfig = new org.catalytic.sdk.generated.model.IntegrationConfiguration();
        internalConfig.setAuthorizeBaseUrl(oauthConfig.getAuthorizedBaseUrl());
        internalConfig.setClientId(oauthConfig.getClientId());
        internalConfig.setClientSecret(oauthConfig.getClientSecret());
        internalConfig.setRevokePath(oauthConfig.getRevokePath());
        internalConfig.setScopes(oauthConfig.getScopes());
        internalConfig.setSite(oauthConfig.getSite());
        internalConfig.setTokenPath(oauthConfig.getTokenPath());
        internalConfig.setUseBodyAuth(oauthConfig.getUseBodyAuth());

        IntegrationUpdateRequest request = new IntegrationUpdateRequest();
        request.setName(name);
        request.setConfig(internalConfig);
        request.setType(IntegrationType.OAUTH2);

        org.catalytic.sdk.generated.model.Integration internalIntegration;

        try {
            log.debug("Updating Integration with id: {}", () -> name);
            internalIntegration = this.integrationsApi.updateIntegration(id, request);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                throw new IntegrationNotFoundException("Integration with id " + id + " not found", e);
            }
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to update Integration", e);
        }

        Integration integration = createIntegration(internalIntegration);
        return integration;
    }

    /**
     * Delete an Integration
     *
     * @param id                            The id of the Integration to delete
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws UnauthorizedException        If unauthorized
     * @throws IntegrationNotFoundException If an Integration with the id does not exist
     * @throws InternalErrorException       If any error getting Integration
     */
    public void delete(String id) throws AccessTokenNotFoundException, UnauthorizedException, IntegrationNotFoundException, InternalErrorException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.Integration internalIntegration = null;
        try {
            log.debug("Deleting Integration with id {}", () -> id);
            this.integrationsApi.deleteIntegration(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new IntegrationNotFoundException("Integration with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to delete Integration", e);
        }
    }

    /**
     * Get an Integration Connection by id
     *
     * @param id                                        The id of the Integration Connection to get
     * @return                                          The IntegrationConnection object
     * @throws AccessTokenNotFoundException             If Access Token is not found or if the client was instantiated without an Access Token
     * @throws UnauthorizedException                    If unauthorized
     * @throws IntegrationConnectionNotFoundException   If an Integration Connection with the id does not exist
     * @throws InternalErrorException                   If any error deleting the Integration Connection
     */
    public IntegrationConnection getIntegrationConnection(String id) throws AccessTokenNotFoundException, UnauthorizedException, IntegrationConnectionNotFoundException, InternalErrorException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.IntegrationConnection internalIntegrationConnection = null;
        try {
            log.debug("Getting Integration Connection with id {}", () -> id);
            // The REST api supports wildcard instance id when searching for Integration Connections
            // https://cloud.google.com/apis/design/design_patterns#list_sub-collections
            String wildcardIntegrationId = "-";
            internalIntegrationConnection = this.integrationsApi.getIntegrationConnection(wildcardIntegrationId, id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new IntegrationConnectionNotFoundException("Integration Connection with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get Integration Connection", e);
        }
        IntegrationConnection integrationConnection = createIntegrationConnection(internalIntegrationConnection);
        return integrationConnection;
    }

    /**
     * Create an Integration Connection
     *
     * @param integrationId                 The id of the Integration to create a connection from
     * @param name                          The name to give to the Integration
     * @param connectionParams              The connection params to use when creating the Integration Connection
     * @return                              The newly created Integration
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws IntegrationNotFoundException If no Integration with the id is found
     * @throws InternalErrorException       If any error creating Integrations
     * @throws UnauthorizedException        If unauthorized
     */
    public IntegrationConnection createIntegrationConnection(String integrationId, String name, List<Field> connectionParams) throws AccessTokenNotFoundException, UnauthorizedException, InternalErrorException, IntegrationNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        List<FieldUpdateRequest> connectionParamRequest = createFieldUpdateRequests(connectionParams);
        IntegrationConnectionCreationRequest request = new IntegrationConnectionCreationRequest();
        request.setName(name);
        request.setConnectionParams(connectionParamRequest);
        request.setIntegrationId(integrationId);

        org.catalytic.sdk.generated.model.IntegrationConnection internalIntegrationConnection;

        try {
            log.debug("Creating Integration Connection with integrationId: {}, and name: ", integrationId, name);
            internalIntegrationConnection = this.integrationsApi.createIntegrationConnection(integrationId, request);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            if (e.getCode() == 404) {
                throw new IntegrationNotFoundException("Integration with id " + integrationId + " not found", e);
            }
            throw new InternalErrorException("Unable to create Integration Connection", e);
        }

        IntegrationConnection integrationConnection = createIntegrationConnection(internalIntegrationConnection);
        return integrationConnection;
    }

    /**
     * Delete an Integration Connection
     *
     * @param id                                        The id of the Integration Connection to delete
     * @throws AccessTokenNotFoundException             If Access Token is not found or if the client was instantiated without an Access Token
     * @throws UnauthorizedException                    If unauthorized
     * @throws IntegrationConnectionNotFoundException   If an Integration with the id does not exist
     * @throws InternalErrorException                   If any error deleting the Integration Connection
     */
    public void deleteIntegrationConnection(String id) throws AccessTokenNotFoundException, UnauthorizedException, IntegrationConnectionNotFoundException, InternalErrorException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        try {
            log.debug("Deleting Integration Connection with id {}", () -> id);
            // The REST api supports wildcard instance id when searching for Integration Connections
            // https://cloud.google.com/apis/design/design_patterns#list_sub-collections
            String wildcardIntegrationId = "-";
            this.integrationsApi.deleteIntegrationConnection(wildcardIntegrationId, id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new IntegrationConnectionNotFoundException("Integration Connection with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to delete Integration Connection", e);
        }
    }

    /**
     * Create an Integration from an internal Integration object
     *
     * @param internalIntegration   The internal Integration to create an Integration object from
     * @return                      The created Integration object
     */
    private Integration createIntegration(org.catalytic.sdk.generated.model.Integration internalIntegration) {

        List<Field> connectionParams = createFields(internalIntegration.getConnectionParams());
        List<IntegrationConnection> integrationConnections = createIntegrationConnections(internalIntegration.getConnections());

        // Create an external Integration
        Integration integration = new Integration(
                internalIntegration.getId(),
                internalIntegration.getName(),
                internalIntegration.getIsCustomIntegration(),
                integrationConnections,
                connectionParams
        );

        return integration;
    }

    /**
     * Create external IntegrationConnections from internal IntegrationConnections
     *
     * @param internalIntegrationConnections    The internal IntegrationConnections to create external IntegrationConnections from
     * @return                                  The external IntegrationConnections
     */
    private List<IntegrationConnection> createIntegrationConnections(List<org.catalytic.sdk.generated.model.IntegrationConnection> internalIntegrationConnections) {
        List<IntegrationConnection> integrationConnections = new ArrayList<>();

        if (internalIntegrationConnections != null) {
            // Create external IntegrationConnections from internal IntegrationConnections
            for (org.catalytic.sdk.generated.model.IntegrationConnection internalIntegrationConnection : internalIntegrationConnections) {
                IntegrationConnection integrationConnection = createIntegrationConnection(internalIntegrationConnection);
                integrationConnections.add(integrationConnection);
            }
        }

        return integrationConnections;
    }

    /**
     * Create external IntegrationConnection from an internal IntegrationConnection
     *
     * @param internalIntegrationConnection The internal IntegrationConnection to create an external IntegrationConnection from
     * @return                              The external IntegrationConnnection
     */
    private IntegrationConnection createIntegrationConnection(org.catalytic.sdk.generated.model.IntegrationConnection internalIntegrationConnection) {
        IntegrationConnection integrationConnection = new IntegrationConnection(
                internalIntegrationConnection.getId(),
                internalIntegrationConnection.getName(),
                internalIntegrationConnection.getIntegrationId()
        );

        return integrationConnection;
    }
}
