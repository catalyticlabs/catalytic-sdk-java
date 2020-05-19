package org.catalytic.sdk.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which creates and stores all the search filter criteria
 */
public class Filter {

    // Contains all the search filter criteria objects to
    // be used for actual filtering against the api
    public List<FilterCriteria> searchFilters = new ArrayList<FilterCriteria>();

    /**
     * Syntactic sugar to allow chaining
     *
     * @return  The Filter object
     */
    public Filter and() {
        return this;
    }

    /**
     * Creates a FilterCriteria object for filtering text
     *
     * @return  The created FilterCriteria object
     */
    public FilterCriteria text () {
        FilterCriteria filterCriteria = new FilterCriteria(this, "text");
        return filterCriteria;
    }

    /**
     * Creates a FilterCriteria object for filtering owner
     *
     * @return  The created FilterCriteria object
     */
    public FilterCriteria owner () {
        FilterCriteria filterCriteria = new FilterCriteria(this, "owner");
        return filterCriteria;
    }

    /**
     * Creates a FilterCriteria object for filtering category
     *
     * @return  The created FilterCriteria object
     */
    public FilterCriteria category () {
        FilterCriteria filterCriteria = new FilterCriteria(this, "category");
        return filterCriteria;
    }

    /**
     * Creates a FilterCriteria object for filtering status
     *
     * @return  The created FilterCriteria object
     */
    public FilterCriteria status () {
        FilterCriteria filterCriteria = new FilterCriteria(this, "status");
        return filterCriteria;
    }

    /**
     * Creates a FilterCriteria object for filtering workflowId
     *
     * @return  The created FilterCriteria object
     */
    public FilterCriteria workflowId () {
        FilterCriteria filterCriteria = new FilterCriteria(this, "workflowId");
        return filterCriteria;
    }

    /**
     * Creates a FilterCriteria object for filtering assignee
     *
     * @return  The created FilterCriteria object
     */
    public FilterCriteria assignee () {
        FilterCriteria filterCriteria = new FilterCriteria(this, "assignee");
        return filterCriteria;
    }
}
