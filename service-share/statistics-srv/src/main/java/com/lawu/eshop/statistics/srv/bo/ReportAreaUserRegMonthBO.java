package com.lawu.eshop.statistics.srv.bo;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/8/14.
 */
public class ReportAreaUserRegMonthBO {
    private Long id;

    /**
     *
     * 会员总数
     * report_area_user_reg_month.member_count
     *
     * @mbg.generated
     */
    private Integer memberCount;

    /**
     *
     * 商家总数
     * report_area_user_reg_month.merchant_count
     *
     * @mbg.generated
     */
    private Integer merchantCount;

    /**
     *
     * 普通商家数
     * report_area_user_reg_month.merchant_normal_count
     *
     * @mbg.generated
     */
    private Integer merchantNormalCount;

    /**
     *
     * 实体商家数
     * report_area_user_reg_month.merchant_entity_count
     *
     * @mbg.generated
     */
    private Integer merchantEntityCount;

    /**
     *
     * 市级区域ID
     * report_area_user_reg_month.city_id
     *
     * @mbg.generated
     */
    private Integer cityId;

    /**
     *
     * 市级名称
     * report_area_user_reg_month.city_name
     *
     * @mbg.generated
     */
    private String cityName;

    /**
     *
     * 统计数据所属日期
     * report_area_user_reg_month.gmt_report
     *
     * @mbg.generated
     */
    private Date gmtReport;

    /**
     *
     * 统计时间
     * report_area_user_reg_month.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getMerchantNormalCount() {
        return merchantNormalCount;
    }

    public void setMerchantNormalCount(Integer merchantNormalCount) {
        this.merchantNormalCount = merchantNormalCount;
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
