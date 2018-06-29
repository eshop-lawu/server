package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendDTO;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.WeixinUserService;
import com.lawu.eshop.user.dto.WeixinUserBindDTO;
import com.lawu.eshop.user.param.WeixinUserBindParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 微信用户接口类
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
@Api(tags = "weixinUser")
@RestController
@RequestMapping(value = "weixinUser/")
public class WeixinUserController extends BaseController{
	
	@Autowired
	private WeixinUserService weixinUserService;


	@Audit(date = "2018-01-02", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "是否绑定公众号", notes = "是否绑定公众号（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getWeixinUserIsBind", method = RequestMethod.GET)
	public Result<WeixinUserBindDTO> getWeixinUserIsBind( @RequestParam @ApiParam(required = true, value = "openId") String openId) {
		Result<WeixinUserBindDTO> result = weixinUserService.getWeixinUserIsBind(openId);
		return successGet(result);
	}

	@Audit(date = "2018-01-02", reviewer = "孙林青")
	@ApiOperation(value = "绑定公众号", notes = "绑定公众号[2000]（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "weixinUserBind", method = RequestMethod.POST)
	public Result weixinUserBind(@ModelAttribute WeixinUserBindParam bindParam) {
		Result result = weixinUserService.weixinUserBind(bindParam);
		return successCreated(result);
	}

	@Audit(date = "2018-01-17", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "通过账号判断是否绑定公众号", notes = "是否绑定公众号（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getwxUserIsBindByAccount", method = RequestMethod.GET)
	public Result<Boolean> getwxUserIsBindByAccount( @RequestParam @ApiParam(required = true, value = "account") String account) {
		Result<Boolean> result = weixinUserService.getwxUserIsBindByAccount(account);
		return successGet(result);
	}

}
