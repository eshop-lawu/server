package com.lawu.eshop.cache.dto;

/**
 * 单人答案DTO
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
public class SingleAnswerDTO {
    
    /**
     * 累计游戏得分
     */
    private Integer accumulatedGameScore;
    
    /**
     * 是否答对
     */
    private Boolean flag;
    
    /**
     * 正确答案(答案下标)
     */
    private Integer rightAnswer;
    
    public Integer getAccumulatedGameScore() {
        return accumulatedGameScore;
    }

    public void setAccumulatedGameScore(Integer accumulatedGameScore) {
        this.accumulatedGameScore = accumulatedGameScore;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Integer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Integer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
    
}
