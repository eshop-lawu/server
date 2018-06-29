package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/28.
 */
@FeignClient(value = "cache-srv")
public interface RecommendStoreCacheService {

    /**
     * 新店推荐
     *
     * @param regionPath
     * @return
     */
    @RequestMapping(value = "recommendStore/getNewMerchant", method = RequestMethod.GET)
    Result<String> getNewMerchant(@RequestParam("regionPath") String regionPath);

    /**
     * 优选美食-人气最高
     *
     * @param regionPath
     * @return
     */
    @RequestMapping(value = "recommendStore/getRecommendFoodConsume", method = RequestMethod.GET)
    Result<String> getRecommendFoodConsume(@RequestParam("regionPath") String regionPath);

    /**
     * 优选美食-评价最高
     *
     * @param regionPath
     * @return
     */
    @RequestMapping(value = "recommendStore/getRecommendFoodComment", method = RequestMethod.GET)
    Result<String> getRecommendFoodComment(@RequestParam("regionPath") String regionPath);

}
