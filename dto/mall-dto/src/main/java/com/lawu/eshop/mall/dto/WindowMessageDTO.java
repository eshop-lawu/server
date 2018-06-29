package com.lawu.eshop.mall.dto;

import com.lawu.eshop.mall.constants.WindowMessageTypeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public class WindowMessageDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "弹窗图片")
    private String imgPath;

    @ApiModelProperty(value = "关联id")
    private Long relateId;

    @ApiModelProperty(value = "类型枚举：POINT_LOTTERY--积分抽奖")
    private WindowMessageTypeEnum typeEnum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public WindowMessageTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(WindowMessageTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
