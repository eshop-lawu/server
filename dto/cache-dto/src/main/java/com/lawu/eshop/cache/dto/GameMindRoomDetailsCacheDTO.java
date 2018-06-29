package com.lawu.eshop.cache.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.cache.param.GameMindParticipateGameQuestionParam;

/**
 * 头脑PK房间详情缓存DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
public class GameMindRoomDetailsCacheDTO implements Serializable {
    
    private static final long serialVersionUID = -7804289970946702669L;
    
    /**
     * 参与编号
     */
    private String attendNum;
    
    /**
     * 房间编号
     */
    private String roomNum;
    
    /**
     * 入场积分
     */
    private Integer point;
    
    /**
     * 初始所有用户编号集合
     */
    private List<String> initialUserNums;
    
    /**
     * 房间内所有用户编号
     * 用过用户编号, 可以查找每个用户的游戏状态
     */
    private List<String> userNums;
    
    /**
     * 问题
     */
    private List<GameMindParticipateGameQuestionParam> questions;
    
    /**
     * 答题开始时间
     */
    private List<Date> answerStartTimes;
    
    /**
     * 退出用户信息
     */
    private List<GameMindUserDetailsCacheDTO> exitUserInformations;
    
    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public List<String> getInitialUserNums() {
        return initialUserNums;
    }

    public void setInitialUserNums(List<String> initialUserNums) {
        this.initialUserNums = initialUserNums;
    }

    public List<String> getUserNums() {
        return userNums;
    }

    public void setUserNums(List<String> userNums) {
        this.userNums = userNums;
    }

    public List<GameMindParticipateGameQuestionParam> getQuestions() {
        return questions;
    }

    public void setQuestions(List<GameMindParticipateGameQuestionParam> questions) {
        this.questions = questions;
    }

    public List<Date> getAnswerStartTimes() {
        return answerStartTimes;
    }

    public void setAnswerStartTimes(List<Date> answerStartTimes) {
        this.answerStartTimes = answerStartTimes;
    }
    
    public List<GameMindUserDetailsCacheDTO> getExitUserInformations() {
        return exitUserInformations;
    }

    public void setExitUserInformations(List<GameMindUserDetailsCacheDTO> exitUserInformations) {
        this.exitUserInformations = exitUserInformations;
    }

    /**
     * 如果房间编号不为空, 取房间编号<p>
     * 如果为空, 取参与编号
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月22日
     * @updateDate 2018年3月22日
     */
    public String getGroupNum() {
        if (getRoomNum() != null) {
            return getRoomNum();
        }
        return getAttendNum();
    }
}
