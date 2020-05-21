package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.exceptions.CredentialsNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.UserCredentialsApi;

/**
 * Credentials client
 */
public class Credentials {

    private static final Logger log = CatalyticLogger.getLogger(Credentials.class);
    private UserCredentialsApi userCredentialsApi;

    public Credentials(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.userCredentialsApi = new UserCredentialsApi(apiClient);
    }

    /**
     * Get credentials by id
     *
     * @param id                            The id of the credentials to get
     * @return Credentials                  The Credentials object
     * @throws InternalErrorException       If error fetching credentials
     * @throws CredentialsNotFoundException If credentials with id do not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public org.catalytic.sdk.entities.Credentials get(String id) throws InternalErrorException, CredentialsNotFoundException, UnauthorizedException {
        org.catalytic.sdk.generated.model.Credentials internalCredentials;
        try {
            log.debug("Getting credentials with id {}", () -> id);
            internalCredentials = this.userCredentialsApi.getCredentials(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            } else if (e.getCode() == 404) {
                throw new CredentialsNotFoundException("Credentials with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get credentials", e);
        }
        org.catalytic.sdk.entities.Credentials credentials = createCredentials(internalCredentials);
        return credentials;
    }

    /**
     * Finds credentials by a variety of filters
     *
     * @param filter            The filter to search credentials by
     * @param pageToken         The token of the page to fetch
     * @param pageSize          The number of credentials per page to fetch
     * @return CredentialsPage  A CredentialsPage object which contains the results
     */
//    public org.catalytic.sdk.entities.Credentials find(Filter filter, String pageToken, int pageSize) {
//        // TODO: Implement
//    }

//    public org.catalytic.sdk.entities.Credentials create(String team, String email, String password) {
//        // TODO: Implement
//    }

    /**
     * Revoke credentials for a specific id
     *
     * @param id    The id of the credentials to revoke
     * @return ?????
     */
    // TODO: Add return type
//    public org.catalytic.sdk.entities.Credentials revoke(String id) {
//        // TODO: Implement
//    }

    /**
     * Create a Credentials object from an internal File object
     *
     * @param internalCredentials   The internal credentials to create a Credentials object from
     * @return                      The created Credentials object
     */
    private org.catalytic.sdk.entities.Credentials createCredentials(org.catalytic.sdk.generated.model.Credentials internalCredentials) {
        org.catalytic.sdk.entities.Credentials credentials = new org.catalytic.sdk.entities.Credentials(
                internalCredentials.getId(),
                internalCredentials.getDomain(),
                internalCredentials.getName(),
                internalCredentials.getType().getValue(),
                internalCredentials.getToken(),
                internalCredentials.getSecret(),
                internalCredentials.getEnvironment(),
                internalCredentials.getOwner()
        );
        return credentials;
    }
}
