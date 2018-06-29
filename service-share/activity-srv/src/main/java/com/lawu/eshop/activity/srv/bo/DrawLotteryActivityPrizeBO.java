package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityPrizeBO {

    private Long id;

    private Long drawLotteryActivityId;

    private String name;

    private String imgPath;

    private BigDecimal price;

    private Integer number;

    private Integer inventory;

    private Byte status;

    private Boolean isAddress;

    private Boolean isSendPrize;

    private Byte prizeType;

    private BigDecimal freightPrice;

    private Long merchantStoreId;

    private String adImgPath;

    private BigDecimal rate;

    private Date gmtModified;

    private Date gmtCreate;

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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getAddress() {
        return isAddress;
    }

    public void setAddress(Boolean address) {
        isAddress = address;
    }

    public Boolean getSendPrize() {
        return isSendPrize;
    }

    public void setSendPrize(Boolean sendPrize) {
        isSendPrize = sendPrize;
    }

    public Byte getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(Byte prizeType) {
        this.prizeType = prizeType;
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
