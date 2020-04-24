package org.catalytic.sdk.entities;

/**
 * Contains all the possible values for the status of an instance
 */
public enum InstanceStatus {

    RUNNING("running"),
    COMPLETED("completed"),
    CANCELLED("cancelled");

    private String value;

    InstanceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}