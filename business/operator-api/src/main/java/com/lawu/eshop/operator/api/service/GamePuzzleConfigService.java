package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.eshop.game.dto.GamePuzzleConfigDTO;
import com.lawu.framework.web.Result;

/**
 * 拼图游戏设置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@FeignClient(value = "game-srv", path = "gamePuzzleConfig/")
public interface GamePuzzleConfigService {
	
	@RequestMapping(value = "saveGamePuzzleConfig", method = RequestMethod.POST)
	Result saveGamePuzzleConfig(@RequestBody GamePuzzleConfigParam param);
	
	@RequestMapping(value = "setEnable", method = RequestMethod.POST)
	Result setEnable(@RequestParam("statusEnum") GameConfigStatusEnum statusEnum);
	
	@RequestMapping(value = "updateGamePuzzleConfig/{id}", method = RequestMethod.POST)
	Result updateGamePuzzleConfig(@PathVariable("id") Long id,@RequestBody GamePuzzleConfigParam param);
	
	
	@RequestMapping(value = "getGamePuzzleConfig", method = RequestMethod.GET)
	Result<GamePuzzleConfigDTO> getGamePuzzleConfig();

}
