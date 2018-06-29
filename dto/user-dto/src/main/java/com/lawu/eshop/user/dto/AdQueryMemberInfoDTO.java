package com.lawu.eshop.user.dto;

import java.util.List;

public class AdQueryMemberInfoDTO {

	/**
	 * 用户所属区域
	 */
    private String regionPath;

    /**
     * 用户的粉丝列表
     */
    private List<Long> fansList;

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public List<Long> getFansList() {
		return fansList;
	}

	public void setFansList(List<Long> fansList) {
		this.fansList = fansList;
	}
    
}
