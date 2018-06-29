package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.GameInformStatusEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月13日
 */
public class DealInformParam {
	
	private Long id;
	
	private GameInformStatusEnum statusEnum;
	
	private Integer auditorId;
	
	private String auditorName;
	
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GameInformStatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(GameInformStatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Integer getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Integer auditorId) {
		this.auditorId = auditorId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
