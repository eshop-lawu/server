package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

import com.lawu.eshop.activity.constants.AbnormalStatusEnum;

/**
 * @author zhangyong
 * @date 2018/3/1.
 */
public class SignAbnormalAccountBO {

    private Long id;

    private Date attendTime;

    private Integer activityId;

    private String account;

    private String userNum;

    private Integer helpCount;

    private AbnormalStatusEnum statusEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Integer getHelpCount() {
        return helpCount;
    }

    public void setHelpCount(Integer helpCount) {
        this.helpCount = helpCount;
    }

    public AbnormalStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(AbnormalStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
