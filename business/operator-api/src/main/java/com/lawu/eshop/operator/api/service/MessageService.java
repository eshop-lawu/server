package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.mall.dto.OperatorMessageDTO;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageQueryParam;
import com.lawu.eshop.mall.param.OperatorMessageInfoParam;
import com.lawu.eshop.mall.param.OperatorMessageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@FeignClient(value = "mall-srv")
public interface MessageService {

    @RequestMapping(value = "message/saveMessageOperator/{userNum}", method = RequestMethod.POST)
    public Result saveMessageOperator(@PathVariable("userNum") String userNum, @ModelAttribute OperatorMessageInfoParam messageInfoParam);

    @RequestMapping(value = "message/saveMessageToAll", method = RequestMethod.POST)
    Result saveMessageToAll(@ModelAttribute OperatorMessageParam param);

    @RequestMapping(value = "message/getOperatorMessageList",method = RequestMethod.POST)
    Result<Page<OperatorMessageDTO>> getOperatorMessageList(@ModelAttribute MessageQueryParam param);

    @RequestMapping(value = "message/delMessageByIds",method = RequestMethod.DELETE)
    Result delMessageByIds(@RequestParam("ids") String ids);

    /**
     * 插入站内消息
     *
     * @param userNum          用户编号
     * @param messageInfoParam 消息参数
     */
    @RequestMapping(method = RequestMethod.POST, value = "message/saveMessage/{userNum}")
    Result saveMessage(@PathVariable("userNum") String userNum, @ModelAttribute MessageInfoParam messageInfoParam);
}
