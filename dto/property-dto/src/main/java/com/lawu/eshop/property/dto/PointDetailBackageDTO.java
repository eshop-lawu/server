package com.lawu.eshop.property.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2017/5/22.
 */
public class PointDetailBackageDTO {

    @ApiModelProperty(value = "账号", required = true)
    private String account;

    @ApiModelProperty(value = "账号类型", required = true)
    private String accountType;

    @ApiModelProperty(value = "积分标题", required = true)
    private String title;

    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;

    @ApiModelProperty(value = "积分", required = true)
    private BigDecimal point;

    @ApiModelProperty(value = "充值时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @ApiModelProperty(value = "关联充值ID", required = true)
    private String bizId;

    @ApiModelProperty(value = "支付方式", required = true)
    private String payType;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
