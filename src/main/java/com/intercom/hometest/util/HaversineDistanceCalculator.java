package com.intercom.hometest.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.intercom.hometest.model.GPSCoordinate;

@Component
@Qualifier("Haversine_FORMULA")
public class HaversineDistanceCalculator implements IDistanceCalculator {

    public double distanceInKilometer(GPSCoordinate x, GPSCoordinate y) {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        double lon1 = Math.toRadians(x.getLongitude());
        double lon2 = Math.toRadians(y.getLongitude());
        double lat1 = Math.toRadians(x.getLatitude());
        double lat2 = Math.toRadians(y.getLatitude());

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

        double computation = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return (computation * r);
    }

}
