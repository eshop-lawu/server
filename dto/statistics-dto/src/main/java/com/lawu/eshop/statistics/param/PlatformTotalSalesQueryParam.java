package com.lawu.eshop.statistics.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.statistics.constants.ReportTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 平台总销量查询参数
 * 
 * @author Sunny
 * @date 2017年6月30日
 */
@ApiModel
public class PlatformTotalSalesQueryParam {
	
	/**
	 * 报表类型
	 */
	@ApiModelProperty(value = "报表类型", required = true)
	private ReportTypeEnum type;
	
	/**
	 * 查询日期-开始(yyyy-MM-dd)
	 */
	@ApiModelProperty(value = "查询日期", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start;
	
	/**
	 * 查询日期-结束(yyyy-MM-dd)
	 */
	@ApiModelProperty(value = "查询日期", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;
	
	public ReportTypeEnum getType() {
		return type;
	}

	public void setType(ReportTypeEnum type) {
		this.type = type;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}
