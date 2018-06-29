package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.RandomMatchService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 随机匹配控制器
 * @author jiangxinjun
 * @createDate 2018年5月13日
 * @updateDate 2018年5月13日
 */
@Api(tags = {"随机匹配控制器"})
@RestController
@RequestMapping(value = "randomMatch/")
public class RandomMatchController extends BaseController {
    
    @Autowired
    private RandomMatchService randomMatchService;
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "释放机器人资源", notes = "释放机器人资源 [] 蒋鑫俊", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "releaseRobotResources", method = RequestMethod.PUT)
    public Result releaseRobotResources(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
        String robotUserNum = UserUtil.getCurrentUserNum(getRequest());
        return successCreated(randomMatchService.releaseRobotResources(robotUserNum));
    }
    
}
