package com.mibo2000.linuxlab.exception;

/**
 * @author : MibO2000
 */
public class ErrorCode {
    public ErrorCode() {
    }

    public interface Business {
        String SUCCESS = "00000";
        String UNKNOWN = "99999";
        String INVALID_DIRECTION = "00001";
        String INVALID_PAGE_NO = "00002";
        String INVALID_PAGE_SIZE = "00003";
        String INVALID_SORT_BY = "00004";
        String ENTITY_DOES_NOT_EXISTS = "00005";
        String ENTITY_EXISTS = "00006";
        String REST_TEMPLATE_ERROR = "00007";


    }
}
