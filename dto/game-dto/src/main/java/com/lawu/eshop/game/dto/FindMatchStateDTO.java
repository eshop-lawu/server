package com.lawu.eshop.game.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询匹配状态DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月16日
 * @updateDate 2018年3月16日
 */
@ApiModel("匹配状态")
public class FindMatchStateDTO {
    
    /**
     * 参与编号
     */
    @ApiModelProperty(value = "参与编号", required = true)
    private String attendNum;
    
    /**
     * 是否是房主
     */
    @ApiModelProperty(value = "是否是房主", required = true)
    private Boolean roomMaster;

    public String getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(String attendNum) {
        this.attendNum = attendNum;
    }

    public Boolean getRoomMaster() {
        return roomMaster;
    }

    public void setRoomMaster(Boolean roomMaster) {
        this.roomMaster = roomMaster;
    }
    
}
