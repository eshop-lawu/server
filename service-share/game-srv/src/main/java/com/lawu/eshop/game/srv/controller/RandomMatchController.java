package com.lawu.eshop.game.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.game.srv.service.RandomMatchService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 随机匹配控制器
 * @author jiangxinjun
 * @createDate 2018年5月13日
 * @updateDate 2018年5月13日
 */
@RestController
@RequestMapping(value = "randomMatch/")
public class RandomMatchController extends BaseController {
    
    @Autowired
    private RandomMatchService randomMatchService;
    
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
    public Result releaseRobotResources(@RequestParam("robotUserNum") String robotUserNum){
        randomMatchService.releaseRobotResources(robotUserNum);
        return successCreated();
    }
    
}
