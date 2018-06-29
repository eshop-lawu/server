package com.lawu.eshop.property.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

public class PointDetailDTO {
	
    /**
     * 积分标题
     */
	@ApiModelProperty(value = "积分标题", required = true)
    private String title;

    /**
     * 积分
     */
	@JsonSerialize(using = KeepDecimalJsonSerializer.class)
	@ApiModelProperty(value = "积分", required = true)
    private BigDecimal point;
	
    /**
     * 资金流向
     */
	@ApiModelProperty(value = "资金流向(1-支出|2-收入)", required = true)
    private ConsumptionTypeEnum direction;
	
    /**
     * 备注
     */
	@ApiModelProperty(value = "备注", required = false)
    private String remark;
    
	/**
     * 积分时间
     */
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "积分时间", required = true)
    private Date integralDate;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public ConsumptionTypeEnum getDirection() {
		return direction;
	}

	public void setDirection(ConsumptionTypeEnum direction) {
		this.direction = direction;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Date getIntegralDate() {
		return integralDate;
	}

	public void setIntegralDate(Date integralDate) {
		this.integralDate = integralDate;
	}
	
}