package com.lawu.eshop.property.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;

public class TransactionDetailH5InfoBO {

    /**
     * 交易标题
     */
    private String title;

    /**
     * 交易编号
     */
    private String transactionNum;

    /**
     * 交易类型(用户（1-余额充值2-广告3-扫红包4-退款5-下级收益6-买单7-付商品订单8-积分充值9-提现）商家（100-买单101-订单102-下级收益103-余额充值104-投放广告105-积分充值106-退款107-提现）)
     */
    private Byte transactionType;

    /**
     * 支付方式(支付方式、退款方式、提现账户)
     */
    private String otherDesc;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 资金流向（1-支出|2-收入）
     */
    private ConsumptionTypeEnum direction;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    private String transactionDesc;

    private String userNum;

    //用于查询订单积分抵扣时查询
    private String bizId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public Byte getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Byte transactionType) {
        this.transactionType = transactionType;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ConsumptionTypeEnum getDirection() {
        return direction;
    }

    public void setDirection(ConsumptionTypeEnum direction) {
        this.direction = direction;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }
}
