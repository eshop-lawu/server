package com.lawu.eshop.game.dto;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.game.constants.GameRoomStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
public class GameRoomDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "房主编号")
    private String userNum;

    @ApiModelProperty(value = "房主账号")
    private String account;

    @ApiModelProperty(value = "房间号码")
    private String roomNum;

    @ApiModelProperty(value = "玩家信息")
    private List<GameRoomPlayerInfoDTO> playerInfoDTOS;

    @ApiModelProperty(value = "入场积分")
    private BigDecimal point;

    @ApiModelProperty(value = "是否需要密码")
    private Boolean isPwd;

    @ApiModelProperty(value = "MIND--头脑PK，PUZZLE--拼图")
    private GameTypeEnum typeEnum;

    @ApiModelProperty(value = "WAITING--等待中，PLAYING--进行中，FINISHED--已结束")
    private GameRoomStatusEnum statusEnum;

    @ApiModelProperty(value = "SIMPLE--简单，COMMONLY--一般，DIFFICULTY--困难，RANDOM--随机")
    private GameHardLevelEnum hardLevelEnum;

    @ApiModelProperty(value = "密码")
    private String pwd;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public List<GameRoomPlayerInfoDTO> getPlayerInfoDTOS() {
        return playerInfoDTOS;
    }

    public void setPlayerInfoDTOS(List<GameRoomPlayerInfoDTO> playerInfoDTOS) {
        this.playerInfoDTOS = playerInfoDTOS;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public Boolean getIsPwd() {
        return isPwd;
    }

    public void setIsPwd(Boolean pwd) {
        isPwd = pwd;
    }

    public GameTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(GameTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public GameRoomStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GameRoomStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public GameHardLevelEnum getHardLevelEnum() {
        return hardLevelEnum;
    }

    public void setHardLevelEnum(GameHardLevelEnum hardLevelEnum) {
        this.hardLevelEnum = hardLevelEnum;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
