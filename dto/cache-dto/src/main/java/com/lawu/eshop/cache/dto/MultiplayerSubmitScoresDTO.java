package com.lawu.eshop.cache.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK多人提交成绩DTO
 * @author jiangxinjun
 * @createDate 2018年3月19日
 * @updateDate 2018年3月19日
 */
@ApiModel("头脑PK提交成绩DTO")
public class MultiplayerSubmitScoresDTO {
    
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required = true)
    private String userNum;
    
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
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
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

}
