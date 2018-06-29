package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.constants.AppStatusEnum;
import com.lawu.eshop.mall.dto.AppVersionOperatorDTO;
import com.lawu.eshop.mall.param.AppVersionOperatorParam;
import com.lawu.eshop.mall.param.AppVersionParam;
import com.lawu.eshop.operator.api.service.AppVersionService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping(value = "appVersion/")
public class AppVersionController extends BaseController{

	@Autowired
	private AppVersionService appVersionService;
	
	/**
	 * 保存app版本
	 * @param param
	 * @return
	 */
	@ApiOperation(value = "保存app版本", notes = "保存app版本,[]（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("app:save")
	@RequestMapping(value = "saveAppVersion", method = RequestMethod.POST)
	Result saveAppVersion(@ModelAttribute @ApiParam AppVersionParam param) {
		return 	appVersionService.saveAppVersion(param);
	}
	
	/**
	 * 启用|禁用
	 * @param id
	 * @param statusEnum
	 * @return
	 */
	@ApiOperation(value = "启用|禁用", notes = "启用|禁用,[]（张荣成）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("app:edit")
	@RequestMapping(value = "updateAppVersion/{id}", method = RequestMethod.PUT)
	Result updateAppVersion(@PathVariable @ApiParam(value = "id") Integer id,@RequestParam @ApiParam(required = true, value = "修改状态") AppStatusEnum statusEnum) {
		return 	appVersionService.updateAppVersion(id,statusEnum);
	}
	
	/**
	 * 删除
	 * @param id
	 * @param statusEnum
	 * @return
	 */
	@ApiOperation(value = "删除", notes = "删除,[]（张荣成）", httpMethod = "DELETE")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequiresPermissions("app:delete")
	@RequestMapping(value = "deleteAppVersion/{id}", method = RequestMethod.DELETE)
	Result deleteAppVersion(@PathVariable @ApiParam(value = "id") Integer id) {
		Result result =appVersionService.updateAppVersion(id,AppStatusEnum.DELETE);
		return 	successDelete();
	}
	
	/**
	 * 列表查询
	 * @param query
	 * @return
	 */
	@ApiOperation(value = "列表查询", notes = "列表查询,[]（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@PageBody
	@RequiresPermissions("app:list")
	@RequestMapping(value = "getVersionOperator", method = RequestMethod.GET)
	Result<Page<AppVersionOperatorDTO>>  getVersionOperator( @ModelAttribute @ApiParam(value = "查询信息") AppVersionOperatorParam query) {
		return appVersionService.getVersionOperator(query);
	}
	
}
