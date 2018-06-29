package com.lawu.eshop.mall.param.foreign;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.lawu.eshop.mall.constants.DiscountPackageUpdateStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DiscountPackageUpdateForeignParam {
	
	/**
	 * 套餐名称
	 */
	@NotBlank
	@ApiModelProperty(name = "name", value = "套餐名称", required = true)
	private String name;

	/**
	 * 套餐价格
	 */
	@NotNull
	@ApiModelProperty(name = "name", value = "套餐价格", required = true)
	private BigDecimal price;

	/**
	 * 其他说明
	 */
	@ApiModelProperty(name = "name", value = "其他说明", required = true)
	private String otherInstructions;

	/**
	 * 有效期-开始(yyyy-MM-dd)
	 */
	@NotNull
	@ApiModelProperty(name = "validityPeriodBegin", value = "有效期-开始(yyyy-MM-dd)", required = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validityPeriodBegin;

	/**
	 * 有效期-结束(yyyy-MM-dd)
	 */
	@NotNull
	@ApiModelProperty(name = "validityPeriodEnd", value = "有效期-结束(yyyy-MM-dd)", required = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validityPeriodEnd;

	/**
	 * 使用时间-周一到周日(用1-7表示,并用逗号分隔)
	 */
	@NotBlank
	@ApiModelProperty(name = "useTimeWeek", value = "使用时间-周一到周日(用0-6表示,0为周日,并用逗号分隔)", required = true)
	private String useTimeWeek;

	/**
	 * 使用时间-开始(HH:mm)
	 */
	@NotNull
	@ApiModelProperty(name = "useTimeBegin", value = "使用时间-开始(HH:mm)", required = true)
	@DateTimeFormat(pattern = "HH:mm")
	private Date useTimeBegin;

	/**
	 * 使用时间-结束(HH:mm)
	 */
	@NotNull
	@ApiModelProperty(name = "useTimeBegin", value = "使用时间-开始(HH:mm)", required = true)
	@DateTimeFormat(pattern = "HH:mm")
	private Date useTimeEnd;

	/**
	 * 是否需要预约
	 */
	@NotNull
	@ApiModelProperty(name = "isReservation", value = "是否需要预约", required = true)
	private Boolean isReservation;

	/**
	 * 套餐内容
	 */
	@NotNull(message = "套餐内容不能为空")
	@ApiModelProperty(name = "discountPackageContents", value = "套餐内容（如果是新增的套餐内容id置空）<br/> example:[{\"id\":null,\"name\":null,\"quantity\":null,\"unit\":null,\"unitPrice\":null}]", required = true)
	private String discountPackageContents;
	
	/**
	 * 套餐图片详情
	 */
	@NotNull(message = "套餐图片详情不能为空")
	@ApiModelProperty(name = "discountPackageImages", value = "套餐图片详情（如果是新增的图片详情id置空）<br/> example:[{\"description\":null,\"id\":null}]", required = true)
	private String discountPackageImages;
	
    /**
    * 提前预约时间
    */
	@ApiModelProperty(value = "提前预约时间")
    private String advanceBookingTime;

    /**
    * 购买须知
    */
	@ApiModelProperty(value = "购买须知")
    private String purchaseNotes;

	/**
	 * 使用规则
	 */
	@ApiModelProperty(name = "useRules", value = "使用规则")
	private String useRules;

    /**
    * 状态
    */
	@ApiModelProperty( value = "状态")
    private DiscountPackageUpdateStatusEnum status;

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

	public String getDiscountPackageContents() {
		return discountPackageContents;
	}

	public void setDiscountPackageContents(String discountPackageContents) {
		this.discountPackageContents = discountPackageContents;
	}

	public String getDiscountPackageImages() {
		return discountPackageImages;
	}

	public void setDiscountPackageImages(String discountPackageImages) {
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

	public String getUseRules() {
		return useRules;
	}

	public void setUseRules(String useRules) {
		this.useRules = useRules;
	}

	public DiscountPackageUpdateStatusEnum getStatus() {
		return status;
	}

	public void setStatus(DiscountPackageUpdateStatusEnum status) {
		this.status = status;
	}

}
