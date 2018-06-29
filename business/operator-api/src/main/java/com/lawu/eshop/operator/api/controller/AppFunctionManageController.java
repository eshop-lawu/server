package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.mall.dto.AppFunctionManageInfoDTO;
import com.lawu.eshop.mall.param.AppFunctionManageParam;
import com.lawu.eshop.operator.api.service.AppFunctionManageService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月11日
 */
@Api(tags = "appFunctionManage")
@RestController
@RequestMapping(value = "appFunctionManage")
public class AppFunctionManageController extends BaseController{
	
	@Autowired
	private AppFunctionManageService appFunctionManageService;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "app功能启用状态查询", notes = "app功能启用状态查询（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("appFunctionManage:get")
	@RequestMapping(value = "getAppFunctionManageInfo", method = RequestMethod.GET)
	public Result<AppFunctionManageInfoDTO> getAppFunctionManageInfo() {
		Result<AppFunctionManageInfoDTO> result =appFunctionManageService.getAppFunctionManageInfo();
		return successGet(result);
	}
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "添加修改app功能启用禁用", notes = "添加app功能启用禁用（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequiresPermissions("appFunctionManage:save")
	@RequestMapping(value = "saveAppFunctionManage", method = RequestMethod.POST)
	public Result<AppFunctionManageInfoDTO> saveAppFunctionManage(@RequestBody AppFunctionManageParam param) {
		Result result =appFunctionManageService.saveAppFunctionManage(param);
		return successCreated(result);
	}

}
