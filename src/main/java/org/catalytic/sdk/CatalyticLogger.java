package org.catalytic.sdk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Catalytic logger
 */
public class CatalyticLogger {

    /**
     * Gets a logger
     *
     * @param clazz The class to create a logger from
     * @return      A logger
     */
    public static Logger getLogger(Class clazz) {
        return LogManager.getLogger(clazz);
    }
}
