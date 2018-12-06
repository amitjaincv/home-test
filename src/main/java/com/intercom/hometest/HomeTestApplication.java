package com.intercom.hometest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.repository.filter.SearchCriteria;
import com.intercom.hometest.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class HomeTestApplication implements CommandLineRunner {

    @Value("${distance:100}")
    private double distance;

    @Value("${range:10}")
    private int range;

    @Autowired
    CustomerService customerService;

    public static void main(String[] args) {
        SpringApplication.run(HomeTestApplication.class, args);
    }

    public void run(String... arg0) throws Exception {
        log.info("distance  ---> " + distance);
        log.info("range  ---> " + range);
        SearchCriteria criteria = new SearchCriteria();
        criteria.setDistance(distance);
        criteria.setLowerBound(-range);
        criteria.setUpperBound(range);
        List<CustomerEntity> customers = customerService.searchCustomer(criteria);
        for (CustomerEntity customer : customers) {
            log.debug(customer.toString());
            System.out.println(customer.toString());
        }
    }
}
