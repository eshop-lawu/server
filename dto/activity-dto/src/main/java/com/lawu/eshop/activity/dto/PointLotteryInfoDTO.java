package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public class PointLotteryInfoDTO {

    @ApiModelProperty(value = "活动id")
    private Long id;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "奖品价格")
    private BigDecimal prizePrice;

    @ApiModelProperty(value = "奖品图片")
    private String prizeImagePath;

    @ApiModelProperty(value = "抽奖积分")
    private Integer lotteryPoint;

    @ApiModelProperty(value = "参与人数")
    private Integer attentNumber;

    @ApiModelProperty(value = "热门活动")
    private Boolean isHot;

    @ApiModelProperty(value = "距离开始天数")
    private Integer beginDays;

    @ApiModelProperty(value = "距离开始毫秒数")
    private Long beginMillisecond;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private Date beginTime;

    @ApiModelProperty(value = "距离结束天数")
    private Integer endDays;

    @ApiModelProperty(value = "距离结束毫秒数")
    private Long endMillisecond;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "活动状态:UNPUBLISHED--未发布，PROCESSING--进行中，PUBLISHED--即将开始，PARTICIPATION_END--已结束，ALREADY_LOTTERY--已开奖，DELETE--删除")
    private PointLotteryActivityStatusEnum statusEnum;

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

    public Integer getLotteryPoint() {
        return lotteryPoint;
    }

    public void setLotteryPoint(Integer lotteryPoint) {
        this.lotteryPoint = lotteryPoint;
    }

    public Integer getAttentNumber() {
        return attentNumber;
    }

    public void setAttentNumber(Integer attentNumber) {
        this.attentNumber = attentNumber;
    }

    public Boolean getHot() {
        return isHot;
    }

    public void setHot(Boolean hot) {
        isHot = hot;
    }

    public Integer getBeginDays() {
        return beginDays;
    }

    public void setBeginDays(Integer beginDays) {
        this.beginDays = beginDays;
    }

    public Long getBeginMillisecond() {
        return beginMillisecond;
    }

    public void setBeginMillisecond(Long beginMillisecond) {
        this.beginMillisecond = beginMillisecond;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndDays() {
        return endDays;
    }

    public void setEndDays(Integer endDays) {
        this.endDays = endDays;
    }

    public Long getEndMillisecond() {
        return endMillisecond;
    }

    public void setEndMillisecond(Long endMillisecond) {
        this.endMillisecond = endMillisecond;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public PointLotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(PointLotteryActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
