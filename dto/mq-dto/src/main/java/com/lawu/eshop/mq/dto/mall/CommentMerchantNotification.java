package com.lawu.eshop.mq.dto.mall;


import com.lawu.compensating.transaction.Notification;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/4/7.
 */
public class CommentMerchantNotification extends Notification {

	private static final long serialVersionUID = 8578002481915305012L;
	
	@ApiModelProperty(value = "买单ID")
    private Long payOrderId;

    @ApiModelProperty(value = "评论总数")
	private Integer commentsCount;

    public Long getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(Long payOrderId) {
        this.payOrderId = payOrderId;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }
}
