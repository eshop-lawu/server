package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
@FeignClient(value = "cache-srv", path = "gameMindCache/")
public interface GameMindCacheService {

    @RequestMapping(value = "getMindQuestions", method = RequestMethod.GET)
    Result<String> getMindQuestions(@RequestParam("attendId") String attendId);

    @RequestMapping(value = "getLastGameTime/{attendId}", method = RequestMethod.GET)
    Result<Long> getLastGameTime(@PathVariable("attendId") Long attendId);
    
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "setGameStartTime/{attendId}", method = RequestMethod.PUT)
    Result setGameTime(@PathVariable("attendId") Long attendId, @RequestParam("times") Long times);
}
