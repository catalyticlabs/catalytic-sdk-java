package org.catalytic.sdk.entities;

public enum CredentialType {

    USER("user"),
    ACTIONWORKER("actionWorker");

    private String value;

    CredentialType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
