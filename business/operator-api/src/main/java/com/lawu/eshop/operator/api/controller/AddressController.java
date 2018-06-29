package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.dto.AppVersionOperatorDTO;
import com.lawu.eshop.operator.api.service.AddressService;
import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.eshop.user.param.OperatorAddressParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月14日
 */
@RestController
@RequestMapping(value = "address/")
public class AddressController extends BaseController {
	
	@Autowired
	private AddressService addressService;
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "地址列表查询", notes = "地址查询,[]（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@PageBody
	@RequiresPermissions("address:list")
	@RequestMapping(value = "operatorAddressPage", method = RequestMethod.GET)
	Result<Page<AddressDTO>>  operatorAddressPage(@ModelAttribute @ApiParam(value = "查询信息") OperatorAddressParam query) {
		Result<Page<AddressDTO>> result = addressService.operatorAddressPage(query);
		return successCreated(result);
	}
	

}
