package com.lawu.eshop.game.srv.bo;

import java.math.BigDecimal;

public class GamePuzzlePointStarDetail {

	private String userNum;
	private BigDecimal point;
	private int star;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

}
