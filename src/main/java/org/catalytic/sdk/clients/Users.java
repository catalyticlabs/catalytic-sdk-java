package org.catalytic.sdk.clients;

import org.apache.logging.log4j.Logger;
import org.catalytic.sdk.CatalyticLogger;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.entities.User;
import org.catalytic.sdk.entities.UsersPage;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.exceptions.UnauthorizedException;
import org.catalytic.sdk.exceptions.UserNotFoundException;
import org.catalytic.sdk.generated.ApiClient;
import org.catalytic.sdk.generated.ApiException;
import org.catalytic.sdk.generated.api.UsersApi;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Users client
 */
public class Users {

    private static final Logger log = CatalyticLogger.getLogger(Users.class);
    private UsersApi usersApi;

    public Users(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.usersApi = new UsersApi(apiClient);
    }

    /**
     * Constructor used for unit testing.
     *
     * Allows you to pass in a mock UsersApi
     *
     * @param usersApi The mocked UsersApi
     */
    public Users(UsersApi usersApi) {
        this.usersApi = usersApi;
    }

    /**
     * Get a user by either id, email, or username
     *
     * @param identifier                The id, email, or username of the user to get
     * @return                          The User object
     * @throws UserNotFoundException    If user is not found
     * @throws InternalErrorException   If any error getting a user
     * @throws UnauthorizedException    If unauthorized
     */
    public User get(String identifier) throws InternalErrorException, UserNotFoundException, UnauthorizedException {
        org.catalytic.sdk.generated.model.User internalUser = null;
        try {
            log.debug("Getting user with identifier {}", () -> identifier);
            internalUser = this.usersApi.getUser(identifier);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
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
     * @return                          A UsersPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public UsersPage find() throws InternalErrorException, UnauthorizedException {
        return find(null, null, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          A UsersPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public UsersPage find(String pageToken) throws InternalErrorException, UnauthorizedException {
        return this.find(null, pageToken, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param filter                    The filter to search users by
     * @return                          A UsersPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public UsersPage find(Filter filter) throws InternalErrorException, UnauthorizedException {
        return find(filter, null, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param filter                    The filter to search users by
     * @param pageToken                 The token of the page to fetch
     * @return                          A UsersPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public UsersPage find(Filter filter, String pageToken) throws InternalErrorException, UnauthorizedException {
        return find(filter, pageToken, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param filter                    The filter to search users by
     * @param pageToken                 The token of the page to fetch
     * @param pageSize                  The number of users per page to fetch
     * @return                          A UsersPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     * @throws UnauthorizedException    If unauthorized
     */
    public UsersPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException, UnauthorizedException {
        org.catalytic.sdk.generated.model.UsersPage results;
        List<User> users = new ArrayList<>();
        String text = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
        }

        try {
            log.debug("Finding users with text: {}", text);
            results = this.usersApi.findUsers(text, null, null, null, null, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            if (e.getCode() == 401) {
                throw new UnauthorizedException();
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
                internalUser.getUsername(),
                internalUser.getEmail(),
                internalUser.getFullName()
        );
        return user;
    }
}
