package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 加入房间参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月16日
 * @updateDate 2018年3月16日
 */
public class JoinGameRoomParam {
    
    /**
     * 游戏类型
     */
    private GameTypeEnum gameType;
    
    /**
     * 房间编号
     */
    private String roomNum;
    
    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 头像
     */
    private String headImg;

    /**
     * 昵称
     */
    private String nickName;

    @ApiModelProperty(value = "所属区域")
    private String regionName;
    
    public GameTypeEnum getGameType() {
        return gameType;
    }

    public void setGameType(GameTypeEnum gameType) {
        this.gameType = gameType;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
    
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
}
