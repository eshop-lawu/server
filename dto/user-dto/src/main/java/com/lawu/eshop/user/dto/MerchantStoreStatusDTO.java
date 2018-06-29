package com.lawu.eshop.user.dto;

/**
 * @author zhangyong
 * @date 2017/6/23.
 */
public class MerchantStoreStatusDTO {

    private boolean isExist;

    private byte status;

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
