package com.lawu.eshop.mall.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
public class LotteryRecordDTO {

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "奖品价值")
    private BigDecimal prizePrice;

    @ApiModelProperty(value = "奖品图片")
    private String imagePath;

    @ApiModelProperty(value = "参与人数")
    private Integer lotteryNumber;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "获奖人账号")
    private List<String> lotteryAccountList;

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
