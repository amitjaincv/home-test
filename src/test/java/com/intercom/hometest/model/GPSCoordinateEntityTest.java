package com.intercom.hometest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class GPSCoordinateEntityTest {
    @Test(expected = java.lang.IllegalAccessException.class)
    public void calidatesThatClassFooIsNotInstantiable() throws Exception {
        Class cls = Class.forName("com.intercom.hometest.model.GPSCoordinate");
        cls.newInstance(); // exception here
    }

    @Test
    public void createEntity_userBuilder() {
        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        assertEquals(1.2, coordinate.getLatitude(), .000001);
        assertEquals(1.1, coordinate.getLongitude(), .000001);
    }

    @Test
    public void customerEntity_equal() {
        GPSCoordinate c1 = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        GPSCoordinate c2 = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        assertEquals(c1, c2);
    }

    @Test
    public void customerEntity_notEqual() {
        GPSCoordinate c1 = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        GPSCoordinate c2 = GPSCoordinate.builder().latitude(1.1).longitude(1.1).build();
        assertNotEquals(c1, c2);
    }

    @Test
    public void customerEntity_equalHashcode() {
        GPSCoordinate c1 = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        GPSCoordinate c2 = GPSCoordinate.builder().latitude(1.1).longitude(1.1).build();
        assertNotEquals(c1.hashCode(), c2.hashCode());

        GPSCoordinate c3 = GPSCoordinate.builder().latitude(1.2).longitude(1.2).build();
        assertNotEquals(c1.hashCode(), c3.hashCode());
    }
}
