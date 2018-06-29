package com.lawu.eshop.user.srv.domain.extend;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2017/4/24.
 */
public class MerchantPushView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String num;

    private String gtCid;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGtCid() {
        return gtCid;
    }

    public void setGtCid(String gtCid) {
        this.gtCid = gtCid;
    }
}
