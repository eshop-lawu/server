package com.lawu.eshop.property.param;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 商家申请退保证金
 * </p>
 * @author Yangqh
 * @date 2017年4月15日 下午4:40:13
 *
 */
public class BusinessRefundDepositParam {

	@NotBlank(message="id不能为空")
	@ApiParam(name = "id", required = true, value = "保证金ID")
	private String id;

	@NotBlank(message="businessBankAccountId不能为空")
	@ApiParam(name = "businessBankAccountId", required = true, value = "商家银行卡ID")
	private String businessBankAccountId;
	
	@NotBlank(message="msgId不能为空")
	@ApiParam(name = "msgId", required = true, value = "短信验证码ID")
	private String msgId;
	
	@NotBlank(message="msg不能为空")
	@ApiParam(name = "msg", required = true, value = "短信验证码")
	private String msg;
	
	@NotBlank(message="payPwd不能为空")
	@ApiParam(name = "payPwd", required = true, value = "支付密码")
	private String payPwd;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessBankAccountId() {
		return businessBankAccountId;
	}

	public void setBusinessBankAccountId(String businessBankAccountId) {
		this.businessBankAccountId = businessBankAccountId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

}
