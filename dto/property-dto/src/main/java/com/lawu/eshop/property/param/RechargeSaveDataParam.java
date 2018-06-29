package com.lawu.eshop.property.param;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月12日 下午8:42:22
 *
 */
public class RechargeSaveDataParam extends RechargeSaveParam{

	@NotBlank(message = "userNum不能为空")
	private String userNum;
	
	private String rechargeScale;

	//充值账户省/市/区ID，非必填
	private String regionPath;
	
	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getRechargeScale() {
		return rechargeScale;
	}

	public void setRechargeScale(String rechargeScale) {
		this.rechargeScale = rechargeScale;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}
}