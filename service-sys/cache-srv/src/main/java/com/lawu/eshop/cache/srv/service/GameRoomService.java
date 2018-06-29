package com.lawu.eshop.cache.srv.service;

import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.param.GameRoomOperationParam;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
public interface GameRoomService {

    /**
     * 获取房间号
     *
     * @param roomType
     * @param roomTotalNum
     * @return
     * @author meishuquan
     */
    String getGameRoomNum(String roomType, Integer roomTotalNum);

    /**
     * 回收房间号
     *
     * @param roomType
     * @param roomNum
     * @author meishuquan
     */
    void recycleGameRoomNum(String roomType, String roomNum);

    /**
     * 房主创建房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomDetailsDTO createGameRoom(GameRoomOperationParam param);

    /**
     * 加入房间
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomDetailsDTO joinGameRoom(GameRoomOperationParam param);

    /**
     * 退出房间
     * 1.如果是普通成员, 只清除对应的缓存信息
     * 2.如果是房主, 清除房间内的所有缓存信息, 回收房间号
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomDetailsDTO exitGameRoom(GameRoomOperationParam param);

    /**
     * 更新房间用户准备状态
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomDetailsDTO updateGameRoomPlayerReadyStatus(GameRoomOperationParam param);

    /**
     * 获取房间所有玩家
     *
     * @param param
     * @return
     * @author meishuquan
     */
    GameRoomDetailsDTO getGameRoomAllPlayer(GameRoomOperationParam param);

}
