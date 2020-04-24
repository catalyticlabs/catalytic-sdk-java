package org.catalytic.sdk.entities;

/**
 * Contains all the possible values for the status of an instance step
 */
public enum InstanceStepStatus {

    PENDING("pending"),
    ACTIVE("active"),
    COMPLETED("completed"),
    CANCELLED("cancelled"),
    SNOOZED("snoozed"),
    SKIPPED("skipped"),
    ERROR("error");

    private String value;

    InstanceStepStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
