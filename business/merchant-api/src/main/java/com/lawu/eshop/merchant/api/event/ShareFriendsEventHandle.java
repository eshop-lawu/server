package com.lawu.eshop.merchant.api.event;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageRemarkParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.merchant.api.service.InviterService;
import com.lawu.eshop.merchant.api.service.MemberService;
import com.lawu.eshop.merchant.api.service.MerchantService;
import com.lawu.eshop.merchant.api.service.MessageService;
import com.lawu.eshop.user.constants.InviterTypeEnum;
import com.lawu.eshop.user.dto.EFriendInviterDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.framework.core.event.AsyncEventHandle;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author meihsuquan
 * @date 2018/5/14.
 */
@Component
public class ShareFriendsEventHandle implements AsyncEventHandle<ShareFriendsEvent> {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private InviterService inviterService;

    @Autowired
    private MerchantService merchantService;

    @Override
    public void execute(ShareFriendsEvent event) {
        Result<RongYunDTO> shareUserDTOResult = merchantService.getRongYunInfoByNum(event.getShareUserNum());
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setShareUserName(shareUserDTOResult.getModel().getNickName());
        messageTempParam.setProductName(event.getProductName());
        messageTempParam.setGameTypeName(event.getTypeEnum().getName());
        messageTempParam.setAdTypeName(event.getTypeEnum().getName());

        MessageInfoParam messageInfoParam = new MessageInfoParam();
        messageInfoParam.setRelateId(event.getRelateId());
        messageInfoParam.setTypeEnum(MessageTypeEnum.getEnum(event.getTypeEnum().getVal()));
        messageInfoParam.setMessageParam(messageTempParam);
        if (StringUtils.isNotEmpty(event.getRoomHostNum()) || event.getAttendTypeEnum() != null) {
            MessageRemarkParam param = new MessageRemarkParam();
            param.setRoomHostNum(event.getRoomHostNum());
            param.setAttendTypeEnum(event.getAttendTypeEnum());
            messageInfoParam.setRemark(JSONObject.toJSONString(param));
        }

        if (event.getIsAll()) {
            EFriendQueryDataParam dataParam = new EFriendQueryDataParam();
            dataParam.setUserNum(event.getShareUserNum());
            dataParam.setTypeEnum(InviterTypeEnum.MEMBER);
            dataParam.setPageSize(1000);
            int currentPage = 0;
            while (true) {
                currentPage++;
                dataParam.setCurrentPage(currentPage);
                Result<Page<EFriendInviterDTO>> page = inviterService.selectEFriend(dataParam);
                if (page.getModel() == null || page.getModel().getRecords() == null || page.getModel().getRecords().isEmpty()) {
                    return;
                }
                for (EFriendInviterDTO inviterDTO : page.getModel().getRecords()) {
                    Result<RongYunDTO> userDTOResult = memberService.getRongYunInfoByNum(inviterDTO.getUserNum());
                    messageTempParam.setUserName(userDTOResult.getModel().getNickName());
                    messageService.saveMessage(inviterDTO.getUserNum(), messageInfoParam);
                }
            }
        } else {
            String[] userNumArr = event.getUserNums().split(",");
            for (String userNum : userNumArr) {
                Result<RongYunDTO> userDTOResult = memberService.getRongYunInfoByNum(userNum);
                messageTempParam.setUserName(userDTOResult.getModel().getNickName());
                messageService.saveMessage(userNum, messageInfoParam);
            }
        }
    }

}
