package com.mibo2000.linuxlab.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : MibO2000
 */
@Service
@Slf4j
public class UtilImp implements Util {
    ObjectMapper objectMapper;

    public UtilImp() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        this.objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    @Override
    public String toJson(Object object) {
        String json;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("Cannot convert java object to json string", e);
            json = object.toString();
        }
        return json;
    }

    @Override
    public String toJsonPretty(Object object) {
        String json;
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn("Cannot convert java object to json string", e);
            json = object.toString();
        }
        return json;
    }

    @Override
    public <T> T toObject(String json, Class t) {
        try {
            return (T) objectMapper.readValue(json, t);
        } catch (JsonProcessingException e) {
            log.error("Cannot convert to object", e);
        }
        return null;
    }
}
