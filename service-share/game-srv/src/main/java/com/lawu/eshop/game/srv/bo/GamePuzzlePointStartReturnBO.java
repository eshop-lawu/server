package com.lawu.eshop.game.srv.bo;

import java.math.BigDecimal;

public class GamePuzzlePointStartReturnBO {

	private BigDecimal point;
	private int star;

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

	public GamePuzzlePointStartReturnBO(BigDecimal point, int star) {
		this.point = point;
		this.star = star;
	}

}
