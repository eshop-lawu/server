package com.lawu.eshop.mq.dto.game;

import com.lawu.compensating.transaction.Notification;

/**
 * @author lihj <br>
 * @date 2018/3/13
 */
public class PuzzleGameDeductionNotification extends Notification {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6557323177635567471L;

	private Long id;
	private String userNum;
	private String point;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

}
