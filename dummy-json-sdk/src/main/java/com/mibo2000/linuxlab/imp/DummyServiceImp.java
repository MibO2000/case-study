package com.mibo2000.linuxlab.imp;

import com.mibo2000.linuxlab.DummyService;
import com.mibo2000.linuxlab.config.SdkConfig;
import com.mibo2000.linuxlab.dto.DummyPostResponse;
import com.mibo2000.linuxlab.dto.DummyProductResponse;
import com.mibo2000.linuxlab.dto.DummyQuoteResponse;
import com.mibo2000.linuxlab.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.mibo2000.linuxlab.exception.ErrorCode.Business.REST_TEMPLATE_ERROR;

/**
 * @author : MibO2000
 */
@Service
@Slf4j
public class DummyServiceImp implements DummyService {
    final RestTemplate restTemplate;
    final SdkConfig config;

    public DummyServiceImp(SdkConfig config) {
        this.restTemplate = new RestTemplate();
        this.config = config;
    }

    @Value("config")
    @Override
    public DummyPostResponse getDummyPost() {
        StringBuilder stringBuilder = new StringBuilder(config.getUrl());
        stringBuilder.append("/posts");
        long start = System.currentTimeMillis();
        try {
            return restTemplate.getForObject(stringBuilder.toString(), DummyPostResponse.class);
        } catch (Exception e) {
            log.error("ERROR WHILE CALLING POSTS : " + e);
            throw new BusinessException(REST_TEMPLATE_ERROR);
        } finally {
            log.info("[{}] ms to call Posts API", System.currentTimeMillis() - start);
        }
    }

    @Override
    public DummyProductResponse getDummyProducts() {
        StringBuilder stringBuilder = new StringBuilder(config.getUrl());
        stringBuilder.append("/products");
        long start = System.currentTimeMillis();
        try {
            return restTemplate.getForObject(stringBuilder.toString(), DummyProductResponse.class);
        } catch (Exception e) {
            log.error("ERROR WHILE CALLING PRODUCTS : " + e);
            throw new BusinessException(REST_TEMPLATE_ERROR);
        } finally {
            log.info("[{}] ms to call Products API", System.currentTimeMillis() - start);
        }
    }

    @Override
    public DummyQuoteResponse getDummyQuotes() {
        StringBuilder stringBuilder = new StringBuilder(config.getUrl());
        stringBuilder.append("/quotes");
        long start = System.currentTimeMillis();
        try {
            return restTemplate.getForObject(stringBuilder.toString(), DummyQuoteResponse.class);
        } catch (Exception e) {
            log.error("ERROR WHILE CALLING QUOTES : " + e);
            throw new BusinessException(REST_TEMPLATE_ERROR);
        } finally {
            log.info("[{}] ms to call Quotes API", System.currentTimeMillis() - start);
        }
    }
}
