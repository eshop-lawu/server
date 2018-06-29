package com.lawu.eshop.ad.dto;

import java.math.BigDecimal;

import com.lawu.eshop.common.constants.ManageTypeEnum;

/**
 * 积分排行版广告BO
 * 
 * @author jiangxinjun
 * @date 2017年7月19日
 */
public class AdPointDTO {

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 商家门店id
	 */
	private Long merchantStoreId;
	
	/**
	 * 经营类型
	 */
	private ManageTypeEnum manageType;
	
	/**
	 * 名称
	 */
	private String title;

	/**
	 * 门店logo
	 */
	private String logoUrl;

	/**
	 * 平面广告图片或者视频封面图片
	 */
	private String imgUrl;

	/**
	 * 总投放积分
	 */
	private BigDecimal totalPoint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public ManageTypeEnum getManageType() {
		return manageType;
	}

	public void setManageType(ManageTypeEnum manageType) {
		this.manageType = manageType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public BigDecimal getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(BigDecimal totalPoint) {
		this.totalPoint = totalPoint;
	}

}