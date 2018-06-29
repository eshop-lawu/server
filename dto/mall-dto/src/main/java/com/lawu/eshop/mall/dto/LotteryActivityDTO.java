package com.lawu.eshop.mall.dto;

import java.math.BigDecimal;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2017/11/23.
 */
public class LotteryActivityDTO {

    @ApiModelProperty(value = "抽奖活动ID")
    private Long LotteryActivityId;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "奖品价格")
    private BigDecimal prizePrice;

    @ApiModelProperty(value = "奖品数量")
    private Integer prizeNumber;

    @ApiModelProperty(value = "奖品图片")
    private String imagePath;

    @ApiModelProperty(value = "距离开始毫秒数")
    private Long startMillisecond;

    @ApiModelProperty(value = "距离结束毫秒数")
    private Long millisecond;

    @ApiModelProperty(value = "等级")
    private MemberGradeEnum gradeEnum;

    @ApiModelProperty(value = "等级")
    private Byte grade;

    @ApiModelProperty(value = "状态")
    private LotteryActivityStatusEnum statusEnum;

    @ApiModelProperty(value = "参与次数")
    private Integer lotteryCount;

    @ApiModelProperty(value = "参与人数")
    private Integer lotteryNumber;

    @ApiModelProperty(value = "兑换积分")
    private Integer point;

    public Long getLotteryActivityId() {
        return LotteryActivityId;
    }

    public void setLotteryActivityId(Long lotteryActivityId) {
        LotteryActivityId = lotteryActivityId;
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

    public Long getStartMillisecond() {
        return startMillisecond;
    }

    public void setStartMillisecond(Long startMillisecond) {
        this.startMillisecond = startMillisecond;
    }

    public Long getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(Long millisecond) {
        this.millisecond = millisecond;
    }

    public MemberGradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(MemberGradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
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

    public Integer getLotteryCount() {
        return lotteryCount;
    }

    public void setLotteryCount(Integer lotteryCount) {
        this.lotteryCount = lotteryCount;
    }

    public Integer getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(Integer lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
