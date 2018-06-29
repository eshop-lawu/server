package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.GamePuzzleUserPicRealQuery;
import com.lawu.eshop.game.query.OperatorGamePuzzleUserPicQuery;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicUploadNumberBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public interface GamePuzzleUserPicService {

    /**
     * 保存用户提交的拼图
     *
     * @param param
     * @author meishuquan
     */
    void saveGamePuzzleUserPic(GamePuzzleParam param);

    /**
     * 查询用户提交的拼图数量
     *
     * @param userNum
     * @return
     * @author meishuquan
     */
    GamePuzzleUserPicUploadNumberBO getGamePuzzleUserPicUploadNumber(String userNum);

    /**
     * 查询用户提交的拼图记录
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<GamePuzzleUserPicBO> listGamePuzzleUserPic(GamePuzzleUserPicRealQuery query);

    /**
     * 运营平台审核用户供图列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<GamePuzzleUserPicBO> listOperatorGamePuzzleUserPic(OperatorGamePuzzleUserPicQuery query);

    /**
     * 更新用户供图记录状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateGamePuzzleUserPicStatus(Long id, GamePuzzleUserPicStatusEnum statusEnum);

}
