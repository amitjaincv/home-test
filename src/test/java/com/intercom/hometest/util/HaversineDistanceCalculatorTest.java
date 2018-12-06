package com.intercom.hometest.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.intercom.hometest.model.GPSCoordinate;

public class HaversineDistanceCalculatorTest {
    HaversineDistanceCalculator distanceCalculator;

    @Before
    public void setup() {
        distanceCalculator = new HaversineDistanceCalculator();
    }

    @Test
    public void findDistanceBetweenTwoPoints_sameLocation() throws Exception {

        // arrange
        GPSCoordinate x = GPSCoordinate.builder().latitude(0.0).longitude(0.0).build();
        GPSCoordinate y = GPSCoordinate.builder().latitude(0.0).longitude(0.0).build();

        // act
        double distance = distanceCalculator.distanceInKilometer(x, y);

        // assert
        assertEquals(0, distance, 0);

        // act
        distance = distanceCalculator.distanceInKilometer(y, x);

        // assert
        assertEquals(0, distance, 0);
    }

    @Test
    public void findDistanceBetweenTwoPoints_differentLocation() throws Exception {

        // arrange
        GPSCoordinate pune = GPSCoordinate.builder().latitude(18.529165).longitude(73.874054).build();
        GPSCoordinate mumbai = GPSCoordinate.builder().latitude(18.952375).longitude(72.83301).build();

        // act
        double distance = distanceCalculator.distanceInKilometer(pune, mumbai);

        // assert
        assertEquals(119, Math.round(distance), 0);

        distance = distanceCalculator.distanceInKilometer(mumbai, pune);
        assertEquals(119, Math.round(distance), 0);
    }
}
