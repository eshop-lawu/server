package com.lawu.eshop.property.param;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;

/**
 * 
 * <p>
 * Description: 用户红包退款
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月14日 下午1:02:20
 *
 */
public class MemberRedPacketRefundDataParam {

	// 商家用户编号
	@NotBlank(message = "userNum不能为空")
	private String userNum;

	// 红包ID
	@NotBlank(message = "redPacketId不能为空")
	private String redPacketId;

	@NotBlank(message = "refundMoney不能为空")
	@Pattern(regexp = "^\\d{1,9}(\\.\\d{1,2})?$", message = "金额错误(要求最大8位整数且保留2位小数)")
	private String refundMoney;

	// 支付方式
	@NotNull(message = "transactionPayTypeEnum不能为空")
	private TransactionPayTypeEnum transactionPayTypeEnum;

	// 第三方平台订单号
	@NotBlank(message = "tradeNo不能为空")
	private String tradeNo;

	//主事务执行时间
	private Date gmtExecute;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getRedPacketId() {
		return redPacketId;
	}

	public void setRedPacketId(String redPacketId) {
		this.redPacketId = redPacketId;
	}

	public String getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(String refundMoney) {
		this.refundMoney = refundMoney;
	}

	public TransactionPayTypeEnum getTransactionPayTypeEnum() {
		return transactionPayTypeEnum;
	}

	public void setTransactionPayTypeEnum(TransactionPayTypeEnum transactionPayTypeEnum) {
		this.transactionPayTypeEnum = transactionPayTypeEnum;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Date getGmtExecute() {
		return gmtExecute;
	}

	public void setGmtExecute(Date gmtExecute) {
		this.gmtExecute = gmtExecute;
	}
}
