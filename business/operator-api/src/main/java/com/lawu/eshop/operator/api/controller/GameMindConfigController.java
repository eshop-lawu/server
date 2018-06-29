package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.GameMindConfigDTO;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GameConfigCacheService;
import com.lawu.eshop.operator.api.service.GameMindConfigService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 头脑PK游戏设置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@Api(tags = "gameMindConfig")
@RestController
@RequestMapping(value = "gameMindConfig/")
public class GameMindConfigController extends BaseController {
	
	@Autowired
	private GameMindConfigService gameMindConfigService;
	
	@Autowired
	private GameConfigCacheService gameConfigCacheService;
	
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.GAME_MIND_CONFIG_ADD)
    @ApiOperation(value = "保存头脑PK游戏设置", notes = "保存头脑PK游戏设置（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameMindConfig:save")
    @RequestMapping(value = {"saveGameMindConfig"}, method = RequestMethod.POST)
	public Result saveGameMindConfig(@RequestBody @ApiParam GameMindConfigParam param , BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		Result result = gameMindConfigService.saveGameMindConfig(param);
		if(isSuccess(result)){
			gameConfigCacheService.setGameMindConfig(param);
		}
		return successCreated();
	}
	
	
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.GAME_MIND_CONFIG_ABLE)
    @ApiOperation(value = "游戏禁用启用", notes = "游戏禁用启用（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameMindConfig:enable")
    @RequestMapping(value = {"setEnable"}, method = RequestMethod.PUT)
	public Result setEnable(@RequestParam @ApiParam GameConfigStatusEnum statusEnum ) {
		Result result = gameMindConfigService.setEnable(statusEnum);
		return successCreated(result);
	}
	
	
	@LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_MIND_CONFIG_UPDATE)
    @ApiOperation(value = "修改头脑PK游戏设置", notes = "保存头脑PK游戏设置（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameMindConfig:update")
    @RequestMapping(value = {"updateGameMindConfig/{id}"}, method = RequestMethod.PUT)
	public Result updateGameMindConfig(@PathVariable @ApiParam(value = "ID") Long id, @RequestBody @ApiParam GameMindConfigParam param ,BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		Result result = gameMindConfigService.updateGameMindConfig(id,param);
		if(isSuccess(result)){
			gameConfigCacheService.setGameMindConfig(param);
		}
		return successCreated();
	}
	
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "查询头脑PK游戏设置", notes = "查询头脑PK游戏设置（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameMindConfig:get")
    @RequestMapping(value = {"selectGameMindConfig"}, method = RequestMethod.GET)
	public Result<GameMindConfigDTO> selectGameMindConfig() {
		Result<GameMindConfigDTO> result =gameMindConfigService.getGameMindConfig();
	    return successGet(result);
	}
	
    @ApiOperation(value = "删除分配", notes = "删除分配（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = {"delAllot/{id}"}, method = RequestMethod.PUT)
	public Result delAllot(@PathVariable @ApiParam(value = "ID") Long id) {
		Result result = gameMindConfigService.delAllot(id);
		return successCreated(result);
	}
	
}
