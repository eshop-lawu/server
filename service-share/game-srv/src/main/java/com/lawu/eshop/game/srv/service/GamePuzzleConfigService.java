package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.eshop.game.param.GameAttendSaveParam;
import com.lawu.eshop.game.param.GamePuzzleGetPicSaveAttendParam;
import com.lawu.eshop.game.param.GamePuzzleRewardPointAndStarParam;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleConfigBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleGetPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;

/**
 * 拼图游戏配置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public interface GamePuzzleConfigService {
	
	/**
	 * 设置拼图配置
	 * @param param
	 */
	void saveGamePuzzleConfig(GamePuzzleConfigParam param);

	/**
	 * 根据游戏难易程度出拼图图片
	 * @param param
	 * @return
	 */
    List<GamePuzzleGetPicBO> getPicByHardLevel(GamePuzzleGetPicSaveAttendParam param);
    
	/**
	 * 游戏禁用启用
	 * @param statusEnum
	 */
	void setEnable(GameConfigStatusEnum statusEnum);
	
	/**
	 * 修改游戏配置
	 * @param id
	 * @param param
	 */
	void updateGamePuzzleConfig(Long id,GamePuzzleConfigParam param);

	/**
	 * 拼图开始初始化记录并扣除积分以及星星
	 * @param param
	 * @return
	 */
	/*GameAttendSaveReturnBO savePuzzleGameAttendInfo(GameAttendSaveParam param);*/

	/**
	 * 挑战成功增加积分以及星星
	 * @param gameRewardParam
	 * @return
	 */
	GamePuzzleResultBO rewardPointAndStar(GamePuzzleRewardPointAndStarParam gameRewardParam);
	
	
	/**
	 * 查询游戏配置
	 * @return
	 */
	GamePuzzleConfigBO getGamePuzzleConfig();

	/**
	 * 随机
	 * @param param
	 * @return
	 */
	GameAttendSaveReturnBO saveRandomPuzzleGameAttendInfo(GameAttendSaveParam param);

}
