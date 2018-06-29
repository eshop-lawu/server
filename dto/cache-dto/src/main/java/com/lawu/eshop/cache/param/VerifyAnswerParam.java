package com.lawu.eshop.cache.param;

/**
 * 验证用户提交的答案
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
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
