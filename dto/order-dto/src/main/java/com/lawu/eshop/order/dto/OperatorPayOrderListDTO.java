package com.lawu.eshop.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/6/28.
 */
public class OperatorPayOrderListDTO {

    private Long id;

    private Long merchantId;

    @ApiModelProperty(value = "买单编号")
    private String orderNum;

    @ApiModelProperty(value = "买单金额")
    private BigDecimal actualAmount;

    @ApiModelProperty(value = "商家名称")
    private String merchantName;

    @ApiModelProperty(value = "商家账号")
    private String merchantAccount;

    @ApiModelProperty(value = "用户账号")
    private String memberAccount;

    @ApiModelProperty(value = "买单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty(value = "用户ID")
    private Long memberId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }
}
