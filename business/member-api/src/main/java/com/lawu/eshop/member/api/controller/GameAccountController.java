package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.dto.GameAccountDTO;
import com.lawu.eshop.game.dto.GameAccountInfoDTO;
import com.lawu.eshop.game.dto.ShareCountDTO;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.member.api.service.GameAccountService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.PropertyInfoService;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 游戏账户设置
 *
 * @author zhangrc
 * @date 2018/3/10.
 */
@Api(tags = "gameAccount")
@RestController
@RequestMapping(value = "gameAccount/")
public class GameAccountController extends BaseController{
	
	@Autowired
	private GameAccountService gameAccountService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PropertyInfoService propertyInfoService;

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取用户信息", notes = "获取用户信息(张荣成)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
    @RequestMapping(value = "getGameAccountInfo", method = RequestMethod.GET)
	public Result<GameAccountInfoDTO> getGameAccountInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam(required = false) @ApiParam(value = "MIND 头脑pk   PUZZLE 拼图  DIAL 转盘")  GameTypeEnum typeEnum) {
		Result<MemberDTO> result = memberService.findMemberInfoById(UserUtil.getCurrentUserId(getRequest()));
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		GameAccountParam param = new GameAccountParam();
		param.setUserNum(userNum);
		param.setType(typeEnum);
		param.setAccount(result.getModel().getAccount());
		
		Result<GameAccountDTO> accountResult = gameAccountService.getAccountInfo(param);
		Result<PropertyPointDTO> pointRs = propertyInfoService.getPropertyPoint(userNum);
		if(!isSuccess(accountResult)){
			return  successGet(accountResult);
		}
		GameAccountInfoDTO info = new GameAccountInfoDTO();
		info.setHeadImg(result.getModel().getHeadimg());
		info.setNikeName(result.getModel().getNickname());
		info.setRegionName(result.getModel().getRegionName());
		info.setAttendPoint(accountResult.getModel().getAttendPoint());
		info.setFreeCount(accountResult.getModel().getFreeCount());
		info.setStarCount(accountResult.getModel().getStarCount());
		info.setPoint(pointRs.getModel().getPoint());
		info.setRegionPath(result.getModel().getRegionPath());
		info.setAttendStarCount(accountResult.getModel().getAttendStarCount());
		return successGet(info);
	}

	@SuppressWarnings("unchecked")
	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "分享获取免费次数", notes = "分享获取免费次数(张荣成)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
    @RequestMapping(value = "shareAddAttendCount", method = RequestMethod.GET)
	public Result<ShareCountDTO> shareAddAttendCount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam(required = false) @ApiParam(value = "MIND 头脑pk   PUZZLE 拼图")  GameTypeEnum typeEnum) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<ShareCountDTO> result = gameAccountService.shareAddAttendCount(userNum, typeEnum);
		if(!isSuccess(result)){
			return  successGet(result);
		}
		return successGet(result);
	}

}
