package com.lawu.eshop.merchant.api.event;

import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author meishuquan
 * @date 2017/11/6.
 */
public class InviteFansSendMessageEvent extends AsyncEvent {

    private MessageInfoParam messageInfoParam;
    private MessageTempParam messageTempParam;
    private String nums;

    public InviteFansSendMessageEvent(Object source, MessageInfoParam messageInfoParam, MessageTempParam messageTempParam, String nums) {
        super(source);
        this.messageInfoParam = messageInfoParam;
        this.messageTempParam = messageTempParam;
        this.nums = nums;
    }

    public MessageInfoParam getMessageInfoParam() {
        return messageInfoParam;
    }

    public void setMessageInfoParam(MessageInfoParam messageInfoParam) {
        this.messageInfoParam = messageInfoParam;
    }

    public MessageTempParam getMessageTempParam() {
        return messageTempParam;
    }

    public void setMessageTempParam(MessageTempParam messageTempParam) {
        this.messageTempParam = messageTempParam;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }
}
