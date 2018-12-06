package com.intercom.hometest.mapper;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intercom.hometest.exception.ApplicationCode;
import com.intercom.hometest.exception.ApplicationException;
import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.model.GPSCoordinate;
import com.intercom.hometest.util.Validation;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerMapper implements IMapper<CustomerEntity> {
    private static String USER_ID = "user_id";
    private static String NAME = "name";
    private static String LONGITUDE = "longitude";
    private static String LATITUDE = "latitude";

    @Autowired
    Validation validate;

    public CustomerEntity mapEntityIntoDTO(JSONObject customerObject) {
        StringBuilder msg = new StringBuilder("Converting JSONObject ");
        msg.append(customerObject);
        msg.append(" to customer POJO");
        log.debug(msg.toString());

        double latitude = coordinate(customerObject, LATITUDE);

        double longitude = coordinate(customerObject, LONGITUDE);

        GPSCoordinate coordinate = GPSCoordinate.builder().latitude(latitude).longitude(longitude).build();

        return CustomerEntity.builder().coordinate(coordinate).name(name(customerObject)).id(id(customerObject))
                .build();
    }

    private String name(JSONObject customerObject) {
        Object nameObject = customerObject.get(NAME);
        nullCheck(nameObject);
        nameCheck((String) nameObject);
        return (String) nameObject;
    }

    private int id(JSONObject customerObject) {
        Object userID = customerObject.get(USER_ID);
        nullCheck(userID);
        intValueCheck((Long) userID);
        return ((Long) userID).intValue();
    }

    private double coordinate(JSONObject customerObject, String attibute) {
        Object latitudeObject = customerObject.get(attibute);
        nullCheck(latitudeObject);
        doubleValueCheck((String) latitudeObject);
        return Double.parseDouble((String) latitudeObject);
    }

    protected void nullCheck(Object o) {
        if (validate.validateNull(o)) {
            ApplicationException e = new ApplicationException(ApplicationCode.MISSING_ATTRIBUTE_IN_JSONOBJECT);
            log.error(ApplicationCode.MISSING_ATTRIBUTE_IN_JSONOBJECT.message(), e);
            throw e;
        }
    }

    protected void nameCheck(String name) {
        if (!validate.validateName(name)) {
            ApplicationException e = new ApplicationException(
                    ApplicationCode.WRONG_VALUE_FOR_NAME_ATTRIBUTE_IN_JSONOBJECT);
            log.error(ApplicationCode.WRONG_VALUE_FOR_NAME_ATTRIBUTE_IN_JSONOBJECT.message(), e);
            throw e;
        }
    }

    protected void intValueCheck(Long id) {
        if (!validate.validateLong(id)) {
            ApplicationException e = new ApplicationException(
                    ApplicationCode.WRONG_VALUE_FOR_USER_ID_ATTRIBUTE_IN_JSONOBJECT);
            log.error(ApplicationCode.WRONG_VALUE_FOR_USER_ID_ATTRIBUTE_IN_JSONOBJECT.message(), e);
            throw e;
        }
    }

    protected void doubleValueCheck(String d) {
        if (!validate.validateDouble(d)) {
            ApplicationException e = new ApplicationException(
                    ApplicationCode.WRONG_VALUE_FOR_LONGITUDE_OR_LATITUDE_ATTRIBUTE_IN_JSONOBJECT);
            log.error(ApplicationCode.WRONG_VALUE_FOR_LONGITUDE_OR_LATITUDE_ATTRIBUTE_IN_JSONOBJECT.message(), e);
            throw e;
        }
    }
}
