package com.lawu.eshop.activity.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
public class PointLotteryActivityOperatorDTO {

    @ApiModelProperty(value = "活动id")
    private Long id;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "奖品价格")
    private BigDecimal prizePrice;

    @ApiModelProperty(value = "奖品图片")
    private String prizeImagePath;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "抽奖时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date drawTime;

    @ApiModelProperty(value = "开奖时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lotteryTime;

    @ApiModelProperty(value = "抽奖积分")
    private Integer lotteryPoint;

    @ApiModelProperty(value = "中奖人数")
    private Integer lotteryCount;

    @ApiModelProperty(value = "活动状态")
    private PointLotteryActivityStatusEnum statusEnum;

    @ApiModelProperty(value = "状态描述")
    private String statusDes;

    @ApiModelProperty(value = "参与人数")
    private Integer attentNumber;

    @ApiModelProperty(value = "热度人数")
    private Integer hotNumber;

    @ApiModelProperty(value = "中奖结果编号")
    private String lotteryResultNums;
    
    /**
     * 基础中奖号码
     */
    @ApiModelProperty(value = "基础中奖号码")
    private Integer lotteryBaseNum;

    @ApiModelProperty(value = "活动介绍图片")
    private List<PointLotteryActivityPrizeImageDTO> lotteryDetailDTOS;

    @ApiModelProperty(value = "中奖信息图片")
    private List<PointLotteryActivityPrizeImageDTO> lotteryResultDTOS;

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

    public PointLotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(PointLotteryActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
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

    public String getLotteryResultNums() {
        return lotteryResultNums;
    }

    public void setLotteryResultNums(String lotteryResultNums) {
        this.lotteryResultNums = lotteryResultNums;
    }

    public Integer getLotteryBaseNum() {
        return lotteryBaseNum;
    }

    public void setLotteryBaseNum(Integer lotteryBaseNum) {
        this.lotteryBaseNum = lotteryBaseNum;
    }

    public List<PointLotteryActivityPrizeImageDTO> getLotteryDetailDTOS() {
        return lotteryDetailDTOS;
    }

    public void setLotteryDetailDTOS(List<PointLotteryActivityPrizeImageDTO> lotteryDetailDTOS) {
        this.lotteryDetailDTOS = lotteryDetailDTOS;
    }

    public List<PointLotteryActivityPrizeImageDTO> getLotteryResultDTOS() {
        return lotteryResultDTOS;
    }

    public void setLotteryResultDTOS(List<PointLotteryActivityPrizeImageDTO> lotteryResultDTOS) {
        this.lotteryResultDTOS = lotteryResultDTOS;
    }
}
