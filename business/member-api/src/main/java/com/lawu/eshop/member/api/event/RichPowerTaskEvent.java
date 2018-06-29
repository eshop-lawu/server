package com.lawu.eshop.member.api.event;

import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author meishuquan
 * @date 2018/5/3.
 */
public class RichPowerTaskEvent extends AsyncEvent {

    private String memberNum;

    private PowerTaskTypeEnum type;

    private int shoppingAmount;

    public RichPowerTaskEvent(Object source, RichPowerTaskRecordParam param) {
        super(source);
        this.setMemberNum(param.getMemberNum());
        this.setType(param.getType());
        this.setShoppingAmount(param.getShoppingAmount());
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public PowerTaskTypeEnum getType() {
        return type;
    }

    public void setType(PowerTaskTypeEnum type) {
        this.type = type;
    }

    public int getShoppingAmount() {
        return shoppingAmount;
    }

    public void setShoppingAmount(int shoppingAmount) {
        this.shoppingAmount = shoppingAmount;
    }

}
