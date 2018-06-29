package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.CommentAnonymousEnum;
import com.lawu.eshop.mall.constants.CommentGradeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public class CommentMerchantParam {
    /**
     * 评价内容
     */
    @ApiModelProperty(value = "评价内容", required = true)
    private String content;

    /**
     * 评分
     */
    @ApiModelProperty(value = "评分", required = true)
    private CommentGradeEnum gradeEnum;

    /**
     * 商家ID
     */
    @ApiModelProperty(value = "商家ID", required = true)
    private Long merchantId;
    /**
     * 订单ID
     */
    @ApiModelProperty(value = "买单ID", required = true)
    private Long orderId;

    /**
     * 是否匿名（0：否1：是）
     */
    @ApiModelProperty(value = "是否匿名（UN_COMMENT_ANONYMOUS：否 COMMENT_ANONYMOUS：是）", required = true)
    private CommentAnonymousEnum anonymousEnum;

    @ApiModelProperty(value = "人均消费", required = true)
    private BigDecimal avgSpend;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentGradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(CommentGradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public CommentAnonymousEnum getAnonymousEnum() {
        return anonymousEnum;
    }

    public void setAnonymousEnum(CommentAnonymousEnum anonymousEnum) {
        this.anonymousEnum = anonymousEnum;
    }

    public BigDecimal getAvgSpend() {
        return avgSpend;
    }

    public void setAvgSpend(BigDecimal avgSpend) {
        this.avgSpend = avgSpend;
    }
}
