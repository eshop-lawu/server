package com.lawu.eshop.cache.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 验证答案DTO
 * @author jiangxinjun
 * @createDate 2018年3月15日
 * @updateDate 2018年3月15日
 */
public class VerifyAnswerDTO {
    
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
    
    /**
     * 是否是最后提交答案的
     */
    private Boolean isLastAnswer;
    
    /**
     * 是否是最后最后一道题
     */
    private Boolean isLastQuestion;
    
    /**
     * 如果全部题目都已经答完, 返回用户排名
     */
    private List<GameMindRankDTO> ranks;
    
    /**
     * 如果最后一个人答题, 返回所有用户选择的答案和分数
     */
    private List<MultiplayerSubmitScoresDTO> submitScores;

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

    public Boolean getIsLastAnswer() {
        return isLastAnswer;
    }

    public void setIsLastAnswer(Boolean isLastAnswer) {
        this.isLastAnswer = isLastAnswer;
    }

    public Boolean getIsLastQuestion() {
        return isLastQuestion;
    }

    public void setIsLastQuestion(Boolean isLastQuestion) {
        this.isLastQuestion = isLastQuestion;
    }

    public List<GameMindRankDTO> getRanks() {
        return ranks;
    }

    public void setRanks(List<GameMindRankDTO> ranks) {
        this.ranks = ranks;
    }

    public List<MultiplayerSubmitScoresDTO> getSubmitScores() {
        return submitScores;
    }

    public void setSubmitScores(List<MultiplayerSubmitScoresDTO> submitScores) {
        this.submitScores = submitScores;
    }
    
}
