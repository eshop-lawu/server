package com.lawu.eshop.game.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialPrizeDetailDTO extends GameDialPrizeInfoDTO {

    @ApiModelProperty(value = "是否需要填写地址")
    private Boolean isAddress;

    @ApiModelProperty(value = "是否立即到账")
    private Boolean isSendPrize;

    @ApiModelProperty(value = "运费")
    private BigDecimal freightPrice;

    public Boolean getIsAddress() {
        return isAddress;
    }

    public void setIsAddress(Boolean address) {
        isAddress = address;
    }

    public Boolean getIsSendPrize() {
        return isSendPrize;
    }

    public void setIsSendPrize(Boolean sendPrize) {
        isSendPrize = sendPrize;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }
}
