package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.MerchantFavoredTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public class MerchantFavoredParam {

    /**
     * 1:每满、2:满减、3:全单折扣
     */
    @ApiModelProperty(value = "TYPE_FULL：每满，TYPE_FULL_REDUCE：满减，TYPE_DISCOUNT：折扣", required = true)
    private MerchantFavoredTypeEnum typeEnum;

    /**
     * 满额
     */
    @ApiModelProperty(value = "满额")
    private BigDecimal reachAmount;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal favoredAmount;
    /**
     * 折扣率
     */
    @ApiModelProperty(value = "折扣率")
    private BigDecimal discountRate;

    /**
     * 每周有效时间段
     */
    @ApiModelProperty(value = "每周有效时间段", required = true)
    private String validWeekTime;
    /**
     * 每日有效开始时间
     */
    @ApiModelProperty(value = "每日有效开始时间", required = true)
    private String validDayBeginTime;

    /**
     * 每日有效结束时间
     */
    @ApiModelProperty(value = "每日有效结束时间", required = true)
    private String validDayEndTime;

    /**
     * 总有效期：开始时间
     */
    @ApiModelProperty(value = "总有效期：开始时间", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entireBeginTime;

    /**
     * 总有效期：结束时间
     */
    @ApiModelProperty(value = "总有效期：结束时间", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entireEndTime;

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
}
