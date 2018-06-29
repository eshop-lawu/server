package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Description: 用户端商品详情页面，门店信息
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月26日 上午10:23:23
 *
 */
public class MemberProductStoreDTO {

	@ApiModelProperty(value = "店铺ID")
	private Long storeId;

	@ApiModelProperty(value = "门店名称")
	private String storeName;

	@ApiModelProperty(value = "在售商品数量")
	private String upProductNum;

	@ApiModelProperty(value = "粉丝数量")
	private String fansNum;
	
	@ApiModelProperty(value = "商家logo")
	private String logo;
	
	@ApiModelProperty(value = "是否支持七天无理由退货")
	private boolean isSupportEleven;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getUpProductNum() {
		return upProductNum;
	}

	public void setUpProductNum(String upProductNum) {
		this.upProductNum = upProductNum;
	}

	public String getFansNum() {
		return fansNum;
	}

	public void setFansNum(String fansNum) {
		this.fansNum = fansNum;
	}

	public boolean isSupportEleven() {
		return isSupportEleven;
	}

	public void setSupportEleven(boolean isSupportEleven) {
		this.isSupportEleven = isSupportEleven;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}
