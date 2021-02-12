package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of Access Tokens
 */
public class AccessTokensPage extends Page {

    private List<AccessToken> accessTokens;

    public AccessTokensPage(List<AccessToken> accessTokens, int count) {
        this.accessTokens = accessTokens;
        this.count = count;
    }

    public AccessTokensPage(List<AccessToken> accessTokens, int count, String nextPageToken) {
        this.accessTokens = accessTokens;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<AccessToken> getAccessTokens() {
        return accessTokens;
    }

    public void setAccessTokens(List<AccessToken> credentials) {
        this.accessTokens = credentials;
    }

    /**
     * Add a list of AccessTokens.
     *
     * Note that this is different than setting a list of AccessTokens.
     *
     * @param accessTokens  The Access Tokens to add
     * @param nextPageToken The next page token
     */
    public void addAccessTokens(List<AccessToken> accessTokens, String nextPageToken) {
        this.accessTokens.addAll(accessTokens);
        this.count = this.count + accessTokens.size();
        this.nextPageToken = nextPageToken;
    }

    @Override
    public String toString() {
        return "AccessTokensPage{" +
                "credentials=" + accessTokens +
                ", count=" + count +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}