package com.lawu.eshop.member.ws.service;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.dto.MindPkReadyDTO;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.MindPkReadyParam;
import com.lawu.framework.web.Result;

/**
 * 游戏房间扩展接口
 * 
 * @author jiangxinjun
 * @createDate 2018年3月16日
 * @updateDate 2018年3月17日
 */
public interface GameRoomExtendService {
    
    /**
     * 房间内用户上线
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月16日
     * @updateDate 2018年3月17日
     */
    Result<MindPkOnlineDTO> online(JoinGameRoomParam param);
    
    /**
     * 房间内用户准备
     * 
     * @param userNum
     * @param param
     * @author jiangxinjun
     * @createDate 2018年3月17日
     * @updateDate 2018年3月17日
     */
    Result<MindPkReadyDTO> ready(String userNum, MindPkReadyParam param, GameTypeEnum gameType);
    
    /**
     * 房间内用户退出
     * 
     * @param currentUserNum
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月17日
     * @updateDate 2018年3月17日
     */
    Result<GameRoomDetailsDTO> exitGameRoom(String currentUserNum, String userNum, GameTypeEnum gmetype);
}
