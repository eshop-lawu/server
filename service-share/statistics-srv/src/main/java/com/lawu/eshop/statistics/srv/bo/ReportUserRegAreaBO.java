package com.lawu.eshop.statistics.srv.bo;

/**
 * @author meishuquan
 * @date 2017/6/30.
 */
public class ReportUserRegAreaBO {

    private Integer memberCount;

    private Integer merchantCount;

    private Integer merchantCommonCount;

    private Integer merchantEntityCount;

    private Integer cityId;

    private String cityName;

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getMerchantCount() {
        return merchantCount;
    }

    public void setMerchantCount(Integer merchantCount) {
        this.merchantCount = merchantCount;
    }

    public Integer getMerchantCommonCount() {
        return merchantCommonCount;
    }

    public void setMerchantCommonCount(Integer merchantCommonCount) {
        this.merchantCommonCount = merchantCommonCount;
    }

    public Integer getMerchantEntityCount() {
        return merchantEntityCount;
    }

    public void setMerchantEntityCount(Integer merchantEntityCount) {
        this.merchantEntityCount = merchantEntityCount;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
