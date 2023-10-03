package com.mibo2000.linuxlab.service;

import com.mibo2000.linuxlab.model.EnumPool;
import com.mibo2000.linuxlab.model.entity.DummyData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author : MibO2000
 */
public interface BaseService {
    <T> List<T> getDummyResponse(EnumPool.DummyType type, Class<T> clazz);

    void deleteDummyDataById(Long id);

    void saveDummyData(DummyData dummyData);

    Page<DummyData> getDummyData(Pageable pageable);

    Optional<DummyData> findDummyDataById(Long id);
}
