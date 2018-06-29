package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.constants.MessageQueryTypeEnum;
import com.lawu.eshop.mall.dto.MessageDTO;
import com.lawu.eshop.mall.dto.MessageStatisticsDTO;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageParam;
import com.lawu.eshop.mall.param.MessageStatisticsParam;
import com.lawu.eshop.merchant.api.service.MessageService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockMessageService extends BaseController implements MessageService {

	
    @Override
    public Result<Page<MessageDTO>> getMessageList(@PathVariable("userNum") String userNum,@RequestParam("userType") UserTypeEnum userType, @ModelAttribute MessageParam pageParam) {
        return successCreated();
    }

    @Override
    public Result updateMessageStatus(@PathVariable("messageId") Long messageId, @RequestParam("userNum") String userNum) {
        return successCreated();
    }

    @Override
    public Result saveMessage(@PathVariable("userNum") String userNum, @ModelAttribute MessageInfoParam messageInfoParam) {
        return successCreated();
    }

    @Override
    public Result delMessageStatus(@PathVariable("messageId") Long messageId, @RequestParam("userNum") String userNum) {
        return successDelete();
    }

    @Override
    public Result<MessageDTO> selectMessageById(@PathVariable("id") Long id) {
        return successGet();
    }

    @Override
    public Result delMessageByIds(@RequestParam("ids") String ids, @RequestParam("userNum") String userNum) {
        return successDelete();
    }

	@Override
	public Result<MessageStatisticsDTO> getMessageStatistics(MessageStatisticsParam param) {
		// TODO Auto-generated method stub
		return null;
	}
}
