package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.PropertyInfoService;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.dto.PropertyBalanceDTO;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyPointAndBalanceDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.eshop.property.dto.PropertyRtnDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny 
 * @date 2017/3/29
 */
@Api(tags = "propertyInfo")
@RestController
@RequestMapping(value = "propertyInfo/")
public class PropertyInfoController extends BaseController {

    @Autowired
    private PropertyInfoService propertyInfoService;

	@SuppressWarnings("unchecked")
	@Audit(date = "2017-03-29", reviewer = "孙林青")
    @ApiOperation(value = "获取资产余额", notes = "根据用户编号获取资产余额。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "balance", method = RequestMethod.GET)
    public Result<PropertyBalanceDTO> getPropertyBalance(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	
    	Result<PropertyBalanceDTO> result = propertyInfoService.getPropertyBalance(userNum);
    	if (!isSuccess(result)) {
    		return successGet(result.getRet());
    	}
    	
    	return successGet(result);
    }
    
    /**
     *根据用户编号获取积分。
     *
     * @param token
     * @return
     */
    @SuppressWarnings("unchecked")
	@Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "获取积分", notes = "根据用户编号获取积分。[]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "point", method = RequestMethod.GET)
    public Result<PropertyPointDTO> getPropertyPoint(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	
    	Result<PropertyPointDTO> result = propertyInfoService.getPropertyPoint(userNum);
    	if (!isSuccess(result)) {
    		return successGet(result.getRet());
    	}
    	
    	return successGet(result);
    }

    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "获取资产余额积分", notes = "根据用户编号获取资产余额积分。[]（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getPropertyInfoMoney", method = RequestMethod.GET)
    public Result<PropertyPointAndBalanceDTO> getPropertyInfoMoney(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	return successGet(propertyInfoService.getPropertyInfoMoney(userNum));
    }

    @Audit(date = "2017-08-18", reviewer = "李洪军")
    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "验证支付密码", notes = "验证支付密码(true--正确，false--错误)。[1002|1022]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "varifyPayPwd", method = RequestMethod.GET)
    public Result varifyPayPwd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                               @RequestParam @ApiParam(required = true, value = "支付密码") String payPwd) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return propertyInfoService.varifyPayPwd(userNum, payPwd);
    }

    @Audit(date = "2017-06-30", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取用户是否冻结", notes = "获取用户是否冻结(NO-否|YES-是)。[6026]（蒋鑫俊）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getPropertyinfoFreeze", method = RequestMethod.GET)
    public Result<PropertyInfoFreezeDTO> getPropertyinfoFreeze(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<PropertyInfoFreezeDTO> result = propertyInfoService.getPropertyinfoFreeze(userNum);
        return successGet(result);
    }

    @Audit(date = "2018-03-23", reviewer = "孙林青")
    @ApiOperation(value = "获取用户充值积分比例资产配置", notes = "获取用户充值积分比例资产配置[]（杨清华）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getRechargeRate", method = RequestMethod.GET)
    public Result<PropertyRtnDTO> getRechargeRate(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Result result = propertyInfoService.getRechargeRate(PropertyType.MEMBER_THIRD_PAY_POINT);
        PropertyRtnDTO rtn = new PropertyRtnDTO();
        rtn.setValue(result.getModel().toString());
        return successGet(rtn);
    }

}
