package com.mibo2000.linuxlab.exception;

import com.mibo2000.linuxlab.Translator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : MibO2000
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String message;

    public BusinessException(String errorCode) {
        super(Translator.toLocale(errorCode));
        this.errorCode = errorCode;
    }
}
