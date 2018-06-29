package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Description: 用户端商品详情页面，商品描述
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月26日 上午10:23:23
 *
 */
public class MemberProductImageDetailDTO {


	@ApiModelProperty(value = "描述")
	private String detail;

	@ApiModelProperty(value = "图片")
	private String imageUrl;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
