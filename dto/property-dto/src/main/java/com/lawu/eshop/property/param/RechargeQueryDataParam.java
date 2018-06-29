package com.lawu.eshop.property.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * <p>
 * Description: 运营平台充值记录查询
 * </p>
 * @author Yangqh
 * @date 2017年5月16日 下午3:34:10
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RechargeQueryDataParam  extends RechargeQueryParam{

	private String userNum;

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	
	
}
