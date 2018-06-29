package com.lawu.eshop.user.param;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月14日
 */
public class OperatorAddressParam extends AbstractPageParam {
	
	/**
	 * 用户账户
	 */
	private String account;
	
	/**
	 * 收货人电话
	 */
	private String mobile;
	
	/**
	 * 收货人姓名
	 */
	private String name;
	
	
	private UserTypeEnum userType;
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserTypeEnum getUserType() {
		return userType;
	}

	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

}
