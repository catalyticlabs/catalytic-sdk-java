package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when an Integration is not found
 */
public class IntegrationNotFoundException extends NotFoundException {
    public IntegrationNotFoundException() {
    }

    public IntegrationNotFoundException(String message) {
        super(message);
    }

    public IntegrationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegrationNotFoundException(Throwable cause) {
        super(cause);
    }

    public IntegrationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
