package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.game.srv.bo.GameMindConfigBO;

/**
 *
 *头脑PK游戏配置
 *
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public interface GameMindConfigService {
	
	/**
	 * 保存头脑PK游戏设置
	 * 
	 * @param param
	 */
	void saveGameMindConfig(GameMindConfigParam param);
	
	/**
	 * 游戏禁用启用
	 * @param statusEnum
	 */
	void setEnable(GameConfigStatusEnum statusEnum);
	
	
	/**
	 * 修改头脑PK游戏设置
	 * @param id
	 * @param param
	 */
	void updateGameMindConfig(Long id,GameMindConfigParam param);

	
	/**
	 * 查询头脑PK游戏设置配置
	 * @return
	 */
	GameMindConfigBO getGameMindConfigById();
	
	/**
	 * 从缓存获取游戏配置
	 * @return
	 * @author jiangxinjun
	 * @createDate 2018年3月13日
	 * @updateDate 2018年3月13日
	 */
	GameMindConfigDTO findGameMindConfigFormCache();
	
	/**
	 * 删除匹配
	 * @param id
	 */
	void delAllot(Long id);
}
