package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.framework.web.Result;

/**
 * 游戏配置存入缓存
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@FeignClient(value = "cache-srv", path = "gameConfigCache/")
public interface GameConfigCacheService {
	
	/**
	 * 头脑配置
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "setGameMindConfig", method = RequestMethod.POST)
     Result setGameMindConfig(@ModelAttribute GameMindConfigParam param);
	
	/**
	 * 拼图配置
	 * @return
	 */
	@RequestMapping(value = "setGamePuzzleConfig", method = RequestMethod.POST)
     Result setGamePuzzleConfig(@ModelAttribute GamePuzzleConfigParam param);
	
	
	 @RequestMapping(value = "getGameMindConfig", method = RequestMethod.GET)
	 Result<GameMindConfigDTO> getGameMindConfig();

}
