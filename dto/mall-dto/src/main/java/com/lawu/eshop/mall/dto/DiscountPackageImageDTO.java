package com.lawu.eshop.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 套餐详情图片DTO
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
@ApiModel
public class DiscountPackageImageDTO {
	
    /**
     * 主键
     */
	@ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 文字描述
     */
	@ApiModelProperty(value = "文字描述", required = true)
    private String description;

    /**
     * 图片
     */
	@ApiModelProperty(value = "图片", required = true)
    private String image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}