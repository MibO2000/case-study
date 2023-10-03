package com.mibo2000.linuxlab.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import static com.mibo2000.linuxlab.exception.ErrorCode.Business.INVALID_PAGE_NO;
import static com.mibo2000.linuxlab.exception.ErrorCode.Business.INVALID_PAGE_SIZE;

/**
 * @author : MibO2000
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertRequest {
    @PositiveOrZero(message = INVALID_PAGE_NO)
    int pageNo;
    @Positive(message = INVALID_PAGE_SIZE)
    int pageSize;
}
