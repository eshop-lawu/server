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
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.GameMindConfigDTO;
import com.lawu.eshop.game.dto.GamePuzzleConfigDTO;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GameConfigCacheService;
import com.lawu.eshop.operator.api.service.GamePuzzleConfigService;
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
@Api(tags = "gamePuzzleConfig")
@RestController
@RequestMapping(value = "gamePuzzleConfig/")
public class GamePuzzleConfigController extends BaseController {
	
	@Autowired
	private GamePuzzleConfigService gamePuzzleConfigService;
	
	@Autowired
	private GameConfigCacheService gameConfigCacheService;
	
	@LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.GAME_PUZZLE_CONFIG_ADD)
    @ApiOperation(value = "保存拼图游戏设置", notes = "保存拼图游戏设置（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gamePuzzleConfig:save")
    @RequestMapping(value = {"saveGamePuzzleConfig"}, method = RequestMethod.POST)
	public Result saveGamePuzzleConfig(@RequestBody @ApiParam GamePuzzleConfigParam param ,BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		
		Result result = gamePuzzleConfigService.saveGamePuzzleConfig(param);
		if(isSuccess(result)){
			gameConfigCacheService.setGamePuzzleConfig(param);
		}
		return successCreated();
	}
	
    
    @LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.GAME_PUZZLE_CONFIG_ABLE)
    @ApiOperation(value = "游戏禁用启用", notes = "游戏禁用启用（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gamePuzzleConfig:enable")
    @RequestMapping(value = {"setEnable"}, method = RequestMethod.PUT)
	public Result setEnable(@RequestParam @ApiParam GameConfigStatusEnum statusEnum ) {
		Result result = gamePuzzleConfigService.setEnable(statusEnum);
		return successCreated(result);
	}
    
    
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_PUZZLE_CONFIG_UPDATE)
    @ApiOperation(value = "修改拼图游戏设置", notes = "修改拼图游戏设置（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gamePuzzleConfig:update")
    @RequestMapping(value = {"updateGamePuzzleConfig/{id}"}, method = RequestMethod.PUT)
	public Result updateGamePuzzleConfig(@PathVariable @ApiParam(value = "ID") Long id,@RequestBody @ApiParam GamePuzzleConfigParam param ,BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
		
		Result result = gamePuzzleConfigService.updateGamePuzzleConfig(id,param);
		if(isSuccess(result)){
			gameConfigCacheService.setGamePuzzleConfig(param);
		}
		return successCreated();
	}
    
    
    @SuppressWarnings("unchecked")
   	@ApiOperation(value = "查询拼图游戏设置", notes = "查询拼图游戏设置（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gamePuzzleConfig:get")
    @RequestMapping(value = {"getGamePuzzleConfig"}, method = RequestMethod.GET)
   	public Result<GameMindConfigDTO> selectGameMindConfig() {
   		Result<GamePuzzleConfigDTO> result =gamePuzzleConfigService.getGamePuzzleConfig();
   	    return successGet(result);
   	}
	
}
