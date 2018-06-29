package com.lawu.eshop.game.robot.dto;

import java.io.Serializable;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public class SecScoreParam implements Serializable{

    private static final long serialVersionUID = -3547936579845739839L;
    
	/**
	 * 秒数
	 */
	private int second;
	
	/**
	 * 积分
	 */
	private int point;
	
	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	
	

}
