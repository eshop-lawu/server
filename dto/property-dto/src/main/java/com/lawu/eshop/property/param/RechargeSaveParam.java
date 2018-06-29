package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.PayTypeEnum;

import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午8:42:22
 *
 */
public class RechargeSaveParam {

	@NotBlank(message = "rechargeMoney不能为空")
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "rechargeMoney格式错误要求数字或小数位不超过2位")
	@ApiParam (name="rechargeMoney",required = true, value = "金额或积分")
	private String rechargeMoney;

	@NotNull(message = "payTypeEnum不能为空")
	@ApiParam (name="payTypeEnum",required = true, value = "充值类型")
	private PayTypeEnum payTypeEnum;
	
	@NotNull(message = "transactionPayTypeEnum不能为空")
	@ApiParam (name="transactionPayTypeEnum",required = true, value = "充值方式")
	private TransactionPayTypeEnum transactionPayTypeEnum;

	@ApiParam (name="payPwd",value = "支付密码(余额充值积分时必填)")
	private String payPwd;

	public String getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public PayTypeEnum getPayTypeEnum() {
		return payTypeEnum;
	}

	public void setPayTypeEnum(PayTypeEnum payTypeEnum) {
		this.payTypeEnum = payTypeEnum;
	}

	public TransactionPayTypeEnum getTransactionPayTypeEnum() {
		return transactionPayTypeEnum;
	}

	public void setTransactionPayTypeEnum(TransactionPayTypeEnum transactionPayTypeEnum) {
		this.transactionPayTypeEnum = transactionPayTypeEnum;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

}