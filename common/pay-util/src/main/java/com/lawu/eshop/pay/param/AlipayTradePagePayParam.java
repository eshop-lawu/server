package com.lawu.eshop.pay.param;

/**
 * <p> alipay.trade.page.pay 电脑网页支付 </p>
 *
 * @author yangqh
 * @date 2018/2/1
 */
public class AlipayTradePagePayParam {

    private String outTradeNo;    //商户转账唯一订单号
    private String totalAmount;      //转账金额，单位：元。
    private String subject;   //主题
    private String passbackParams;//公用回传参数

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }
}
