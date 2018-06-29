package com.lawu.eshop.user.param;

/**
 * @author meishuquan
 * @date 2017/8/1.
 */
public class StoreIndexParam {

    private Long merchantStoreId;

    private String favoreInfo;

    private String discountPackage;

    private Double discountOrdinal;

    private String favoreEndTime;

    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    public String getFavoreInfo() {
        return favoreInfo;
    }

    public void setFavoreInfo(String favoreInfo) {
        this.favoreInfo = favoreInfo;
    }

    public String getDiscountPackage() {
        return discountPackage;
    }

    public void setDiscountPackage(String discountPackage) {
        this.discountPackage = discountPackage;
    }

    public Double getDiscountOrdinal() {
        return discountOrdinal;
    }

    public void setDiscountOrdinal(Double discountOrdinal) {
        this.discountOrdinal = discountOrdinal;
    }

    public String getFavoreEndTime() {
        return favoreEndTime;
    }

    public void setFavoreEndTime(String favoreEndTime) {
        this.favoreEndTime = favoreEndTime;
    }
}
