package com.lawu.eshop.mall.query;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2017/11/27.
 */
public class OperatorLotteryRecordQuery extends AbstractPageParam {

    private Long lotteryActivityId;

    private Boolean lotteryResult;

    private String sortName;

    private String sortOrder;

    public Long getLotteryActivityId() {
        return lotteryActivityId;
    }

    public void setLotteryActivityId(Long lotteryActivityId) {
        this.lotteryActivityId = lotteryActivityId;
    }

    public Boolean getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(Boolean lotteryResult) {
        this.lotteryResult = lotteryResult;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
