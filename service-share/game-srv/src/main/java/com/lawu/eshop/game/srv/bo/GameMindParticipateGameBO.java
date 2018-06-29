package com.lawu.eshop.game.srv.bo;

import java.util.List;

/**
 * 头脑PK参与游戏信息BO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
public class GameMindParticipateGameBO {
    
    /**
     * 用户编号集合
     */
    private List<String> userNums;
    
    /**
     * 随机生成的题目
     */
    private List<GameMindParticipateGameQuestionBO> questions;
    
    /**
     * 本轮答题最大分值
     */
    private Integer maximumScore;

    public List<String> getUserNums() {
        return userNums;
    }

    public void setUserNums(List<String> userNums) {
        this.userNums = userNums;
    }

    public List<GameMindParticipateGameQuestionBO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<GameMindParticipateGameQuestionBO> questions) {
        this.questions = questions;
    }

    public Integer getMaximumScore() {
        return maximumScore;
    }

    public void setMaximumScore(Integer maximumScore) {
        this.maximumScore = maximumScore;
    }

}
