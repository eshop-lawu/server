package com.lawu.eshop.ad.param;

import java.util.List;

import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.OrderTypeEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.UserSexEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2017/9/14.
 */
public class AdSolrRealParam extends AbstractPageParam {

    private Long memberId;

    private List<Long> merchantIds;

    private Double longitude;

    private Double latitude;

    private String regionPath;

    private AdTypeEnum typeEnum;

    private AdStatusEnum statusEnum;

    private OrderTypeEnum orderTypeEnum;

    private UserSexEnum sexEnum;

    private Integer age;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public List<Long> getMerchantIds() {
        return merchantIds;
    }

    public void setMerchantIds(List<Long> merchantIds) {
        this.merchantIds = merchantIds;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public AdTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(AdTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public AdStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(AdStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public OrderTypeEnum getOrderTypeEnum() {
        return orderTypeEnum;
    }

    public void setOrderTypeEnum(OrderTypeEnum orderTypeEnum) {
        this.orderTypeEnum = orderTypeEnum;
    }

    public UserSexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(UserSexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
