package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when an Instance is not found
 */
public class InstanceNotFoundException extends NotFoundException {
    public InstanceNotFoundException() {
    }

    public InstanceNotFoundException(String message) {
        super(message);
    }

    public InstanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InstanceNotFoundException(Throwable cause) {
        super(cause);
    }

    public InstanceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
