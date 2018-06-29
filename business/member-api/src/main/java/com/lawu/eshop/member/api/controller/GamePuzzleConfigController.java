package com.lawu.eshop.member.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.game.dto.GamePuzzleDifficultyDTO;
import com.lawu.eshop.member.api.service.GameConfigCacheService;
import com.lawu.eshop.member.api.service.GamePuzzleConfigService;
import com.lawu.eshop.member.api.service.GamePuzzleDifficultyService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 拼图配置
 *
 * @author lihj
 * @date 2018/3/10.
 */
@Api(tags = "gamePuzzleConfig")
@RestController
@RequestMapping(value = "gamePuzzleConfig/")
public class GamePuzzleConfigController extends BaseController {

	@Autowired
	private GamePuzzleDifficultyService gamePuzzleDifficultyService;

	@Audit(date = "2018-03-15", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "获取难易程度", notes = "获取难易程度(张荣成)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getPuzzleDifficulty", method = RequestMethod.GET)
	public Result<List<GamePuzzleDifficultyDTO>> getPuzzleDifficulty() {
		/*
		 * Result<String> result = gameConfigCacheService.getGamePuzzleConfig();
		 * GamePuzzleConfigParam param = JSON.parseObject(result.getModel(),
		 * GamePuzzleConfigParam.class);
		 */
		return successGet(gamePuzzleDifficultyService.getPuzzleDifficulty());
	}
}
