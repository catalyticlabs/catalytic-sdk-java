package org.catalytic.sdk.entities;

import java.util.List;

/**
 * An object which represents a page of credentials
 */
public class CredentialsPage extends Page {

    private List<Credentials> credentials;

    public CredentialsPage(List<Credentials> credentials, int count) {
        this.credentials = credentials;
        this.count = count;
    }

    public CredentialsPage(List<Credentials> credentials, int count, String nextPageToken) {
        this.credentials = credentials;
        this.count = count;
        this.nextPageToken = nextPageToken;
    }

    public List<Credentials> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credentials> credentials) {
        this.credentials = credentials;
    }
}