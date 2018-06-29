package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeTypeEnum;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
public class DrawLotteryActivityRecordBO {

    private Long id;

    private Long userId;

    private String userNum;

    private String userAccount;

    private Long drawLotteryActivityId;

    private Long drawLotteryActivityPrizeId;

    private String prizeName;

    private Byte status;

    private Byte channel;

    private BigDecimal payPoint;

    private String consigneeName;

    private String consigneeMobile;

    private String consigneeAddress;

    private Date gmtModified;

    private Date gmtCreate;
    
    private String expressNum;
    
    private DrawLotteryActivityPrizeTypeEnum prizeType;
    
    private String prizeImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Long getDrawLotteryActivityId() {
        return drawLotteryActivityId;
    }

    public void setDrawLotteryActivityId(Long drawLotteryActivityId) {
        this.drawLotteryActivityId = drawLotteryActivityId;
    }

    public Long getDrawLotteryActivityPrizeId() {
        return drawLotteryActivityPrizeId;
    }

    public void setDrawLotteryActivityPrizeId(Long drawLotteryActivityPrizeId) {
        this.drawLotteryActivityPrizeId = drawLotteryActivityPrizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getChannel() {
        return channel;
    }

    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    public BigDecimal getPayPoint() {
        return payPoint;
    }

    public void setPayPoint(BigDecimal payPoint) {
        this.payPoint = payPoint;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
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

	public String getExpressNum() {
		return expressNum;
	}

	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}

	public DrawLotteryActivityPrizeTypeEnum getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(DrawLotteryActivityPrizeTypeEnum prizeType) {
		this.prizeType = prizeType;
	}

	public String getPrizeImg() {
		return prizeImg;
	}

	public void setPrizeImg(String prizeImg) {
		this.prizeImg = prizeImg;
	}
	
	
    
}
