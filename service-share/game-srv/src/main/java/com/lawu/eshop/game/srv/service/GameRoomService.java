package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.GameRoomParam;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.UpdateGameRoomPlayerReadyStatusParam;
import com.lawu.eshop.game.query.GameRoomQuery;
import com.lawu.eshop.game.srv.bo.GameRoomBO;
import com.lawu.eshop.game.srv.bo.GameRoomCreateBO;
import com.lawu.eshop.game.srv.bo.StartTheGameBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
public interface GameRoomService {

    /**
     * 创建房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomCreateBO saveGameRoom(GameRoomParam param);

    /**
     * 房间列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<GameRoomBO> listGameRoom(GameRoomQuery query);

    /**
     * 根据id查询房间
     *
     * @param id
     * @return
     * @author meishuquan
     */
    GameRoomBO getGameRoom(Long id);

    /**
     * 密码校验
     *
     * @param id
     * @param pwd
     * @return
     * @author meishuquan
     */
    Boolean verifyGameRoomPwd(Long id, String pwd);

    /**
     * 加入房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomDetailsDTO joinGameRoom(JoinGameRoomParam param);

    /**
     * 退出房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomDetailsDTO exitGameRoom(ExitGameRoomParam param);

    /**
     * 更新房间用户准备状态
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomDetailsDTO updateGameRoomPlayerReadyStatus(UpdateGameRoomPlayerReadyStatusParam param);
    
    /**
     * 开始游戏
     * 
     * @param roomNum
     * @param gameType
     * @author jiangxinjun
     * @createDate 2018年3月23日
     * @updateDate 2018年3月23日
     */
    StartTheGameBO startTheGame(String roomNum, GameTypeEnum gameType);

    /**
     * 根据房间号和房间类型查询房间
     *
     * @param roomNum
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    GameRoomBO getGameRoomByRoomNum(String roomNum, GameTypeEnum typeEnum);

    /**
     * 根据用户编号和房间类型查询房间
     *
     * @param userNum
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    GameRoomBO getGameRoomByUserNum(String userNum, GameTypeEnum typeEnum);

    /**
     * 定时关闭无效的房间
     *
     * @author meishuquan
     */
    void closeInvalidGameRoom();

}
