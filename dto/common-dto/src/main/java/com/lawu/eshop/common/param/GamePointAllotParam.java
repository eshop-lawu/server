package com.lawu.eshop.common.param;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiParam;

/**
 * 胜利者积分分配
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月10日
 */
public class GamePointAllotParam implements Serializable{

	private static final long serialVersionUID = 2397586427887016186L;
	@ApiParam (name="id", value = "id")
	private Long id;
	
	@ApiParam (name="attendCount",required = true, value = "参入人数")
	private Integer attendCount;
	
	@ApiParam (name="winNum",required = true, value = "胜利人数")
	private Integer winNum;
	
	@ApiParam (name="rankPoint",required = true, value = "各名称对应积分的比例")
	private List<String> rankPoint;
	
	@ApiParam (name="rankStar",required = true, value = "各名称对应星星的比例")
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
