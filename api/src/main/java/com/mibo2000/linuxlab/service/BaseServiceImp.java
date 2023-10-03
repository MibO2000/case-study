package com.mibo2000.linuxlab.service;

import com.mibo2000.linuxlab.DummyService;
import com.mibo2000.linuxlab.exception.BusinessException;
import com.mibo2000.linuxlab.model.EnumPool.DummyType;
import com.mibo2000.linuxlab.model.entity.DummyData;
import com.mibo2000.linuxlab.model.repo.DummyRepo;
import com.mibo2000.linuxlab.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.mibo2000.linuxlab.exception.ErrorCode.Business.UNKNOWN;

/**
 * @author : MibO2000
 */
@Service
@RequiredArgsConstructor
public class BaseServiceImp implements BaseService {
    final DummyService dummyService;
    final DummyRepo dummyRepo;
    final Util util;

    @Override
    public <T> List<T> getDummyResponse(DummyType type, Class<T> clazz) {
        switch (type) {
            case posts -> {
                return (List<T>) dummyService.getDummyPost().getPosts();
            }
            case products -> {
                return (List<T>) dummyService.getDummyProducts().getProducts();
            }
            case quotes -> {
                return (List<T>) dummyService.getDummyQuotes().getQuotes();
            }
            default -> {
                throw new BusinessException(UNKNOWN);
            }
        }
    }

    @Override
    public void deleteDummyDataById(Long id) {
        dummyRepo.deleteById(id);
    }

    @Override
    public void saveDummyData(DummyData dummyData) {
        dummyRepo.save(dummyData);
    }

    @Override
    public Page<DummyData> getDummyData(Pageable pageable) {
        return dummyRepo.findAllByIdNot(0L, pageable);
    }

    @Override
    public Optional<DummyData> findDummyDataById(Long id) {
        return dummyRepo.findById(id);
    }

}
