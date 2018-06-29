package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;

/**
 * 拼图开始游戏参数
 * 
 * @author lihj
 * @Date 2018年3月20日
 */
public class PuzzleStartParam {

	private String roomNum;
	
	private int costPoint;
	private GameHardLevelEnum gameLevel;
	
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public int getCostPoint() {
		return costPoint;
	}

	public void setCostPoint(int costPoint) {
		this.costPoint = costPoint;
	}

	public GameHardLevelEnum getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(GameHardLevelEnum gameLevel) {
		this.gameLevel = gameLevel;
	}
	

}
