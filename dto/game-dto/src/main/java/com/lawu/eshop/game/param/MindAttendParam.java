package com.lawu.eshop.game.param;

import com.lawu.eshop.game.constants.AttendTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
public class MindAttendParam {

    @ApiModelProperty(value = "用户编号")
    private String userNum;

    @ApiModelProperty(value = "房间号")
    private String roomNum;

    @ApiModelProperty(value = "参与扣除积分")
    private Integer attendPoint;

    @ApiModelProperty(value = "参与类型")
    private AttendTypeEnum attendType;

    @ApiModelProperty(value = "参与扣除星星")
    private Integer attendStar;

    private String questionIds;

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

    public Integer getAttendPoint() {
        return attendPoint;
    }

    public void setAttendPoint(Integer attendPoint) {
        this.attendPoint = attendPoint;
    }

    public AttendTypeEnum getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendTypeEnum attendType) {
        this.attendType = attendType;
    }

    public Integer getAttendStar() {
        return attendStar;
    }

    public void setAttendStar(Integer attendStar) {
        this.attendStar = attendStar;
    }

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }
}
