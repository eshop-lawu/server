package com.lawu.eshop.mall.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public class MerchantFavoredDTO {
    /**
     * 主键
     * merchant_favored.id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    @ApiModelProperty(value = "商家ID")
    private Long merchantId;
    
    /**
     * 1:每满、2:满减、3:全单折扣
     * merchant_favored.type
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "TYPE_FULL：每满，TYPE_FULL_REDUCE：满减，TYPE_DISCOUNT：折扣")
    private MerchantFavoredTypeEnum typeEnum;

    /**
     * 满额
     * merchant_favored.reach_amount
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "满额")
    private BigDecimal reachAmount;

    /**
     * 优惠金额
     * merchant_favored.favored_amount
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal favoredAmount;

    /**
     * 折扣率
     * merchant_favored.discount_rate
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "折扣率")
    private BigDecimal discountRate;

    /**
     * 每周有效时间段
     * merchant_favored.valid_week_time
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "每周有效时间段")
    private String validWeekTime;

    /**
     * 每日有效开始时间
     * merchant_favored.valid_day_begin_time
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "每日有效开始时间")
    private String validDayBeginTime;

    /**
     * 每日有效结束时间
     * merchant_favored.valid_day_end_time
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "每日有效结束时间")
    private String validDayEndTime;

    /**
     * 总有效期：开始时间
     * merchant_favored.entire_begin_time
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "总有效期：开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date entireBeginTime;

    /**
     * 总有效期：结束时间
     * merchant_favored.entire_end_time
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "总有效期：结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date entireEndTime;
    
    @ApiModelProperty(value = "是否逾期：true--是，false--否")
    private Boolean isOverdue ;
    
    @ApiModelProperty(value = "商家编号")
    private String userNum;
    
    
    @ApiModelProperty(value = "名称")
    private String name;
    
    @ApiModelProperty(value = "门店照")
    private String storePic; 

	public Boolean getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(Boolean isOverdue) {
		this.isOverdue = isOverdue;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MerchantFavoredTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(MerchantFavoredTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public BigDecimal getReachAmount() {
        return reachAmount;
    }

    public void setReachAmount(BigDecimal reachAmount) {
        this.reachAmount = reachAmount;
    }

    public BigDecimal getFavoredAmount() {
        return favoredAmount;
    }

    public void setFavoredAmount(BigDecimal favoredAmount) {
        this.favoredAmount = favoredAmount;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public String getValidWeekTime() {
        return validWeekTime;
    }

    public void setValidWeekTime(String validWeekTime) {
        this.validWeekTime = validWeekTime;
    }

    public String getValidDayBeginTime() {
        return validDayBeginTime;
    }

    public void setValidDayBeginTime(String validDayBeginTime) {
        this.validDayBeginTime = validDayBeginTime;
    }

    public String getValidDayEndTime() {
        return validDayEndTime;
    }

    public void setValidDayEndTime(String validDayEndTime) {
        this.validDayEndTime = validDayEndTime;
    }

    public Date getEntireBeginTime() {
        return entireBeginTime;
    }

    public void setEntireBeginTime(Date entireBeginTime) {
        this.entireBeginTime = entireBeginTime;
    }

    public Date getEntireEndTime() {
        return entireEndTime;
    }

    public void setEntireEndTime(Date entireEndTime) {
        this.entireEndTime = entireEndTime;
    }

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStorePic() {
		return storePic;
	}

	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}
	
	
    
}
