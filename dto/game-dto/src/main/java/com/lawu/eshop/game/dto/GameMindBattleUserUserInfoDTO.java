package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 头脑PK对战用户信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@ApiModel("对战用户信息")
public class GameMindBattleUserUserInfoDTO {
    
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String userNum;
    
    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;
    
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", required = true)
    private String headImg;
    
    /**
     * 地区路径
     */
    @ApiModelProperty(value = "地区路径", required = true)
    private String regionPath;
    
    /**
     * 用户所在区域
     */
    @Deprecated
    @ApiModelProperty(value = "用户所在区域", required = true)
    private String regionName;
    
    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }
    
    @Deprecated
    public String getRegionName() {
        return regionName;
    }

    @Deprecated
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
}
