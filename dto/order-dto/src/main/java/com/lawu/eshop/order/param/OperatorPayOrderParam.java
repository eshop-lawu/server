package com.lawu.eshop.order.param;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiParam;

/**
 * @author zhangyong
 * @date 2017/6/28.
 */
public class OperatorPayOrderParam extends AbstractPageParam {

    @ApiParam(name = "merchantId",  value = "商家ID")
    private Long merchantId;

    @ApiParam(name = "memberId",  value = "用户ID")
    private Long memberId;

    @ApiParam(name = "orderNum",  value = "买单编号")
    private String orderNum;

    @ApiParam(name = "merchantAccount",  value = "商家账号")
    private String merchantAccount;

    @ApiParam(name = "memberAccount",  value = "用户账号")
    private String memberAccount;

    @ApiParam(name = "beginDate",  value = "开始时间")
    private String beginDate;

    @ApiParam(name = "endDate",  value = "结束时间")
    private String endDate;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    public String getMemberAccount() {
        return memberAccount;
    }

    public void setMemberAccount(String memberAccount) {
        this.memberAccount = memberAccount;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
