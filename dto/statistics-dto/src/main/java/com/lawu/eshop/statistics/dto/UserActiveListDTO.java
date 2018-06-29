package com.lawu.eshop.statistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/6/30.
 */
public class UserActiveListDTO {

    @ApiModelProperty(value = "会员数")
    private Integer memberCount;

    @ApiModelProperty(value = "商家数")
    private Integer merchantCount;

    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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
