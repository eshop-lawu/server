package com.lawu.eshop.cache.param;

import java.util.List;

/**
 * 头脑PK对战用户信息
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
public class GameMindParticipateGameParam {
    
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
     * 参与记录
     */
    private List<GameMindParticipateGameRecordParam> records;
    
    /**
     * 问题
     */
    private List<GameMindParticipateGameQuestionParam> questions;
    
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

    public List<GameMindParticipateGameRecordParam> getRecords() {
        return records;
    }

    public void setRecords(List<GameMindParticipateGameRecordParam> records) {
        this.records = records;
    }

    public List<GameMindParticipateGameQuestionParam> getQuestions() {
        return questions;
    }

    public void setQuestions(List<GameMindParticipateGameQuestionParam> questions) {
        this.questions = questions;
    }
    
}
