package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when an InstanceStep is not found
 */
public class InstanceStepNotFoundException extends NotFoundException {
    public InstanceStepNotFoundException() {
    }

    public InstanceStepNotFoundException(String message) {
        super(message);
    }

    public InstanceStepNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstanceStepNotFoundException(Throwable cause) {
        super(cause);
    }

    public InstanceStepNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
