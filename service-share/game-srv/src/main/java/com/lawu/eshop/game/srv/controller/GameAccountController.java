package com.lawu.eshop.game.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.dto.GameAccountDTO;
import com.lawu.eshop.game.dto.ShareCountDTO;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.game.srv.bo.GameAccountBO;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 游戏账户
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
@RestController
@RequestMapping(value = "gameAccount/")
public class GameAccountController extends BaseController{
	
	
	@Autowired
	private GameAccountService gameAccountService;
	
	/**
	 * 获取游戏账户相关信息
	 * 
	 * @param userNum
	 * @param typeEnum
	 * @return
	 */
	@RequestMapping(value = "getAccountInfo", method = RequestMethod.POST)
	public Result<GameAccountDTO> getAccountInfo(@RequestBody GameAccountParam param){
		GameAccountBO bo = gameAccountService.getAccountInfo(param);
		
		GameAccountDTO dto = new GameAccountDTO();
		dto.setAttendPoint(bo.getAttendPoint());
		dto.setFreeCount(bo.getFreeCount());
		dto.setStarCount(bo.getStarCount());
		dto.setAttendStarCount(bo.getAttendStarCount());
		return successCreated(dto);
	}
	
	/**
	 * 分享修改参与次数
	 * @param userNum
	 * @param typeEnum
	 * @return
	 */
	@RequestMapping(value = "shareAddAttendCount", method = RequestMethod.POST)
	public Result<ShareCountDTO> shareAddAttendCount(@RequestParam String userNum,@RequestParam GameTypeEnum typeEnum){
		int count = gameAccountService.shareAddAttendCount(userNum, typeEnum);
		if(count < 1){
			 return successCreated(ResultCode.GAME_SHAREL_IS_GET);
		}
		ShareCountDTO dto = new ShareCountDTO();
		dto.setCount(count);
		return successCreated(dto);
	}

	/**
	 * 是否有免费抽奖次数
	 *
	 * @param userNum
	 * @param typeEnum
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "isFreeCount", method = RequestMethod.GET)
	public Result<Boolean> isFreeCount(@RequestParam String userNum, @RequestParam GameTypeEnum typeEnum) {
		Boolean result = gameAccountService.getShareCount(userNum, typeEnum);
		return successGet(result);
	}

}
