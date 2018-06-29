package com.lawu.eshop.property.srv.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;

public class TransactionDetailBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
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
     * 用户编号
     */
    private String userNum;

    /**
     * 交易类型(用户（1-余额充值2-广告3-扫红包4-退款5-下级收益6-买单7-付商品订单8-积分充值9-提现）商家（100-买单101-订单102-下级收益103-余额充值104-投放广告105-积分充值106-退款107-提现）)
     */
    private Byte transactionType;

    /**
     * 第三方账户(如果是余额记账号，第三方记第三方账号)
     */
    private String transactionAccount;

    /**
     * 支付方式
     */
    private TransactionPayTypeEnum transactionAccountType;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 资金流向
     */
    private ConsumptionTypeEnum direction;

    /**
     * 第三方支付交易号
     */
    private String thirdTransactionNum;

    /**
     * 业务类型操作对应的业务表ID
     */
    private String bizId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public TransactionPayTypeEnum getTransactionAccountType() {
		return transactionAccountType;
	}

	public void setTransactionAccountType(TransactionPayTypeEnum transactionAccountType) {
		this.transactionAccountType = transactionAccountType;
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

	public String getThirdTransactionNum() {
		return thirdTransactionNum;
	}

	public void setThirdTransactionNum(String thirdTransactionNum) {
		this.thirdTransactionNum = thirdTransactionNum;
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

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
}