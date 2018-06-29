package com.lawu.eshop.cache.dto;

import java.util.List;

/**
 * @author lihj <br>
 * @date 2018/3/17
 */
public class GamePuzzleCacheAnswerDetailInfo {

    private String userNum;
    private String attendNum;

    /**
     * 正确答案
     */
    private List<String> rightAnswer;
    /**
     * 单个题目用时时间
     */
    private List<String> singleUseTime;
    /**
     * 临时存储答案答题一次移出一个,如果答题完毕集合长度是0
     */
    private List<String> tmpAnswer;

    /**
     * 游戏总得分
     */
    private int totalScore;
    /**
     * 游戏总用时间
     */
    private int totalUseTime;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

    public List<String> getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(List<String> rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getSingleUseTime() {
        return singleUseTime;
    }

    public void setSingleUseTime(List<String> singleUseTime) {
        this.singleUseTime = singleUseTime;
    }

    public List<String> getTmpAnswer() {
        return tmpAnswer;
    }

    public void setTmpAnswer(List<String> tmpAnswer) {
        this.tmpAnswer = tmpAnswer;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalUseTime() {
        return totalUseTime;
    }

    public void setTotalUseTime(int totalUseTime) {
        this.totalUseTime = totalUseTime;
    }
}
