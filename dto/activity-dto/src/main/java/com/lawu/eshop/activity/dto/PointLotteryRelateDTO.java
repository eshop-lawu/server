package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/1.
 */
public class PointLotteryRelateDTO {

    @ApiModelProperty(value = "活动id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "活动状态")
    private String statusDes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }
}
