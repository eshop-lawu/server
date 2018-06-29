package com.lawu.eshop.mall.param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;

public class DiscountPackageSaveParam {
	
	/**
	 * 套餐名称
	 */
	private String name;

	/**
	 * 套餐价格
	 */
	private BigDecimal price;

	/**
	 * 其他说明
	 */
	private String otherInstructions;

	/**
	 * 有效期-开始(yyyy-MM-dd)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date validityPeriodBegin;

	/**
	 * 有效期-结束(yyyy-MM-dd)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date validityPeriodEnd;

	/**
	 * 使用时间-周一到周日(用1-7表示,并用逗号分隔)
	 */
	private String useTimeWeek;

	/**
	 * 使用时间-开始(HH:mm)
	 */
	@JsonFormat(pattern = "HH:mm")
	private Date useTimeBegin;

	/**
	 * 使用时间-结束(HH:mm)
	 */
	@JsonFormat(pattern = "HH:mm")
	private Date useTimeEnd;

	/**
	 * 是否需要预约
	 */
	private Boolean isReservation;

	/**
	 * 使用规则
	 */
	private String useRules;
	
	/**
	 * 封面图片
	 */
	private String coverImage;

	/**
	 * 实体门店id
	 */
	private Long merchantStoreId;
	
	/**
	 * 套餐内容
	 */
	private List<DiscountPackageContentSaveForeignParam> discountPackageContents;
	
	/**
	 * 套餐图片详情
	 */
	private List<DiscountPackageImageSaveParam> discountPackageImages;
	
    /**
    *
    * 提前预约时间
    */
    private String advanceBookingTime;
    
    /**
    * 购买须知
    */
    private String purchaseNotes;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getOtherInstructions() {
		return otherInstructions;
	}

	public void setOtherInstructions(String otherInstructions) {
		this.otherInstructions = otherInstructions;
	}

	public Date getValidityPeriodBegin() {
		return validityPeriodBegin;
	}

	public void setValidityPeriodBegin(Date validityPeriodBegin) {
		this.validityPeriodBegin = validityPeriodBegin;
	}

	public Date getValidityPeriodEnd() {
		return validityPeriodEnd;
	}

	public void setValidityPeriodEnd(Date validityPeriodEnd) {
		this.validityPeriodEnd = validityPeriodEnd;
	}

	public String getUseTimeWeek() {
		return useTimeWeek;
	}

	public void setUseTimeWeek(String useTimeWeek) {
		this.useTimeWeek = useTimeWeek;
	}

	public Date getUseTimeBegin() {
		return useTimeBegin;
	}

	public void setUseTimeBegin(Date useTimeBegin) {
		this.useTimeBegin = useTimeBegin;
	}

	public Date getUseTimeEnd() {
		return useTimeEnd;
	}

	public void setUseTimeEnd(Date useTimeEnd) {
		this.useTimeEnd = useTimeEnd;
	}

	public Boolean getIsReservation() {
		return isReservation;
	}

	public void setIsReservation(Boolean isReservation) {
		this.isReservation = isReservation;
	}

	public String getUseRules() {
		return useRules;
	}

	public void setUseRules(String useRules) {
		this.useRules = useRules;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
	}

	public List<DiscountPackageContentSaveForeignParam> getDiscountPackageContents() {
		return discountPackageContents;
	}

	public void setDiscountPackageContents(List<DiscountPackageContentSaveForeignParam> discountPackageContents) {
		this.discountPackageContents = discountPackageContents;
	}

	public List<DiscountPackageImageSaveParam> getDiscountPackageImages() {
		return discountPackageImages;
	}

	public void setDiscountPackageImages(List<DiscountPackageImageSaveParam> discountPackageImages) {
		this.discountPackageImages = discountPackageImages;
	}

	public String getAdvanceBookingTime() {
		return advanceBookingTime;
	}

	public void setAdvanceBookingTime(String advanceBookingTime) {
		this.advanceBookingTime = advanceBookingTime;
	}

	public String getPurchaseNotes() {
		return purchaseNotes;
	}

	public void setPurchaseNotes(String purchaseNotes) {
		this.purchaseNotes = purchaseNotes;
	}
	
}
