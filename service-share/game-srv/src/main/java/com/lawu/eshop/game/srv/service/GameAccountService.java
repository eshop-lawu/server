package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.srv.bo.GameAccountBO;

/**
 * 游戏账户
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public interface GameAccountService {

	/**
	 * 获取游戏账户相关信息
	 * @param param
	 * @return
	 */
	GameAccountBO getAccountInfo(GameAccountParam param);
	
	/**
	 * 分享获取免费参与次数
	 * 
	 * @param userNum
	 * @param typeEnum
	 */
	int shareAddAttendCount(String userNum,GameTypeEnum typeEnum);
	
	
	/**
	 * 用户加星星、减星星
	 * 
	 * @param param
	 */
	void dealStar(GameAccountStarParam param);


    /**
     * 每月星星清零
     * 
     */
	void accountStarReset();
	
	/**
	 * 判断是否有免费次数
	 * @param userNum
	 * @return
	 */
	Boolean getShareCount(String userNum,GameTypeEnum typeEnum);
	
	/**
	 * 减免费次数
	 * @param userNum
	 * @param typeEnum
	 */
	void reduceFreeCount(String userNum,GameTypeEnum typeEnum);

	/**
	 * 每日免费次数更新
	 */
	void accountFreeCountReset();
}
