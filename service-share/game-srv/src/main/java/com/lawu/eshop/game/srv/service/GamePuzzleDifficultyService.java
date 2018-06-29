package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.param.DifficultyInfoParam;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleMaxPointStartByDiffBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePointStartByDiffBO;

/**
 * 拼图游戏困难系数设置
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public interface GamePuzzleDifficultyService {

    /**
     * 根据困难级别获取信息
     * @param level
     * @return
     */
    GamePuzzleDifficultyBO getPuzzleDifficultyByHardLevel(GameHardLevelEnum level);
    
    /**
     * 获取困难级别
     * @return
     */
    List<GamePuzzleDifficultyBO> getPuzzleDifficulty();


    GamePuzzleDifficultyBO getPuzzleDifficultyById(Long id);
    
    /**
     * 获取困难级别信息
     * @param param
     * @return
     */
    GamePuzzlePointStartByDiffBO getPuzzlePointStart(DifficultyInfoParam param);
    
    
    /**
     * 获取最大得分和级别
     * @param param
     * @return
     */
    GamePuzzleMaxPointStartByDiffBO getPuzzleMaxPointLevel(GameHardLevelEnum levelEnum);
}
