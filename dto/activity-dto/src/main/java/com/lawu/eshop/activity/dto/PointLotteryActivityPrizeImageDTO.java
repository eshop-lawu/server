package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/2/6.
 */
public class PointLotteryActivityPrizeImageDTO {

    @ApiModelProperty(value = "图片路径")
    private String imagePath;

    @ApiModelProperty(value = "序号")
    private Integer orderNum;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
