package com.lawu.eshop.game.dto;

import java.math.BigDecimal;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
public class GameRoomPlayerJoinDTO {

    @ApiModelProperty(value = "房间id")
    private Long roomId;

    @ApiModelProperty(value = "房主编号")
    private String roomHostNum;

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    @ApiModelProperty(value = "房间号码")
    private String roomNum;

    @ApiModelProperty(value = "是否房主")
    private Boolean isHomeowner;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "入场积分")
    private BigDecimal point;

    @ApiModelProperty(value = "账户积分")
    private BigDecimal accountPoint;

    @ApiModelProperty(value = "星星数量")
    private Integer starCount;

    @ApiModelProperty(value = "拼图游戏房间难易程度：SIMPLE--简单，COMMONLY--一般，DIFFICULTY--困难，RANDOM--随机")
    private GameHardLevelEnum hardLevelEnum;

    @ApiModelProperty(value = "房间密码")
    private String roomPwd;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomHostNum() {
        return roomHostNum;
    }

    public void setRoomHostNum(String roomHostNum) {
        this.roomHostNum = roomHostNum;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Boolean getIsHomeowner() {
        return isHomeowner;
    }

    public void setIsHomeowner(Boolean homeowner) {
        isHomeowner = homeowner;
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

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getAccountPoint() {
        return accountPoint;
    }

    public void setAccountPoint(BigDecimal accountPoint) {
        this.accountPoint = accountPoint;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public GameHardLevelEnum getHardLevelEnum() {
        return hardLevelEnum;
    }

    public void setHardLevelEnum(GameHardLevelEnum hardLevelEnum) {
        this.hardLevelEnum = hardLevelEnum;
    }

    public String getRoomPwd() {
        return roomPwd;
    }

    public void setRoomPwd(String roomPwd) {
        this.roomPwd = roomPwd;
    }
}
