package org.catalytic.sdk.entities;

public enum TokenType {

    USER("user"),
    ACTIONWORKER("actionWorker");

    private String value;

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
