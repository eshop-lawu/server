package com.lawu.eshop.mall.dto;

import com.lawu.eshop.mall.constants.WindowMessageStatusEnum;
import com.lawu.eshop.mall.constants.WindowMessageTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public class WindowMessageOperatorDTO extends WindowMessageDTO {

    @ApiModelProperty(value = "关联名称")
    private String relateName;

    @ApiModelProperty(value = "类型枚举")
    private WindowMessageTypeEnum typeEnum;

    @ApiModelProperty(value = "类型描述")
    private String typeDes;

    @ApiModelProperty(value = "状态枚举")
    private WindowMessageStatusEnum statusEnum;

    @ApiModelProperty(value = "状态描述")
    private String statusDes;

    public String getRelateName() {
        return relateName;
    }

    public void setRelateName(String relateName) {
        this.relateName = relateName;
    }

    @Override
    public WindowMessageTypeEnum getTypeEnum() {
        return typeEnum;
    }

    @Override
    public void setTypeEnum(WindowMessageTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getTypeDes() {
        return typeDes;
    }

    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes;
    }

    public WindowMessageStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(WindowMessageStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }
}
