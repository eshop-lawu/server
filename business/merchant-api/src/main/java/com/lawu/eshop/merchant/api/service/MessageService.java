package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.dto.MessageDTO;
import com.lawu.eshop.mall.dto.MessageStatisticsDTO;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageParam;
import com.lawu.eshop.mall.param.MessageStatisticsParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 站内信息接口
 * Created by zhangyong on 2017/3/29.
 */
@FeignClient(value = "mall-srv")
public interface MessageService {

    @RequestMapping(method = RequestMethod.POST, value = "message/getMessageStatistics")
    Result<MessageStatisticsDTO> getMessageStatistics(@RequestBody MessageStatisticsParam param);

    /**
     * 站内信息列表
     *
     * @param userNum   用户编号
     * @param pageParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "message/getMessageList/{userNum}")
    Result<Page<MessageDTO>> getMessageList(@PathVariable("userNum") String userNum,@RequestParam("userType") UserTypeEnum userType, @ModelAttribute MessageParam pageParam);

    /**
     * 站内信息操作（已读，删除）
     *
     * @param messageId
     */
    @RequestMapping(method = RequestMethod.PUT, value = "message/updateMessageStatus/{messageId}")
    Result updateMessageStatus(@PathVariable("messageId") Long messageId, @RequestParam("userNum") String userNum);

    /**
     * 插入站内消息
     *
     * @param userNum          用户编号
     * @param messageInfoParam 消息参数
     */
    @RequestMapping(method = RequestMethod.POST, value = "message/saveMessage/{userNum}")
    Result saveMessage(@PathVariable("userNum") String userNum, @ModelAttribute MessageInfoParam messageInfoParam);

    @RequestMapping(value = "message/delMessageStatus/{messageId}", method = RequestMethod.DELETE)
    Result delMessageStatus(@PathVariable("messageId") Long messageId, @RequestParam("userNum") String userNum);
    
    @RequestMapping(value = "message/selectMessageById/{id}", method = RequestMethod.GET)
    Result<MessageDTO> selectMessageById(@PathVariable("id") Long id);

    /**
     * 批量删除消息
     * @param ids
     * @return
     */
    @RequestMapping(value = "message/delMessageByIds",method = RequestMethod.DELETE)
    Result delMessageByIds(@RequestParam("ids") String ids, @RequestParam("userNum") String userNum);
}
