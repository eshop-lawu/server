package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.List;

/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichDetailBO {
	/**
	 * 瑞奇岛总人数
	 */
	private int totalRichPeople;

	/**
	 * 创世居民人数
	 */
	private int creatorsPeople;

	/**
	 * 累计产出E钻总数
	 */
	private BigDecimal totalRichDiamond;

	/**
	 * 日产量
	 */
	private BigDecimal dayRichDiamond;

	/**
	 * 公告
	 */
	private String notice;

	/**
	 * 动力排行榜TOP10
	 */
	private List<RichPowerDetailBO> powerList;

	public int getTotalRichPeople() {
		return totalRichPeople;
	}

	public void setTotalRichPeople(int totalRichPeople) {
		this.totalRichPeople = totalRichPeople;
	}

	public int getCreatorsPeople() {
		return creatorsPeople;
	}

	public void setCreatorsPeople(int creatorsPeople) {
		this.creatorsPeople = creatorsPeople;
	}

	public BigDecimal getTotalRichDiamond() {
		return totalRichDiamond;
	}

	public void setTotalRichDiamond(BigDecimal totalRichDiamond) {
		this.totalRichDiamond = totalRichDiamond;
	}

	public BigDecimal getDayRichDiamond() {
		return dayRichDiamond;
	}

	public void setDayRichDiamond(BigDecimal dayRichDiamond) {
		this.dayRichDiamond = dayRichDiamond;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public List<RichPowerDetailBO> getPowerList() {
		return powerList;
	}

	public void setPowerList(List<RichPowerDetailBO> powerList) {
		this.powerList = powerList;
	}
	
}
