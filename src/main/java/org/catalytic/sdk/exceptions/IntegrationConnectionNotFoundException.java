package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when an IntegrationConnection is not found
 */
public class IntegrationConnectionNotFoundException extends NotFoundException {
    public IntegrationConnectionNotFoundException() {
    }

    public IntegrationConnectionNotFoundException(String message) {
        super(message);
    }

    public IntegrationConnectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegrationConnectionNotFoundException(Throwable cause) {
        super(cause);
    }

    public IntegrationConnectionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
