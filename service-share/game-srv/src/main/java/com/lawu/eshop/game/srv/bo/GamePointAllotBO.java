package com.lawu.eshop.game.srv.bo;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月13日
 */
public class GamePointAllotBO {
	
	private Long id;
	
	private Integer attendCount;
	
	private Integer winNum;
	
	private String rankPoint;
	
	private String rankStar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getRankPoint() {
		return rankPoint;
	}

	public void setRankPoint(String rankPoint) {
		this.rankPoint = rankPoint;
	}

	public String getRankStar() {
		return rankStar;
	}

	public void setRankStar(String rankStar) {
		this.rankStar = rankStar;
	}
	
	

}
