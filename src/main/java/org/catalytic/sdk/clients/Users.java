package org.catalytic.sdk.clients;

import org.catalytic.sdk.ApiClient;
import org.catalytic.sdk.ApiException;
import org.catalytic.sdk.ConfigurationUtils;
import org.catalytic.sdk.api.UsersApi;
import org.catalytic.sdk.entities.User;
import org.catalytic.sdk.entities.UsersPage;
import org.catalytic.sdk.exceptions.InternalErrorException;
import org.catalytic.sdk.search.Filter;
import org.catalytic.sdk.search.SearchUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Users client
 */
public class Users {

    private UsersApi usersApi;

    public Users(String secret) {
        ApiClient apiClient = ConfigurationUtils.getApiClient(secret);
        this.usersApi = new UsersApi(apiClient);
    }

    /**
     * Get a user by either id, email, or username
     *
     * @param identifier                The id, email, or username of the user to get
     * @return                          The User object
     * @throws InternalErrorException   If any error getting a user
     */
    public User get(String identifier) throws InternalErrorException {
        org.catalytic.sdk.model.User internalUser = null;
        try {
            internalUser = this.usersApi.getUser(identifier);
        } catch (ApiException e) {
            if (e.getCode() == 404) {
                throw new InternalErrorException("User with id, email, or username " + identifier + " not found", e);
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
     */
    public UsersPage find() throws InternalErrorException {
        return find(null, null, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param pageToken                 The token of the page to fetch
     * @return                          A UsersPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     */
    public UsersPage find(String pageToken) throws InternalErrorException {
        return this.find(null, pageToken, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param filter                    The filter to search users by
     * @return                          A UsersPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     */
    public UsersPage find(Filter filter) throws InternalErrorException {
        return find(filter, null, null);
    }

    /**
     * Finds users by a variety of filters
     *
     * @param filter                    The filter to search users by
     * @param pageToken                 The token of the page to fetch
     * @return                          A UsersPage object which contains the results
     * @throws InternalErrorException   If any error finding users
     */
    public UsersPage find(Filter filter, String pageToken) throws InternalErrorException {
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
     */
    public UsersPage find(Filter filter, String pageToken, Integer pageSize) throws InternalErrorException {
        String text = null;

        if (filter != null) {
            text = SearchUtils.getSearchCriteriaValueByKey(filter.searchFilters, "text");
        }

        org.catalytic.sdk.model.UsersPage results;
        List<User> users = new ArrayList<User>();

        try {
            results = this.usersApi.findUsers(text, null, null, null, null, null, null, pageToken, pageSize);
        } catch (ApiException e) {
            throw new InternalErrorException("Unable to find users", e);
        }

        for (org.catalytic.sdk.model.User internalUser : results.getUsers()) {
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
    private User createUser(org.catalytic.sdk.model.User internalUser) {
        User user = new User(
                internalUser.getId(),
                internalUser.getUsername(),
                internalUser.getEmail(),
                internalUser.getFullName()
        );
        return user;
    }
}
