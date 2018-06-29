package com.lawu.eshop.order.param.foreign;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * MemberApi购物车结算对外暴露参数
 * 
 * @author Sunny
 * @date 2017/4/6
 */
@ApiModel
public class ShoppingOrderSettlementForeignParam {
	
	/**
	 * 商家id
	 */
	@NotNull(message = "商家id不能为空")
	@ApiModelProperty(name = "merchantId", required = true, value = "商家id")
	private Long merchantId;
	
	/**
	 * 运费
	 */
	@Deprecated
	@ApiModelProperty(name = "freightPrice", required = false, value = "运费", hidden = true)
	private BigDecimal freightPrice;
	
	/**
	 * 买家留言
	 */
	@ApiModelProperty(name = "message", required = false, value = "买家留言")
	private String message;
	
	/**
	 * 地址id
	 */
	@Deprecated
	@NotNull(message = "地址id不能为空")
	@ApiModelProperty(name = "addressId", value = "地址id", hidden = true)
	private Long addressId;
	
	/**
	 * 购物车id列表
	 */
	@NotNull(message = "购物车id列表不能为空")
	@ApiModelProperty(name = "ids", required = true, value = "购物车id列表")
	private List<Long> ids;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	@Deprecated
	public BigDecimal getFreightPrice() {
		return freightPrice;
	}

	@Deprecated
	public void setFreightPrice(BigDecimal freightPrice) {
		this.freightPrice = freightPrice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Deprecated
	public Long getAddressId() {
		return addressId;
	}

	@Deprecated
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

}