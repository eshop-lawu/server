package com.lawu.eshop.mq.dto.user;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
public class RegMachineNotification implements Serializable {
    private static final long serialVersionUID = 3044636837333338725L;

    private String userNum;

    /**
     * 推荐类型
     */
    private Byte type;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
