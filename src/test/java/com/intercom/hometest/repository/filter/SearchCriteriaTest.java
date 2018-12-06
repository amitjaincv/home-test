package com.intercom.hometest.repository.filter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.intercom.hometest.model.GPSCoordinate;
import com.intercom.hometest.repository.filter.SearchCriteria.Filter;
import com.intercom.hometest.repository.filter.SearchCriteria.SortBy;
import com.intercom.hometest.repository.filter.SearchCriteria.SortOrder;

public class SearchCriteriaTest {
    @Test
    public void filterEnumLength_validate() {
        assertEquals(1, SearchCriteria.Filter.values().length);
    }

    @Test
    public void sortByEnumLength_validate() {
        assertEquals(1, SearchCriteria.SortBy.values().length);
    }

    @Test
    public void fortOrderEnumLength_validate() {
        assertEquals(2, SearchCriteria.SortOrder.values().length);
    }

    @Test
    public void setter_validation() {
        SearchCriteria criteria = new SearchCriteria();
        criteria.setDistance(10.1);

        double delta = .00001;
        criteria.setLowerBound(-5);
        assertEquals(-5, criteria.getLowerBound(), delta);
        criteria.setLowerBound(-10);
        assertEquals(-10, criteria.getLowerBound(), delta);
        criteria.setLowerBound(1);
        assertEquals(1, criteria.getLowerBound(), delta);

        criteria.setUpperBound(5);
        assertEquals(5, criteria.getUpperBound(), delta);
        criteria.setUpperBound(-10);
        assertEquals(-10, criteria.getUpperBound(), delta);
        criteria.setUpperBound(1);
        assertEquals(1, criteria.getUpperBound(), delta);

        criteria.setSortOrder(SortOrder.DESC);
        assertEquals(SortOrder.DESC, criteria.getSortOrder());

        criteria.setFilter(Filter.BY_DISTANCE_IN_RANGE);
        assertEquals(Filter.BY_DISTANCE_IN_RANGE, criteria.getFilter());

        criteria.setSortBy(SortBy.BY_ID);
        assertEquals(SortBy.BY_ID, criteria.getSortBy());

        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        criteria.setSource(coordinate);
        assertEquals(coordinate, criteria.getSource());
    }
}
