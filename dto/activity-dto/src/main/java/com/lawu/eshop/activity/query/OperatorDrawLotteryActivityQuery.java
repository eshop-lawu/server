package com.lawu.eshop.activity.query;

import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/1/16.
 */
public class OperatorDrawLotteryActivityQuery extends AbstractPageParam {

    private String name;

    private String beginTime;

    private String endTime;

    private DrawLotteryActivityStatusEnum statusEnum;

    private String sortName;

    private String sortOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public DrawLotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityStatusEnum statusEnum) {
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
