package com.lawu.eshop.activity.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/15.
 */
public class TakeLotteryParam {

    @ApiModelProperty(value = "抽奖记录id", required = true)
    private Long id;

    @ApiModelProperty(value = "收货人姓名")
    private String consigneeName;

    @ApiModelProperty(value = "收货人手机号")
    private String consigneeMobile;

    @ApiModelProperty(value = "收货人地址")
    private String consigneeAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }
}
