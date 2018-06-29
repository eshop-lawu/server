package com.lawu.eshop.game.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK参与游戏信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@ApiModel("头脑PK开始DTO")
public class MindPkStartDTO {
    
    /**
     * 本轮答题最大分值
     */
    @ApiModelProperty(value = "本轮答题的最大分值", required = true)
    private Integer maximumScore;
    
    /**
     * 对战用户信息
     */
    @ApiModelProperty(value = "对战用户信息", required = true)
    private List<GameMindBattleUserUserInfoDTO> battleUsers;
    
    /**
     * 随机生成的题目
     */
    @ApiModelProperty(value = "随机生成的题目", required = true)
    private List<GameMindParticipateGameQuestionDTO> questions;

    public Integer getMaximumScore() {
        return maximumScore;
    }

    public void setMaximumScore(Integer maximumScore) {
        this.maximumScore = maximumScore;
    }

    public List<GameMindBattleUserUserInfoDTO> getBattleUsers() {
        return battleUsers;
    }

    public void setBattleUsers(List<GameMindBattleUserUserInfoDTO> battleUsers) {
        this.battleUsers = battleUsers;
    }

    public List<GameMindParticipateGameQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<GameMindParticipateGameQuestionDTO> questions) {
        this.questions = questions;
    }
    
}
