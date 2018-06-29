package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordEnum;

/**
 * 参与列表
 * 
 * @Description
 * @author zhangrc
 * @date 2018年2月1日
 */
public class PointLotteryActivityAttendRecordBO {
	
	/**
	 * 抽奖活动ID
	 */
	private Long pointLotteryActivityId;
	
	/**
	 * 奖品图片路径
	 */
	private String prizeImagePath;
	
	/**
	 * 奖品名称
	 */
	private String prizeName;
	
	/**
	 * 开奖时间
	 */
	private Date lotteryTime;
	
	/**
	 * 参与次数
	 */
	private Integer attendCount;

	/**
	 * 状态
	 */
	private PointLotteryActivityRecordEnum statusEnum;
	
	

	public Long getPointLotteryActivityId() {
		return pointLotteryActivityId;
	}

	public void setPointLotteryActivityId(Long pointLotteryActivityId) {
		this.pointLotteryActivityId = pointLotteryActivityId;
	}

	public String getPrizeImagePath() {
		return prizeImagePath;
	}

	public void setPrizeImagePath(String prizeImagePath) {
		this.prizeImagePath = prizeImagePath;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public Date getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public Integer getAttendCount() {
		return attendCount;
	}

	public void setAttendCount(Integer attendCount) {
		this.attendCount = attendCount;
	}

	public PointLotteryActivityRecordEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(PointLotteryActivityRecordEnum statusEnum) {
		this.statusEnum = statusEnum;
	}
	
	
}
