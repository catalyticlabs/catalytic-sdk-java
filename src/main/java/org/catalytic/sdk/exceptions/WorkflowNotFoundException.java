package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when a Workflow is not found
 */
public class WorkflowNotFoundException extends NotFoundException {
    public WorkflowNotFoundException() {
    }

    public WorkflowNotFoundException(String message) {
        super(message);
    }

    public WorkflowNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkflowNotFoundException(Throwable cause) {
        super(cause);
    }

    public WorkflowNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
