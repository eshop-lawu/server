package com.lawu.eshop.game.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
public class GameMindQuestionResultDTO {

    @ApiModelProperty(value = "参与ID,多个用逗号隔开")
    private String attendIds;

    private List<GameRandomQuestionDTO> questionDTOS;

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

    public List<GameRandomQuestionDTO> getQuestionDTOS() {
        return questionDTOS;
    }

    public void setQuestionDTOS(List<GameRandomQuestionDTO> questionDTOS) {
        this.questionDTOS = questionDTOS;
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
