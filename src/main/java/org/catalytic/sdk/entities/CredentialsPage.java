package org.catalytic.sdk.entities;

/**
 * An object which represents a page of credentials
 */
public class CredentialsPage extends Page {

    private Credentials[] credentials;

    public CredentialsPage(Credentials[] credentials, int count) {
        this.credentials = credentials;
        this.count = count;
    }

    public CredentialsPage(Credentials[] credentials, int count, String nextPageToken) {
        this.credentials = credentials;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public Credentials[] getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials[] credentials) {
        this.credentials = credentials;
    }
}