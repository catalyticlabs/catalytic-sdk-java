package org.catalytic.sdk.entities;

public enum FieldVisibility {

    PUBLIC("public"),
    INTERNAL("internal"),
    CONFIDENTIAL("confidential"),
    HIGHLYCONFIDENTIAL("highlyConfidential");

    private String value;

    FieldVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
