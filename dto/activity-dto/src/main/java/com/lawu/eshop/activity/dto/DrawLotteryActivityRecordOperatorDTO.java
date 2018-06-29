package com.lawu.eshop.activity.dto;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordChannelEnum;
import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;

/**
 * @author meishuquan
 * @date 2018/1/17.
 */
public class DrawLotteryActivityRecordOperatorDTO {

    private Long id;

    private String userAccount;

    private String nickName;

    private String prizeName;

    private DrawLotteryActivityRecordStatusEnum statusEnum;

    private String statusDes;

    private DrawLotteryActivityRecordChannelEnum channelEnum;

    private String channelDes;

    private String consigneeName;

    private String consigneeMobile;

    private String consigneeAddress;
    
    private String expressNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public DrawLotteryActivityRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public DrawLotteryActivityRecordChannelEnum getChannelEnum() {
        return channelEnum;
    }

    public void setChannelEnum(DrawLotteryActivityRecordChannelEnum channelEnum) {
        this.channelEnum = channelEnum;
    }

    public String getChannelDes() {
        return channelDes;
    }

    public void setChannelDes(String channelDes) {
        this.channelDes = channelDes;
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

	public String getExpressNum() {
		return expressNum;
	}

	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}
    
    
}
