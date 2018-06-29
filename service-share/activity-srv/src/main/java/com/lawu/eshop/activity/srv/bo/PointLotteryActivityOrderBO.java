package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
public class PointLotteryActivityOrderBO {

    private Long id;

    private String userNum;

    private String mobile;

    private Long pointLotteryActivityId;

    private Integer attentCount;

    private BigDecimal payPoint;

    private Byte status;

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

    public Long getPointLotteryActivityId() {
        return pointLotteryActivityId;
    }

    public void setPointLotteryActivityId(Long pointLotteryActivityId) {
        this.pointLotteryActivityId = pointLotteryActivityId;
    }

    public Integer getAttentCount() {
        return attentCount;
    }

    public void setAttentCount(Integer attentCount) {
        this.attentCount = attentCount;
    }

    public BigDecimal getPayPoint() {
        return payPoint;
    }

    public void setPayPoint(BigDecimal payPoint) {
        this.payPoint = payPoint;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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