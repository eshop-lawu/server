package com.lawu.eshop.jobs.service;

import com.lawu.eshop.statistics.dto.UserActiveDTO;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
@FeignClient(value = "statistics-srv")
public interface CollectionUserActiveService {

    @RequestMapping(value = "userActive/collectionMemberActiveDaily", method = RequestMethod.GET)
    Result<Integer> collectionMemberActiveDaily(@RequestParam(value = "reportDate") String  reportDate);

    @RequestMapping(value = "userActive/collectionMerchantActiveDaily", method = RequestMethod.GET)
    Result<Integer> collectionMerchantActiveDaily(@RequestParam(value = "reportDate") String  reportDate);

    @RequestMapping(value = "userActive/collectionMemberActiveMonth", method = RequestMethod.GET)
    Result<Integer> collectionMemberActiveMonth(@RequestParam(value = "reportDate") String  reportDate);

    @RequestMapping(value = "userActive/collectionMerchantActiveMonth", method = RequestMethod.GET)
    Result<Integer> collectionMerchantActiveMonth(@RequestParam(value = "reportDate") String  reportDate);

    @RequestMapping(value = "userActive/collectionMemberActiveAreaDaily", method = RequestMethod.GET)
    Result<List<UserActiveDTO>> collectionMemberActiveAreaDaily(@RequestParam(value = "reportDate") String  reportDate);

    @RequestMapping(value = "userActive/collectionMerchantActiveAreaDaily", method = RequestMethod.GET)
    Result<List<UserActiveDTO>> collectionMerchantActiveAreaDaily(@RequestParam(value = "reportDate") String  reportDate);

    @RequestMapping(value = "userActive/collectionMemberActiveAreaMonth", method = RequestMethod.GET)
    Result<List<UserActiveDTO>> collectionMemberActiveAreaMonth(@RequestParam(value = "reportDate") String  reportDate);

    @RequestMapping(value = "userActive/collectionMerchantActiveAreaMonth", method = RequestMethod.GET)
    Result<List<UserActiveDTO>> collectionMerchantActiveAreaMonth(@RequestParam(value = "reportDate") String  reportDate);
}
