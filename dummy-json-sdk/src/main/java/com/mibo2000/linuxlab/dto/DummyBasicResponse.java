package com.mibo2000.linuxlab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : MibO2000
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DummyBasicResponse {
    Integer limit;
    Integer total;
    Integer skip;
}
