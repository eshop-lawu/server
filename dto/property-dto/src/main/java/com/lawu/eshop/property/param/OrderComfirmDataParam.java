package com.lawu.eshop.property.param;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * <p>
 * Description:订单确认收货 
 * </p>
 * @author Yangqh
 * @date 2017年4月14日 上午10:17:47
 *
 */
public class OrderComfirmDataParam {
	
	//商家编号
	@NotBlank(message = "userNum不能为空")
	private String userNum;
	
	//业务ID(如：订单、)
	@NotBlank(message = "bizId不能为空")
	@Pattern(regexp = "^[1-9]d*$", message = "bizId为大于0的正整数")
	private String bizId;
	
	@NotBlank(message = "totalOrderMoney不能为空")
	@Pattern(regexp = "^\\d{0,8}\\.{0,1}(\\d{1,2})?$", message = "totalOrderMoney格式错误要求数字或小数位不超过2位")
	private String totalOrderMoney;

	@NotBlank(message = "orderNum不能为空")
	private String orderNum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getTotalOrderMoney() {
		return totalOrderMoney;
	}

	public void setTotalOrderMoney(String totalOrderMoney) {
		this.totalOrderMoney = totalOrderMoney;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
