package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModelProperty;

public class PuzzleCheckIsOverDTO {

	@ApiModelProperty(value = "是否其他人都拼完图")
	private boolean isOver;
	@ApiModelProperty(value="多少人未拼完图")
	private int count;
	public boolean getIsOver() {
		return isOver;
	}

	public void setIsOver(boolean isOver) {
		this.isOver = isOver;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
