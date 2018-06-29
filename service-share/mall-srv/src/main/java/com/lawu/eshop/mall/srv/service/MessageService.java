package com.lawu.eshop.mall.srv.service;

import java.util.List;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mall.constants.MessageQueryTypeEnum;
import com.lawu.eshop.mall.constants.MessageStatusEnum;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageParam;
import com.lawu.eshop.mall.param.MessageQueryParam;
import com.lawu.eshop.mall.param.MessageStatisticsParam;
import com.lawu.eshop.mall.param.OperatorMessageInfoParam;
import com.lawu.eshop.mall.param.OperatorMessageParam;
import com.lawu.eshop.mall.param.PushParam;
import com.lawu.eshop.mall.srv.bo.MessageBO;
import com.lawu.eshop.mall.srv.bo.MessageStatisticsBO;
import com.lawu.eshop.mall.srv.bo.MessageTemplateBO;
import com.lawu.framework.core.page.Page;

/**
 * 站内消息接口
 * Created by Administrator on 2017/3/29.
 */
public interface MessageService {
    /**
     * 查询未读信息总条数
     *
     * @param userNum
     * @return
     */
    int selectNoReadCount(MessageStatisticsParam param);

    /**
     * 查询最后一条未读信息
     *
     * @param userNum
     */
    MessageStatisticsBO selectLastMessage(String userNum);

    /**
     * 查询站内信息列表
     *
     * @param userNum
     * @param pageParam
     * @return
     */
    Page<MessageBO> getMessageList(UserTypeEnum userType ,String userNum, MessageParam pageParam);

    /**
     * 站内信息操作（已读，删除）
     *
     * @param messageId
     * @param statusEnum
     */
    void updateMessageStatus(Long messageId, MessageStatusEnum statusEnum, String userNum);

    /**
     * 新增站内信息
     *
     * @param messageInfoParam
     */
    Integer saveMessage(String userNum, MessageInfoParam messageInfoParam);

    MessageTemplateBO getTemplateByType(MessageTypeEnum typeEnum);

    Integer saveMessageOperator(String userNum ,OperatorMessageInfoParam messageInfoParam);

    void saveMessageToAll(OperatorMessageParam messageInfoParam);

    Page<MessageBO> getOperatorMessageList(MessageQueryParam param);
    
    MessageBO selectMessageId(Long id);
    
    /**
     * 设置cid 并推送消息给用户
     * @param userNum
     * @param typeEnum
     */
    void pushMessageBySetCid(String userNum,MessageTypeEnum typeEnum);

    void batchMessages(String title, String content, List<PushParam> params);
    
    
    
    
}
