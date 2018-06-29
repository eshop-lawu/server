package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.dto.GameAccountDTO;
import com.lawu.eshop.game.dto.ShareCountDTO;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.framework.web.Result;

@FeignClient(value = "game-srv", path = "gameAccount/")
public interface GameAccountService {

	/**
	 * 获取游戏账户信息
	 * @param userNum
	 * @param typeEnum
	 * @return
	 */
	@RequestMapping(value = "getAccountInfo", method = RequestMethod.POST)
	Result<GameAccountDTO> getAccountInfo(@RequestBody GameAccountParam param);
	
	/**
	 * 分享增加参与次数
	 * @param userNum
	 * @param typeEnum
	 * @return
	 */
	@RequestMapping(value = "shareAddAttendCount", method = RequestMethod.POST)
	Result<ShareCountDTO> shareAddAttendCount(@RequestParam("userNum") String userNum,@RequestParam("typeEnum") GameTypeEnum typeEnum);

	/**
	 * 是否有免费抽奖次数
	 *
	 * @param userNum
	 * @param typeEnum
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "isFreeCount", method = RequestMethod.GET)
	Result<Boolean> isFreeCount(@RequestParam("userNum") String userNum, @RequestParam("typeEnum") GameTypeEnum typeEnum);

}
