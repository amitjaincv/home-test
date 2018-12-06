package com.intercom.hometest.repository.filter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.mockito.Mockito;

import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.model.GPSCoordinate;

public class DistanceCriteriaTest {

    @Test
    public void applyCriteria_default() {
        SearchCriteria criteria = new SearchCriteria();
        List<CustomerEntity> customers = new ArrayList<>();
        DistanceCriteria distanceCriteria = Mockito.mock(DistanceCriteria.class);

        Mockito.when(distanceCriteria.calculateDistance(Mockito.anyObject(), Mockito.anyObject())).thenReturn(10.1);
        Mockito.when(distanceCriteria.rule(Mockito.anyDouble(), Mockito.anyObject())).thenReturn(true);
        Mockito.when(distanceCriteria.applyCriteria(customers, criteria)).thenCallRealMethod();

        // act
        List<CustomerEntity> afterCriteria = distanceCriteria.applyCriteria(customers, criteria);
        // assert
        assertEquals(0, afterCriteria.size());

        // arrange
        GPSCoordinate p1 = GPSCoordinate.builder().latitude(52.986375).longitude(-6.043701).build();
        CustomerEntity c1 = CustomerEntity.builder().id(1).name("test1").coordinate(p1).build();

        GPSCoordinate p2 = GPSCoordinate.builder().latitude(51.92893).longitude(-10.27699).build();
        CustomerEntity c2 = CustomerEntity.builder().id(2).name("test2").coordinate(p2).build();

        GPSCoordinate p3 = GPSCoordinate.builder().latitude(51.8856167).longitude(-10.4240951).build();
        CustomerEntity c3 = CustomerEntity.builder().id(3).name("test3").coordinate(p3).build();

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        Mockito.when(distanceCriteria.calculateDistance(Mockito.anyObject(), Mockito.anyObject())).thenReturn(ThreadLocalRandom.current().nextDouble(6, 500));
        Mockito.when(distanceCriteria.rule(Mockito.anyDouble(), Mockito.anyObject())).thenReturn(true);

        // act
        afterCriteria = distanceCriteria.applyCriteria(customers, criteria);

        // assert
        assertEquals(3, afterCriteria.size());

        Mockito.when(distanceCriteria.rule(Mockito.anyDouble(), Mockito.anyObject())).thenReturn(false);
        afterCriteria = distanceCriteria.applyCriteria(customers, criteria);
        assertEquals(0, afterCriteria.size());
    }
}
