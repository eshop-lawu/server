package com.lawu.eshop.game.srv.bo;

public class PuzzleCheckIsOverBO {
	private boolean isOver;
	/**
	 * 多个人未拼完
	 */
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
