package com.lawu.eshop.activity.query;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/1/16.
 */
public class OperatorDrawLotteryActivityPrizeQuery extends AbstractPageParam {

    private String name;

    private Long drawLotteryActivityId;

    private DrawLotteryActivityPrizeStatusEnum statusEnum;

    private String sortName;

    private String sortOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDrawLotteryActivityId() {
        return drawLotteryActivityId;
    }

    public void setDrawLotteryActivityId(Long drawLotteryActivityId) {
        this.drawLotteryActivityId = drawLotteryActivityId;
    }

    public DrawLotteryActivityPrizeStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityPrizeStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
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
