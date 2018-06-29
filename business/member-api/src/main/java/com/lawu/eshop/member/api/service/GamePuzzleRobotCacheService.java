package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

/** 
 * 
 * @author lihj
 * @date 2018年5月14日
 */
@FeignClient(value = "cache-srv", path = "gameConfigCache")
public interface GamePuzzleRobotCacheService {

    @RequestMapping(value = "getRedisKeyGamePuzzleStartType", method = RequestMethod.GET)
    Result<String> getRedisKeyGamePuzzleStartType(@RequestParam("key") String key);
    
}
