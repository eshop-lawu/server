package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

/**
 * 参与列表
 * 
 * @Description
 * @author zhangrc
 * @date 2018年2月1日
 */
public class PointLotteryActivityLotteryInfoBO {
	
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
	private Date drawTime;
	
	/**
	 * 参与次数
	 */
	private Integer attendCount;

	private String mobile;
	

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

	public Date getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}

	public Integer getAttendCount() {
		return attendCount;
	}

	public void setAttendCount(Integer attendCount) {
		this.attendCount = attendCount;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
