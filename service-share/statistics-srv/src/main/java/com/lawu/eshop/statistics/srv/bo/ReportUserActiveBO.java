package com.lawu.eshop.statistics.srv.bo;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public class ReportUserActiveBO {

    private Long id;

    /**
     *
     * 活跃会员数
     * report_user_active_daily.member_count
     *
     * @mbg.generated
     */
    private Integer memberCount;

    /**
     *
     * 活跃商家数
     * report_user_active_daily.merchant_count
     *
     * @mbg.generated
     */
    private Integer merchantCount;

    /**
     *
     * 统计数据所属日期
     * report_user_active_daily.gmt_report
     *
     * @mbg.generated
     */
    private Date gmtReport;

    /**
     *
     * 统计时间
     * report_user_active_daily.gmt_create
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
