package com.lawu.eshop.game.srv.bo;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.common.constants.GameScoreLevelEnum;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public class GameMindResultBO {
    //答题是否正确 true:正确，false：错误
    private Boolean isTrue;

    //"分数"
    private Integer point;

    //GAMEPLAYSUCCESS：游戏成功，GAMEPLAYFAIL：游戏失败
    private GameAttendRecordStatusEnum status;

    //是否结束 true:结束，false：未结束"
    private Boolean isFinish;

    //奖励/扣除积分
    private Integer awardPoint;

    //奖励/扣除星星
    private Integer awardStar;

    private Integer totalScore;

    //正确下标
    private Integer rightAnswer;

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

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }


    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }
}
