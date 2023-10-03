package com.mibo2000.linuxlab.util;

/**
 * @author : MibO2000
 */
public interface Util {
    String toJson(Object object);

    String toJsonPretty(Object object);

    <T> T toObject(String json, Class t);
}
