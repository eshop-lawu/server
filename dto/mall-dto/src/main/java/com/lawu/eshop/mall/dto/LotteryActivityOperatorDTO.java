package com.lawu.eshop.mall.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/11/24.
 */
public class LotteryActivityOperatorDTO {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "奖品价格")
    private BigDecimal prizePrice;

    @ApiModelProperty(value = "奖品数量")
    private Integer prizeNumber;

    @ApiModelProperty(value = "奖品图片")
    private String imagePath;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "等级")
    private MemberGradeEnum gradeEnum;

    @ApiModelProperty(value = "等级描述")
    private String gradeDes;

    @ApiModelProperty(value = "状态")
    private LotteryActivityStatusEnum statusEnum;

    @ApiModelProperty(value = "状态描述")
    private String statusDes;

    @ApiModelProperty(value = "开奖时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lotteryDate;

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

    public MemberGradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(MemberGradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
    }

    public String getGradeDes() {
        return gradeDes;
    }

    public void setGradeDes(String gradeDes) {
        this.gradeDes = gradeDes;
    }

    public LotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(LotteryActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public Date getLotteryDate() {
        return lotteryDate;
    }

    public void setLotteryDate(Date lotteryDate) {
        this.lotteryDate = lotteryDate;
    }
}
