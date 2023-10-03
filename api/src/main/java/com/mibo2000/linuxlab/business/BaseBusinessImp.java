package com.mibo2000.linuxlab.business;

import com.mibo2000.linuxlab.BaseResponse;
import com.mibo2000.linuxlab.Translator;
import com.mibo2000.linuxlab.business.dto.DummySingleData;
import com.mibo2000.linuxlab.business.dto.FetchResponse;
import com.mibo2000.linuxlab.business.dto.FetchSingleResponse;
import com.mibo2000.linuxlab.business.dto.InsertRequest;
import com.mibo2000.linuxlab.exception.BusinessException;
import com.mibo2000.linuxlab.model.EnumPool;
import com.mibo2000.linuxlab.model.EnumPool.DirectionType;
import com.mibo2000.linuxlab.model.dto.BasicDummyId;
import com.mibo2000.linuxlab.model.dto.Post;
import com.mibo2000.linuxlab.model.dto.Product;
import com.mibo2000.linuxlab.model.dto.Quote;
import com.mibo2000.linuxlab.model.entity.DummyData;
import com.mibo2000.linuxlab.service.BaseService;
import com.mibo2000.linuxlab.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.mibo2000.linuxlab.exception.ErrorCode.Business.*;

/**
 * @author : MibO2000
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class BaseBusinessImp implements BaseBusiness {
    final BaseService baseService;
    final Util util;

    @Override
    public BaseResponse fetchDummyData(int pageNo, int pageSize) {
        checkRequest(pageNo, pageSize);
        return generateBaseResponse(getDummyResponse(pageNo, pageSize));
        /*
        * Another way to get a response
        * */
//        return generateBaseResponse(getDummyAnotherResponse(pageNo, pageSize));
    }

    @Override
    public BaseResponse insertDummyData(InsertRequest request) {
        int startIndex = (request.getPageNo() - 1) * request.getPageSize();
        int endIndex = Math.min(startIndex + request.getPageSize(), 30);
        Map<Long, Post> postMap = getIdAndDummyDataMap(EnumPool.DummyType.posts, Post.class);
        Map<Long, Product> productMap = getIdAndDummyDataMap(EnumPool.DummyType.products, Product.class);
        Map<Long, Quote> quoteMap = getIdAndDummyDataMap(EnumPool.DummyType.quotes, Quote.class);
        DummyData data = new DummyData();
        List<DummyData> duplicate = new ArrayList<>();
        for (; startIndex <= endIndex; startIndex++) {
            data.setId((long) startIndex);
            data.setPost(postMap.get((long) startIndex));
            data.setProduct(productMap.get((long) startIndex));
            data.setQuote(quoteMap.get((long) startIndex));
            if (baseService.findDummyDataById(data.getId()).isPresent()) {
                duplicate.add(data);
            } else {
                baseService.saveDummyData(data);
            }
        }
        if (!duplicate.isEmpty()) {
            log.info("DUPLICATES : [{}]", util.toJson(duplicate));
            return generateBaseResponse("Duplicates are not added");
        }
        return generateBaseResponse(null);
    }

    @Override
    public BaseResponse getDummyData(int pageNo, int pageSize, String sortBy, DirectionType direction) {
        checkRequest(pageNo + 1, pageSize);
        return generateBaseResponse(baseService.getDummyData(getPageable(pageNo, pageSize, sortBy, direction)));
    }

    @Override
    public BaseResponse deleteDummyDataById(Long id) {
        if (baseService.findDummyDataById(id).isEmpty()) {
            throw new BusinessException(ENTITY_DOES_NOT_EXISTS);
        }
        baseService.deleteDummyDataById(id);
        return generateBaseResponse(null);
    }

    private BaseResponse generateBaseResponse(Object result) {
        return new BaseResponse(SUCCESS, Translator.toLocale(SUCCESS), result);
    }

    private Pageable getPageable(int pageNo, int pageSize, String sortBy, DirectionType direction) {
        Sort sort = (direction == DirectionType.desc) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return PageRequest.of(pageNo, pageSize, sort);
    }

    private FetchResponse getDummyResponse(int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, 30);
        CompletableFuture<List<Post>> postsFuture = CompletableFuture.supplyAsync(() -> getSortedListOfDummyResponse(EnumPool.DummyType.posts, Post.class).subList(startIndex, endIndex));
        CompletableFuture<List<Product>> productsFuture = CompletableFuture.supplyAsync(() -> getSortedListOfDummyResponse(EnumPool.DummyType.products, Product.class).subList(startIndex, endIndex));
        CompletableFuture<List<Quote>> quotesFuture = CompletableFuture.supplyAsync(() -> getSortedListOfDummyResponse(EnumPool.DummyType.quotes, Quote.class).subList(startIndex, endIndex));

        try {
            return CompletableFuture.allOf(postsFuture, productsFuture, quotesFuture)
                    .thenApply(ignored -> {
                        try {
                            List<Post> posts = postsFuture.get();
                            List<Product> products = productsFuture.get();
                            List<Quote> quotes = quotesFuture.get();

                            return FetchResponse.builder()
                                    .postList(posts)
                                    .productList(products)
                                    .quoteList(quotes)
                                    .pageNo(pageNo)
                                    .pageSize(pageSize)
                                    .build();

                        } catch (InterruptedException | ExecutionException e) {
                            log.error("ERROR IN COMPLETABLE FUTURE : " + e);
                            throw new BusinessException(UNKNOWN);
                        }
                    }).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("ERROR OUT OF COMPLETABLE FUTURE : " + e);
            throw new BusinessException(UNKNOWN);
        }
    }
    private FetchSingleResponse getDummyAnotherResponse(int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, 30);
        CompletableFuture<List<Post>> postsFuture = CompletableFuture.supplyAsync(() -> getSortedListOfDummyResponse(EnumPool.DummyType.posts, Post.class).subList(startIndex, endIndex));
        CompletableFuture<List<Product>> productsFuture = CompletableFuture.supplyAsync(() -> getSortedListOfDummyResponse(EnumPool.DummyType.products, Product.class).subList(startIndex, endIndex));
        CompletableFuture<List<Quote>> quotesFuture = CompletableFuture.supplyAsync(() -> getSortedListOfDummyResponse(EnumPool.DummyType.quotes, Quote.class).subList(startIndex, endIndex));
        try {
            return CompletableFuture.allOf(postsFuture, productsFuture, quotesFuture)
                    .thenApply(ignored -> {
                        try {
                            return FetchSingleResponse.builder()
                                    .dataList(changeResponseToSingle(postsFuture.get(), productsFuture.get(), quotesFuture.get()))
                                    .pageNo(pageNo)
                                    .pageSize(pageSize)
                                    .build();

                        } catch (InterruptedException | ExecutionException e) {
                            log.error("ERROR IN COMPLETABLE FUTURE : " + e);
                            throw new BusinessException(UNKNOWN);
                        }
                    }).get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("ERROR OUT OF COMPLETABLE FUTURE : " + e);
            throw new BusinessException(UNKNOWN);
        }
    }

    private <T extends BasicDummyId> List<T> getSortedListOfDummyResponse(EnumPool.DummyType type, Class<T> clazz) {
        List<T> responseList = baseService.getDummyResponse(type, clazz);
        responseList.sort(Comparator.comparingLong(T::getId));
        return responseList;
    }

    private <T extends BasicDummyId> Map<Long, T> getIdAndDummyDataMap(EnumPool.DummyType type, Class<T> clazz) {
        return getSortedListOfDummyResponse(type, clazz)
                .stream().collect(Collectors.toMap(T::getId, x -> x));
    }

    private void checkRequest(int pageNo, int pageSize) {
        if (pageNo <= 0) {
            throw new BusinessException(INVALID_PAGE_NO);
        } else if (pageSize <= 0) {
            throw new BusinessException(INVALID_PAGE_SIZE);
        }
    }

    private List<DummySingleData> changeResponseToSingle(List<Post> postList, List<Product> productList, List<Quote> quoteList) {
        List<DummySingleData> returnList = new ArrayList<>();
        for (int i = 0; i < postList.size(); i++) {
            returnList.add(DummySingleData.builder()
                    .post(postList.get(i)).product(productList.get(i)).quote(quoteList.get(i))
                    .build());
        }
        return returnList;
    }

}
