package com.lawu.eshop.game.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public class GameMindAnswerQuestionParam {

    @ApiModelProperty(value = "参与ID")
    private Long attendId;

    @ApiModelProperty(value = "题目ID")
    private Long questionId;

    @ApiModelProperty(value = "答案下标")
    private Integer rightAnswer;

    @ApiModelProperty(value = "题目列表")
    private String questionIds;

    @ApiModelProperty(value = "用时")
    private Integer userTime;

    @ApiModelProperty(value = "编号")
    private String userNum;

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

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }

    public Integer getUserTime() {
        return userTime;
    }

    public void setUserTime(Integer userTime) {
        this.userTime = userTime;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }
}
