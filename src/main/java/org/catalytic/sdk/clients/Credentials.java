package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.CredentialsPage;
import org.catalytic.sdk.exceptions.CredentialsNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.AuthenticationApi;
import org.catalytic.sdk.generated.api.UserCredentialsApi;
import org.catalytic.sdk.generated.model.CredentialsCreationRequest;
import org.catalytic.sdk.generated.model.CredentialsCreationWithEmailAndPasswordRequest;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Credentials client
 */
public class Credentials {

    private static final Logger log = CatalyticLogger.getLogger(Credentials.class);
    private UserCredentialsApi userCredentialsApi;
    private AuthenticationApi authenticationApi;

    public Credentials(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.userCredentialsApi = new UserCredentialsApi(apiClient);
        this.authenticationApi = new AuthenticationApi(apiClient);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in a mock UserCredentialsApi
     *
     * @param userCredentialsApi    The mocked UserCredentialsApi
     * @param authenticationApi     The mocked AuthenticationApi
     */
    public Credentials(UserCredentialsApi userCredentialsApi, AuthenticationApi authenticationApi) {
        this.userCredentialsApi = userCredentialsApi;
        this.authenticationApi = authenticationApi;
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
            log.debug("Getting Credentials with id {}", () -> id);
            internalCredentials = this.userCredentialsApi.getCredentials(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
            } else if (e.getCode() == 404) {
                throw new CredentialsNotFoundException("Credentials with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get Credentials", e);
        }
        org.catalytic.sdk.entities.Credentials credentials = createCredentials(internalCredentials);
        return credentials;
    }

    /**
     * Finds all Credentials
     *
     * @return                          A CredentialsPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public CredentialsPage find() throws InternalErrorException, UnauthorizedException {
        return find(null, null, null);
    }

    /**
     * Finds Credentials by a variety of filters
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          A CredentialsPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public CredentialsPage find(String pageToken) throws InternalErrorException, UnauthorizedException {
        return this.find(null, pageToken, null);
    }

    /**
     * Finds Credentials by a variety of filters
     *
     * @param filter                    The filter to search users by
     * @return                          A CredentialsPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public CredentialsPage find(Filter filter) throws InternalErrorException, UnauthorizedException {
        return find(filter, null, null);
    }

    /**
     * Finds Credentials by a variety of filters
     *
     * @param filter                    The filter to search users by
     * @param pageToken                 The token of the page to fetch
     * @return                          A CredentialsPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public CredentialsPage find(Filter filter, String pageToken) throws InternalErrorException, UnauthorizedException {
        return find(filter, pageToken, null);
    }

    /**
     * Finds Credentials by a variety of filters
     *
     * @param filter                    The filter to search users by
     * @param pageToken                 The token of the page to fetch
     * @param pageSize                  The number of users per page to fetch
     * @return                          A CredentialsPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public CredentialsPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException, UnauthorizedException {
        org.catalytic.sdk.generated.model.CredentialsPage results;
        List<org.catalytic.sdk.entities.Credentials> allCredentials = new ArrayList<>();
        String text = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
        }

        try {
            log.debug("Finding users with text: {}", text);
            results = this.userCredentialsApi.findCredentials(text, null, null, null, null, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find users", e);
        }

        for (org.catalytic.sdk.generated.model.Credentials internalCredentials : results.getCredentials()) {
            org.catalytic.sdk.entities.Credentials credentials = createCredentials(internalCredentials);
            allCredentials.add(credentials);
        }

        CredentialsPage credentialsPage = new CredentialsPage(allCredentials, results.getCount(), results.getNextPageToken());
        return credentialsPage;
    }

    /**
     * Create new Credentials
     *
     * @param teamName  The team name or hostname of your Catalytic team
     * @param email     The email you use to login to Catalytic
     * @param password  The password you use to login to Catalytic
     * @return          The newly created Credentials
     * @throws UnauthorizedException    If unauthorized
     * @throws InternalErrorException   If any errors creating the Credentials
     */
    public org.catalytic.sdk.entities.Credentials create(String teamName, String email, String password) throws UnauthorizedException, InternalErrorException {
        return create(teamName, email, password, null);
    }

    /**
     * Create new Credentials
     *
     * @param teamName  The team name or hostname of your Catalytic team
     * @param email     The email you use to login to Catalytic
     * @param password  The password you use to login to Catalytic
     * @param name      A name to identify the Credentials
     * @return          The newly created Credentials
     * @throws UnauthorizedException    If unauthorized
     * @throws InternalErrorException   If any errors creating the Credentials
     */
    public org.catalytic.sdk.entities.Credentials create(String teamName, String email, String password, String name) throws UnauthorizedException, InternalErrorException {
        org.catalytic.sdk.generated.model.Credentials internalCredentials;
        String domain = getDomainFromTeamName(teamName);
        CredentialsCreationWithEmailAndPasswordRequest request = new CredentialsCreationWithEmailAndPasswordRequest();
        request.setDomain(domain);
        request.setEmail(email);
        request.setPassword(password);
        request.setName(name);
        try {
            log.debug("Creating Credentials with email {}, teamName {}, and name {}", teamName, email, name);
            internalCredentials = this.authenticationApi.createAndApproveCredentials(request);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to create Credentials");
        }

        org.catalytic.sdk.entities.Credentials credentials = createCredentials(internalCredentials);
        return credentials;
    }

    /**
     * Create new Credentials
     *
     * The Credentials will need to be approved before they can be used
     *
     * @param teamName                  The team name or hostname of your Catalytic team
     * @return                          The newly created Credentials
     * @throws UnauthorizedException    If unauthorized
     * @throws InternalErrorException   If any errors creating the Credentials
     */
    public org.catalytic.sdk.entities.Credentials createWithWebApprovalFlow(String teamName) throws UnauthorizedException, InternalErrorException {
        return createWithWebApprovalFlow(teamName, null);
    }

    /**
     * Create new Credentials
     *
     * The Credentials will need to be approved before they can be used
     *
     * @param teamName                  The team name or hostname of your Catalytic team
     * @param name                      A name to identify the Credentials
     * @return                          The newly created Credentials
     * @throws UnauthorizedException    If unauthorized
     * @throws InternalErrorException   If any errors creating the Credentials
     */
    public org.catalytic.sdk.entities.Credentials createWithWebApprovalFlow(String teamName, String name) throws UnauthorizedException, InternalErrorException {
        org.catalytic.sdk.generated.model.Credentials internalCredentials;
        String domain = getDomainFromTeamName(teamName);
        CredentialsCreationRequest request = new CredentialsCreationRequest();
        request.setDomain(domain);
        request.setName(name);
        try {
            log.debug("Creating Credentials with teamName {} and name {}", teamName, name);
            internalCredentials = this.authenticationApi.createCredentials(request);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to create Credentials");
        }

        org.catalytic.sdk.entities.Credentials credentials = createCredentials(internalCredentials);
        return credentials;
    }

    /**
     * Gets the url that the user must visit to approve Credentials created with createWithWebApprovalFlow
     *
     * @param credentials       The Credentials to get the approval url from
     * @return                  The url a user must visit to approve the created Credentials
     * @throws InternalErrorException
     */
    public String getApprovalUrl(org.catalytic.sdk.entities.Credentials credentials) throws InternalErrorException {
        return getApprovalUrl(credentials, "Catalytic SDK");
    }

    /**
     * Gets the url that the user must visit to approve Credentials created with createWithWebApprovalFlow
     *
     * @param credentials       The Credentials to get the approval url from
     * @param applicationName   The name of the application to label the token with
     * @return                  The url a user must visit to approve the created Credentials
     * @throws InternalErrorException
     */
    public String getApprovalUrl(org.catalytic.sdk.entities.Credentials credentials, String applicationName) throws InternalErrorException {
        try {
            return "https://" + credentials.getDomain() + "/credentials/approve?userTokenID=" + credentials.getId() + "&application=" + URLEncoder.encode(applicationName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalErrorException("Unable to generate approval url");
        }
    }

    /**
     * Revoke credentials for a specific id
     *
     * @param id                            The id of the Credentials to revoke
     * @return Credentials                  The Credentials that were revoked
     * @throws InternalErrorException       If error fetching Credentials
     * @throws CredentialsNotFoundException If credentials with id do not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public org.catalytic.sdk.entities.Credentials revoke(String id) throws UnauthorizedException, CredentialsNotFoundException, InternalErrorException {
        org.catalytic.sdk.generated.model.Credentials internalCredentials;
        try {
            log.debug("Revoking Credentials with id {}", () -> id);
            internalCredentials = this.userCredentialsApi.revokeCredentials(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new CredentialsNotFoundException("Credentials with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to revoke Credentials", e);
        }

        org.catalytic.sdk.entities.Credentials credentials = createCredentials(internalCredentials);
        return credentials;
    }

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
                internalCredentials.getType().getValue(),
                internalCredentials.getName(),
                internalCredentials.getToken(),
                internalCredentials.getSecret(),
                internalCredentials.getEnvironment(),
                internalCredentials.getOwner()
        );
        return credentials;
    }

    /**
     * Gets the domain or team name validates it
     *
     * @param teamNameOrDomain  The domain or team name to validate
     * @return                  The domain
     */
    private String getDomainFromTeamName(String teamNameOrDomain) {
        // If domain was passed in, validate the teamName and return the domain
        if (teamNameOrDomain.contains(".")) {
            // Split on periods
            String[] pieces = teamNameOrDomain.split("\\.");
            String teamName = pieces[0];
            validateTeamName(teamName);
            return teamNameOrDomain;
        }

        // Otherwise teamName was passed in so validate it, build the domain, and return it
        validateTeamName(teamNameOrDomain);
        return teamNameOrDomain + ".pushbot.com";
    }

    /**
     * Validates the passed in teamName
     *
     * @param teamName                  The teamName to validate
     * @throws IllegalArgumentException If the teamName is not valid
     */
    private void validateTeamName(String teamName) {
//        Pattern validTeamNameRegex = Pattern.compile("^[a-z0-9][a-z0-9-_]+$");
//        Matcher matcher = validTeamNameRegex.matcher(teamName);
//        if (!matcher.find()) {
        String validTeamNameRegex = "^[a-z0-9][a-z0-9-_]+$";
        if (!teamName.matches(validTeamNameRegex)) {
            throw new IllegalArgumentException("Invalid teamName");
        }
    }
}
