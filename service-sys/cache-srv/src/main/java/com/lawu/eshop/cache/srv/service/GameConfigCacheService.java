package com.lawu.eshop.cache.srv.service;

import java.util.List;

import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.framework.web.Result;

/**
 * 游戏配置缓存
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public interface GameConfigCacheService {
	
	/**
	 * 保存头脑PK设置
	 * 
	 * @param param
	 */
	void setGameMindConfig(GameMindConfigParam param);
	
	
	/**
	 * 保存拼图设置
	 * 
	 * @param param
	 */
	void setGamePuzzleConfig(GamePuzzleConfigParam param);
	
	
	/**
	 * 获取头脑PK设置
	 * @return
	 */
	GameMindConfigDTO getGameMindConfig();
	
	
	/**
	 * 获取拼图设置
	 * @return
	 */
	GamePuzzleConfigCacheDTO getGamePuzzleConfig();

	/**
	 * 设置用户参与拼图获取到的信息
	 * @param key
	 * @param value
	 * @return
	 */
	Boolean setGamePuzzleUserKeyValue(String key,String value);

	/**
	 * 获取设置用户参与拼图获取到的信息
	 * @param key
	 * @return
	 */
	String getGamePuzzleUserKey(String key);

	/**
	 * 解除锁用户参与拼图获取到的信息
	 * @param key
	 * @return
	 */
	Long delGamePuzzleUserKeyKey(String key);

	/**
	 * 设置用户拼图时间每次请求都记录
	 * @param userNum
	 * @param time 时间戳
	 */
	void setGamePuzzleStartTimeValue(String userNum,String time);

	/**
	 * 获取用户拼图开始时间
	 */
	String getGamePuzzleStartTimeValue(String userNum);

	void setRedisKeyGamePuzzleStartType(String key, String date);

	String getRedisKeyGamePuzzleStartType(String key);

    List<String> getLikeRedisKeyGamePuzzleStartType(String key);

	void delRedisKeyGamePuzzleStartType(String key);

    Result removeStartCacheData(String attendNum, String userNum);

    Result removeMyPuzzleCacheData(String userNum);
}
