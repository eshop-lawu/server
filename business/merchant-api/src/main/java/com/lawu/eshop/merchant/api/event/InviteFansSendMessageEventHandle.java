package com.lawu.eshop.merchant.api.event;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.merchant.api.service.MemberService;
import com.lawu.eshop.merchant.api.service.MessageService;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.event.AsyncEventHandle;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/11/6.
 */
@Component
public class InviteFansSendMessageEventHandle implements AsyncEventHandle<InviteFansSendMessageEvent> {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MessageService messageService;

    @Override
    public void execute(InviteFansSendMessageEvent event) {
        String[] numArr = event.getNums().split(",");
        Result<UserDTO> userDTOResult;
        for (String num : numArr) {
            userDTOResult = memberService.getMemberByNum(num);
            event.getMessageTempParam().setUserName("E店会员");
            if (userDTOResult.getRet() == ResultCode.SUCCESS && StringUtils.isNotEmpty(userDTOResult.getModel().getNickname())) {
                event.getMessageTempParam().setUserName(userDTOResult.getModel().getNickname());
            }
            event.getMessageInfoParam().setMessageParam(event.getMessageTempParam());
            messageService.saveMessage(num, event.getMessageInfoParam());
        }
    }
}
