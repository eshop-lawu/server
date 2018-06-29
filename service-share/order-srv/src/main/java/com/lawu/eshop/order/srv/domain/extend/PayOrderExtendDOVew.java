package com.lawu.eshop.order.srv.domain.extend;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/7/28.
 */
public class PayOrderExtendDOVew implements Serializable{
    private static final long serialVersionUID = -2632446283901127581L;
    /**
     *
     *
     * pay_order.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 用户ID
     * pay_order.member_id
     *
     * @mbg.generated
     */
    private Long memberId;

    /**
     *
     * 用户编号
     * pay_order.member_num
     *
     * @mbg.generated
     */
    private String memberNum;

    /**
     *
     * 商家ID
     * pay_order.merchant_id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     *
     * 商家编号
     * pay_order.merchant_num
     *
     * @mbg.generated
     */
    private String merchantNum;

    /**
     *
     * 买单编号
     * pay_order.order_num
     *
     * @mbg.generated
     */
    private String orderNum;

    /**
     *
     * 第三方支付编号
     * pay_order.third_number
     *
     * @mbg.generated
     */
    private String thirdNumber;

    /**
     *
     * 原价
     * pay_order.total_amount
     *
     * @mbg.generated
     */
    private BigDecimal totalAmount;

    /**
     *
     * 实际消费
     * pay_order.actual_amount
     *
     * @mbg.generated
     */
    private BigDecimal actualAmount;

    /**
     *
     * 优惠金额
     * pay_order.favored_amount
     *
     * @mbg.generated
     */
    private BigDecimal favoredAmount;

    /**
     *
     * 不参与优惠金额
     * pay_order.not_favored_amount
     *
     * @mbg.generated
     */
    private BigDecimal notFavoredAmount;

    /**
     *
     * 支付类型
     * pay_order.pay_type
     *
     * @mbg.generated
     */
    private Byte payType;

    /**
     *
     * 评价：0未评1已评
     * pay_order.is_evaluation
     *
     * @mbg.generated
     */
    private Boolean isEvaluation;

    /**
     *
     * 状态0：删除1-待支付,2-成功,3-失败
     * pay_order.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * 状态(0删除1正常)
     * pay_order.order_status
     *
     * @mbg.generated
     */
    private Boolean orderStatus;

    /**
     *
     * 是否计算过提成(0-没有计算过提成|1-计算过提成)
     * pay_order.commission_status
     *
     * @mbg.generated
     */
    private Byte commissionStatus;

    /**
     *
     * 评价时间
     * pay_order.comment_time
     *
     * @mbg.generated
     */
    private Date commentTime;

    /**
     *
     * 修改时间
     * pay_order.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * pay_order.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     *
     * 计算提成时间
     * pay_order.gmt_commission
     *
     * @mbg.generated
     */
    private Date gmtCommission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getFavoredAmount() {
        return favoredAmount;
    }

    public void setFavoredAmount(BigDecimal favoredAmount) {
        this.favoredAmount = favoredAmount;
    }

    public BigDecimal getNotFavoredAmount() {
        return notFavoredAmount;
    }

    public void setNotFavoredAmount(BigDecimal notFavoredAmount) {
        this.notFavoredAmount = notFavoredAmount;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Boolean getEvaluation() {
        return isEvaluation;
    }

    public void setEvaluation(Boolean evaluation) {
        isEvaluation = evaluation;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Byte getCommissionStatus() {
        return commissionStatus;
    }

    public void setCommissionStatus(Byte commissionStatus) {
        this.commissionStatus = commissionStatus;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCommission() {
        return gmtCommission;
    }

    public void setGmtCommission(Date gmtCommission) {
        this.gmtCommission = gmtCommission;
    }
}
