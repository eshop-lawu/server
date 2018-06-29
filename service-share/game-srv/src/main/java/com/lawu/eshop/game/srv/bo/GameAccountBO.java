package com.lawu.eshop.game.srv.bo;

public class GameAccountBO {
	
	private int attendPoint;
	
	private int starCount;
	
	private int freeCount;

	private Integer attendStarCount;

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

	public Integer getAttendStarCount() {
		return attendStarCount;
	}

	public void setAttendStarCount(Integer attendStarCount) {
		this.attendStarCount = attendStarCount;
	}
}
