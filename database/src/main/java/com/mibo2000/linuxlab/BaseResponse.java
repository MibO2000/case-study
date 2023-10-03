package com.mibo2000.linuxlab;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author : MibO2000
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String status;
    private String message;
    private Object result;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Yangon")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public BaseResponse(String status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
