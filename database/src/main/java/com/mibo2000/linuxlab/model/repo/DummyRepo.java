package com.mibo2000.linuxlab.model.repo;

import com.mibo2000.linuxlab.model.entity.DummyData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : MibO2000
 */
public interface DummyRepo extends MongoRepository<DummyData, Long> {
    Page<DummyData> findAllByIdNot(Long id, Pageable pageable);
}
