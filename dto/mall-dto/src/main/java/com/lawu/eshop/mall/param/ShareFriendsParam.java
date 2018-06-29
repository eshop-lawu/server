package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.AttendTypeEnum;
import com.lawu.eshop.mall.constants.ShareTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meihsuquan
 * @date 2018/5/14.
 */
public class ShareFriendsParam {

    @ApiModelProperty(value = "分享类型：PRODUCT--商品，AD_FLAT--平面广告，AD_VIDEO--视频广告，AD_PRAISE--E咻，GAME_MIND--知识王者，GAME_PUZZLE--拼图挑战，GAME_DIAL--幸运转盘，REDPACKET_MEMBER--用户红包，REDPACKET_MERCHANT--商家红包，SECKILL--抢购", required = true)
    private ShareTypeEnum typeEnum;

    @ApiModelProperty(value = "关联id：PRODUCT--商品id，AD--广告id，GAME--游戏房间id(转盘游戏传0)，REDPACKET--商家端商家id/用户端红包id，SECKILL--抢购活动商品id", required = true)
    private Long relateId;

    @ApiModelProperty(value = "分享的E友编号，多个用逗号隔开，如果分享给所有E友则为空", required = false)
    private String userNums;

    @ApiModelProperty(value = "是否分享给所有E友", required = true)
    private Boolean isAll;

    @ApiModelProperty(value = "游戏房主编号，分享游戏必传参数", required = false)
    private String roomHostNum;

    @ApiModelProperty(value = "STANDALONE--单机，RANDOM--随机，MANYPEOPLE--多人", required = false)
    private AttendTypeEnum attendTypeEnum;

    @ApiModelProperty(value = "商品名称，分享商品必传参数", required = false)
    private String productName;

    public ShareTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(ShareTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public String getUserNums() {
        return userNums;
    }

    public void setUserNums(String userNums) {
        this.userNums = userNums;
    }

    public Boolean getIsAll() {
        return isAll;
    }

    public void setIsAll(Boolean all) {
        isAll = all;
    }

    public String getRoomHostNum() {
        return roomHostNum;
    }

    public void setRoomHostNum(String roomHostNum) {
        this.roomHostNum = roomHostNum;
    }

    public AttendTypeEnum getAttendTypeEnum() {
        return attendTypeEnum;
    }

    public void setAttendTypeEnum(AttendTypeEnum attendTypeEnum) {
        this.attendTypeEnum = attendTypeEnum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
