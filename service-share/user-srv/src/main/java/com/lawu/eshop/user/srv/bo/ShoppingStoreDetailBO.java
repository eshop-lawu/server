package com.lawu.eshop.user.srv.bo;

/**
 * @author meishuquan
 * @date 2017/4/21.
 */
public class ShoppingStoreDetailBO {

    private Long merchantId;

    private String name;

    private Integer fansCount;

    private Boolean isFans;

    private Boolean isFavorite;

    private String logoPic;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Boolean getFans() {
        return isFans;
    }

    public void setFans(Boolean fans) {
        isFans = fans;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getLogoPic() {
        return logoPic;
    }

    public void setLogoPic(String logoPic) {
        this.logoPic = logoPic;
    }
}
