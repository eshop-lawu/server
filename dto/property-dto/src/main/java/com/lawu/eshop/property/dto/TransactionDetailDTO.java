package com.lawu.eshop.property.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.framework.web.json.KeepDecimalJsonSerializer;

import io.swagger.annotations.ApiModelProperty;

/**
 * 交易明细DTO
 * 
 * @author Sunny
 * @date 2017/3/29
 */
public class TransactionDetailDTO {
	
    /**
     * 交易标题
     */
	@ApiModelProperty(value = "交易标题", required = true)
    private String title;

    /**
     * 金额
     */
	@JsonSerialize(using = KeepDecimalJsonSerializer.class)
	@ApiModelProperty(value = "金额", required = true)
    private BigDecimal amount;

    /**
     * 交易时间
     */
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "交易时间", required = true)
    private Date transactionDate;
	
	/**
	 * 资金流向
	 */
	@ApiModelProperty(value = "资金流向(EXPENDITURE-支出|INCOME-收入)", required = true)
	private ConsumptionTypeEnum direction;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = true)
	private String remark;
	
    /**
     * 业务类型操作对应的业务表ID
     */
	@ApiModelProperty(value = "业务ID", required = false)
    private String bizId;
	
    /**
     * API层加入
     * 提现状态
     */
	@ApiModelProperty(value = "提现状态(APPLY-申请中|ACCEPT-受理中|SUCCESS-成功|FAILURE-失败)", required = false)
    private CashStatusEnum status;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
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

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public CashStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CashStatusEnum status) {
		this.status = status;
	}
	
}