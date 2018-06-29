package com.lawu.eshop.agent.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.manager.TokenManager;
import com.lawu.eshop.agent.api.service.AgentUserService;
import com.lawu.eshop.agent.dto.LoginDTO;
import com.lawu.eshop.agent.dto.LoginUserDTO;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/8/9.
 */
@Api(tags = "common")
@RestController
@RequestMapping(value = "/")
public class CommonController extends BaseController {

    @Autowired
    private AgentUserService agentUserService;

    @Autowired
    private TokenManager tokenManager;

    @ApiOperation(value = "登录", notes = "根据账号密码登录，成功返回token。[2000]（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "login/{account}", method = RequestMethod.POST)
    public Result<LoginDTO> login(@PathVariable @ApiParam(required = true, value = "账号") String account,
                                  @RequestParam @ApiParam(required = true, value = "密码") String pwd) {
       Result<LoginUserDTO> result =  agentUserService.find(account, pwd);
        if (!isSuccess(result)) {
            return successCreated(result);
        }
        LoginUserDTO userDTO = result.getModel();
        String token = tokenManager.createToken(UserConstant.AGENT_TOKEN_TYPE, userDTO.getNum(), userDTO.getId(), userDTO.getAccount());

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserNum(userDTO.getNum());
        loginDTO.setToken(token);
        loginDTO.setRegionPath(userDTO.getRegionPath());
        return successCreated(loginDTO);
    }

    @ApiOperation(value = "退出", notes = "退出登录，清除token。（章勇）", httpMethod = "DELETE")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @Authorization
    @RequestMapping(value = "logout", method = RequestMethod.DELETE)
    public Result logout(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        tokenManager.delRelationshipByToken(token);
        return successDelete();
    }
}
