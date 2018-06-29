package com.lawu.eshop.cache.dto;

import java.util.List;

/**
 * 游戏房间详情DTO
 * @author jiangxinjun
 * @createDate 2018年3月16日
 * @updateDate 2018年3月16日
 */
public class GameRoomDetailsDTO {
    
    /**
     * 房间是否解散<p>
     * 1.好友对战, 并且还未开始游戏
     * 2.是房主退出
     */
    private Boolean isDissolution;
    
    
    /**
     * 房间内所有用户信息
     */
    List<GameRoomPlayerInfoDTO> playerInfos;
    
    public Boolean getIsDissolution() {
        return isDissolution;
    }

    public void setIsDissolution(Boolean isDissolution) {
        this.isDissolution = isDissolution;
    }

    public List<GameRoomPlayerInfoDTO> getPlayerInfos() {
        return playerInfos;
    }

    public void setPlayerInfos(List<GameRoomPlayerInfoDTO> playerInfos) {
        this.playerInfos = playerInfos;
    }

}
