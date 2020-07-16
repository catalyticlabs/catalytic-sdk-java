package org.catalytic.sdk.entities;

import java.util.UUID;

/**
 * A WorkflowExport object
 */
public class WorkflowExport {

    private UUID id;
    private String name;
    private UUID fileId;
    private String errorMessage;

    public WorkflowExport(UUID id, String name, UUID fileId, String errorMessage) {
        this.id = id;
        this.name = name;
        this.fileId = fileId;
        this.errorMessage = errorMessage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getFileId() {
        return fileId;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "WorkflowExport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fileId=" + fileId +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}