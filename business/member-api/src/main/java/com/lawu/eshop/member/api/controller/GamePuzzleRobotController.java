package com.lawu.eshop.member.api.controller;

import java.util.List;

import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.member.api.service.GameConfigCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.constants.GameCacheKeyEnum;
import com.lawu.eshop.game.dto.GamePuzzleGetPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleGetPicReturnDTO;
import com.lawu.eshop.member.api.service.GamePuzzleRobotCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 机器人拼图
 * 
 * @author lihj
 * @date 2018年5月14日
 */
@Api(tags = "gamePuzzleRobot")
@RestController
@RequestMapping(value = "gamePuzzleRobot/")
public class GamePuzzleRobotController extends BaseController{

	@Autowired
	private GamePuzzleRobotCacheService gamePuzzleRobotCacheService;
	@Autowired
	private GameConfigCacheService gameConfigCacheService;

	@ApiOperation(value = "获取缓存中拼图信息", notes = "获取缓存中拼图信息(李洪军)", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getPuzzleInfo", method = RequestMethod.POST)
	public Result<GamePuzzleGetPicReturnDTO> getPuzzleInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@RequestParam @ApiParam(required = true, value = "参与编号") String attendNum){
		String jsonObject = gamePuzzleRobotCacheService.getRedisKeyGamePuzzleStartType(GameCacheKeyEnum.PUZZLE_JSON + attendNum).getModel();
		List<GamePuzzleGetPicDTO> dtoCache = JSON.parseArray(jsonObject, GamePuzzleGetPicDTO.class);// 缓存数据
		GamePuzzleGetPicReturnDTO returnDTO = new GamePuzzleGetPicReturnDTO();
		returnDTO.setPuzzleInfo(dtoCache);
		return successGet(returnDTO);
	}

	@ApiOperation(value = "获取缓存中拼图配置", notes = "获取缓存中拼图配置(李洪军)", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getGamePuzzleConfig", method = RequestMethod.POST)
	public Result<GamePuzzleConfigCacheDTO> getGamePuzzleConfig(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
		return gameConfigCacheService.getGamePuzzleConfig();
	}


	

}
