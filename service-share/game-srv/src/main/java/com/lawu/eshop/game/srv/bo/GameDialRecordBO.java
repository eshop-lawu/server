package com.lawu.eshop.game.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialRecordBO {

    private Long id;

    private Long userId;

    private String userNum;

    private String userAccount;

    private Long gameDialId;

    private Long gameDialPrizeId;

    private Byte status;

    private BigDecimal payPoint;

    private String consigneeName;

    private String consigneeMobile;

    private String consigneeAddress;

    private Date gmtModified;

    private Date gmtCreate;
    
    private String prizeName;

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

    public Long getGameDialId() {
        return gameDialId;
    }

    public void setGameDialId(Long gameDialId) {
        this.gameDialId = gameDialId;
    }

    public Long getGameDialPrizeId() {
        return gameDialPrizeId;
    }

    public void setGameDialPrizeId(Long gameDialPrizeId) {
        this.gameDialPrizeId = gameDialPrizeId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
    
    
}
