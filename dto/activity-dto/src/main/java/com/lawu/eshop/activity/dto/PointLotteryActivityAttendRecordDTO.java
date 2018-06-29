package com.lawu.eshop.activity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.PointLotteryActivityRecordEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 参与列表
 * 
 * @Description
 * @author zhangrc
 * @date 2018年2月1日
 */
public class PointLotteryActivityAttendRecordDTO {
	
	/**
	 * 抽奖活动ID
	 */
	@ApiModelProperty(value = "抽奖活动ID")
	private Long pointLotteryActivityId;
	
	/**
	 * 奖品图片路径
	 */
	@ApiModelProperty(value = "奖品图片")
	private String prizeImagePath;
	
	/**
	 * 奖品名称
	 */
	@ApiModelProperty(value = "奖品名称")
	private String prizeName;
	
	/**
	 * 开奖时间
	 */
	@ApiModelProperty(value = "开奖时间")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date lotteryTime;
	
	/**
	 * 参与次数
	 */
	@ApiModelProperty(value = "参与次数")
	private Integer attendCount;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "NO_OPEN_LOTTERY 未开奖  WIN_LOTTERY 已中奖 NO_WIN_LOTTERY 未中奖")
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
