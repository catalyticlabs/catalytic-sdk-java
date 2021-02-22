package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when an Action is not found
 */
public class ActionNotFoundException extends NotFoundException {
    public ActionNotFoundException() {
    }

    public ActionNotFoundException(String message) {
        super(message);
    }

    public ActionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionNotFoundException(Throwable cause) {
        super(cause);
    }

    public ActionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
