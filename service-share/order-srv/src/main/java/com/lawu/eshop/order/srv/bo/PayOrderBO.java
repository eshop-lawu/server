package com.lawu.eshop.order.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public class PayOrderBO {
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
     * 商家ID
     * pay_order.merchant_id
     *
     * @mbg.generated
     */
    private Long merchantId;

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

    private String orderNum;

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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
