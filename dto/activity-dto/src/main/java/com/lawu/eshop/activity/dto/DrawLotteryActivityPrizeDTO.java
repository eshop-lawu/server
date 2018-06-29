package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityPrizeDTO {

    @ApiModelProperty(value = "奖品id")
    private Long id;

    @ApiModelProperty(value = "奖品名称")
    private String name;

    @ApiModelProperty(value = "奖品图片")
    private String imgPath;

    @ApiModelProperty(value = "奖品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "奖品数量")
    private Integer number;

    @ApiModelProperty(value = "奖品库存")
    private Integer inventory;

    @ApiModelProperty(value = "状态")
    private DrawLotteryActivityPrizeStatusEnum statusEnum;

    @ApiModelProperty(value = "状态描述")
    private String statusDes;

    @ApiModelProperty(value = "邮费")
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "门店id")
    private Long merchantStoreId;

    @ApiModelProperty(value = "广告图片")
    private String adImgPath;

    @ApiModelProperty(value = "中奖概率")
    private BigDecimal rate;

    @ApiModelProperty(value = "门店名称")
    private String storeName;

    @ApiModelProperty(value = "门店logo")
    private String storeLogo;

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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public DrawLotteryActivityPrizeStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityPrizeStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }
}
