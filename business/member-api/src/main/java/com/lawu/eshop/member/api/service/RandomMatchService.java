package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.framework.web.Result;

@FeignClient(value = "game-srv", path = "randomMatch/")
public interface RandomMatchService {
    
    /**
     * 释放机器人资源
     * @param robotUserNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月13日
     * @updateDate 2018年5月13日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "releaseRobotResources", method = RequestMethod.PUT)
    Result releaseRobotResources(@RequestParam("robotUserNum") String robotUserNum);
    
}
