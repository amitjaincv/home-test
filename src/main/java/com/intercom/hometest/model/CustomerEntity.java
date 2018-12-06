package com.intercom.hometest.model;

import java.io.Serializable;

import com.intercom.hometest.exception.ApplicationCode;
import com.intercom.hometest.exception.ApplicationException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@EqualsAndHashCode
@Slf4j
public class CustomerEntity implements Comparable<CustomerEntity>, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2157956291196873295L;

    private GPSCoordinate coordinate;

    private int id;
    private String name;

    private CustomerEntity() {
    }

    @Override
    public int compareTo(CustomerEntity customer) {
        // let's sort the customer based on id in ascending order
        // returns a negative integer, zero, or a positive integer as this customer id
        // is less than, equal to, or greater than the specified object.
        return (this.id - customer.id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public CustomerEntity(Builder builder) {
        this.id = builder.id;
        this.coordinate = builder.coordinate;
        this.name = builder.name;
    }

    /**
     * This entity is so simple that you don't really need to use the builder
     * pattern (use a constructor instead). I use the builder pattern here because
     * it makes the code a bit more easier to read.
     */
    public static class Builder {
        private int id;
        private String name;
        private GPSCoordinate coordinate;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder coordinate(GPSCoordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public CustomerEntity build() {
            requireValidValue();
            CustomerEntity build = new CustomerEntity(this);
            return build;
        }

        private void requireValidValue() {
            if (this.name == null || this.name.trim().equals("")) {
                ApplicationException e = new ApplicationException(ApplicationCode.CUSTOMER_NAME_IS_EMPTY_OR_NULL);
                log.error(ApplicationCode.ENTITY_ALREADY_EXIST.message(), e);
                throw e;
            }
        }
    }

}
