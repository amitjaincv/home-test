package com.intercom.hometest.repository.filter;

import org.springframework.stereotype.Component;

import com.intercom.hometest.model.GPSCoordinate;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class SearchCriteria {

    public enum Filter {
        BY_DISTANCE_IN_RANGE
    }

    public enum SortBy {
        BY_ID
    }

    public enum SortOrder {
        ASC, DESC
    }

    private SortOrder sortOrder = SortOrder.ASC;
    private Filter filter = Filter.BY_DISTANCE_IN_RANGE;
    private SortBy sortBy = SortBy.BY_ID;
    private GPSCoordinate source = GPSCoordinate.builder().latitude(53.339428).longitude(-6.257664).build();
    private double distance = 100;
    private int lowerBound = -10;
    private int upperBound = 10;

}
