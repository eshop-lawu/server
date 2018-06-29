package com.lawu.eshop.user.srv.domain.extend;

import java.io.Serializable;

/**
 * @author zhangyong
 * @date 2017/6/15.
 */
public class ShoppingStoreInfoDOView implements Serializable{

    private static final long serialVersionUID = 3250490551606873811L;
    private Long merchantId;

    private String path;

    private String name;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
