package com.intercom.hometest.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import com.intercom.hometest.exception.ApplicationException;
import com.intercom.hometest.model.CustomerEntity;

public class IMapperTest {
    @Test
    public void mapFileEntitiesIntoStringList_fromFile() throws Exception {
        // arrange
        IMapper<?> mapper = Mockito.mock(IMapper.class);
        Mockito.when(mapper.mapFileEntitiesIntoStringList("customers.txt")).thenCallRealMethod();

        // act and assert
        assertEquals(32, mapper.mapFileEntitiesIntoStringList("customers.txt").size());
    }

    @Test
    public void mapFileEntitiesIntoStringList_fileNotFoundException() throws Exception {
        // arrange
        IMapper<?> mapper = Mockito.mock(IMapper.class);
        Mockito.when(mapper.mapFileEntitiesIntoStringList("some_file_not_present.txt")).thenCallRealMethod();
        try {
            // act
            assertEquals(32, mapper.mapFileEntitiesIntoStringList("some_file_not_present.txt").size());
            fail();
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1003", exception.getErrorCode().id());
        }
    }

    @Test
    public void mapStringEntityIntoJSONObject_successfully() throws Exception {
        // arrange
        IMapper<?> mapper = Mockito.mock(IMapper.class);
        Mockito.when(mapper.mapStringEntityIntoJSONObject("{\"name\" : \"john\"}")).thenCallRealMethod();

        // act
        Object jsonObject = mapper.mapStringEntityIntoJSONObject("{\"name\" : \"john\"}");

        // assert
        assertTrue(jsonObject instanceof JSONObject);
    }

    @Test
    public void mapStringEntityIntoJSONObject_parserException() throws Exception {
        // arrange
        IMapper<?> mapper = Mockito.mock(IMapper.class);
        Mockito.when(mapper.mapStringEntityIntoJSONObject("abc")).thenCallRealMethod();

        try {
            // act
            mapper.mapStringEntityIntoJSONObject("abc");
        } catch (ApplicationException exception) {
            // assert
            assertEquals("1002", exception.getErrorCode().id());
        }
    }

    @Test
    public void getListOfEntitiesAsPOJO_fromFile() throws Exception {
        // arrange
        CustomerEntity customer = new CustomerEntity.Builder().name("test").build();
        JSONObject jsonObject = new JSONObject();
        List<String> customers = new ArrayList<>();
        customers.add("test");
        @SuppressWarnings("unchecked")
        IMapper<CustomerEntity> mapper = Mockito.mock(IMapper.class);
        Mockito.when(mapper.mapFileEntitiesIntoStringList("some_file.txt")).thenReturn(customers);
        Mockito.when(mapper.mapStringEntityIntoJSONObject("test")).thenReturn(jsonObject);
        Mockito.when(mapper.mapEntityIntoDTO(jsonObject)).thenReturn(customer);
        Mockito.when(mapper.mapEntitiesFromFile("some_file.txt")).thenCallRealMethod();

        // act
        Collection<CustomerEntity> collection = mapper.mapEntitiesFromFile("some_file.txt");
        assertEquals(1, collection.size());
        assertEquals(customer, collection.iterator().next());

    }
}
