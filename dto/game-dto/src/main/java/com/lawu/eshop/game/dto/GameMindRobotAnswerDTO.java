package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/5/14.
 */
public class GameMindRobotAnswerDTO {

    @ApiModelProperty(value = "題目ID")
    private Long questionId;

    @ApiModelProperty(value = "答题")
    private Integer rightAnswer;

    @ApiModelProperty(value = "用时")
    private Integer useTime;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }
}
