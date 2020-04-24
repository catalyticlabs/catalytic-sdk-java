package org.catalytic.sdk.exceptions;

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
