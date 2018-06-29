package com.lawu.eshop.mall.query;

import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
public class ListLotteryActivityQuery extends AbstractPageParam {

    private String prizeName;

    private String beginTime;

    private String endTime;

    private LotteryActivityStatusEnum statusEnum;

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

    public LotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(LotteryActivityStatusEnum statusEnum) {
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
