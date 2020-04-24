package org.catalytic.sdk.entities;

public enum FieldType {

    UNDEFINED("undefined"),
    TEXT("text"),
    INTEGER("integer"),
    DECIMAL("decimal"),
    DATE("date"),
    DATETIME("dateTime"),
    JSON("json"),
    BOOL("bool"),
    SINGLECHOICE("singleChoice"),
    MULTIPLECHOICE("multipleChoice"),
    INSTRUCTIONS("instructions"),
    FILE("file"),
    TABLE("table"),
    WORKFLOW("workflow"),
    INSTANCE("instance"),
    USER("user");

    private String value;

    FieldType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
