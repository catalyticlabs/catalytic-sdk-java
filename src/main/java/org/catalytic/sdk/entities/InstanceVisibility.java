package org.catalytic.sdk.entities;

public enum InstanceVisibility {

    OPEN("open"),
    RESTRICTED("restricted");

    private String value;

    InstanceVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
