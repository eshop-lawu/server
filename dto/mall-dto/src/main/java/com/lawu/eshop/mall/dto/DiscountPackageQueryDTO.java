package com.lawu.eshop.mall.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠套餐详情DTO
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
@ApiModel
public class DiscountPackageQueryDTO {
	
	/**
     * 主键
     */
	@ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 套餐名称
     */
	@ApiModelProperty(value = "套餐名称", required = true)
    private String name;

    /**
     * 封面图片
     */
	@ApiModelProperty(value = "封面图片", required = true)
    private String coverImage;

    /**
     * 套餐价格
     */
	@ApiModelProperty(value = "套餐价格", required = true)
    private BigDecimal price;

    /**
     * 原价
     */
	@ApiModelProperty(value = "原价", required = true)
    private BigDecimal originalPrice;

    /**
     * 是否需要预约
     */
	@ApiModelProperty(value = "是否需要预约(false-免预约|true-需要预约)", required = true)
    private Boolean isReservation;

    /**
     * 上架时间
     */
	@ApiModelProperty(value = "上架时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtUp;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Boolean getIsReservation() {
		return isReservation;
	}

	public void setIsReservation(Boolean isReservation) {
		this.isReservation = isReservation;
	}

	public Date getGmtUp() {
		return gmtUp;
	}

	public void setGmtUp(Date gmtUp) {
		this.gmtUp = gmtUp;
	}
	
}