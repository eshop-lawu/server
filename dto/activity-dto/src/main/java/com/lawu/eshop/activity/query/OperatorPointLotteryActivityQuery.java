package com.lawu.eshop.activity.query;

import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
public class OperatorPointLotteryActivityQuery extends AbstractPageParam {

    private String prizeName;

    private String beginTime;

    private String endTime;

    private PointLotteryActivityStatusEnum statusEnum;

    private String sortName;

    private String sortOrder;

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
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

    public PointLotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(PointLotteryActivityStatusEnum statusEnum) {
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
