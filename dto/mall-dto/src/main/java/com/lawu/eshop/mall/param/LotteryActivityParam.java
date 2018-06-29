package com.lawu.eshop.mall.param;

import java.math.BigDecimal;

import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
public class LotteryActivityParam {

    private Long id;

    private String prizeName;

    private BigDecimal prizePrice;

    private Integer prizeNumber;

    private String imagePath;

    private String beginTime;

    private String endTime;

    private Byte grade;

    private LotteryActivityStatusEnum statusEnum;

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

    public Integer getPrizeNumber() {
        return prizeNumber;
    }

    public void setPrizeNumber(Integer prizeNumber) {
        this.prizeNumber = prizeNumber;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public LotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(LotteryActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
