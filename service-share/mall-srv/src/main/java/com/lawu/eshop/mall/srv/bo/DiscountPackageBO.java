package com.lawu.eshop.mall.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.mall.constants.DiscountPackageStatusEnum;

/**
 * 优惠套餐BO
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class DiscountPackageBO {
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 实体门店id
     */
    private Long merchantStoreId;

    /**
     * 套餐名称
     */
    private String name;

    /**
     * 封面图片
     */
    private String coverImage;

    /**
     * 套餐价格
     */
    private BigDecimal price;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 其他说明
     */
    private String otherInstructions;

    /**
     * 有效期-开始
     */
    private Date validityPeriodBegin;

    /**
     * 有效期-结束
     */
    private Date validityPeriodEnd;

    /**
     * 使用时间-周一到周日
     */
    private String useTimeWeek;

    /**
     * 使用时间-开始
     */
    private Date useTimeBegin;

    /**
     * 使用时间-结束
     */
    private Date useTimeEnd;

    /**
     * 状态
     */
    private DiscountPackageStatusEnum status;

    /**
     * 是否需要预约
     */
    private Boolean isReservation;
    
    /**
    * 提前预约时间(xx小时|xx分钟|)
    */
    private String advanceBookingTime;

   /**
    * 购买须知
    */
    private String purchaseNotes;
    
    /**
     * 使用规则
     */
    private String useRules;

    /**
     * 上架时间
     */
    private Date gmtUp;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getMerchantStoreId() {
		return merchantStoreId;
	}

	public void setMerchantStoreId(Long merchantStoreId) {
		this.merchantStoreId = merchantStoreId;
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

	public DiscountPackageStatusEnum getStatus() {
		return status;
	}

	public void setStatus(DiscountPackageStatusEnum status) {
		this.status = status;
	}

	public Boolean getIsReservation() {
		return isReservation;
	}

	public void setIsReservation(Boolean isReservation) {
		this.isReservation = isReservation;
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

	public String getUseRules() {
		return useRules;
	}

	public void setUseRules(String useRules) {
		this.useRules = useRules;
	}

	public Date getGmtUp() {
		return gmtUp;
	}

	public void setGmtUp(Date gmtUp) {
		this.gmtUp = gmtUp;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
    
}