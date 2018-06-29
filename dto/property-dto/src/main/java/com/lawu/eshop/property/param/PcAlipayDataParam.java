package com.lawu.eshop.property.param;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: pc调用支付宝前请求参数
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月7日 下午2:15:10
 *
 */
public class PcAlipayDataParam extends PcAlipayParam implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	@NotBlank(message = "userNum不能为空")
	private String userNum;

	@NotBlank(message = "totalAmount不能为空")
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "totalAmount格式错误要求数字或小数位不超过2位")
	private String totalAmount;

//	@ApiParam(name = "outTradeNo", required = true, value = "商户订单号")
//	@NotBlank(message = "outTradeNo不能为空")
//	private String outTradeNo;
	
	@NotBlank(message = "subject不能为空")
	private String subject;

	//商家缴纳保证金时需要回调发送消息改门店状态（事务）
	private Long merchantId;

	//代理商区域统计，11/1101/110101
	private String regionPath;
	
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
		
//	public String getOutTradeNo() {
//		return outTradeNo;
//	}
//
//	public void setOutTradeNo(String outTradeNo) {
//		this.outTradeNo = outTradeNo;
//	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}
}