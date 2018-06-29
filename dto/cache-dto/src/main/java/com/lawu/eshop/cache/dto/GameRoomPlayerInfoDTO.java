package com.lawu.eshop.cache.dto;

import java.io.Serializable;
import java.util.Date;

import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@ApiModel("房间内用户信息")
public class GameRoomPlayerInfoDTO implements Serializable {

    private static final long serialVersionUID = 2044103397425242722L;

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "NOT_READY--未准备，READY--准备")
    private GameRoomPlayerStatusEnum statusEnum;

    @ApiModelProperty(value = "是否房主")
    private Boolean isRoomHost;

    @Deprecated
    @ApiModelProperty(value = "区域名称", hidden = true)
    private String regionName;

    @ApiModelProperty(value = "所属区域")
    private String regionPath;

    /**
     * 加入时间
     */
    @ApiModelProperty(value = "加入时间")
    private Date joinTime;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public GameRoomPlayerStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(GameRoomPlayerStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Boolean getIsRoomHost() {
        return isRoomHost;
    }

    public void setIsRoomHost(Boolean roomHost) {
        isRoomHost = roomHost;
    }

    @Deprecated
    public String getRegionName() {
        return regionName;
    }

    @Deprecated
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

}
