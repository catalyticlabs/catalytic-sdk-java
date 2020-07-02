package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of Access Tokens
 */
public class AccessTokensPage extends Page {

    private List<AccessToken> credentials;

    public AccessTokensPage(List<AccessToken> credentials, int count) {
        this.credentials = credentials;
        this.count = count;
    }

    public AccessTokensPage(List<AccessToken> credentials, int count, String nextPageToken) {
        this.credentials = credentials;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<AccessToken> getAccessTokens() {
        return credentials;
    }

    public void setAccessTokens(List<AccessToken> credentials) {
        this.credentials = credentials;
    }
}