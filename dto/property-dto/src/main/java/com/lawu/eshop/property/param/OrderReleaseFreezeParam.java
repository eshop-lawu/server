package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * <p>
 * Description:确认收货后7天，系统自动将订单金额加入商家余额
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月14日 上午10:17:47
 *
 */
public class OrderReleaseFreezeParam {

	// 商家编号
	@NotBlank(message = "userNums不能为空")
	private String userNums;

	// 商品订单ID
	@NotBlank(message = "orderIds不能为空")
	private String orderIds;

	// 商家账号
	@NotBlank(message = "accounts不能为空")
	private String accounts;
	
	//订单支付方式
	@NotNull(message="payWays不能为空")
	private Byte payWays;

	//商家门店省/市/区ID
	private String regionPaths;

	private String orderItemProductName;

	//用户编号-释放冻结资金时用于更新用户等级
	private String memberNum;

	//主事务执行时间
	private Date gmtExecute;

	public String getUserNums() {
		return userNums;
	}

	public void setUserNums(String userNums) {
		this.userNums = userNums;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public Byte getPayWays() {
		return payWays;
	}

	public void setPayWays(Byte payWays) {
		this.payWays = payWays;
	}

	public String getRegionPaths() {
		return regionPaths;
	}

	public void setRegionPaths(String regionPaths) {
		this.regionPaths = regionPaths;
	}

	public String getOrderItemProductName() {
		return orderItemProductName;
	}

	public void setOrderItemProductName(String orderItemProductName) {
		this.orderItemProductName = orderItemProductName;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public Date getGmtExecute() {
		return gmtExecute;
	}

	public void setGmtExecute(Date gmtExecute) {
		this.gmtExecute = gmtExecute;
	}
}
