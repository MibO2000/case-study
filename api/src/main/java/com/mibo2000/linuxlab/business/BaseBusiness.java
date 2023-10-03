package com.mibo2000.linuxlab.business;

import com.mibo2000.linuxlab.BaseResponse;
import com.mibo2000.linuxlab.business.dto.InsertRequest;
import com.mibo2000.linuxlab.model.EnumPool.DirectionType;

/**
 * @author : MibO2000
 */
public interface BaseBusiness {
    BaseResponse fetchDummyData(int pageNo, int pageSize);

    BaseResponse insertDummyData(InsertRequest request);

    BaseResponse getDummyData(int pageNo, int pageSize, String sortBy, DirectionType direction);

    BaseResponse deleteDummyDataById(Long id);
}
