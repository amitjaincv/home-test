package com.intercom.hometest.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.intercom.hometest.exception.ApplicationCode;
import com.intercom.hometest.exception.ApplicationException;

public interface IMapper<T> {

    public default List<String> mapFileEntitiesIntoStringList(String fileName) {
        // Read text file into list of entities in string format.
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
            return IOUtils.readLines(is);
        } catch (NullPointerException e) {
            throw new ApplicationException(ApplicationCode.FILE_NOT_FOUND, e);
        } catch (IOException e) {
            throw new ApplicationException(ApplicationCode.UNEXPECTED_ERROR_IN_READING_FILE, e);
        }
    }

    public default JSONObject mapStringEntityIntoJSONObject(String entityAsString) {
        JSONParser jsonParser = new JSONParser();

        // Parse String to JSON
        Object obj;
        try {
            obj = jsonParser.parse(entityAsString);
            return (JSONObject) obj;
        } catch (ParseException e) {
            throw new ApplicationException(ApplicationCode.ENTITY_AS_STRING_NOT_JSON_FORMATTED, e);
        }

    }

    public default Collection<T> mapEntitiesFromFile(String fileName) {
        Set<T> t = new HashSet<>();

        List<String> entitiesAsString = mapFileEntitiesIntoStringList(fileName);
        for (String entityAsString : entitiesAsString) {
            t.add(mapEntityIntoDTO(mapStringEntityIntoJSONObject(entityAsString)));
        }

        return t;
    }

    T mapEntityIntoDTO(JSONObject jsonObject);
}
