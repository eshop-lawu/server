package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.dto.GameDialDTO;
import com.lawu.eshop.game.param.GameDialParam;
import com.lawu.eshop.game.query.GameDialQuery;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GameDialService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 转盘游戏设置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
@Api(tags = "gameDial")
@RestController
@RequestMapping(value = "gameDial/")
public class GameDialController extends BaseController {
	
	@Autowired
	private GameDialService gameDialService;
	
	
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.GAME_DIAL_ADD)
    @ApiOperation(value = "转盘游戏设置", notes = "转盘游戏设置（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDial:save")
    @RequestMapping(value = {"saveGameDial"}, method = RequestMethod.POST)
	public Result saveGameDial(@RequestBody @ApiParam GameDialParam param ,BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		Result result = gameDialService.saveGameDial(param);
		
		return successCreated();
	}
	
	
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_DIAL_UPDATE)
    @ApiOperation(value = "修改转盘游戏", notes = "修改转盘游戏（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDial:update")
    @RequestMapping(value = {"updateGameDial/{id}"}, method = RequestMethod.PUT)
	public Result updateGameDial(@PathVariable @ApiParam(value = "ID") Long id, @RequestBody @ApiParam GameDialParam param ,BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		Result result = gameDialService.updateGameDial(id,param);
		return successCreated();
	}
	
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询转盘游戏设置", notes = "查询转盘游戏设置（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDial:get")
    @RequestMapping(value = {"getGameDialById/{id}"}, method = RequestMethod.GET)
	public Result<GameDialDTO> getGameDialById(@PathVariable @ApiParam(value = "ID") Long id) {
		Result<GameDialDTO> result =gameDialService.getGameDialById(id);
	    return successGet(result);
	}
    
    
	@SuppressWarnings("unchecked")
	@PageBody
	@ApiOperation(value = "查询列表", notes = "查询列表（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequiresPermissions("gameDial:list")
	@RequestMapping(value = { "page" }, method = RequestMethod.POST)
	public Result<Page<GameDialDTO>> page(@RequestBody GameDialQuery query) {
		Result<Page<GameDialDTO>> result = gameDialService.page(query);
		return successCreated(result);
	}
	
	
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_DIAL_UPDATE)
    @ApiOperation(value = "禁用转盘游戏", notes = "禁用转盘游戏（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDial:setDisable")
    @RequestMapping(value = {"setDisableGameDial/{id}"}, method = RequestMethod.PUT)
	public Result setDisGameDial(@PathVariable @ApiParam(value = "ID") Long id) {
		Result result = gameDialService.setGameDial(id, GameDialStatusEnum.DISABLED);
		return successCreated();
	}
	
	
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_DIAL_UPDATE)
    @ApiOperation(value = "启用转盘游戏", notes = "启用转盘游戏（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDial:setAble")
    @RequestMapping(value = {"setAbleGameDial/{id}"}, method = RequestMethod.PUT)
	public Result setAbleGameDial(@PathVariable @ApiParam(value = "ID") Long id) {
		Result result = gameDialService.setGameDial(id, GameDialStatusEnum.ENABLED);
		return successCreated();
	}
	
}
