package com.lawu.eshop.game.robot.param;

/**
 * @author zhangyong
 * @date 2018/5/14.
 */
public class VerifyAnswerParam {
    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 问题id
     */
    private Long questionId;

    /**
     * 答案
     */
    private Integer rightAnswer;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
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
