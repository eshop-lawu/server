package com.lawu.eshop.mq.dto.user;

import java.io.Serializable;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
public class CommentInfo implements Serializable {

    private static final long serialVersionUID = -5723296976237554244L;

    private Long merchantId;

    private Integer commentsCount;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(Integer commentsCount) {
        this.commentsCount = commentsCount;
    }
}
