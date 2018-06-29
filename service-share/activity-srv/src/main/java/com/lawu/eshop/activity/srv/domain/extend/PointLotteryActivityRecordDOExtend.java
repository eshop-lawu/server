package com.lawu.eshop.activity.srv.domain.extend;

import java.io.Serializable;
import java.util.Date;

public class PointLotteryActivityRecordDOExtend implements Serializable{

	private static final long serialVersionUID = 3033051803155587008L;
	
	/**
	 * 抽奖活动ID
	 */
	private Long pointLotteryActivityId;
	
	/**
	 * 参与次数
	 */
	private Integer attendCount;
	
	/**
	 * 奖品名称
	 */
	private String prizeName;
	
	/**
	 * 奖品图片
	 */
	private String prizeImagePath;
	
	/**
	 * 开奖时间
	 */
	private Date lotteryTime;
	

	public Long getPointLotteryActivityId() {
		return pointLotteryActivityId;
	}

	public void setPointLotteryActivityId(Long pointLotteryActivityId) {
		this.pointLotteryActivityId = pointLotteryActivityId;
	}

	public Integer getAttendCount() {
		return attendCount;
	}

	public void setAttendCount(Integer attendCount) {
		this.attendCount = attendCount;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeImagePath() {
		return prizeImagePath;
	}

	public void setPrizeImagePath(String prizeImagePath) {
		this.prizeImagePath = prizeImagePath;
	}

	public Date getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}
	
	

}
