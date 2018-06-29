package com.lawu.eshop.game.param;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public class MindAttendRecordParam {

    private Long attendId;

    private GameAttendRecordStatusEnum status;

    private Integer gameScore;

    private Integer rewardPoint;

    private Integer rewardStar;

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public GameAttendRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(GameAttendRecordStatusEnum status) {
        this.status = status;
    }

    public Integer getGameScore() {
        return gameScore;
    }

    public void setGameScore(Integer gameScore) {
        this.gameScore = gameScore;
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
}
