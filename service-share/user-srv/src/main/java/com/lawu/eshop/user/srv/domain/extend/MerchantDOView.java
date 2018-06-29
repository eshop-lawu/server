package com.lawu.eshop.user.srv.domain.extend;

/**
 * @author meishuquan
 * @date 2017/6/29.
 */
public class MerchantDOView {

    private String storeName;

    private String account;

    private Long id;

    private Long storeId;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
