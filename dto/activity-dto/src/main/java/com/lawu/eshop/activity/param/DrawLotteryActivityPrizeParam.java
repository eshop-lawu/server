package com.lawu.eshop.activity.param;

import java.math.BigDecimal;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeTypeEnum;

/**
 * @author meishuquan
 * @date 2018/1/16.
 */
public class DrawLotteryActivityPrizeParam {

    private Long id;

    private Long drawLotteryActivityId;

    private String name;

    private String imgPath;

    private BigDecimal price;

    private Integer number;

    private DrawLotteryActivityPrizeStatusEnum statusEnum;

    private BigDecimal freightPrice;

    private Long merchantStoreId;

    private String adImgPath;

    private BigDecimal rate;

    private Boolean isAddress;

    private Boolean isSendPrize;

    private DrawLotteryActivityPrizeTypeEnum prizeTypeEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrawLotteryActivityId() {
        return drawLotteryActivityId;
    }

    public void setDrawLotteryActivityId(Long drawLotteryActivityId) {
        this.drawLotteryActivityId = drawLotteryActivityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public DrawLotteryActivityPrizeStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityPrizeStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    public String getAdImgPath() {
        return adImgPath;
    }

    public void setAdImgPath(String adImgPath) {
        this.adImgPath = adImgPath;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Boolean getIsAddress() {
        return isAddress;
    }

    public void setIsAddress(Boolean address) {
        isAddress = address;
    }

    public Boolean getIsSendPrize() {
        return isSendPrize;
    }

    public void setIsSendPrize(Boolean sendPrize) {
        isSendPrize = sendPrize;
    }

    public DrawLotteryActivityPrizeTypeEnum getPrizeTypeEnum() {
        return prizeTypeEnum;
    }

    public void setPrizeTypeEnum(DrawLotteryActivityPrizeTypeEnum prizeTypeEnum) {
        this.prizeTypeEnum = prizeTypeEnum;
    }
}
