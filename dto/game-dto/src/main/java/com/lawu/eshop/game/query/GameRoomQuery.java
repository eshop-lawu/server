package com.lawu.eshop.game.query;

import java.math.BigDecimal;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
public class GameRoomQuery extends AbstractPageParam {

    @ApiModelProperty(value = "最少积分")
    private BigDecimal minPoint;

    @ApiModelProperty(value = "最多积分")
    private BigDecimal maxPoint;

    @ApiModelProperty(value = "房间号码")
    private String roomNum;

    @ApiModelProperty(required = true, value = "MIND--头脑PK，PUZZLE--拼图")
    private GameTypeEnum typeEnum;

    public BigDecimal getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(BigDecimal minPoint) {
        this.minPoint = minPoint;
    }

    public BigDecimal getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(BigDecimal maxPoint) {
        this.maxPoint = maxPoint;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public GameTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(GameTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
