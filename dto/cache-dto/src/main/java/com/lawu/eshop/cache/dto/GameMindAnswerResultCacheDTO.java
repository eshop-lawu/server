package com.lawu.eshop.cache.dto;

import java.io.Serializable;

/**
 * 头脑PK用户答题结果缓存DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
public class GameMindAnswerResultCacheDTO implements Serializable {
    
    private static final long serialVersionUID = -8393752851490703049L;

    /**
     * 问题id
     */
    private Long questionId;
    
    /**
     * 答案(下标)
     */
    private Integer rightAnswer;
    
    /**
     * 是否正确
     */
    private Boolean flag;
    
    /**
     * 题目得分
     */
    private Integer point;
    
    /**
     * 使用时间
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

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getUseTime() {
        return useTime;
    }

    public void setUseTime(Integer useTime) {
        this.useTime = useTime;
    }
    
}
