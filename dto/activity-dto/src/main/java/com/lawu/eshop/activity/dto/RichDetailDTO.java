package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 瑞奇岛详细信息
 * 
 * @author lihj
 * @date 2018年5月3日
 */
public class RichDetailDTO {

	@ApiModelProperty(value = "瑞奇岛总人数")
	private int totalRichPeople;

	@ApiModelProperty(value = "创世居民人数")
	private int creatorsPeople;

	@ApiModelProperty(value = "累计产出E钻总数")
	private BigDecimal totalRichDiamond;

	@ApiModelProperty(value = "日产量")
	private BigDecimal dayRichDiamond;

	@ApiModelProperty(value = "公告")
	private String notice;

	@ApiModelProperty(value = "动力排行榜TOP10")
	private List<RichPowerDetailDTO> powerList;

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

	public List<RichPowerDetailDTO> getPowerList() {
		return powerList;
	}

	public void setPowerList(List<RichPowerDetailDTO> powerList) {
		this.powerList = powerList;
	}

}
