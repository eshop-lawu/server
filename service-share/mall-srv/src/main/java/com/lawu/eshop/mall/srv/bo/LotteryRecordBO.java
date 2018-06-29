package com.lawu.eshop.mall.srv.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
public class LotteryRecordBO {

    private String prizeName;

    private BigDecimal prizePrice;

    private String imagePath;

    private Integer lotteryNumber;

    private Date endTime;

    List<String> lotteryAccountList;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(Integer lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getLotteryAccountList() {
        return lotteryAccountList;
    }

    public void setLotteryAccountList(List<String> lotteryAccountList) {
        this.lotteryAccountList = lotteryAccountList;
    }
}
