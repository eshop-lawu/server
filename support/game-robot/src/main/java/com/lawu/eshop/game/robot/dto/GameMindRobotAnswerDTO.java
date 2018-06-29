package com.lawu.eshop.game.robot.dto;

/**
 * @author zhangyong
 * @date 2018/5/14.
 */
public class GameMindRobotAnswerDTO {

    /**
     * 题目ID
     */
    private Long questionId;

    /**
     * 答案
     */
    private Integer rightAnswer;

    /**
     * 用时
     */
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
