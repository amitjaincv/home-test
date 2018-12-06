package com.intercom.hometest.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.intercom.hometest.exception.ApplicationException;
import com.intercom.hometest.model.CustomerEntity;
import com.intercom.hometest.util.Validation;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { CustomerMapper.class, Validation.class })
public class CustomerMapperTest {

    @InjectMocks
    CustomerMapper customerMapper;

    @Mock
    Validation validate;

    @Test
    public void mapStringEntityIntoJSONObject_successfully() throws Exception {
        // arrange
        String entityAsString = "{\"latitude\": \"10.00\", \"user_id\": 11, \"name\": \"test\", \"longitude\": \"-10.10\"}";
        JSONObject customerJSONObject = customerMapper.mapStringEntityIntoJSONObject(entityAsString);
        Mockito.when(validate.validateName(Mockito.anyString())).thenReturn(true);
        Mockito.when(validate.validateDouble(Mockito.anyString())).thenReturn(true);
        Mockito.when(validate.validateLong(Mockito.anyLong())).thenReturn(true);

        // act
        CustomerEntity customerEntity = customerMapper.mapEntityIntoDTO(customerJSONObject);

        // assert
        assertNotNull(customerEntity);
        assertNotNull(customerEntity.getCoordinate());
        assertEquals(11, customerEntity.getId());
        assertEquals("test", customerEntity.getName());
        assertEquals(10.00, customerEntity.getCoordinate().getLatitude(), 0.001);
        assertEquals(-10.10, customerEntity.getCoordinate().getLongitude(), 0.001);
    }

    @Test
    public void mapStringEntityIntoJSONObject_nameValidationFail() throws Exception {
        // arrange
        String entityAsString = "{\"latitude\": \"10.00\", \"user_id\": 11, \"name\": \"test\", \"longitude\": \"-10.10\"}";
        Mockito.when(validate.validateName(Mockito.anyString())).thenReturn(false);
        Mockito.when(validate.validateDouble(Mockito.anyString())).thenReturn(true);
        Mockito.when(validate.validateLong(Mockito.anyLong())).thenReturn(true);
        JSONObject customerJSONObject = customerMapper.mapStringEntityIntoJSONObject(entityAsString);

        try {
            // act
            customerMapper.mapEntityIntoDTO(customerJSONObject);
            fail();
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1007", exception.getErrorCode().id());
        }
    }

    @Test
    public void nullCheck() {
        Mockito.when(validate.validateNull(Mockito.anyObject())).thenReturn(false);
        customerMapper.nullCheck(new Object());

        try {
            Mockito.when(validate.validateNull(Mockito.anyObject())).thenReturn(true);
            customerMapper.nullCheck(null);
            fail();
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1006", exception.getErrorCode().id());
        }
    }

    @Test
    public void nameCheck() {
        Mockito.when(validate.validateName(Mockito.anyString())).thenReturn(true);
        customerMapper.nameCheck("");

        try {
            Mockito.when(validate.validateName(Mockito.anyString())).thenReturn(false);
            customerMapper.nameCheck("23^&%^%");
            fail();
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1007", exception.getErrorCode().id());
        }
    }

    @Test
    public void intValueCheck() {
        Mockito.when(validate.validateLong(Mockito.anyLong())).thenReturn(true);
        customerMapper.intValueCheck(1L);

        try {
            Mockito.when(validate.validateLong(Mockito.anyLong())).thenReturn(false);
            customerMapper.intValueCheck(-879464L);
            fail();
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1008", exception.getErrorCode().id());
        }
    }

    @Test
    public void doubleValueCheck() {
        Mockito.when(validate.validateDouble(Mockito.anyString())).thenReturn(true);
        customerMapper.doubleValueCheck("10.01");

        try {
            Mockito.when(validate.validateDouble(Mockito.anyString())).thenReturn(false);
            customerMapper.doubleValueCheck("23^&%^%");
            fail();
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1009", exception.getErrorCode().id());
        }
    }
}
