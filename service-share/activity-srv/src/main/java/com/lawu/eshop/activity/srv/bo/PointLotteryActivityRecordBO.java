package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;

/**
 * 积分夺宝活动参与记录分页BO
 * 
 * @author jiangxinjun
 * @createDate 2018年2月1日
 * @updateDate 2018年2月1日
 */
public class    PointLotteryActivityRecordBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 抽奖手机号
     */
    private String mobile;

    /**
     * 抽奖号码
     */
    private Integer lotteryNum;

    private Long pointLotteryActivityId;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 状态
     */
    private PointLotteryActivityRecordStatusEnum status;

    private Date gmtModified;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getLotteryNum() {
        return lotteryNum;
    }

    public void setLotteryNum(Integer lotteryNum) {
        this.lotteryNum = lotteryNum;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public PointLotteryActivityRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PointLotteryActivityRecordStatusEnum status) {
        this.status = status;
    }

    public Long getPointLotteryActivityId() {
        return pointLotteryActivityId;
    }

    public void setPointLotteryActivityId(Long pointLotteryActivityId) {
        this.pointLotteryActivityId = pointLotteryActivityId;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}