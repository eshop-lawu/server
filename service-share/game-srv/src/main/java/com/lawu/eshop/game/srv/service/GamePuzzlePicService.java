package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.OperatorGamePuzzlePicQuery;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/10.
 */
public interface GamePuzzlePicService {

    /**
     * 保存游戏拼图
     *
     * @param param
     * @author meishuquan
     */
    void saveGamePuzzlePic(GamePuzzleParam param);

    /**
     * 根据id查询游戏拼图
     *
     * @param id
     * @return
     * @author meishuquan
     */
    GamePuzzlePicBO getGamePuzzlePic(Long id);

    /**
     * 更新游戏拼图状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateGamePuzzlePicStatus(Long id, GamePuzzlePicStatusEnum statusEnum);

    /**
     * 游戏拼图运营平台列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<GamePuzzlePicBO> listOperatorGamePuzzlePic(OperatorGamePuzzlePicQuery query);

    /**
     * 根据困难程度随机获取图片
     * @param level
     * @return
     */
    List<GamePuzzlePicBO> getRandomGamePuzzlePic(GameHardLevelEnum level,int picCount);

	GamePuzzlePicBO getRandomGamePuzzlePicSimg(GameHardLevelEnum level, List lt);
}
