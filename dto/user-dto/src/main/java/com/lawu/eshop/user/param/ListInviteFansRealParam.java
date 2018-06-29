package com.lawu.eshop.user.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2017/4/26.
 */
public class ListInviteFansRealParam extends AbstractPageParam{

    private Long merchantId;

    private String regionPath;

    private Byte sex;

    private Boolean isAgeLimit;

    private Integer startAge;

    private Integer endAge;

    private Integer inviteCount;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Boolean getAgeLimit() {
        return isAgeLimit;
    }

    public void setAgeLimit(Boolean ageLimit) {
        isAgeLimit = ageLimit;
    }

    public Integer getStartAge() {
        return startAge;
    }

    public void setStartAge(Integer startAge) {
        this.startAge = startAge;
    }

    public Integer getEndAge() {
        return endAge;
    }

    public void setEndAge(Integer endAge) {
        this.endAge = endAge;
    }

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }
}
