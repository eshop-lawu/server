package com.lawu.eshop.game.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
public class RankListParam extends AbstractPageParam {

    @ApiModelProperty(value = "日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reportDate;

    private Boolean status;

    private String account;

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
