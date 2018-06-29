package com.lawu.eshop.statistics.param;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户收支查询参数
 * 
 * @author Sunny
 * @date 2017年6月30日
 */
public class ReportUserIncomeExpenditureQueryParam extends AbstractPageParam {
	
	/**
	 * 用户类型
	 */
	@ApiModelProperty(value = "用户类型", required = true)
	private UserTypeEnum userType;
	
	/**
	 * 用户账号
	 */
	@ApiModelProperty(value = "用户账号", required = true)
	private String account;
	
	/**
	 * 开始日期(yyyy-MM-dd)
	 */
	@ApiModelProperty(value = "开始日期(yyyy-MM-dd)", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start;
	
	/**
	 * 结束日期(yyyy-MM-dd)
	 */
	@ApiModelProperty(value = "结束日期(yyyy-MM-dd)", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end;
	
	/**
	 * 差额范围-最小
	 */
	@ApiModelProperty(value = "差额范围-最小", required = true)
	private BigDecimal min;
	
	/**
	 * 差额范围-最大
	 */
	@ApiModelProperty(value = "差额范围-最大", required = true)
	private BigDecimal max;

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}
	
}
