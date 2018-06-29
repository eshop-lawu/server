package com.lawu.eshop.product.dto;

import com.lawu.eshop.product.constant.ProductTopPositionEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/4/12.
 */
public class ProductSearchDTO {

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "特征图片")
    private String featureImage;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "详细描述")
    private String content;

    @ApiModelProperty(value = "原价")
    private Double originalPrice;

    @ApiModelProperty(value = "现价")
    private Double price;

    @ApiModelProperty(value = "销量")
    private Integer salesVolume;
    
    @ApiModelProperty(value = "链接")
    private String linkUrl;
    
    @ApiModelProperty(value = "活动类型")
    private ProductTopPositionEnum typeEnum;

    @ApiModelProperty(value = "是否包邮")
    private Boolean isExpressFree;

    @ApiModelProperty(value = "是否支持退换货")
    private Boolean isAllowRefund;

    @ApiModelProperty(value = "发货地")
    private String shipmentAddress;

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public ProductTopPositionEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(ProductTopPositionEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

    public Boolean getIsExpressFree() {
        return isExpressFree;
    }

    public void setIsExpressFree(Boolean expressFree) {
        isExpressFree = expressFree;
    }

    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    public void setIsAllowRefund(Boolean allowRefund) {
        isAllowRefund = allowRefund;
    }

    public String getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(String shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }
}
