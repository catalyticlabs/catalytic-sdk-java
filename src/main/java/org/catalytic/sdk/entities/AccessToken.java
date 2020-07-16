package org.catalytic.sdk.entities;

import java.util.UUID;

/**
 * An Access Token object
 */
public class AccessToken {

    private UUID id;
    private String domain;
    private String name;
    private String type;
    private String token;
    private String secret;
    private String environment;
    private String owner;

    public AccessToken(UUID id, String domain, String type, String name, String token, String secret, String environment, String owner) {
        this.id = id;
        this.domain = domain;
        this.name = name;
        this.type = type;
        this.token = token;
        this.secret = secret;
        this.environment = environment;
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "id=" + id +
                ", domain='" + domain + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", token='" + token + '\'' +
                ", secret='" + secret + '\'' +
                ", environment='" + environment + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}