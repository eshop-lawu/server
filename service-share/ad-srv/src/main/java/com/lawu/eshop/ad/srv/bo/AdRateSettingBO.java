package com.lawu.eshop.ad.srv.bo;

public class AdRateSettingBO {
	
	private Long id;

	/**
	 *
	 * 游戏时长（单位秒） ad_rate_setting.game_time
	 *
	 * @mbg.generated
	 */
	private Integer gameTime;

	/**
	 *
	 * 对应命中率0~100 ad_rate_setting.rate
	 *
	 * @mbg.generated
	 */
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
