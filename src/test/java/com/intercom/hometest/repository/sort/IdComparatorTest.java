package com.intercom.hometest.repository.sort;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.intercom.hometest.model.CustomerEntity;

public class IdComparatorTest {

    IdComparator comparator;

    @Before
    public void setup() {
        comparator = new IdComparator();
    }

    @Test
    public void compareByID_equalCheck() {
        CustomerEntity o1 = CustomerEntity.builder().id(1).name("test").build();
        CustomerEntity o2 = CustomerEntity.builder().id(1).name("test").build();
        assertEquals(0, comparator.compare(o1, o2));
    }

    @Test
    public void compareByID_lessThanCheck() {
        CustomerEntity o1 = CustomerEntity.builder().id(1).name("test").build();
        CustomerEntity o2 = CustomerEntity.builder().id(2).name("test").build();
        assertEquals(-1, comparator.compare(o1, o2));
    }

    @Test
    public void compareByID_greaterThanCheck() {
        CustomerEntity o1 = CustomerEntity.builder().id(2).name("test").build();
        CustomerEntity o2 = CustomerEntity.builder().id(1).name("test").build();
        assertEquals(1, comparator.compare(o1, o2));
    }

    @Test
    public void compareByID_assendingOrder() {
        CustomerEntity o1 = CustomerEntity.builder().id(1).name("test").build();
        CustomerEntity o12 = CustomerEntity.builder().id(12).name("test").build();
        CustomerEntity o9 = CustomerEntity.builder().id(9).name("test").build();
        CustomerEntity o3 = CustomerEntity.builder().id(3).name("test").build();
        CustomerEntity o18 = CustomerEntity.builder().id(18).name("test").build();

        List<CustomerEntity> customers = new ArrayList<>();
        customers.add(o12);
        customers.add(o1);
        customers.add(o9);
        customers.add(o18);
        customers.add(o3);

        Collections.shuffle(customers);
        Collections.shuffle(customers);

        Collections.sort(customers, comparator);

        assertEquals(18, customers.get(4).getId());
    }
}
