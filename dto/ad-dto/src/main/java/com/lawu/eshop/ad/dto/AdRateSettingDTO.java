package com.lawu.eshop.ad.dto;

import io.swagger.annotations.ApiModelProperty;

public class AdRateSettingDTO {
	
	@ApiModelProperty(value = "id")
	private Long id;
	
	@ApiModelProperty(value = "秒数")
	private Integer gameTime;

	@ApiModelProperty(value = "中奖率(0~100)")
	private Integer rate;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGameTime() {
		return gameTime;
	}

	public void setGameTime(Integer gameTime) {
		this.gameTime = gameTime;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

}
