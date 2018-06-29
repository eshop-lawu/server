package com.lawu.eshop.member.ws.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

import java.util.List;

/**
 * 头脑PK缓存接口服务
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@FeignClient(value = "cache-srv", path = "gamePuzzleCache/")
public interface GamePuzzleCacheService {
    
    /**
     * 每次用户上线, 把当前用户放入缓存中, 并且返回, 目前已经在组内的用户
     * 
     * @param userNum
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月16日
     */
    @RequestMapping(value = "readyStartGame", method = RequestMethod.PUT)
    Result<List<String>> readyStartGame(@RequestParam("userNum") String userNum, @RequestParam("attendNum") String attendNum);
    
    @RequestMapping(value = "incrementCount", method = RequestMethod.GET)
    Result<Long> incrementCount(@RequestParam("attendNum") String attendNum);
    
}
