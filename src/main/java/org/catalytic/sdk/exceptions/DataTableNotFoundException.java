package org.catalytic.sdk.exceptions;

/**
 * An exception to be thrown when a DataTable is not found
 */
public class DataTableNotFoundException extends NotFoundException {
    public DataTableNotFoundException() {
    }

    public DataTableNotFoundException(String message) {
        super(message);
    }

    public DataTableNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataTableNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataTableNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
