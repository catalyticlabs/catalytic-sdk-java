package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.User;
import org.catalytic.sdk.entities.UsersPage;
import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.exceptions.UserNotFoundException;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.UsersApi;
import org.catalytic.sdk.generated.model.BoolSearchExpression;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;
import org.catalytic.sdk.search.UserSearchClause;

import java.util.ArrayList;
import java.util.List;

/**
 * Users client
 */
public class Users extends BaseClient {

    private String token;
    private static final Logger log = CatalyticLogger.getLogger(Users.class);
    private UsersApi usersApi;

    public Users(String token) {
        this.token = token;
        ApiClient apiClient = ConfigurationUtils.getApiClient(this.token);
        this.usersApi = new UsersApi(apiClient);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in a mock UsersApi
     *
     * @param token     The token to be used
     * @param usersApi  The mocked UsersApi
     */
    public Users(String token, UsersApi usersApi) {
        this.token = token;
        this.usersApi = usersApi;
    }

    /**
     * Get a user by either id, email, or username
     *
     * @param identifier                    The id, email, or username of the user to get
     * @return                              The User object
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws UserNotFoundException        If user is not found
     * @throws InternalErrorException       If any error getting a user
     * @throws UnauthorizedException        If unauthorized
     */
    public User get(String identifier) throws InternalErrorException, UserNotFoundException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.User internalUser;
        try {
            log.debug("Getting user with identifier {}", () -> identifier);
            internalUser = this.usersApi.getUser(identifier);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            } else if (e.getCode() == 404) {
                throw new UserNotFoundException("User with id, email, or username " + identifier + " not found", e);
            }
            throw new InternalErrorException("Unable to get user", e);
        }
        User user = createUser(internalUser);
        return user;
    }

    /**
     * Finds all users
     *
     * @deprecated
     * Use {@link Users#list()}search instead
     *
     * @return                              A UsersPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    @Deprecated
    public UsersPage find() throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return find(null, null, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @deprecated
     * Use {@link Users#search(UserSearchClause, String, Integer)}search instead
     *
     * @param pageToken                     The token of the page to fetch
     * @return                              A UsersPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    @Deprecated
    public UsersPage find(String pageToken) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return this.find(null, pageToken, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @deprecated
     * Use {@link Users#search(UserSearchClause, String, Integer)}search instead
     *
     * @param filter                        The filter to search users by
     * @return                              A UsersPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    @Deprecated
    public UsersPage find(Filter filter) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return find(filter, null, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @deprecated
     * Use {@link Users#search(UserSearchClause, String, Integer)}search instead
     *
     * @param filter                        The filter to search users by
     * @param pageToken                     The token of the page to fetch
     * @return                              A UsersPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    @Deprecated
    public UsersPage find(Filter filter, String pageToken) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        return find(filter, pageToken, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @deprecated
     * Use {@link Users#search(UserSearchClause, String, Integer)}search instead
     *
     * @param filter                        The filter to search users by
     * @param pageToken                     The token of the page to fetch
     * @param pageSize                      The number of users per page to fetch
     * @return                              A UsersPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    @Deprecated
    public UsersPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException, UnauthorizedException, AccessTokenNotFoundException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.UsersPage results;
        List<User> users = new ArrayList<>();
        String text = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
        }

        try {
            log.debug("Finding users with text: {}", text);
            results = this.usersApi.findUsers(text, null, null, null, null, null, null, null, null, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find users", e);
        }

        for (org.catalytic.sdk.generated.model.User internalUser : results.getUsers()) {
            User user = createUser(internalUser);
            users.add(user);
        }

        UsersPage usersPage = new UsersPage(users, results.getCount(), results.getNextPageToken());
        return usersPage;
    }

    /**
     * Get all users
     *
     * @return                              A UsersPage object which contains the all the users from all pages of results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    public UsersPage list() throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        // Get the first page of users
        UsersPage usersPage = search(null, null);
        UsersPage results = usersPage;

        // Loop through the rest of the pages and add the users to results
        while(!usersPage.getNextPageToken().isEmpty()) {
            usersPage = search(null, usersPage.getNextPageToken());
            results.addUsers(usersPage.getUsers(), usersPage.getNextPageToken());
        }

        return results;
    }

    /**
     * Finds users by a variety of filters
     *
     * @param userSearchClause              The search criteria to search users by
     * @return                              A UsersPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    public UsersPage search(UserSearchClause userSearchClause) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(userSearchClause, null, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param userSearchClause              The search criteria to search users by
     * @param pageToken                     The token of the page to fetch
     * @return                              A UsersPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    public UsersPage search(UserSearchClause userSearchClause, String pageToken) throws InternalErrorException, AccessTokenNotFoundException, UnauthorizedException {
        return search(userSearchClause, pageToken, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param userSearchClause              The search criteria to search users by
     * @param pageToken                     The token of the page to fetch
     * @param pageSize                      The number of users to fetch per page
     * @return                              A UsersPage object which contains the results
     * @throws AccessTokenNotFoundException If Access Token is not found or if the client was instantiated without an Access Token
     * @throws InternalErrorException       If any error finding users
     * @throws UnauthorizedException        If unauthorized
     */
    public UsersPage search(UserSearchClause userSearchClause, String pageToken, Integer pageSize) throws AccessTokenNotFoundException, InternalErrorException, UnauthorizedException {
        ClientHelpers.verifyAccessTokenExists(this.token);

        org.catalytic.sdk.generated.model.UsersPage results;
        List<User> users = new ArrayList<>();

        if (userSearchClause == null) {
            userSearchClause = new UserSearchClause();
        }

        org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(userSearchClause.getId());
        org.catalytic.sdk.generated.model.StringSearchExpression internalEmail = createInternalStringSearchExpression(userSearchClause.getEmail());
        org.catalytic.sdk.generated.model.StringSearchExpression internalFullName = createInternalStringSearchExpression(userSearchClause.getFullName());
        BoolSearchExpression internalIsTeamAdmin = createInternalBooleanSearchExpression(userSearchClause.getIsTeamAdmin());
        BoolSearchExpression internalIsDeactivated = createInternalBooleanSearchExpression(userSearchClause.getIsDeactivated());
        BoolSearchExpression internalIsLockedOut = createInternalBooleanSearchExpression(userSearchClause.getIsLockedOut());
        org.catalytic.sdk.generated.model.DateTimeSearchExpression internalCreatedDate = createInternalDateTimeSearchExpression(userSearchClause.getCreatedDate());

        List<org.catalytic.sdk.generated.model.UserSearchClause> internalAnd = createInternalUserSearchClause(userSearchClause.getAnd());
        List<org.catalytic.sdk.generated.model.UserSearchClause> internalOr = createInternalUserSearchClause(userSearchClause.getOr());

        org.catalytic.sdk.generated.model.UserSearchClause userSearchRequest = new org.catalytic.sdk.generated.model.UserSearchClause();
        userSearchRequest.setAnd(internalAnd);
        userSearchRequest.setOr(internalOr);
        userSearchRequest.setId(internalId);
        userSearchRequest.setEmail(internalEmail);
        userSearchRequest.setFullName(internalFullName);
        userSearchRequest.setIsTeamAdmin(internalIsTeamAdmin);
        userSearchRequest.setIsDeactivated(internalIsDeactivated);
        userSearchRequest.setIsLockedOut(internalIsLockedOut);
        userSearchRequest.setCreatedDate(internalCreatedDate);

        try {
            log.debug("Finding users with criteria {}", () -> userSearchRequest);
            results = this.usersApi.searchUsers(pageToken, pageSize, userSearchRequest);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException(e);
            }
            throw new InternalErrorException("Unable to find users", e);
        }

        for (org.catalytic.sdk.generated.model.User internalUser : results.getUsers()) {
            User user = createUser(internalUser);
            users.add(user);
        }

        UsersPage usersPage = new UsersPage(users, results.getCount(), results.getNextPageToken());
        return usersPage;
    }

    /**
     * Create a User object from an internal User object
     *
     * @param internalUser  The internal user to create a User object from
     * @return User         The created User object
     */
    private User createUser(org.catalytic.sdk.generated.model.User internalUser) {
        User user = new User(
                internalUser.getId(),
                internalUser.getTeamName(),
                internalUser.getUsername(),
                internalUser.getEmail(),
                internalUser.getFullName(),
                internalUser.getIsTeamAdmin(),
                internalUser.getIsDeactivated(),
                internalUser.getIsLockedOut(),
                internalUser.getCreatedDate()
        );
        return user;
    }

    /**
     * Create an internal UserSearchClause from an external UserSearchClause
     *
     * @param userSearchClause  The external UserSearchClause to create an internal one from
     * @return                  An internal UserSearchClause
     */
    private List<org.catalytic.sdk.generated.model.UserSearchClause> createInternalUserSearchClause(List<UserSearchClause> userSearchClause) {
        List<org.catalytic.sdk.generated.model.UserSearchClause> internalUserSearchClauses = null;

        if (userSearchClause != null) {

            internalUserSearchClauses = new ArrayList<>();

            for (UserSearchClause searchClause : userSearchClause) {
                org.catalytic.sdk.generated.model.GuidSearchExpression internalId = createInternalGuidSearchExpression(searchClause.getId());
                org.catalytic.sdk.generated.model.StringSearchExpression internalEmail = createInternalStringSearchExpression(searchClause.getEmail());
                org.catalytic.sdk.generated.model.StringSearchExpression internalFullName = createInternalStringSearchExpression(searchClause.getFullName());
                org.catalytic.sdk.generated.model.BoolSearchExpression internalIsTeamAdmin = createInternalBooleanSearchExpression(searchClause.getIsTeamAdmin());
                org.catalytic.sdk.generated.model.BoolSearchExpression internalIsDeactivated = createInternalBooleanSearchExpression(searchClause.getIsDeactivated());
                org.catalytic.sdk.generated.model.BoolSearchExpression internalIsLockedOut = createInternalBooleanSearchExpression(searchClause.getIsLockedOut());
                org.catalytic.sdk.generated.model.DateTimeSearchExpression internalCreatedDate = createInternalDateTimeSearchExpression(searchClause.getCreatedDate());

                org.catalytic.sdk.generated.model.UserSearchClause internalUserSearchClause = new org.catalytic.sdk.generated.model.UserSearchClause();
                internalUserSearchClause.setId(internalId);
                internalUserSearchClause.setEmail(internalEmail);
                internalUserSearchClause.setFullName(internalFullName);
                internalUserSearchClause.setIsTeamAdmin(internalIsTeamAdmin);
                internalUserSearchClause.setIsDeactivated(internalIsDeactivated);
                internalUserSearchClause.setIsLockedOut(internalIsLockedOut);
                internalUserSearchClause.setCreatedDate(internalCreatedDate);
                List<org.catalytic.sdk.generated.model.UserSearchClause> internalAnd = createInternalUserSearchClause(searchClause.getAnd());
                List<org.catalytic.sdk.generated.model.UserSearchClause> internalOr = createInternalUserSearchClause(searchClause.getOr());
                internalUserSearchClause.setAnd(internalAnd);
                internalUserSearchClause.setOr(internalOr);
                internalUserSearchClauses.add(internalUserSearchClause);
            }
        }
        return internalUserSearchClauses;
    }
}
