package com.lawu.eshop.statistics.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/8/11.
 */
public class AgentUserRegUserListDTO {

    @ApiModelProperty(value = "会员总数")
    private Integer memberCount;

    @ApiModelProperty(value = "商家总数")
    private Integer merchantCount;

    @ApiModelProperty(value = "普通商家数")
    private Integer merchantCommonCount;

    @ApiModelProperty(value = "实体商家数")
    private Integer merchantEntityCount;

    @ApiModelProperty(value = "市级区域ID")
    private Integer cityId;

    @ApiModelProperty(value = "市级名称")
    private String cityName;

    @ApiModelProperty(value = "统计数据所属日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtReport;

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

    public Date getGmtReport() {
        return gmtReport;
    }

    public void setGmtReport(Date gmtReport) {
        this.gmtReport = gmtReport;
    }
}
