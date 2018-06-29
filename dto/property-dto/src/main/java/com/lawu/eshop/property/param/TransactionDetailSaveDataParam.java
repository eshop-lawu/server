package com.lawu.eshop.property.param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月6日 下午12:58:39
 */
public class TransactionDetailSaveDataParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 交易标题
     */
    private String title;

    /**
     * 交易编号
     */
    private String transactionNum;

    /**
     * 用户编号 transaction_detail.user_num
     *
     * @mbg.generated
     */
    private String userNum;

    /**
     * 交易类型(取枚举)
     */
    private Byte transactionType;

    /**
     * 第三方账户(如果是余额记账号，第三方记第三方账号)
     */
    private String transactionAccount;

    /**
     * 支付方式(1-余额2-支付宝3微信)
     */
    private Byte transactionAccountType;

    private String thirdTransactionNum;

    /**
     * 金额
     */
    private BigDecimal amount;

    private BigDecimal previousAmount;

    /**
     * 业务类型操作对应的业务表ID
     */
    private String bizId;

    /**
     * 备注
     */
    private String remark;

    private Byte direction;

    private String bizNum;

    //省市区ID
    private String regionPath;

    private String transactionDesc;

    //主事务执行时间
    private Date gmtExecute;

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

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Byte getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Byte transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionAccount() {
        return transactionAccount;
    }

    public void setTransactionAccount(String transactionAccount) {
        this.transactionAccount = transactionAccount;
    }

    public Byte getTransactionAccountType() {
        return transactionAccountType;
    }

    public void setTransactionAccountType(Byte transactionAccountType) {
        this.transactionAccountType = transactionAccountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThirdTransactionNum() {
        return thirdTransactionNum;
    }

    public void setThirdTransactionNum(String thirdTransactionNum) {
        this.thirdTransactionNum = thirdTransactionNum;
    }

    public Byte getDirection() {
        return direction;
    }

    public void setDirection(Byte direction) {
        this.direction = direction;
    }

    public String getBizNum() {
        return bizNum;
    }

    public void setBizNum(String bizNum) {
        this.bizNum = bizNum;
    }

    public BigDecimal getPreviousAmount() {
        return previousAmount;
    }

    public void setPreviousAmount(BigDecimal previousAmount) {
        this.previousAmount = previousAmount;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    public Date getGmtExecute() {
        return gmtExecute;
    }

    public void setGmtExecute(Date gmtExecute) {
        this.gmtExecute = gmtExecute;
    }
}