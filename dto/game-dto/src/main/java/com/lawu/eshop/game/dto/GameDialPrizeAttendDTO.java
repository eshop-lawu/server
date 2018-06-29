package com.lawu.eshop.game.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.constants.GameDialPrizeTypeEnum;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialPrizeAttendDTO {

    private Long id;

    private Long gameDialId;

    private String name;

    private String imgPath;

    private BigDecimal price;

    private Integer number;

    private Integer inventory;

    private GameDialPrizeStatusEnum status;

    private Boolean isAddress;

    private Boolean isSendPrize;

    private GameDialPrizeTypeEnum prizeType;

    private BigDecimal freightPrice;

    private BigDecimal rate;

    private Date gmtModified;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameDialId() {
        return gameDialId;
    }

    public void setGameDialId(Long gameDialId) {
        this.gameDialId = gameDialId;
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

    public GameDialPrizeStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameDialPrizeStatusEnum status) {
		this.status = status;
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
   

    public GameDialPrizeTypeEnum getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(GameDialPrizeTypeEnum prizeType) {
		this.prizeType = prizeType;
	}

	public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
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
