package com.lawu.eshop.common.dto;

/**
 * 游戏需要的用户信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年5月22日
 */
public class GameUserInfoDTO {
    
    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 用户昵称
     */
    private String nickName;
    
    /**
     * 用户头像
     */
    private String headImg;
    
    /**
     * 用户所在区域
     */
    @Deprecated
    private String regionName;
    
    /**
     * 地区路径
     */
    private String regionPath;
    
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
    
}
