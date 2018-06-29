package com.lawu.eshop.activity.dto;

import com.lawu.eshop.activity.constants.RedPacketTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 参与红包活动所需要的信息DTO
 * 
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
@ApiModel
public class RedpacketActivityInfoOfAttendDTO {
    
    /**
     * 红包类型
     */
    @ApiModelProperty(value = "红包类型", required = true)
    private RedPacketTypeEnum redpacketType;

    public RedPacketTypeEnum getRedpacketType() {
        return redpacketType;
    }

    public void setRedpacketType(RedPacketTypeEnum redpacketType) {
        this.redpacketType = redpacketType;
    }
    
}