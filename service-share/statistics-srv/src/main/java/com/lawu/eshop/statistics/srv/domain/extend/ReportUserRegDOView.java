package com.lawu.eshop.statistics.srv.domain.extend;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/6/30.
 */
public class ReportUserRegDOView {

    private Integer memberCount;

    private Integer merchantCount;

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

    public Date getGmtReport() {
        return gmtReport;
    }

    public void setGmtReport(Date gmtReport) {
        this.gmtReport = gmtReport;
    }
}
