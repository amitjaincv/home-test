package com.intercom.hometest.repository.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.model.GPSCoordinate;
import com.intercom.hometest.util.IDistanceCalculator;

@Component
public abstract class DistanceCriteria implements Criteria<CustomerEntity> {

    @Autowired
    @Qualifier("Haversine_FORMULA")
    private IDistanceCalculator distanceCalculator;

    @Override
    public List<CustomerEntity> applyCriteria(List<CustomerEntity> customers, SearchCriteria criteria) {
        List<CustomerEntity> filteredCustomers = new ArrayList<>();
        GPSCoordinate source = criteria.getSource();
        double distanceFromSource = criteria.getDistance();
        for (CustomerEntity customer : customers) {
            GPSCoordinate target = customer.getCoordinate();
            double distance = calculateDistance(source, target);
            double difference = distanceFromSource - Math.round(distance);
            if (rule(difference, criteria)) {
                filteredCustomers.add(customer);
            }
        }
        return filteredCustomers;
    }

    protected double calculateDistance(GPSCoordinate source, GPSCoordinate target) {
        return Math.round(distanceCalculator.distanceInKilometer(source, target));
    }
    protected abstract boolean rule(double distance, SearchCriteria criteria);
}
