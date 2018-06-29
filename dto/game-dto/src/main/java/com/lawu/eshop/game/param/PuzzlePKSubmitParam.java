package com.lawu.eshop.game.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("拼图PK提交答案参数")
public class PuzzlePKSubmitParam {

	@ApiModelProperty(value = "游戏id", required = true)
	private String gameId;
	@ApiModelProperty(value = "拼图序列", required = true)
	private String picNum;
	@ApiModelProperty(value = "参与编号", required = true)
	private String attendNum;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPicNum() {
		return picNum;
	}

	public void setPicNum(String picNum) {
		this.picNum = picNum;
	}

	public String getAttendNum() {
		return attendNum;
	}

	public void setAttendNum(String attendNum) {
		this.attendNum = attendNum;
	}

}
