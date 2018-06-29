package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public class PointLotteryActivityBO {

    private Long id;

    private String prizeName;

    private BigDecimal prizePrice;

    private String prizeImagePath;

    private Date beginTime;

    private Date endTime;

    private Date drawTime;

    private Date lotteryTime;

    private Integer lotteryPoint;

    private Integer lotteryCount;

    private Byte status;

    private Integer attentNumber;

    private Integer hotNumber;

    private String lotteryParam;

    private Integer lotteryBaseNum;

    private String lotteryResultNums;

    private Date gmtModified;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public BigDecimal getPrizePrice() {
        return prizePrice;
    }

    public void setPrizePrice(BigDecimal prizePrice) {
        this.prizePrice = prizePrice;
    }

    public String getPrizeImagePath() {
        return prizeImagePath;
    }

    public void setPrizeImagePath(String prizeImagePath) {
        this.prizeImagePath = prizeImagePath;
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

    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
    }

    public Date getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }

    public Integer getLotteryPoint() {
        return lotteryPoint;
    }

    public void setLotteryPoint(Integer lotteryPoint) {
        this.lotteryPoint = lotteryPoint;
    }

    public Integer getLotteryCount() {
        return lotteryCount;
    }

    public void setLotteryCount(Integer lotteryCount) {
        this.lotteryCount = lotteryCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getAttentNumber() {
        return attentNumber;
    }

    public void setAttentNumber(Integer attentNumber) {
        this.attentNumber = attentNumber;
    }

    public Integer getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(Integer hotNumber) {
        this.hotNumber = hotNumber;
    }

    public String getLotteryParam() {
        return lotteryParam;
    }

    public void setLotteryParam(String lotteryParam) {
        this.lotteryParam = lotteryParam;
    }

    public Integer getLotteryBaseNum() {
        return lotteryBaseNum;
    }

    public void setLotteryBaseNum(Integer lotteryBaseNum) {
        this.lotteryBaseNum = lotteryBaseNum;
    }

    public String getLotteryResultNums() {
        return lotteryResultNums;
    }

    public void setLotteryResultNums(String lotteryResultNums) {
        this.lotteryResultNums = lotteryResultNums;
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
