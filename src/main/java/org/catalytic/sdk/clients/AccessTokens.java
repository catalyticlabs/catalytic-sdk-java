package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.AccessToken;
import org.catalytic.sdk.entities.AccessTokensPage;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.AccessTokensApi;
import org.catalytic.sdk.generated.model.AccessTokenCreationRequest;
import org.catalytic.sdk.generated.model.AccessTokenCreationWithEmailAndPasswordRequest;
import org.catalytic.sdk.generated.model.WaitForAccessTokenApprovalRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Credentials client
 */
public class AccessTokens {

    private String token;
    private static final Logger log = CatalyticLogger.getLogger(AccessTokens.class);
    private AccessTokensApi accessTokensApi;

    public AccessTokens(String token) {
        this.token = token;
        ApiClient apiClient = ConfigurationUtils.getApiClient(token);
        this.accessTokensApi = new AccessTokensApi(apiClient);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in a mock UserCredentialsApi
     *
     * @param token             The token to be used
     * @param accessTokensApi   The mocked UserCredentialsApi
     */
    public AccessTokens(String token, AccessTokensApi accessTokensApi) {
        this.token = token;
        this.accessTokensApi = accessTokensApi;
    }

    /**
     * Get all Access Tokens
     *
     * @return                              An AccessTokensPage object which contains all the Access Tokens
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    public AccessTokensPage list() throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        AccessTokensPage results;

        // Get first page of Access Tokens
        AccessTokensPage accessTokensPage = getPageOfAccessTokens(null);
        results = accessTokensPage;

        // Loop through the rest of the pages and add the Access Tokens to results
        while(!accessTokensPage.getNextPageToken().isEmpty()) {
            accessTokensPage = getPageOfAccessTokens(accessTokensPage.getNextPageToken());
            results.addAccessTokens(accessTokensPage.getAccessTokens(), accessTokensPage.getNextPageToken());
        }

        return results;
    }

    /**
     * Get Access Token by id
     *
     * @param id                            The id of the Access Token to get
     * @return                              The Access Token object
     * @throws InternalErrorException       If error fetching the Access Token
     * @throws AccessTokenNotFoundException If Access Token with id does not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public AccessToken get(String id) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.AccessToken internalAccessToken;
        try {
            log.debug("Getting Access Token with id {}", () -> id);
            internalAccessToken = this.accessTokensApi.getAccessToken(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new AccessTokenNotFoundException("Access Token with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to get Access Token", e);
        }
        AccessToken accessToken = createAccessToken(internalAccessToken);
        return accessToken;
    }

    /**
     * Create new Access Token
     *
     * @param teamName  The team name or hostname of your Catalytic team
     * @param email     The email you use to login to Catalytic
     * @param password  The password you use to login to Catalytic
     * @return          The newly created Access Token
     * @throws UnauthorizedException    If unauthorized
     * @throws InternalErrorException   If any errors creating the Access Token
     */
    public AccessToken create(String teamName, String email, String password) throws UnauthorizedException, InternalErrorException {
        return create(teamName, email, password, null);
    }

    /**
     * Create new Access Token
     *
     * @param teamName  The team name or hostname of your Catalytic team
     * @param email     The email you use to login to Catalytic
     * @param password  The password you use to login to Catalytic
     * @param name      A name to identify the Access Token
     * @return          The newly created Access Token
     * @throws UnauthorizedException    If unauthorized
     * @throws InternalErrorException   If any errors creating the Access Token
     */
    public AccessToken create(String teamName, String email, String password, String name) throws UnauthorizedException, InternalErrorException {
        org.catalytic.sdk.generated.model.AccessToken internalAccessToken;
        String domain = getDomainFromTeamName(teamName);
        AccessTokenCreationWithEmailAndPasswordRequest request = new AccessTokenCreationWithEmailAndPasswordRequest();
        request.setDomain(domain);
        request.setEmail(email);
        request.setPassword(password);
        request.setName(name);
        try {
            log.debug("Creating Access Token with email {}, teamName {}, and name {}", teamName, email, name);
            internalAccessToken = this.accessTokensApi.createAndApproveAccessToken(request);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to create Access Token", e);
        }

        AccessToken accessToken = createAccessToken(internalAccessToken);
        return accessToken;
    }

    /**
     * Create new Access Token
     *
     * The Access Token will need to be approved before it can be used
     *
     * @param teamName                  The team name or hostname of your Catalytic team
     * @return                          The newly created Access Token
     * @throws UnauthorizedException    If unauthorized
     * @throws InternalErrorException   If any errors creating the Access Token
     */
    public AccessToken createWithWebApprovalFlow(String teamName) throws UnauthorizedException, InternalErrorException {
        return createWithWebApprovalFlow(teamName, null);
    }

    /**
     * Create new Access Token
     *
     * The Access Token will need to be approved before it can be used
     *
     * @param teamName                  The team name or hostname of your Catalytic team
     * @param name                      A name to identify the Access Token
     * @return                          The newly created Access Token
     * @throws UnauthorizedException    If unauthorized
     * @throws InternalErrorException   If any errors creating the Access Token
     */
    public AccessToken createWithWebApprovalFlow(String teamName, String name) throws UnauthorizedException, InternalErrorException {
        org.catalytic.sdk.generated.model.AccessToken internalAccessToken;
        String domain = getDomainFromTeamName(teamName);
        AccessTokenCreationRequest request = new AccessTokenCreationRequest();
        request.setDomain(domain);
        request.setName(name);
        try {
            log.debug("Creating Access Token with teamName {} and name {}", teamName, name);
            internalAccessToken = this.accessTokensApi.createAccessToken(request);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to create Access Token", e);
        }

        AccessToken accessToken = createAccessToken(internalAccessToken);
        return accessToken;
    }

    /**
     * Gets the url that the user must visit to approve the Access Token created with createWithWebApprovalFlow
     *
     * @param accessToken       The Access Token to get the approval url from
     * @return                  The url a user must visit to approve the created Access Token
     * @throws InternalErrorException
     */
    public String getApprovalUrl(AccessToken accessToken) throws InternalErrorException {
        return getApprovalUrl(accessToken, "Catalytic SDK");
    }

    /**
     * Gets the url that the user must visit to approve the Access Token created with createWithWebApprovalFlow
     *
     * @param accessToken       The Access Token to get the approval url from
     * @param applicationName   The name of the application to label the token with
     * @return                  The url a user must visit to approve the created Access Token
     * @throws InternalErrorException
     */
    public String getApprovalUrl(AccessToken accessToken, String applicationName) throws InternalErrorException {
        try {
            return "https://" + accessToken.getDomain() + "/credentials/approve?userTokenID=" + accessToken.getId() + "&application=" + URLEncoder.encode(applicationName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalErrorException("Unable to generate approval url");
        }
    }

    /**
     * Waits for an Access Token to be approved
     *
     * @param accessToken       The Access Token to wait for approval of
     * @param waitTimeInMillis  The amount of time to wait in milliseconds before timing out
     * @throws InternalErrorException
     * @throws UnauthorizedException
     */
    public void waitForApproval(AccessToken accessToken, Integer waitTimeInMillis) throws InternalErrorException, UnauthorizedException {
        WaitForAccessTokenApprovalRequest request = new WaitForAccessTokenApprovalRequest();
        request.setToken(accessToken.getToken());
        request.setWaitTimeMillis(waitTimeInMillis);
        try {
            this.accessTokensApi.waitForAccessTokenApproval(request);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to wait for AccessToken approval", e);
        }
    }

    /**
     * Revoke Access Token by id
     *
     * @param id                            The id of the Access Token to revoke
     * @return                              The Access Token that was revoked
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If error fetching Access Token
     * @throws AccessTokenNotFoundException If Access Token with id do not exist
     * @throws UnauthorizedException        If unauthorized
     */
    public AccessToken revoke(String id) throws UnauthorizedException, AccessTokenNotFoundException, InternalErrorException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.AccessToken internalAccessToken;
        try {
            log.debug("Revoking Access Token with id {}", () -> id);
            internalAccessToken = this.accessTokensApi.revokeAccessToken(id);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new AccessTokenNotFoundException("Access Token with id " + id + " not found", e);
            }
            throw new InternalErrorException("Unable to revoke Access Token", e);
        }

        AccessToken accessToken = createAccessToken(internalAccessToken);
        return accessToken;
    }

    /**
     * Create an Access Token object from an internal File object
     *
     * @param internalAccessToken   The internal Access Token to create an Access Token object from
     * @return                      The created Access Token object
     */
    private AccessToken createAccessToken(org.catalytic.sdk.generated.model.AccessToken internalAccessToken) {
        AccessToken accessToken = new AccessToken(
                internalAccessToken.getId(),
                internalAccessToken.getDomain(),
                internalAccessToken.getType().getValue(),
                internalAccessToken.getName(),
                internalAccessToken.getToken(),
                internalAccessToken.getSecret(),
                internalAccessToken.getEnvironment(),
                internalAccessToken.getOwner()
        );
        return accessToken;
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
        String validTeamNameRegex = "^[a-z0-9][a-z0-9-_]+$";
        if (!teamName.matches(validTeamNameRegex)) {
            throw new IllegalArgumentException("Invalid teamName");
        }
    }

    /**
     * Get a page of Access Tokens
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          An AccessTokensPage object which contains the current page of Access Tokens
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    private AccessTokensPage getPageOfAccessTokens(String pageToken) throws UnauthorizedException, InternalErrorException {
        org.catalytic.sdk.generated.model.AccessTokensPage internalAccessTokensPage;
        List<AccessToken> accessTokens = new ArrayList<>();

        try {
            log.debug("Getting all Access Tokens");
            internalAccessTokensPage = this.accessTokensApi.listAccessTokens(pageToken, null);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to get Access Tokens", e);
        }

        for (org.catalytic.sdk.generated.model.AccessToken internalAccessToken : internalAccessTokensPage.getAccessTokens()) {
            AccessToken accessToken = createAccessToken(internalAccessToken);
            accessTokens.add(accessToken);
        }

        AccessTokensPage accessTokensPage = new AccessTokensPage(accessTokens, internalAccessTokensPage.getCount(), internalAccessTokensPage.getNextPageToken());
        return accessTokensPage;
    }
}
