package com.lawu.eshop.user.param;

import com.lawu.eshop.user.constants.StoreSolrEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2017/4/13.
 */
public class StoreSolrParam extends AbstractPageParam {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "行业，全部为空字符串")
    private String industryPath;

    @ApiModelProperty(value = "距离(1,3,5...单位km,附近和全部为0)")
    private Integer distance;

    @ApiModelProperty(value = "区域", required = true)
    private String regionPath;

    @ApiModelProperty(value = "经度", required = true)
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度", required = true)
    private BigDecimal latitude;

    @ApiModelProperty(value = "门店ID，更多商家必传参数")
    private Long storeId;

    @ApiModelProperty(value = "排序类型:INTELLIGENT_SORT--智能排序，DISTANCE_SORT--离我最近，FEEDBACK_SORT--好评优先，POPULARITY_SORT--人气最高")
    private StoreSolrEnum storeSolrEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustryPath() {
        return industryPath;
    }

    public void setIndustryPath(String industryPath) {
        this.industryPath = industryPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public StoreSolrEnum getStoreSolrEnum() {
        return storeSolrEnum;
    }

    public void setStoreSolrEnum(StoreSolrEnum storeSolrEnum) {
        this.storeSolrEnum = storeSolrEnum;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
