package com.lawu.eshop.activity.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public class PointLotteryRollDTO {

    @ApiModelProperty(value = "中奖手机号")
    private String mobile;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }
}
