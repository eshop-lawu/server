package com.lawu.eshop.cache.srv.service;

import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GamePuzzleCacheDetail;
import com.lawu.eshop.cache.dto.GamePuzzleCallBackCacheDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.ExitMatchQueueParam;
import com.lawu.eshop.cache.param.JoinGameCacheParam;
import com.lawu.eshop.cache.param.MatchingRobotParam;

/**
 * 游戏公用缓存
 * 
 * @author lihj
 * @Date 2018年3月14日
 */
public interface GameCommonCacheService {

	/**
	 * 加入缓存
	 * @param param
	 */
	int joinCache(JoinGameCacheParam param);

	/**
	 * 检查是否有匹配成功
	 * @param param
	 * @return
	 */
	GameMatchResultDTO checkCacheMatchEatchother(CheckCacheMatchParam param);

	/**
	 * 定时任务更新
	 * @param key 
	 * @param newKey
	 */
	void batchUpdateMatchEatchother(String key,String newKey);

	/**
	 * 退出匹配队列
	 * @param param
	 */
	void exitMatchQueue(ExitMatchQueueParam param);

	GamePuzzleCallBackCacheDTO setCallBackCache(GamePuzzleCacheDetail param);
	
	/**
	 * 匹配机器人
	 * 把用户个人的信息和机器人信息放入缓存中
	 * 
	 * @author jiangxinjun
	 * @createDate 2018年5月11日
	 * @updateDate 2018年5月11日
	 */
	void matchingRobot(MatchingRobotParam param);
}
