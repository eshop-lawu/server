package com.lawu.eshop.cache.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;

/**
 * 头脑PK用户详情缓存DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
public class GameMindUserDetailsCacheDTO implements Serializable {
    
    private static final long serialVersionUID = -3639460234261687921L;
    
    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 是否是房主
     */
    private Boolean isHomeowner;
    
    /**
     * 参与记录id
     */
    private Long recordId;
    
    /**
     * 参与状态
     */
    private GameAttendRecordStatusEnum attendStatus;
    
    /**
     * 参与编号
     */
    private String attendNum;
    
    /**
     * 房间编号
     */
    private String roomNum;
    
    /**
     * 累计游戏得分
     */
    private Integer accumulatedGameScore;
    
    /**
     * 游戏排名
     */
    private Integer gameRank;
    
    /**
     * 奖励积分
     */
    private BigDecimal rewardPoint;
    
    /**
     * 奖励星星
     */
    private Integer rewardStar;
    
    /**
     * 答题结果集
     */
    private List<GameMindAnswerResultCacheDTO> answerResults;
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Boolean getIsHomeowner() {
        return isHomeowner;
    }

    public void setIsHomeowner(Boolean isHomeowner) {
        this.isHomeowner = isHomeowner;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public GameAttendRecordStatusEnum getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(GameAttendRecordStatusEnum attendStatus) {
        this.attendStatus = attendStatus;
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

    public Integer getAccumulatedGameScore() {
        return accumulatedGameScore;
    }

    public void setAccumulatedGameScore(Integer accumulatedGameScore) {
        this.accumulatedGameScore = accumulatedGameScore;
    }

    public Integer getGameRank() {
        return gameRank;
    }

    public void setGameRank(Integer gameRank) {
        this.gameRank = gameRank;
    }
    
    public BigDecimal getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(BigDecimal rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public Integer getRewardStar() {
        return rewardStar;
    }

    public void setRewardStar(Integer rewardStar) {
        this.rewardStar = rewardStar;
    }

    public List<GameMindAnswerResultCacheDTO> getAnswerResults() {
        return answerResults;
    }

    public void setAnswerResults(List<GameMindAnswerResultCacheDTO> answerResults) {
        this.answerResults = answerResults;
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
