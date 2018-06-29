package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.AttendTypeEnum;

/**
 * 拼图开始参数
 * @author lihj
 * @Date 2018年3月16日
 */
public class GamePuzzleStartParam {

	private AttendTypeEnum attendType;
	private GamePuzzleFriendPlaySaveParam gameParam;
	public AttendTypeEnum getAttendType() {
		return attendType;
	}
	public void setAttendType(AttendTypeEnum attendType) {
		this.attendType = attendType;
	}
	public GamePuzzleFriendPlaySaveParam getGameParam() {
		return gameParam;
	}
	public void setGameParam(GamePuzzleFriendPlaySaveParam gameParam) {
		this.gameParam = gameParam;
	}
	
}
