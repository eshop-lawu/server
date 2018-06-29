package com.lawu.eshop.merchant.api.event;

import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author meishuquan
 * @date 2018/5/3.
 */
public class RichPowerTaskEvent extends AsyncEvent {

    private String merchantNum;

    private MerchantPowerTaskTypeEnum type;

    private int point;

    private int fensInviteCount;

    public RichPowerTaskEvent(Object source, RichMerchantPowerTaskRecordParam param) {
        super(source);
        this.merchantNum = param.getMerchantNum();
        this.type = param.getType();
        this.point = param.getPoint();
        this.fensInviteCount = param.getFensInviteCount();
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    public MerchantPowerTaskTypeEnum getType() {
        return type;
    }

    public void setType(MerchantPowerTaskTypeEnum type) {
        this.type = type;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getFensInviteCount() {
        return fensInviteCount;
    }

    public void setFensInviteCount(int fensInviteCount) {
        this.fensInviteCount = fensInviteCount;
    }
}
