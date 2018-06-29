package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.game.dto.GameInformDTO;
import com.lawu.eshop.game.param.DealInformParam;
import com.lawu.eshop.game.query.GameInformQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "game-srv", path = "gameInform/")
public interface GameInformService {
	
	@RequestMapping(value = "page", method = RequestMethod.POST)
	Result<Page<GameInformDTO>> page(@RequestBody GameInformQuery query);
	
	
	@RequestMapping(value = "dealInform", method = RequestMethod.POST)
	Result dealInform(@RequestBody DealInformParam param);

}
