package com.lawu.eshop.game.srv.bo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
public class GameQuestionResultBO {

    @ApiModelProperty(value = "参与Id,多个用户逗号隔开")
    private String attendIds;

    @ApiModelProperty(value = "题目列表")
    private List<GameMindQuestionBO> questionBOS;

    @ApiModelProperty(value = "倒计时时间/秒")
    private Integer countDown;

    @ApiModelProperty(value = "题目总分数")
    private Integer score;

    @ApiModelProperty(value = "挑战成功分数")
    private Integer successScore;

    public String getAttendIds() {
        return attendIds;
    }

    public void setAttendIds(String attendIds) {
        this.attendIds = attendIds;
    }

    public List<GameMindQuestionBO> getQuestionBOS() {
        return questionBOS;
    }

    public void setQuestionBOS(List<GameMindQuestionBO> questionBOS) {
        this.questionBOS = questionBOS;
    }

    public Integer getCountDown() {
        return countDown;
    }

    public void setCountDown(Integer countDown) {
        this.countDown = countDown;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSuccessScore() {
        return successScore;
    }

    public void setSuccessScore(Integer successScore) {
        this.successScore = successScore;
    }
}
