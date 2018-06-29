package com.lawu.eshop.game.srv.bo;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2018/3/15.
 */
public class GameMindAttendRecordBO {
    private Long id;

    /**
     *
     * 参与用户编号
     */
    private String userNum;

    /**
     *
     * 参与类型1单机、2随机对战、3好友对战多人
     */
    private Byte attendType;

    /**
     *
     * 参与编号(同一个房间编号相同)
     */
    private String attendNum;

    /**
     *
     * 房间号
     */
    private String roomNum;

    /**
     *
     * 参与人数
     */
    private Integer attendCount;

    /**
     *
     * 参与扣除积分
     */
    private Integer attendPoint;

    /**
     *
     * 参与扣除星星
     */
    private Integer attendStar;

    /**
     *
     * 0-初始化 1-参与成功 2-参与失败 3-游戏成功 4-游戏失败
     */
    private Byte status;

    /**
     *
     * 答题用时单位秒
     */
    private Integer questionUseTime;

    /**
     *
     * 游戏分数
     */
    private Integer gameScore;

    /**
     *
     * 游戏名次
     */
    private Integer gameRank;

    /**
     *
     * 参与成功奖励积分
     */
    private Integer rewardPoint;

    /**
     *
     * 参与成功奖励星星
     */
    private Integer rewardStar;

    /**
     *
     * 问题id集合逗号隔开
     */
    private String questionIds;

    /**
     *
     * 修改时间
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     */
    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Byte getAttendType() {
        return attendType;
    }

    public void setAttendType(Byte attendType) {
        this.attendType = attendType;
    }

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

    public Integer getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(Integer attendCount) {
        this.attendCount = attendCount;
    }

    public Integer getAttendPoint() {
        return attendPoint;
    }

    public void setAttendPoint(Integer attendPoint) {
        this.attendPoint = attendPoint;
    }

    public Integer getAttendStar() {
        return attendStar;
    }

    public void setAttendStar(Integer attendStar) {
        this.attendStar = attendStar;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getQuestionUseTime() {
        return questionUseTime;
    }

    public void setQuestionUseTime(Integer questionUseTime) {
        this.questionUseTime = questionUseTime;
    }

    public Integer getGameScore() {
        return gameScore;
    }

    public void setGameScore(Integer gameScore) {
        this.gameScore = gameScore;
    }

    public Integer getGameRank() {
        return gameRank;
    }

    public void setGameRank(Integer gameRank) {
        this.gameRank = gameRank;
    }

    public Integer getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(Integer rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public Integer getRewardStar() {
        return rewardStar;
    }

    public void setRewardStar(Integer rewardStar) {
        this.rewardStar = rewardStar;
    }

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
