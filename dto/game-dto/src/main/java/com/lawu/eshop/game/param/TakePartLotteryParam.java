package com.lawu.eshop.game.param;

import java.math.BigDecimal;

import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class TakePartLotteryParam {

    private Long userId;

    private String userNum;

    private String userAccount;

    private Long gameDialId;

    private BigDecimal payPoint;

    private GameDialRecordStatusEnum statusEnum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Long getGameDialId() {
        return gameDialId;
    }

    public void setGameDialId(Long gameDialId) {
        this.gameDialId = gameDialId;
    }

    public BigDecimal getPayPoint() {
        return payPoint;
    }

    public void setPayPoint(BigDecimal payPoint) {
        this.payPoint = payPoint;
    }

    public GameDialRecordStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GameDialRecordStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }
}
