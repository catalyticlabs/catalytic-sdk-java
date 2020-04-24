package org.catalytic.sdk.search;

/**
 * Class used to generate chainable filter clauses
 *
 * Mainly syntactic sugar for creating FilterCriteria objects
 */
public class Where {

    /**
     * Text to be filtered
     *
     * @return  The created FilterCritiera object
     */
    public FilterCriteria text() {
        return new Filter().text();
    }

    /**
     * Owner to be filtered
     *
     * @return  The created FilterCritiera object
     */
    public FilterCriteria owner() {
        return new Filter().owner();
    }

    /**
     * Category to be filtered
     *
     * @return  The created FilterCritiera object
     */
    public FilterCriteria category() {
        return new Filter().category();
    }

    /**
     * Status to be filtered
     *
     * @return  The created FilterCritiera object
     */
    public FilterCriteria status() {
        return new Filter().status();
    }

    /**
     * workflowId to be filtered
     *
     * @return  The created FilterCritiera object
     */
    public FilterCriteria workflowId() {
        return new Filter().workflowId();
    }

    /**
     * Assignee to be filtered
     *
     * @return  The created FilterCritiera object
     */
    public FilterCriteria assignee() {
        return new Filter().assignee();
    }

    /**
     * InstanceId to be filtered
     *
     * @return  The created FilterCritiera object
     */
    public FilterCriteria instanceId() {
        return new Filter().instanceId();
    }
}
