package com.intercom.hometest.util;

import org.springframework.stereotype.Component;

@Component
public class Validation {

    public boolean validateNull(Object o) {
        return o == null ? true : false;
    }

    public boolean validateName(String name) {
        return true;
    }

    public boolean validateLong(Long l) {
        return true;
    }

    public boolean validateDouble(String d) {
        return true;
    }

}
