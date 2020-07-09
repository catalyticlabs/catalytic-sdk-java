package org.catalytic.sdk.clients;

import org.catalytic.sdk.exceptions.AccessTokenNotFoundException;

public class ClientHelpers {

    /**
     * Verifies that the passed in token is not null
     *
     * @param token                         The token to verify is not null
     * @throws AccessTokenNotFoundException If the token is null
     */
    public static void verifyAccessTokenExists(String token) throws AccessTokenNotFoundException {
        if (token == null) {
            throw new AccessTokenNotFoundException("Access Token not found. Instantiate CatalyticClient with one of the authentication options or call CatalyticClient.setToken()");
        }
    }
}
