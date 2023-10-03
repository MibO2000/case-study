package com.mibo2000.linuxlab.model;

import java.util.Arrays;

/**
 * @author : MibO2000
 */
public class EnumPool {
    public enum DummyType {
        posts, products, quotes
    }

    public enum DirectionType {
        asc, desc;

        public static boolean isValid(String value) {
            return Arrays.stream(DirectionType.values()).anyMatch(x -> value.equals(x.name()));
        }
    }
}
