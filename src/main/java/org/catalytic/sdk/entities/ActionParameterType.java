package org.catalytic.sdk.entities;

public enum ActionParameterType {

    UNDEFINED("undefined"),
    TEXT("text"),
    INTEGER("integer"),
    DECIMAL("decimal"),
    DATE("date"),
    DATETIME("dateTime"),
    BOOLEAN("boolean"),
    SINGLECHOICE("singleChoice"),
    MULTIPLECHOICE("multipleChoice"),
    JSON("json"),
    FILE("file"),
    FILES("files"),
    TABLE("table"),
    WORKFLOW("workflow"),
    INSTANCE("instance"),
    USER("user"),
    ARRAY("array"),
    FIELDS("fields"),
    METADATA("metadata");

    private String value;

    ActionParameterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
