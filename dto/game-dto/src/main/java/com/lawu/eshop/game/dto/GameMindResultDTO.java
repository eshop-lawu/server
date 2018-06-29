package com.lawu.eshop.game.dto;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public class GameMindResultDTO {

    @ApiModelProperty(value = "答题是否正确 true:正确，false：错误")
    private Boolean isTrue;

    @ApiModelProperty(value = "正确答案下标")
    private Integer rightAnwser;

    @ApiModelProperty(value = "分数")
    private Integer point;

    @ApiModelProperty(value = "GAMEPLAYSUCCESS：游戏成功，GAMEPLAYFAIL：游戏失败")
    private GameAttendRecordStatusEnum status;
    
    @ApiModelProperty(value = "是否结束 true:结束，false：未结束")
    private Boolean isFinish;

    //奖励/扣除积分
    @ApiModelProperty(value = "奖励/扣除积分")
    private Integer awardPoint;

    //奖励/扣除星星
    @ApiModelProperty(value = "奖励/扣除星星")
    private Integer awardStar;

    @ApiModelProperty(value = "最后一题返回总分数")
    private Integer totalScore;

    @ApiModelProperty(value = "参与编号")
    private String attendNum;

    public Boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public GameAttendRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GameAttendRecordStatusEnum status) {
        this.status = status;
    }

    public Boolean getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Boolean finish) {
        isFinish = finish;
    }

    public Integer getAwardPoint() {
        return awardPoint;
    }

    public void setAwardPoint(Integer awardPoint) {
        this.awardPoint = awardPoint;
    }

    public Integer getAwardStar() {
        return awardStar;
    }

    public void setAwardStar(Integer awardStar) {
        this.awardStar = awardStar;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getRightAnwser() {
        return rightAnwser;
    }

    public void setRightAnwser(Integer rightAnwser) {
        this.rightAnwser = rightAnwser;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }
}
