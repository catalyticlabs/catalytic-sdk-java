package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when an Action is not found
 */
public class ActionInvocationNotFoundException extends NotFoundException {
    public ActionInvocationNotFoundException() {
    }

    public ActionInvocationNotFoundException(String message) {
        super(message);
    }

    public ActionInvocationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionInvocationNotFoundException(Throwable cause) {
        super(cause);
    }

    public ActionInvocationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
