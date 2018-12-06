package com.intercom.hometest.repository.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.intercom.hometest.exception.ApplicationCode;
import com.intercom.hometest.exception.ApplicationException;
import com.intercom.hometest.mapper.IMapper;
import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.repository.IRepository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@Qualifier("customer_repo_in-memory")
public class CustomerRepositoryInMemory implements IRepository<CustomerEntity> {
    private static String customerListOfFile = "customers.txt";

    private IMapper<CustomerEntity> customerMapper;
    private Set<CustomerEntity> customers;

    @Autowired
    public CustomerRepositoryInMemory(IMapper<CustomerEntity> customerMapper) {
        super();
        customers = new HashSet<>();
        this.customerMapper = customerMapper;
        init();
    }

    private void init() {
        StringBuilder msg = new StringBuilder("Initializing in-memory database from file : ");
        msg.append(customerListOfFile);
        log.info(msg.toString());

        customers.addAll(this.customerMapper.mapEntitiesFromFile(customerListOfFile));

        msg = new StringBuilder("In-memory database initialized succesfully.");
        log.info(msg.toString());
    }

    @Override
    public void create(CustomerEntity customer) {
        if (!customers.add(customer)) {
            ApplicationException e = new ApplicationException(ApplicationCode.ENTITY_ALREADY_EXIST);
            log.error(ApplicationCode.ENTITY_ALREADY_EXIST.message(), e);
            throw e;
        }
        StringBuilder msg = new StringBuilder("Customer created successfully");
        log.info(msg.toString());
        msg = new StringBuilder(customer.toString());
        log.debug(msg.toString());
    }

    @Override
    public Set<CustomerEntity> readAll() {
        return Collections.unmodifiableSet(customers);
    }
}
