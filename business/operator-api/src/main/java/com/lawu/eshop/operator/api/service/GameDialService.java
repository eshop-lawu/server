package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.dto.GameDialDTO;
import com.lawu.eshop.game.param.GameDialParam;
import com.lawu.eshop.game.query.GameDialQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "game-srv", path = "gameDial/")
public interface GameDialService {
	
	@RequestMapping(value = "getGameDialById/{id}", method = RequestMethod.GET)
    Result<GameDialDTO> getGameDialById(@PathVariable("id") Long id);
	
	@RequestMapping(value = "updateGameDial/{id}", method = RequestMethod.POST)
	Result updateGameDial(@PathVariable("id") Long id,@RequestBody GameDialParam param);
	
	@RequestMapping(value = "saveGameDial", method = RequestMethod.POST)
    Result saveGameDial(@RequestBody GameDialParam param);
	
	
	@RequestMapping(value = "page", method = RequestMethod.POST)
    Result<Page<GameDialDTO>> page(@RequestBody GameDialQuery query);
	
	@RequestMapping(value = "setGameDial/{id}", method = RequestMethod.PUT)
	Result setGameDial(@PathVariable("id") Long id, @RequestParam("statusEnum") GameDialStatusEnum statusEnum);

}
