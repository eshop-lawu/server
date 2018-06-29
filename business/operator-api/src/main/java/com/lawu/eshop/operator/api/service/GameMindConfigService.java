package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.game.dto.GameMindConfigDTO;
import com.lawu.framework.web.Result;

/**
 * 头脑PK游戏设置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@FeignClient(value = "game-srv", path = "gameMindConfig/")
public interface GameMindConfigService {
	
	@RequestMapping(value = "saveGameMindConfig", method = RequestMethod.POST)
	Result saveGameMindConfig(@RequestBody GameMindConfigParam param);
	
	@RequestMapping(value = "setEnable", method = RequestMethod.POST)
	Result setEnable(@RequestParam("statusEnum") GameConfigStatusEnum statusEnum);
	
	
	@RequestMapping(value = "updateGameMindConfig/{id}", method = RequestMethod.POST)
	Result updateGameMindConfig(@PathVariable("id") Long id,@RequestBody GameMindConfigParam param);
	
	@RequestMapping(value = "getGameMindConfig", method = RequestMethod.GET)
	Result<GameMindConfigDTO> getGameMindConfig();
	
	@RequestMapping(value = "delAllot/{id}", method = RequestMethod.PUT)
	Result delAllot(@PathVariable("id") Long id);

}
