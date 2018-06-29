package com.lawu.eshop.activity.srv.bo;

import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityBO {

    private Long id;

    private String name;

    private String imgPath;

    private Byte grade;

    private Byte status;

    private String remark;

    private Date beginTime;

    private Date endTime;

    private Date gmtModified;

    private Date gmtCreate;

    private Integer prizeNumber;

    private Integer freeLotteryCount;

    private Byte lotteryChannel;

    private List<DrawLotteryActivityPrizeBO> prizeBOS;

    private List<LotteryInfoBO> lotteryInfoBOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Integer getPrizeNumber() {
        return prizeNumber;
    }

    public void setPrizeNumber(Integer prizeNumber) {
        this.prizeNumber = prizeNumber;
    }

    public Integer getFreeLotteryCount() {
        return freeLotteryCount;
    }

    public void setFreeLotteryCount(Integer freeLotteryCount) {
        this.freeLotteryCount = freeLotteryCount;
    }

    public Byte getLotteryChannel() {
        return lotteryChannel;
    }

    public void setLotteryChannel(Byte lotteryChannel) {
        this.lotteryChannel = lotteryChannel;
    }

    public List<DrawLotteryActivityPrizeBO> getPrizeBOS() {
        return prizeBOS;
    }

    public void setPrizeBOS(List<DrawLotteryActivityPrizeBO> prizeBOS) {
        this.prizeBOS = prizeBOS;
    }

    public List<LotteryInfoBO> getLotteryInfoBOS() {
        return lotteryInfoBOS;
    }

    public void setLotteryInfoBOS(List<LotteryInfoBO> lotteryInfoBOS) {
        this.lotteryInfoBOS = lotteryInfoBOS;
    }
}
