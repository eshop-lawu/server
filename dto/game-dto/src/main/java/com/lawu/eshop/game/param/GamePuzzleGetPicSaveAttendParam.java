package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;

/**
 * 活动参与信息
 *
 * @author lihj <br>
 * @date 2018/3/12
 */
public class GamePuzzleGetPicSaveAttendParam {
    /**
     * 游戏难度扩展表
     */
    //private long difficultyId;

    private GameHardLevelEnum level;
    private AttendTypeEnum attendType;
    /**
     * 拼图数量
     */
    private int picCount;
    /**
     * 房间号
     */
    private String roomNum;
    
   /* public long getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(long difficultyId) {
        this.difficultyId = difficultyId;
    }*/

    public GameHardLevelEnum getLevel() {
        return level;
    }

    public void setLevel(GameHardLevelEnum level) {
        this.level = level;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

	public AttendTypeEnum getAttendType() {
		return attendType;
	}

	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}

	public int getPicCount() {
		return picCount;
	}

	public void setPicCount(int picCount) {
		this.picCount = picCount;
	}

}
