package com.lawu.eshop.game.param;

import java.util.List;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.dto.GameCommonNumDTO;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lihj <br>
 * @date 2018/3/15
 */
public class GamePuzzleFriendPlayInitParam {

	@ApiModelProperty(value = "参与人用户编号")
	private List<GameCommonNumDTO> userNums;
	@ApiModelProperty(value = "房间号")
	private String roomNum;
	@ApiModelProperty(value = "游戏难度SIMPLE简单，COMMONLY一般，DIFFICULTY困难，RANDOM随机")
	private GameHardLevelEnum gameLevel;
	@ApiModelProperty(value = "消耗积分")
	private int costPoint;
	public List<GameCommonNumDTO> getUserNums() {
		return userNums;
	}
	public void setUserNums(List<GameCommonNumDTO> userNums) {
		this.userNums = userNums;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public GameHardLevelEnum getGameLevel() {
		return gameLevel;
	}
	public void setGameLevel(GameHardLevelEnum gameLevel) {
		this.gameLevel = gameLevel;
	}
	public int getCostPoint() {
		return costPoint;
	}
	public void setCostPoint(int costPoint) {
		this.costPoint = costPoint;
	}

   
}
