package com.lawu.eshop.pay.param;/**
 * Created by ${Yangqh} on 2018/1/17.
 */

import java.math.BigDecimal;

/**
 * <p> 单笔转账到支付宝账户接口 </p>
 *
 * @author yangqh
 * @date 2018/1/17 13:44
 */
public class AlipayToaccountTransferParam {

    //参考：https://docs.open.alipay.com/api_28/alipay.fund.trans.toaccount.transfer

    private String outBizNo;    //商户转账唯一订单号
    private String payeeType;   //收款方账户类型
    private String payeeAccount;//收款方账户
    private BigDecimal amount;      //转账金额，单位：元。
    private String payerShowName;//付款方姓名
    private String payeeRealName;//收款方真实姓名
    private String remark;       //转账备注

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayerShowName() {
        return payerShowName;
    }

    public void setPayerShowName(String payerShowName) {
        this.payerShowName = payerShowName;
    }

    public String getPayeeRealName() {
        return payeeRealName;
    }

    public void setPayeeRealName(String payeeRealName) {
        this.payeeRealName = payeeRealName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
