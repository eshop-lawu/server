package com.lawu.eshop.mall.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
public class MerchantFavoredBO {
    /**
     * 主键
     * merchant_favored.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 商家ID
     * merchant_favored.merchant_id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     * 1:每满、2:满减、3:全单折扣
     * merchant_favored.type
     *
     * @mbg.generated
     */
    private Byte type;

    /**
     * 满额
     * merchant_favored.reach_amount
     *
     * @mbg.generated
     */
    private BigDecimal reachAmount;

    /**
     * 优惠金额
     * merchant_favored.favored_amount
     *
     * @mbg.generated
     */
    private BigDecimal favoredAmount;

    /**
     * 折扣率
     * merchant_favored.discount_rate
     *
     * @mbg.generated
     */
    private BigDecimal discountRate;

    /**
     * 每周有效时间段
     * merchant_favored.valid_week_time
     *
     * @mbg.generated
     */
    private String validWeekTime;

    /**
     * 每日有效开始时间
     * merchant_favored.valid_day_begin_time
     *
     * @mbg.generated
     */
    private String validDayBeginTime;

    /**
     * 每日有效结束时间
     * merchant_favored.valid_day_end_time
     *
     * @mbg.generated
     */
    private String validDayEndTime;

    /**
     * 总有效期：开始时间
     * merchant_favored.entire_begin_time
     *
     * @mbg.generated
     */
    private Date entireBeginTime;

    /**
     * 总有效期：结束时间
     * merchant_favored.entire_end_time
     *
     * @mbg.generated
     */
    private Date entireEndTime;

    /**
     * 状态（1：有效0：无效）
     * merchant_favored.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     * 修改时间
     * merchant_favored.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     * 创建时间
     * merchant_favored.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
