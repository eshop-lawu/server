package com.lawu.eshop.user.srv.bo;

/**
 * @author zhangyong
 * @date 2017/6/23.
 */
public class MerchantStoreStatusBO {

    private boolean isExist;

    private Byte status;

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
