package com.lawu.eshop.mall.param;

import com.lawu.eshop.mall.constants.AttendTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meihsuquan
 * @date 2018/5/14.
 */
public class MessageRemarkParam {

    @ApiModelProperty(value = "游戏房主编号")
    private String roomHostNum;

    @ApiModelProperty(value = "游戏参与类型")
    private AttendTypeEnum attendTypeEnum;

    @ApiModelProperty(value = "用户id")
    private Long memberId;

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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
