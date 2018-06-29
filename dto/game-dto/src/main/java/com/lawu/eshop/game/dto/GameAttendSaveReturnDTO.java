package com.lawu.eshop.game.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class GameAttendSaveReturnDTO {

	@ApiModelProperty(value = "参与编号")
	private String attendNum;
	@ApiModelProperty(value = "是否匹配成功")
	private boolean flag;
	@ApiModelProperty(value = "是否是房主")
	private Boolean roomMaster;
	/**
	 * 对战用户信息
	 */
	@ApiModelProperty(value = "对战用户信息", required = true)
	private List<GameMindBattleUserUserInfoDTO> battleUsers;

	public String getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Boolean getRoomMaster() {
		return roomMaster;
	}

	public void setRoomMaster(Boolean roomMaster) {
		this.roomMaster = roomMaster;
	}

	public List<GameMindBattleUserUserInfoDTO> getBattleUsers() {
		return battleUsers;
	}

	public void setBattleUsers(List<GameMindBattleUserUserInfoDTO> battleUsers) {
		this.battleUsers = battleUsers;
	}

	
}
