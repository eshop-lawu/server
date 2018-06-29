package com.lawu.eshop.game.param;

import io.swagger.annotations.ApiParam;

/**
 * @author lihj <br>
 * @date 2018/3/13
 */
public class GamePuzzleValidSuccParam {
	
	@ApiParam(name = "picNum", required = true, value = "拼图正确序列")
	private String picNum;
	@ApiParam(name = "gameId", required = true, value = "游戏id")
	private String gameId;
	@ApiParam(name = "attendNum", required = true, value = "参与编号")
	private String attendNum;
	public String getPicNum() {
		return picNum;
	}
	public void setPicNum(String picNum) {
		this.picNum = picNum;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getAttendNum() {
		return attendNum;
	}
	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}
	
	
}
