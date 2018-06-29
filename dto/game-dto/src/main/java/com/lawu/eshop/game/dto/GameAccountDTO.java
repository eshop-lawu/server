package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModelProperty;

public class GameAccountDTO {
	
	@ApiModelProperty(value = "参与积分")
	private int attendPoint;
	
	@ApiModelProperty(value = "星星数量")
	private int starCount;
	
	@ApiModelProperty(value = "免费次数")
	private int freeCount;

	@ApiModelProperty(value = "参与星星数量")
	private int attendStarCount;

	public int getAttendPoint() {
		return attendPoint;
	}

	public void setAttendPoint(int attendPoint) {
		this.attendPoint = attendPoint;
	}

	public int getStarCount() {
		return starCount;
	}

	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}

	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}

	public int getAttendStarCount() {
		return attendStarCount;
	}

	public void setAttendStarCount(int attendStarCount) {
		this.attendStarCount = attendStarCount;
	}
}
