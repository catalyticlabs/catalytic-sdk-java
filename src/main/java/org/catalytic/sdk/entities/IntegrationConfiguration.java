package org.catalytic.sdk.entities;

import java.net.URI;
import java.util.List;

/**
 * An IntegrationConfiguration object
 */
public class IntegrationConfiguration {

    private String clientId;
    private String clientSecret;
    private String tokenPath;
    private String revokePath;
    private URI site;
    private URI authorizedBaseUrl;
    private List<String> scopes;
    private Boolean useBodyAuth;

    public IntegrationConfiguration(String clientId, String clientSecret, String tokenPath, String revokePath, URI site, URI authorizedBaseUrl, List<String> scopes, Boolean useBodyAuth) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.tokenPath = tokenPath;
        this.revokePath = revokePath;
        this.site = site;
        this.authorizedBaseUrl = authorizedBaseUrl;
        this.scopes = scopes;
        this.useBodyAuth = useBodyAuth;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getTokenPath() {
        return tokenPath;
    }

    public void setTokenPath(String tokenPath) {
        this.tokenPath = tokenPath;
    }

    public String getRevokePath() {
        return revokePath;
    }

    public void setRevokePath(String revokePath) {
        this.revokePath = revokePath;
    }

    public URI getSite() {
        return site;
    }

    public void setSite(URI site) {
        this.site = site;
    }

    public URI getAuthorizedBaseUrl() {
        return authorizedBaseUrl;
    }

    public void setAuthorizedBaseUrl(URI authorizedBaseUrl) {
        this.authorizedBaseUrl = authorizedBaseUrl;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public Boolean getUseBodyAuth() {
        return useBodyAuth;
    }

    public void setUseBodyAuth(Boolean useBodyAuth) {
        this.useBodyAuth = useBodyAuth;
    }

    @Override
    public String toString() {
        return "IntegrationConfiguration{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", tokenPath='" + tokenPath + '\'' +
                ", revokePath='" + revokePath + '\'' +
                ", site=" + site +
                ", authorizedBaseUrl=" + authorizedBaseUrl +
                ", scopes=" + scopes +
                ", useBodyAuth=" + useBodyAuth +
                '}';
    }
}
