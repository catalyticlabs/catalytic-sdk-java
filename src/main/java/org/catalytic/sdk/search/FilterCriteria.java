package org.catalytic.sdk.search;

/**
 * An object which contains a filter criteria name and value
 */
public class FilterCriteria {

    // The Filter that was passed into the constructor
    // which holds all the filter criteria
    private Filter filter;
    public String name;
    public String value;

    public FilterCriteria(Filter filter, String name) {
        this.filter = filter;
        this.name = name;
    }

    /**
     * Adds the term as the filter criteria value
     *
     * The name of this method is for syntactic sugar
     *
     * @param term  The term to add as the filter criteria value
     * @return      The Filter object that the filter criteria value was added to
     */
    public Filter matches(String term) {
        return addAsFilterCriteria(term);
    }

    /**
     * Adds the term as the filter criteria value
     *
     * The name of this method is for syntactic sugar
     *
     * @param term  The term to add as the filter criteria value
     * @return      The Filter object that the filter criteria value was added to
     */
    public Filter is(String term) {
        return addAsFilterCriteria(term);
    }

    /**
     * Adds the passed in term to the filter's search criteria
     *
     * @param term  The term to add as the filter criteria value
     * @return      The Filter object that the filter criteria value was added to
     */
    private Filter addAsFilterCriteria(String term) {
        this.value = term;
        this.filter.searchFilters.add(this);
        return this.filter;
    }
}
