package com.lawu.eshop.activity.query;

import com.lawu.eshop.activity.constants.DrawLotteryActivityRecordStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2018/1/17.
 */
public class OperatorDrawLotteryActivityRecordQuery extends AbstractPageParam {

    private String userAccount;

    private String prizeName;

    private String consigneeName;

    private String consigneeMobile;

    private Long drawLotteryActivityId;

    private DrawLotteryActivityRecordStatusEnum statusEnum;

    private String sortName;

    private String sortOrder;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public Long getDrawLotteryActivityId() {
        return drawLotteryActivityId;
    }

    public void setDrawLotteryActivityId(Long drawLotteryActivityId) {
        this.drawLotteryActivityId = drawLotteryActivityId;
    }

    public DrawLotteryActivityRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityRecordStatusEnum statusEnum) {
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
