package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.param.GameInformParam;
import com.lawu.framework.web.Result;

/**
 * 游戏举报
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
@FeignClient(value = "game-srv", path = "gameInform/")
public interface GameInformService {
	
	/**
	 * 游戏举报
	 * 
	 * @param userNum
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "saveGameInform", method = RequestMethod.POST)
	Result saveGameInform(@RequestParam("userNum") String userNum, @RequestBody GameInformParam param);

}
