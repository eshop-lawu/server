package com.lawu.eshop.user.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiParam;

public class UserGradeUpdateParam {

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getGradeWeight() {
        return gradeWeight;
    }

    public void setGradeWeight(Integer gradeWeight) {
        this.gradeWeight = gradeWeight;
    }

    public Integer getMinGrowthValue() {
        return minGrowthValue;
    }

    public void setMinGrowthValue(Integer minGrowthValue) {
        this.minGrowthValue = minGrowthValue;
    }

    public Integer getLotteryActivityPoint() {
        return lotteryActivityPoint;
    }

    public void setLotteryActivityPoint(Integer lotteryActivityPoint) {
        this.lotteryActivityPoint = lotteryActivityPoint;
    }

    public Integer getNextFreeLotteryCount() {
        return nextFreeLotteryCount;
    }

    public void setNextFreeLotteryCount(Integer nextFreeLotteryCount) {
        this.nextFreeLotteryCount = nextFreeLotteryCount;
    }

    @NotBlank(message="会员名称不能为空")
    @ApiParam(name = "gradeName", value = "会员名称")
    private String gradeName;

    @NotNull(message="等级权值不能为空")
    @Min(value=1,message = "等级权值不能小于或等于0")
    @ApiParam(name = "gradeWeight", value = "等级权值")
    private Integer gradeWeight;

    @NotNull(message="最小成长值不能为空")
    @Min(value=1,message = "最小成长值不能小于或等于0")
    @ApiParam(name = "minGrowthValue", value = "最小成长值")
    private Integer minGrowthValue;

    @NotNull(message="下次抽奖次数不能为空")
    @Min(value=0,message = "下次抽奖次数不能小于0")
    @ApiParam(name = "nextFreeLotteryCount", value = "下次抽奖次数")
    private Integer nextFreeLotteryCount;

    @NotNull(message="抽奖活动兑换积分不能为空")
    @Min(value=1,message = "抽奖活动兑换积分不能小于或等于0")
    @ApiParam(name = "lotteryActivityPoint", value = "抽奖活动兑换积分")
    private Integer lotteryActivityPoint;

}
