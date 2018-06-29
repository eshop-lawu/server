package com.lawu.eshop.merchant.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.dto.AppFunctionManageDTO;
import com.lawu.eshop.mall.dto.AppMerchantFunctionManageDTO;
import com.lawu.eshop.merchant.api.service.AppFunctionManageService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.framework.web.util.HeaderUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年6月8日
 */
@Api(tags = "appFunctionManage")
@RestController
@RequestMapping(value = "appFunctionManage")
public class AppFunctionManageController extends BaseController{
	
	@Autowired
	private AppFunctionManageService appFunctionManageService;

	@Audit(date = "2018-04-11", reviewer = "孙林青")
	@ApiOperation(value = "app功能启用状态查询", notes = "app功能启用状态查询[8115]（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getAppFunctionManage", method = RequestMethod.GET)
	public Result<AppMerchantFunctionManageDTO> getAppFunctionManage() {
		String  appVersion = HeaderUtil.getRequestAppVersion(getRequest());
		if(StringUtils.isEmpty(appVersion)){
			return successGet(ResultCode.GET_HEADER_ERROR);
		}
		Result<AppFunctionManageDTO> result =appFunctionManageService.getAppFunctionManage(appVersion,UserTypeEnum.MERCHANT);
		AppMerchantFunctionManageDTO dto = new AppMerchantFunctionManageDTO();
		dto.setIsEnableMerchantRich(result.getModel().getIsEnableMerchantRich());
		
		return successGet(dto);
	}

}
