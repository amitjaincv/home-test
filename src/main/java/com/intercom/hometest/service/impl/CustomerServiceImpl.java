package com.intercom.hometest.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.repository.IRepository;
import com.intercom.hometest.repository.filter.SearchCriteria;
import com.intercom.hometest.repository.filter.Criteria;
import com.intercom.hometest.repository.sort.IdComparator;
import com.intercom.hometest.service.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    @Qualifier("customer_repo_in-memory")
    private IRepository<CustomerEntity> repository;

    @Autowired
    @Qualifier("distance_in_range")
    private Criteria<CustomerEntity> distanceInRangeCriteria;

    @Override
    public List<CustomerEntity> searchByDistance(SearchCriteria criteria) {
        List<CustomerEntity> customers = new ArrayList<>();
        customers.addAll(repository.readAll());
        return distanceInRangeCriteria.applyCriteria(customers, criteria);
    }

    @Override
    public List<CustomerEntity> searchCustomer(SearchCriteria criteria) {
        List<CustomerEntity> customers = new ArrayList<>();
        if (criteria.getFilter().equals(SearchCriteria.Filter.BY_DISTANCE_IN_RANGE)) {
            customers = searchByDistance(criteria);
        }

        if (criteria.getSortBy().equals(SearchCriteria.SortBy.BY_ID)) {
            Collections.sort(customers, new IdComparator());
        }

        return customers;
    }

}
