package com.lawu.eshop.cache.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK提交成绩DTO
 * @author jiangxinjun
 * @createDate 2018年3月19日
 * @updateDate 2018年3月19日
 */
@ApiModel("头脑PK提交成绩DTO")
public class MindPkSubmitDTO {
    
    /**
     * 当前提交答案用户编号
     */
    @ApiModelProperty(value = "当前提交答案用户编号", required = true)
    private String currentSubmitUserNum;
    
    /**
     * 累计游戏得分
     */
    @ApiModelProperty(value = "累计游戏得分", required = true)
    private Integer accumulatedGameScore;
    
    /**
     * 选择的答案
     */
    @ApiModelProperty(value = "选择的答案", required = true)
    private Integer selectedAnswer;
    
    /**
     * 如果全部题目都已经答完, 返回用户排名
     */
    @ApiModelProperty(value = "用户排名", required = false)
    private List<GameMindRankDTO> ranks;
    
    /**
     * 如果最后一个人答题, 返回所有用户选择的答案和分数
     */
    @ApiModelProperty(value = "多个用户提交成绩", required = false)
    private List<MultiplayerSubmitScoresDTO> submitScores;
    
    public String getCurrentSubmitUserNum() {
        return currentSubmitUserNum;
    }

    public void setCurrentSubmitUserNum(String currentSubmitUserNum) {
        this.currentSubmitUserNum = currentSubmitUserNum;
    }

    public Integer getAccumulatedGameScore() {
        return accumulatedGameScore;
    }

    public void setAccumulatedGameScore(Integer accumulatedGameScore) {
        this.accumulatedGameScore = accumulatedGameScore;
    }

    public Integer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Integer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
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
