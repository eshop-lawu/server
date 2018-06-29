package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.constants.MessageQueryTypeEnum;
import com.lawu.eshop.mall.dto.MessageDTO;
import com.lawu.eshop.mall.dto.MessageStatisticsDTO;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageParam;
import com.lawu.eshop.mall.param.MessageStatisticsParam;
import com.lawu.eshop.member.api.service.MessageService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;


@Service
class MockMessageService extends BaseController implements MessageService {

   

    @Override
    public Result<Page<MessageDTO>> getMessageList(@PathVariable("userNum") String userNum,@RequestParam("userType") UserTypeEnum userType, @ModelAttribute MessageParam pageParam) {
        return null;
    }

    @Override
    public Result updateMessageStatus(@PathVariable("messageId") Long messageId, @RequestParam("userNum") String userNum) {
        return null;
    }

    @Override
    public Result delMessageStatus(@PathVariable("messageId") Long messageId, @RequestParam("userNum") String userNum) {
        return null;
    }

    @Override
    public Result saveMessage(@PathVariable("userNum") String userNum, @ModelAttribute MessageInfoParam messageInfoParam) {
        return successCreated();
    }

    @Override
    public Result delMessageByIds(@RequestParam("ids") String ids, @RequestParam("userNum") String userNum) {
        return null;
    }

	@Override
	public Result pushMessageBySetCid(@RequestParam("userNum") String userNum,@ModelAttribute MessageTypeEnum typeEnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<MessageStatisticsDTO> getMessageStatistics(MessageStatisticsParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
