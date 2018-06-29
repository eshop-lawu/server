package com.lawu.eshop.mall.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.mall.constants.DiscountPackageStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 优惠套餐详情DTO
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
@ApiModel
public class DiscountPackageDetailForMemberDTO {
	
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
     * 其他说明
     */
	@ApiModelProperty(value = "其他说明", required = true)
    private String otherInstructions;

    /**
     * 有效期-开始(yyyy-MM-dd)
     */
	@ApiModelProperty(value = "有效期-开始(yyyy-MM-dd)", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date validityPeriodBegin;

    /**
     * 有效期-结束(yyyy-MM-dd)
     */
	@ApiModelProperty(value = "有效期-结束(yyyy-MM-dd)", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd")
    private Date validityPeriodEnd;

    /**
     * 使用时间-周一到周日(用1-7表示,并用逗号分隔)
     */
	@ApiModelProperty(value = "使用时间-周一到周日(用0-6表示,0为周日,并用逗号分隔)", required = true)
    private String useTimeWeek;

    /**
     * 使用时间-开始(HH:mm)
     */
	@ApiModelProperty(value = "使用时间-开始(HH:mm)", required = true)
	@JsonFormat(pattern = "HH:mm")
    private Date useTimeBegin;

    /**
     * 使用时间-结束(HH:mm)
     */
	@ApiModelProperty(value = "使用时间-结束(HH:mm)", required = true)
	@JsonFormat(pattern = "HH:mm")
    private Date useTimeEnd;

    /**
     * 状态
     */
	@ApiModelProperty(value = "状态(INVALID-删除|UP-上架|DOWN-下架)", required = true)
    private DiscountPackageStatusEnum status;

    /**
     * 是否需要预约
     */
	@ApiModelProperty(value = "是否需要预约(false-免预约|true-需要预约)", required = true)
    private Boolean isReservation;
	
    /**
    * 提前预约时间(xx小时|xx分钟|)
    */
	@ApiModelProperty(value = "提前预约时间(xx小时|xx分钟|)")
    private String advanceBookingTime;

   /**
    * 购买须知
    */
	@ApiModelProperty(value = "购买须知")
    private List<String> purchaseNotes;
	
    /**
     * 使用规则
     */
	@ApiModelProperty(value = "使用规则")
    private String useRules;
	
	/**
	 * 套餐内容
	 */
	@ApiModelProperty(value = "套餐内容")
	private List<DiscountPackageContentForMemberDTO> discountPackageContents;
	
	/**
	 * 套餐图片详情
	 */
	@ApiModelProperty(value = "套餐图片详情")
	private List<DiscountPackageImageForMemberDTO> discountPackageImages;
	
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

	public List<String> getPurchaseNotes() {
		return purchaseNotes;
	}

	public void setPurchaseNotes(List<String> purchaseNotes) {
		this.purchaseNotes = purchaseNotes;
	}

	public String getUseRules() {
		return useRules;
	}

	public void setUseRules(String useRules) {
		this.useRules = useRules;
	}

	public List<DiscountPackageContentForMemberDTO> getDiscountPackageContents() {
		return discountPackageContents;
	}

	public void setDiscountPackageContents(List<DiscountPackageContentForMemberDTO> discountPackageContents) {
		this.discountPackageContents = discountPackageContents;
	}

	public List<DiscountPackageImageForMemberDTO> getDiscountPackageImages() {
		return discountPackageImages;
	}

	public void setDiscountPackageImages(List<DiscountPackageImageForMemberDTO> discountPackageImages) {
		this.discountPackageImages = discountPackageImages;
	}
	
}