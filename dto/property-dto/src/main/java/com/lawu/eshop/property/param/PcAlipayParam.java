package com.lawu.eshop.property.param;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.property.constants.ThirdPartyBizFlagEnum;
import com.lawu.eshop.property.constants.ThirdPayBodyEnum;

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
public class PcAlipayParam implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiParam(name = "bizId", required = true, value = "业务表ID")
	@NotBlank(message = "bizId不能为空")
	private String bizId;
	
	@ApiParam(name = "thirdPayBodyEnum", required = true, value = "参考链接：http://192.168.1.21:8090/pages/viewpage.action?pageId=1998868")
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

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public ThirdPartyBizFlagEnum getBizFlagEnum() {
		return bizFlagEnum;
	}

	public void setBizFlagEnum(ThirdPartyBizFlagEnum bizFlagEnum) {
		this.bizFlagEnum = bizFlagEnum;
	}

}