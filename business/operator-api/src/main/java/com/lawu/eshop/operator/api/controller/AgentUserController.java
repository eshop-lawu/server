package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.agent.dto.AgentUserListDTO;
import com.lawu.eshop.agent.param.AgentUserListParam;
import com.lawu.eshop.agent.param.AgentUserParam;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.AgentUserService;
import com.lawu.framework.core.page.Page;
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
@Api(tags = "agentUser")
@RestController
@RequestMapping(value = "agentUser/")
public class AgentUserController extends BaseController{

    @Autowired
    private AgentUserService agentUserService;

    @ApiOperation(value = "添加代理商账号", notes = "添加代理商账号。[1000，8111，8112]（章勇）", httpMethod = "POST")
    @RequiresPermissions("agent:add")
    @RequestMapping(value = "addAgentUser", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result addAgentUser(@ModelAttribute @ApiParam AgentUserParam param) {

        return agentUserService.addAgentUser(param);
    }


    @ApiOperation(value = "查询代理商列表", notes = "查询代理商列表。[1000]（章勇）", httpMethod = "GET")
    @RequiresPermissions("agent:list")
    @RequestMapping(value = "getAgentUserList", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    public Result<Page<AgentUserListDTO>> getAgentUserList(@ModelAttribute AgentUserListParam param){

        return agentUserService.getAgentUserList(param);

    }

    @ApiOperation(value = "查询单个代理商", notes = "查询单个代理商。[1000]（章勇）", httpMethod = "GET")
    @RequiresAuthentication
    @RequestMapping(value = "getAgentUser/{id}", method = RequestMethod.GET)
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    public Result<AgentUserListDTO> getAgentUser(@PathVariable("id") Long id){

        return agentUserService.getAgentUser(id);
    }

    @ApiOperation(value = "编辑代理商账号", notes = "编辑代理商账号。[1000，8111，8112]（章勇）", httpMethod = "POST")
    @RequiresPermissions("agent:edit")
    @RequestMapping(value = "editAgentUser/{id}", method = RequestMethod.POST)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    public Result editAgentUser(@PathVariable("id") Long id, @ModelAttribute @ApiParam AgentUserParam param) {

        return agentUserService.editAgentUser(id,param);
    }

}
