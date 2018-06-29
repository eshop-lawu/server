package com.lawu.eshop.game.robot.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 胜利者积分分配
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public class GamePointAllotParam implements Serializable{

	private static final long serialVersionUID = 2397586427887016186L;
	private Long id;
	
	private Integer attendCount;
	
	private Integer winNum;
	
	private List<String> rankPoint;
	
	private List<String> rankStar;

	public Integer getAttendCount() {
		return attendCount;
	}

	public void setAttendCount(Integer attendCount) {
		this.attendCount = attendCount;
	}

	public Integer getWinNum() {
		return winNum;
	}

	public void setWinNum(Integer winNum) {
		this.winNum = winNum;
	}

	public List<String> getRankPoint() {
		return rankPoint;
	}

	public void setRankPoint(List<String> rankPoint) {
		this.rankPoint = rankPoint;
	}

	public List<String> getRankStar() {
		return rankStar;
	}

	public void setRankStar(List<String> rankStar) {
		this.rankStar = rankStar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
