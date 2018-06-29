package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.param.GameInformParam;
import com.lawu.eshop.member.api.service.GameInformService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 游戏举报
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
@Api(tags = "gameInform")
@RestController
@RequestMapping(value = "gameInform/")
public class GameInformController extends BaseController{
	
	@Autowired
	private GameInformService gameInformService;

    @Audit(date = "2018-03-15", reviewer = "孙林青")
	@ApiOperation(value = "游戏举报", notes = "游戏举报[10000](张荣成)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "saveGameInform", method = RequestMethod.POST)
    public Result saveGameInform(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute GameInformParam param) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result result = gameInformService.saveGameInform(userNum, param);
        return successCreated(result);
    }

}
