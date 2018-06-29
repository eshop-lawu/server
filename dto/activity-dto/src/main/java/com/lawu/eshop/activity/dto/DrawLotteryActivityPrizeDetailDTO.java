package com.lawu.eshop.activity.dto;

import com.lawu.eshop.activity.constants.DrawLotteryActivityPrizeTypeEnum;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityPrizeDetailDTO extends DrawLotteryActivityPrizeDTO {

    private Boolean isAddress;

    private Boolean isSendPrize;

    private DrawLotteryActivityPrizeTypeEnum prizeTypeEnum;

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

    public DrawLotteryActivityPrizeTypeEnum getPrizeTypeEnum() {
        return prizeTypeEnum;
    }

    public void setPrizeTypeEnum(DrawLotteryActivityPrizeTypeEnum prizeTypeEnum) {
        this.prizeTypeEnum = prizeTypeEnum;
    }
}
