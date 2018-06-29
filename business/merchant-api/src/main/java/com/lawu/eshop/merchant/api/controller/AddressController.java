package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.AddressService;
import com.lawu.eshop.user.dto.AddressDTO;
import com.lawu.eshop.user.param.AddressMerchantParam;
import com.lawu.eshop.user.param.AddressParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收货地址管理
 * 
 * @author Sunny
 * @date 2017年4月20日
 */
@Api(tags = "address")
@RestController
@RequestMapping(value = "address/")
public class AddressController extends BaseController {

	@Autowired
	private AddressService addressService;

	@Audit(date = "2017-04-21", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@Authorization
	@ApiOperation(value = "收货地址信息查询", notes = "查询当前用户收货地址列表信息[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Result<List<AddressDTO>> selectAddress(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<List<AddressDTO>> result = addressService.selectByUserNum(userNum);
		if (!isSuccess(result)) {
			return successGet(result.getRet());
		}

		return successGet(result);
	}

	@SuppressWarnings("unchecked")
	@Audit(date = "2017-04-21", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "查询单个收货地址", notes = "单个查询收货地址[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Result<AddressDTO> get(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable @ApiParam(required = true, value = "收货地址id") Long id) {
		Result<AddressDTO> addressDTO = addressService.get(id);
		
		if (!isSuccess(addressDTO)) {
			return successGet(addressDTO.getRet());
		}
		
		return successGet(addressDTO);
	}

	@Audit(date = "2017-04-21", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@Authorization
	@ApiOperation(value = "删除收货地址", notes = "删除收货地址[1002]（蒋鑫俊）", httpMethod = "DELETE")
	@ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
	public Result remove(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable @ApiParam(required = true, value = "收货地址id") Long id) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result rs = addressService.delete(id, userNum);
		
		if (!isSuccess(rs)) {
			return successCreated(rs.getRet());
		}
		
		return successDelete();
	}

	@Audit(date = "2017-04-21", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@Authorization
	@ApiOperation(value = "添加收货地址", notes = "添加收货地址[]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "addAddress", method = RequestMethod.POST)
	public Result addAddress(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(required = true, value = "收货地址信息") @Validated AddressMerchantParam addressParam, BindingResult bindingResult) {
		
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		
		AddressParam address=new AddressParam();
		address.setAddr(addressParam.getAddr());
		address.setMobile(addressParam.getMobile());
		address.setName(addressParam.getName());
		address.setRegionPath(addressParam.getRegionPath());
		address.setRegionName(addressParam.getRegionName());
		
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result result = addressService.saveWithUserNum(userNum, address);
		if (!isSuccess(result)) {
			return successCreated(result.getRet());
		}

		return successCreated();
	}

	@Audit(date = "2017-04-21", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@Authorization
	@ApiOperation(value = "修改收货地址", notes = "修改收货地址[]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public Result update(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable @ApiParam(required = true, value = "收货地址id") Long id, @ModelAttribute @ApiParam(required = true, value = "收货地址信息") AddressParam address) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result rs = addressService.update(address, id, userNum);
		if (!isSuccess(rs)) {
			return successCreated(rs.getRet());
		}
		return successCreated();

	}

	@Audit(date = "2017-04-21", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@Authorization
	@ApiOperation(value = "收货默认地址修改", notes = "修改收货默认地址[]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "updateDefault/{id}", method = RequestMethod.PUT)
	public Result updateDefault(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @PathVariable @ApiParam(required = true, value = "收货地址id") Long id) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result rs = addressService.updateDefault(id, userNum);
		if (!isSuccess(rs)) {
			return successCreated(rs.getRet());
		}
		return successCreated();
	}

}
