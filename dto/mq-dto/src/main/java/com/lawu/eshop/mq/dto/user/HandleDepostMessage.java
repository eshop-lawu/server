package com.lawu.eshop.mq.dto.user;


import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2017/5/15.
 */
public class HandleDepostMessage implements Serializable {
	
	private static final long serialVersionUID = 4073015142877452444L;

	private String userNum;

    private MerchantStatusEnum statusEnum;

    private Boolean isShow;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public MerchantStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(MerchantStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }
}
