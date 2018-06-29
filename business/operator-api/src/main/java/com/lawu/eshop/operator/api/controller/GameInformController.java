package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.game.constants.GameInformStatusEnum;
import com.lawu.eshop.game.dto.GameInformDTO;
import com.lawu.eshop.game.param.DealInformParam;
import com.lawu.eshop.game.query.GameInformQuery;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GameInformService;
import com.lawu.eshop.operator.api.service.UserService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.operator.dto.UserListDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月13日
 */
@Api(tags = "gameInform")
@RestController
@RequestMapping(value = "gameInform/")
public class GameInformController extends BaseController{
	
	@Autowired
	private GameInformService gameInformService;
	
	@Autowired
	private  UserService userService;
	
	@SuppressWarnings("rawtypes")
	@LogRecord(type = OperationTypeEnum.UPDATE, title = LogTitleEnum.GAME_INFORM_DEAL_WITH)
	@ApiOperation(value = "举报处理", notes = "举报处理（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequiresPermissions("gameInform:deal")
	@RequestMapping(value = { "dealInform/{id}" }, method = RequestMethod.POST)
	public Result dealInform(@PathVariable @ApiParam(required = true, value = "举报id") Long id,
			@RequestParam @ApiParam(required = true, value = "审核备注") String remark,
			@RequestParam @ApiParam(required = true, value = "审核备注") GameInformStatusEnum statusEnum) {
		DealInformParam param = new DealInformParam();
		Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
		param.setAuditorId(userResult.getModel().getId());
		param.setAuditorName(userResult.getModel().getName());
		param.setStatusEnum(statusEnum);
		param.setRemark(remark);
		param.setId(id);
		Result result = gameInformService.dealInform(param);
		return successCreated(result);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "举报列表", notes = "举报列表（张荣成）", httpMethod = "POST")
	@PageBody
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequiresPermissions("gameInform:list")
	@RequestMapping(value = { "page" }, method = RequestMethod.POST)
	public Result<Page<GameInformDTO>> page(@RequestBody @ApiParam GameInformQuery query) {
		Result<Page<GameInformDTO>> result = gameInformService.page(query);
		return successCreated(result);
	}

}
