package com.intercom.hometest.repository.filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("distance_in_range")
public class DistanceInRangeCriteria extends DistanceCriteria {

    @Override
    public boolean rule(double difference, SearchCriteria criteria) {
        return criteria.getLowerBound() <= difference && difference <= criteria.getUpperBound() ? true : false;
    }

}