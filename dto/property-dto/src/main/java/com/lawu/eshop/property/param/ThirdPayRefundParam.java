package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ClientTypeEnum;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 */
public class ThirdPayRefundParam {


	@ApiParam(name = "refundMoney", required = true, value = "退款金额(单位：元)")
	@NotNull(message = "refundMoney不能为空")
	private String refundMoney;

	@ApiParam(name = "refundTotalMoney", value = "退款总金额(单位：元)，微信时必填")
	@NotNull(message = "refundTotalMoney不能为空")
	private String refundTotalMoney;

	@ApiParam(name = "tradeNo", required = true,  value = "第三方交易号")
	@NotBlank(message = "tradeNo不能为空")
	private String tradeNo;

	@ApiParam(name = "payType", required = true, value = "支付类型")
	@NotNull(message = "payType不能为空")
	private TransactionPayTypeEnum payType;

	@ApiParam(name = "clientType", required = true, value = "客户机类型")
	@NotNull(message = "clientType不能为空")
	private ClientTypeEnum clientType;

	public String getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}

	public String getRefundTotalMoney() {
		return refundTotalMoney;
	}

	public void setRefundTotalMoney(String refundTotalMoney) {
		this.refundTotalMoney = refundTotalMoney;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public TransactionPayTypeEnum getPayType() {
		return payType;
	}

	public void setPayType(TransactionPayTypeEnum payType) {
		this.payType = payType;
	}

	public ClientTypeEnum getClientType() {
		return clientType;
	}

	public void setClientType(ClientTypeEnum clientType) {
		this.clientType = clientType;
	}
}