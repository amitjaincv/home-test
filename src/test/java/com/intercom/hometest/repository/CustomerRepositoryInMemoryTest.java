package com.intercom.hometest.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.intercom.hometest.exception.ApplicationException;
import com.intercom.hometest.mapper.CustomerMapper;
import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.model.GPSCoordinate;
import com.intercom.hometest.repository.impl.CustomerRepositoryInMemory;
import com.intercom.hometest.util.Validation;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CustomerRepositoryInMemory.class, CustomerMapper.class, Validation.class })
public class CustomerRepositoryInMemoryTest {

    CustomerRepositoryInMemory repository;

    @Before
    public void setup() {
        CustomerMapper customerMapper = mock(CustomerMapper.class);
        Mockito.when(customerMapper.mapEntitiesFromFile(Mockito.anyString())).thenReturn(new HashSet<>());
        repository = new CustomerRepositoryInMemory(customerMapper);
    }

    @Test
    public void addCustomer_succesfully() throws Exception {
        // arrange
        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(10.0).longitude(10.0).build();
        CustomerEntity customer = CustomerEntity.builder().id(1).name("a").coordinate(coordinate).build();

        // act
        repository.create(customer);

        // assert
        Set<CustomerEntity> customers = repository.readAll();
        assertEquals(1, customers.size());
        List<CustomerEntity> customerList = new ArrayList<>(customers);
        assertEquals(customerList.get(0), customer);
    }

    @Test
    public void addCustomer_whenAccountAlreadyExists() throws Exception {
        // arrange
        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(10.0).longitude(10.0).build();
        CustomerEntity customer = CustomerEntity.builder().id(1).name("a").coordinate(coordinate).build();

        repository.create(customer);

        Set<CustomerEntity> customers = repository.readAll();
        assertEquals(1, customers.size());
        List<CustomerEntity> customerList = new ArrayList<>(customers);
        assertEquals(customerList.get(0), customer);

        try {

            // act
            repository.create(customer);
            fail();

        } catch (ApplicationException exception) {

            // assert
            assertEquals("1001", exception.getErrorCode().id());

        }
    }

}
