package com.lawu.eshop.property.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.property.constants.ThirdPartyBizFlagEnum;
import com.lawu.eshop.property.constants.ThirdPayBodyEnum;

import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: app调用支付宝前请求参数
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月6日 下午5:36:40
 *
 */
public class ThirdPayParam {

	@ApiParam(name = "bizIds", required = true, value = "业务表ID(支持多个,用英文逗号分割)")
	@NotBlank(message = "bizIds不能为空")
	private String bizIds;

	@ApiParam(name = "thirdPayBodyEnum", required = true, value = "参考链接：http://192.168.1.21:8090/pages/viewpage.action?pageId=1998868")
	@NotNull(message = "thirdPayBodyEnum不能为空")
	private ThirdPayBodyEnum thirdPayBodyEnum;

	@ApiParam(name = "bizFlagEnum", required = true, value = "参考链接：http://192.168.1.21:8090/pages/viewpage.action?pageId=1998868")
	@NotNull(message = "bizFlagEnum不能为空")
	private ThirdPartyBizFlagEnum bizFlagEnum;

	public ThirdPayBodyEnum getThirdPayBodyEnum() {
		return thirdPayBodyEnum;
	}

	public void setThirdPayBodyEnum(ThirdPayBodyEnum thirdPayBodyEnum) {
		this.thirdPayBodyEnum = thirdPayBodyEnum;
	}

	public String getBizIds() {
		return bizIds;
	}

	public void setBizIds(String bizIds) {
		this.bizIds = bizIds;
	}

	public ThirdPartyBizFlagEnum getBizFlagEnum() {
		return bizFlagEnum;
	}

	public void setBizFlagEnum(ThirdPartyBizFlagEnum bizFlagEnum) {
		this.bizFlagEnum = bizFlagEnum;
	}

}