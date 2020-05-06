package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when Credentials are not found
 */
public class CredentialsNotFoundException extends NotFoundException {

    public CredentialsNotFoundException() {
    }

    public CredentialsNotFoundException(String message) {
        super(message);
    }

    public CredentialsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CredentialsNotFoundException(Throwable cause) {
        super(cause);
    }

    public CredentialsNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
