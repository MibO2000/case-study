package com.mibo2000.linuxlab.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mibo2000.linuxlab.BaseResponse;
import com.mibo2000.linuxlab.Translator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.crypto.BadPaddingException;

import static com.mibo2000.linuxlab.exception.ErrorCode.Business.UNKNOWN;

/**
 * @author : MibO2000
 */
@RestControllerAdvice
public class ApiExceptionHandler {
    public static final Logger logger = LogManager.getLogger(ApiExceptionHandler.class.getSimpleName());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleAllException(Exception ex, WebRequest request) {
        logger.error(ex);
        return new BaseResponse(UNKNOWN, Translator.toLocale(UNKNOWN), null);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            BadPaddingException.class,
            JsonProcessingException.class,
            HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse validateException(Exception ex, WebRequest request) {
        logger.error(ex);
        return new BaseResponse(UNKNOWN, "Wrong request", null);
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse businessException(BusinessException ex, WebRequest request) {
        logger.error(ex);
        return new BaseResponse(ex.getErrorCode(), ex.getMessage(), null);
    }
}
