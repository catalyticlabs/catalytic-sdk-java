package org.catalytic.sdk.search;

import java.util.List;

public class SearchUtils {

    /**
     * Finds a SearchCriteria object from $array where the 'name' equals $name
     *
     * @param allFilterCriteria The array of searchCriteria
     * @param name              The name of the search critiera object to look for
     * @return string           The value of the search criteria by name
     */
    public static String getSearchCriteriaValueByKey(List<FilterCriteria> allFilterCriteria, String name) {
        FilterCriteria searchCriteriaObject = allFilterCriteria.stream()
                .filter(filterCriteria -> filterCriteria.name.equals(name))
                .findFirst()
                .orElse(null);

        if (searchCriteriaObject == null) {
            return null;
        }

        return searchCriteriaObject.value;
    }
}
