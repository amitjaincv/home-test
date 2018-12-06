package com.intercom.hometest.service;

import java.util.List;

import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.repository.filter.SearchCriteria;

public interface CustomerService {
    List<CustomerEntity> searchByDistance(SearchCriteria customerSearchCriteria);

    List<CustomerEntity> searchCustomer(SearchCriteria criteria);
}
