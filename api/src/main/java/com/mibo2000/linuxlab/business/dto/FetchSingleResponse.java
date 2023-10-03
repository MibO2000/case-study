package com.mibo2000.linuxlab.business.dto;

import lombok.*;

import java.util.List;

/**
 * @author : MibO2000
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FetchSingleResponse {
    List<DummySingleData> dataList;
    int pageNo;
    int pageSize;
}
