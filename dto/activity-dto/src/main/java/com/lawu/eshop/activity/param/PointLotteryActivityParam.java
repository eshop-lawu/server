package com.lawu.eshop.activity.param;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
public class PointLotteryActivityParam {

    private Long id;

    private String prizeName;

    private BigDecimal prizePrice;

    private String prizeImagePath;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lotteryTime;

    private Integer lotteryPoint;

    private Integer lotteryCount;

    private Integer hotNumber;

    private PointLotteryActivityStatusEnum statusEnum;

    private String imagePath;

    private String orderNum;

    private String type;

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

    public Integer getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(Integer hotNumber) {
        this.hotNumber = hotNumber;
    }

    public PointLotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(PointLotteryActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
