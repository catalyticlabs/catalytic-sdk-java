package org.catalytic.sdk.entities;

/**
 * A PushbotExport object
 */
public class PushbotExport {

    private String id;
    private String name;
    private String fileId;
    private String errorMessage;

    public PushbotExport(String id, String name, String fileId, String errorMessage) {
        this.id = id;
        this.name = name;
        this.fileId = fileId;
        this.errorMessage = errorMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}