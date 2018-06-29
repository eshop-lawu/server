package com.lawu.eshop.game.robot.param;

/**
 * 头脑PK提交参数
 * 
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
public class MindPkSubmitParam {
    
    /**
     * 问题id
     */
    private Long questionId;
    
    /**
     * 答案(下标)
     */
    private Integer rightAnswer;

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
