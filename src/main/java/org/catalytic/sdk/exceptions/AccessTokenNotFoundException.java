package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when Credentials are not found
 */
public class AccessTokenNotFoundException extends NotFoundException {

    public AccessTokenNotFoundException() {
    }

    public AccessTokenNotFoundException(String message) {
        super(message);
    }

    public AccessTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessTokenNotFoundException(Throwable cause) {
        super(cause);
    }

    public AccessTokenNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
