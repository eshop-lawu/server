package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

public class PointLotteryActivityAttendDetailBO {
	
	/**
	 * 奖品名称
	 */
	private String prizeName;
	
	/**
	 * 开奖时间
	 */
	private Date lotteryTime;
	
	/**
	 * 奖品图片路径
	 */
	private String prizeImagePath;
	
	/**
	 * 价格
	 */
	private BigDecimal prizePrice;
	
	/**
	 * 中奖号码
	 */
	private String lotteryResultNums;
	
	
	/**
	 * 参与次数
	 */
	private Integer attendCount;
	
	/**
	 * 抽奖积分
	 */
	private int lotteryPoint;
	
	/**
	 * 参与号码
	 */
	private String attendNums;
	
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "NO_OPEN_LOTTERY 未开奖  WIN_LOTTERY 已中奖 NO_WIN_LOTTERY 未中奖")
	private PointLotteryActivityRecordEnum statusEnum;

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

	public String getPrizeImagePath() {
		return prizeImagePath;
	}

	public void setPrizeImagePath(String prizeImagePath) {
		this.prizeImagePath = prizeImagePath;
	}

	public BigDecimal getPrizePrice() {
		return prizePrice;
	}

	public void setPrizePrice(BigDecimal prizePrice) {
		this.prizePrice = prizePrice;
	}

	public String getLotteryResultNums() {
		return lotteryResultNums;
	}

	public void setLotteryResultNums(String lotteryResultNums) {
		this.lotteryResultNums = lotteryResultNums;
	}

	public Integer getAttendCount() {
		return attendCount;
	}

	public void setAttendCount(Integer attendCount) {
		this.attendCount = attendCount;
	}

	public int getLotteryPoint() {
		return lotteryPoint;
	}

	public void setLotteryPoint(int lotteryPoint) {
		this.lotteryPoint = lotteryPoint;
	}

	public String getAttendNums() {
		return attendNums;
	}

	public void setAttendNums(String attendNums) {
		this.attendNums = attendNums;
	}

	public PointLotteryActivityRecordEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(PointLotteryActivityRecordEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	
	
	
	

}
