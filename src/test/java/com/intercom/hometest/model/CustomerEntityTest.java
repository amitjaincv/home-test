package com.intercom.hometest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.intercom.hometest.exception.ApplicationException;

public class CustomerEntityTest {

    @Test
    public void compareEntity_equal() {
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test").build();
        CustomerEntity c2 = CustomerEntity.builder().id(1).name("test").build();
        assertEquals(0, c1.compareTo(c2));
    }

    @Test
    public void compareEntity_negativeValue() {
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test").build();
        CustomerEntity c2 = CustomerEntity.builder().id(2).name("test").build();
        assertEquals(-1, c1.compareTo(c2));
    }

    @Test
    public void compareEntity_positiveValue() {
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test").build();
        CustomerEntity c2 = CustomerEntity.builder().id(0).name("test").build();
        assertEquals(1, c1.compareTo(c2));
    }

    @Test(expected = java.lang.IllegalAccessException.class)
    public void calidatesThatClassFooIsNotInstantiable() throws Exception {
        Class cls = Class.forName("com.intercom.hometest.model.CustomerEntity");
        cls.newInstance(); // exception here
    }

    @Test
    public void createEntity_userBuilder() {
        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test").coordinate(coordinate).build();

        assertNotNull(c1);
        assertEquals(1, c1.getId());
        assertEquals("test", c1.getName());
        assertNotNull(c1.getCoordinate());
        assertEquals(1.2, c1.getCoordinate().getLatitude(), .0001);
        assertEquals(1.1, c1.getCoordinate().getLongitude(), .0001);
    }

    @Test
    public void createEntity_withCustomerName() {
        try {
            CustomerEntity.builder().id(1).name(" ").build();
            fail();
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1005", exception.getErrorCode().id());
        }

        try {
            CustomerEntity.builder().id(1).build();
            fail();
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1005", exception.getErrorCode().id());
        }
    }

    @Test
    public void customerEntity_equal() {
        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test").coordinate(coordinate).build();
        CustomerEntity c2 = CustomerEntity.builder().id(1).name("test").coordinate(coordinate).build();
        assertEquals(c1, c2);
    }

    @Test
    public void customerEntity_notEqual() {
        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test").coordinate(coordinate).build();
        CustomerEntity c2 = CustomerEntity.builder().id(2).name("test").coordinate(coordinate).build();
        assertNotEquals(c1, c2);

        CustomerEntity c3 = CustomerEntity.builder().id(1).name("test1").coordinate(coordinate).build();
        assertNotEquals(c1, c3);

        GPSCoordinate coordinate1 = GPSCoordinate.builder().latitude(1.1).longitude(1.1).build();
        CustomerEntity c4 = CustomerEntity.builder().id(1).name("test").coordinate(coordinate1).build();
        assertNotEquals(c1, c4);
    }

    @Test
    public void customerEntity_equalHashcode() {
        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test").coordinate(coordinate).build();
        CustomerEntity c2 = CustomerEntity.builder().id(1).name("test").coordinate(coordinate).build();
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void customerEntity_notEqualHashcode() {
        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(1.2).longitude(1.1).build();
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test").coordinate(coordinate).build();
        CustomerEntity c2 = CustomerEntity.builder().id(2).name("test").coordinate(coordinate).build();
        assertNotEquals(c1.hashCode(), c2.hashCode());
    }
}
