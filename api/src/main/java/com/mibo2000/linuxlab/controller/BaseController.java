package com.mibo2000.linuxlab.controller;

import com.mibo2000.linuxlab.BaseResponse;
import com.mibo2000.linuxlab.business.BaseBusiness;
import com.mibo2000.linuxlab.business.dto.InsertRequest;
import com.mibo2000.linuxlab.model.EnumPool.DirectionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : MibO2000
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
@Validated
public class BaseController {
    final BaseBusiness business;

    @GetMapping("/fetch")
    ResponseEntity<BaseResponse> fetchDummyData(@RequestParam(defaultValue = "1") int pageNo,
                                                @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(business.fetchDummyData(pageNo, pageSize));
    }

    @PostMapping("/insert")
    ResponseEntity<BaseResponse> insertDummyData(@Valid @RequestBody InsertRequest request) {
        return ResponseEntity.ok(business.insertDummyData(request));
    }

    @GetMapping("/get")
    ResponseEntity<BaseResponse> getDummyData(@RequestParam(defaultValue = "0") int pageNo,
                                              @RequestParam(defaultValue = "10") int pageSize,
                                              @RequestParam(defaultValue = "id") String sort,
                                              @RequestParam(defaultValue = "asc") DirectionType direction) {
        return ResponseEntity.ok(business.getDummyData(pageNo, pageSize, sort, direction));
    }

    @DeleteMapping("/delete")
    ResponseEntity<BaseResponse> deleteDummyData(@RequestParam Long id) {
        return ResponseEntity.ok(business.deleteDummyDataById(id));
    }

}
