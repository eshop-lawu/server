package com.lawu.eshop.game.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public class GameMindAnswerParam {

    @ApiModelProperty(value = "参与ID")
    private Long attendId;

    @ApiModelProperty(value = "题目ID")
    private Long questionId;

    @ApiModelProperty(value = "答案下标")
    private Integer rightAnswer;

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

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
}
