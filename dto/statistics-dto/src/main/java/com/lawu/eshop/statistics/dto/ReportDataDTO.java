package com.lawu.eshop.statistics.dto;

import java.util.List;

/**
 * 报表数据DTO
 * 
 * @author Sunny
 * @date 2017年7月3日
 */
public class ReportDataDTO {
	
	/**
	 * x轴y轴数据
	 */
	private List<ReportGroupDTO> data;

	public List<ReportGroupDTO> getData() {
		return data;
	}

	public void setData(List<ReportGroupDTO> data) {
		this.data = data;
	}
	
}