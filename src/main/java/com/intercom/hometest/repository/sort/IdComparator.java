package com.intercom.hometest.repository.sort;

import java.util.Comparator;

import com.intercom.hometest.model.CustomerEntity;

public class IdComparator implements Comparator<CustomerEntity> {

    @Override
    public int compare(CustomerEntity o1, CustomerEntity o2) {
        if (o1.getId() < o2.getId()) {
            return -1;
        } else if (o1.getId() == o2.getId()) {
            return 0;
        } else {
            return 1;
        }
    }

}
