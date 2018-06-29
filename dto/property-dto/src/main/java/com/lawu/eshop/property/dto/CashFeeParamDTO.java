package com.lawu.eshop.property.dto;

import io.swagger.annotations.ApiModelProperty;

public class CashFeeParamDTO {

    @ApiModelProperty(value = "手续费比例", required = true)
    private String scale;

    @ApiModelProperty(value = "银行卡提现服务费", required = true)
    private String chargeMoney;

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(String chargeMoney) {
        this.chargeMoney = chargeMoney;
    }
}