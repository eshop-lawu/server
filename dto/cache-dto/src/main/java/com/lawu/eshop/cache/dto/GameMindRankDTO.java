package com.lawu.eshop.cache.dto;

import java.math.BigDecimal;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK排名DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@ApiModel("头脑PK排名DTO")
public class GameMindRankDTO {
    
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", required=  true)
    private String userNum;
    
    /**
     * 参与状态
     */
    @ApiModelProperty(value = "参与状态(INITSTATUS-初始化|ATTENDSUCCESS-参与成功|ATTENDFAIL-参与失败|GAMEPLAYSUCCESS-游戏成功|GAMEPLAYFAIL-游戏失败)", required= true)
    private GameAttendRecordStatusEnum attendStatus;
    
    /**
     * 累计游戏得分
     */
    @ApiModelProperty(value = "累计游戏得分", required=  true)
    private Integer accumulatedGameScore;
    
    /**
     * 游戏排名
     */
    @ApiModelProperty(value = "游戏排名", required=  true)
    private Integer gameRank;
    
    /**
     * 奖励积分
     */
    @ApiModelProperty(value = "奖励积分", required=  true)
    private BigDecimal rewardPoint;
    
    /**
     * 奖励星星
     */
    @ApiModelProperty(value = "奖励星星", required=  true)
    private Integer rewardStar;
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public GameAttendRecordStatusEnum getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(GameAttendRecordStatusEnum attendStatus) {
        this.attendStatus = attendStatus;
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

}
