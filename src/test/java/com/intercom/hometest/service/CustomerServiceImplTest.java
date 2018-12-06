package com.intercom.hometest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.intercom.hometest.mapper.CustomerMapper;
import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.repository.filter.DistanceInRangeCriteria;
import com.intercom.hometest.repository.filter.SearchCriteria;
import com.intercom.hometest.repository.impl.CustomerRepositoryInMemory;
import com.intercom.hometest.service.impl.CustomerServiceImpl;
import com.intercom.hometest.util.Validation;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CustomerRepositoryInMemory.class, CustomerMapper.class, Validation.class })
public class CustomerServiceImplTest {
    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepositoryInMemory repository;

    @Mock
    DistanceInRangeCriteria distanceInRangeCriteria;

    @Test
    public void searchByDistance_default() {
        SearchCriteria criteria = new SearchCriteria();
        Set<CustomerEntity> customers = new HashSet<>();
        Mockito.when(repository.readAll()).thenReturn(customers);
        Mockito.when(distanceInRangeCriteria.applyCriteria(Mockito.anyListOf(CustomerEntity.class), Mockito.any())).thenReturn(new ArrayList<>());
        customerService.searchByDistance(criteria);
    }

    @Test
    public void searchCustomer_default() {
        SearchCriteria criteria = new SearchCriteria();
        Set<CustomerEntity> customers = new HashSet<>();
        customers.add(CustomerEntity.builder().id(4).name("test").build());
        customers.add(CustomerEntity.builder().id(2).name("test").build());
        customers.add(CustomerEntity.builder().id(1).name("test").build());
        customers.add(CustomerEntity.builder().id(3).name("test").build());

        Mockito.when(repository.readAll()).thenReturn(customers);
        List<CustomerEntity> customerList = new ArrayList<>();
        customerList.addAll(customers);
        Mockito.when(distanceInRangeCriteria.applyCriteria(Mockito.anyListOf(CustomerEntity.class), Mockito.any())).thenReturn(customerList);
        List<CustomerEntity> sortedCustomerList = customerService.searchCustomer(criteria);
        assertNotNull(sortedCustomerList);
        assertEquals(4, sortedCustomerList.size());
        assertEquals(1, sortedCustomerList.get(0).getId());
        assertEquals(4, sortedCustomerList.get(3).getId());
    }
}
