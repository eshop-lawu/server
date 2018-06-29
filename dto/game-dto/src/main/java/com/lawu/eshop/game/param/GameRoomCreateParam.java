package com.lawu.eshop.game.param;

import java.math.BigDecimal;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameRoomCreateParam {

    @ApiModelProperty(required = true, value = "入场积分")
    private BigDecimal point;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(required = true, value = "房间类型：MIND--头脑PK，PUZZLE--拼图")
    private GameTypeEnum typeEnum;

    @ApiModelProperty(value = "游戏难易程度(拼图类型必传，头脑PK为null)：SIMPLE--简单，COMMONLY--一般，DIFFICULTY--困难，RANDOM--随机")
    private GameHardLevelEnum hardLevelEnum;

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public GameTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(GameTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public GameHardLevelEnum getHardLevelEnum() {
        return hardLevelEnum;
    }

    public void setHardLevelEnum(GameHardLevelEnum hardLevelEnum) {
        this.hardLevelEnum = hardLevelEnum;
    }
}
