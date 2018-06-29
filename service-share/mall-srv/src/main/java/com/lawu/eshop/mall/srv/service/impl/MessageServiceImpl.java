package com.lawu.eshop.mall.srv.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.lawu.eshop.mall.srv.converter.MessageConverter;
import com.lawu.eshop.mall.srv.domain.MessageDO;
import com.lawu.eshop.mall.srv.domain.MessageDOExample;
import com.lawu.eshop.mall.srv.domain.MessageDOExample.Criteria;
import com.lawu.eshop.mall.srv.domain.MessageTemplateDO;
import com.lawu.eshop.mall.srv.domain.MessageTemplateDOExample;
import com.lawu.eshop.mall.srv.event.EventPublisher;
import com.lawu.eshop.mall.srv.mapper.MessageDOMapper;
import com.lawu.eshop.mall.srv.mapper.MessageTemplateDOMapper;
import com.lawu.eshop.mall.srv.service.MessageService;
import com.lawu.eshop.mq.dto.user.MessagePushInfo;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.framework.core.page.Page;
import com.lawu.mq.message.MessageProducerService;
import com.lawu.utils.DateUtil;

/**
 * message service实现类
 * Created by zhangyong on 2017/3/29.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDOMapper messageDOMapper;


    @Autowired
    private MessageProducerService messageProducerService;

    @Autowired
    private MessageTemplateDOMapper messageTemplateDOMapper;

    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public int selectNoReadCount(MessageStatisticsParam param) {
    	
        MessageDOExample example = new MessageDOExample();
        Criteria criteria = example.createCriteria();
        List<Byte> bytes = new ArrayList<>();
        if(param.getUserType() == UserTypeEnum.MEMBER){
        	if(param.getTypeEnum() !=null){
            	bytes.add(MessageTypeEnum.MESSAGE_TYPE_ORDER_PAY.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_ORDER_START_SEND.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_ORDER_SENDING.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REFUND_AGREE.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REFUND_REFUSE.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REFUND_SUCCESS.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_PAYMENT_SUCCESS.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_RETURN.getVal());
            	if(param.getTypeEnum() == MessageQueryTypeEnum.NOTICE){ //通知
            		criteria.andTypeNotIn(bytes);
            	}else{ //交易记录
            		criteria.andTypeIn(bytes);
            	}
            }
        }else{
        	if(param.getTypeEnum() !=null){
            	bytes.add(MessageTypeEnum.MESSAGE_TYPE_NEW_ORDER.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_SEND_NOTICE.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_RETURN_NOTICE.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REFUND_APPLY.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_USER_SEND.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REVOKE_REFUND_REQUEST.getVal());
            	if(param.getTypeEnum() == MessageQueryTypeEnum.NOTICE){ //通知
            		criteria.andTypeNotIn(bytes);
            	}else{ //交易记录
            		criteria.andTypeIn(bytes);
            	}
            }
        	
        }
        //只查近两个月的消息
        Date now = new Date();
        Date before = DateUtil.add(now,-2, Calendar.MONTH);
        criteria.andUserNumEqualTo(param.getUserNum()).andStatusEqualTo(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val).andGmtCreateBetween(before,now);
        int count = (int)messageDOMapper.countByExample(example);
        return count;
    }

    @Override
    public MessageStatisticsBO selectLastMessage(String userNum) {

        MessageDOExample example = new MessageDOExample();
        example.createCriteria().andStatusEqualTo(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val).andUserNumEqualTo(userNum);
        example.setOrderByClause("id desc");
        List<MessageDO> messageDOS = messageDOMapper.selectByExample(example);
        return messageDOS.isEmpty() ? null : MessageConverter.coverStatisticsBO(messageDOS.get(0));
    }

    @Override
    public Page<MessageBO> getMessageList(UserTypeEnum userType ,String userNum, MessageParam pageParam) {

        MessageDOExample example = new MessageDOExample();
        Criteria criteria = example.createCriteria();
        //只查近两个月的消息
        Date now = new Date();
        Date before = DateUtil.add(now,-2, Calendar.MONTH);
        criteria.andUserNumEqualTo(userNum).andStatusNotEqualTo(MessageStatusEnum.MESSAGE_STATUS_DELETE.val).andGmtCreateBetween(before,now);
        
        if(userType == UserTypeEnum.MEMBER){
        	List<Byte> bytes = new ArrayList<>();
        	if(pageParam.getTypeEnum() !=null){
            	bytes.add(MessageTypeEnum.MESSAGE_TYPE_ORDER_PAY.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_ORDER_START_SEND.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_ORDER_SENDING.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REFUND_AGREE.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REFUND_REFUSE.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REFUND_SUCCESS.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_PAYMENT_SUCCESS.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_RETURN.getVal());
            	if(pageParam.getTypeEnum() == MessageQueryTypeEnum.NOTICE){ //通知
            		criteria.andTypeNotIn(bytes);
            	}else{ //交易记录
            		criteria.andTypeIn(bytes);
            	}
            }
        }else{
        	List<Byte> bytes = new ArrayList<>();
        	if(pageParam.getTypeEnum() !=null){
            	bytes.add(MessageTypeEnum.MESSAGE_TYPE_NEW_ORDER.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_SEND_NOTICE.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_RETURN_NOTICE.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REFUND_APPLY.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_USER_SEND.getVal());
        		bytes.add(MessageTypeEnum.MESSAGE_TYPE_REVOKE_REFUND_REQUEST.getVal());
            	if(pageParam.getTypeEnum() == MessageQueryTypeEnum.NOTICE){ //通知
            		criteria.andTypeNotIn(bytes);
            	}else{ //交易记录
            		criteria.andTypeIn(bytes);
            	}
            }
        	
        }
        
        example.setOrderByClause("id desc");
        //查询总数
        RowBounds rowBounds = new RowBounds(pageParam.getOffset(), pageParam.getPageSize());

        int totalCount = (int)messageDOMapper.countByExample(example);

        Page<MessageBO> page = new Page<>();
        //设置记录总数
        page.setTotalCount(totalCount);
        page.setCurrentPage(pageParam.getCurrentPage());
        List<MessageDO> messageDOS = messageDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<MessageBO> messageBOS = new ArrayList<>();

        for (MessageDO messageDO : messageDOS) {
            MessageBO messageBO = MessageConverter.coverBO(messageDO);
            messageBOS.add(messageBO);
        }
        page.setRecords(messageBOS);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMessageStatus(Long messageId, MessageStatusEnum statusEnum, String userNum) {
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(statusEnum.val);
        MessageDOExample example = new MessageDOExample();
        example.createCriteria().andIdEqualTo(messageId).andUserNumEqualTo(userNum);
        messageDOMapper.updateByExampleSelective(messageDO,example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveMessage(String userNum, MessageInfoParam messageInfoParam) {
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setUserNum(userNum);
        messageDO.setImgUrl(messageInfoParam.getImgUrl());
        messageDO.setType(messageInfoParam.getTypeEnum().getVal());
        messageDO.setRemark(messageInfoParam.getRemark());
        if(messageInfoParam.getIsPush()!=null){
        	messageDO.setIsPush(messageInfoParam.getIsPush());
        }

        //查询类型对应的消息模板
        MessageTemplateDOExample example = new MessageTemplateDOExample();
        example.createCriteria().andTypeEqualTo(messageInfoParam.getTypeEnum().getVal());
        example.setOrderByClause("id desc");
        List<MessageTemplateDO> dos = messageTemplateDOMapper.selectByExample(example);
        if (dos.isEmpty()) {
            return null;
        }

        String content = dos.get(0).getContent();
        /**
         * {0}用户昵称、{1}订单编号、{2}运单编号、{3}余额、{4}充值金额、{5}当前积分、{6}消费金额
         * {7}优惠金额、{8}退款编号、{9}商品名称、{10}收益金额、{11}收益积分、{12}商家名称
         * {13}广告名称、{14}门店名称、{15}消费积分、{16}充值编号、{17}广告类型名称、{18}失败原因
         * {19}代发货商品数量、{20}快递名称、{21}退款金额、{22}订单金额、{23}工单的问题、{24}工单回复的内容
         * {25}充值积分、、{26}活动名称、{27}推荐E友奖励金、{28}实际退款金额、{29}退还积分、{30}分享的用户昵称、{31}游戏类型名称
         */

        //消息替换占位符
        if (messageInfoParam.getMessageParam() != null) {

            content = content.replace("{0}", messageInfoParam.getMessageParam().getUserName() == null ? "" : messageInfoParam.getMessageParam().getUserName());
            content = content.replace("{1}", messageInfoParam.getMessageParam().getOrderNum() == null ? "" : messageInfoParam.getMessageParam().getOrderNum());
            content = content.replace("{2}", messageInfoParam.getMessageParam().getWaybillNum() == null ? "" : messageInfoParam.getMessageParam().getWaybillNum());
            content = content.replace("{3}", messageInfoParam.getMessageParam().getBalance() == null ? "" : messageInfoParam.getMessageParam().getBalance().toString());
            content = content.replace("{4}", messageInfoParam.getMessageParam().getRechargeBalance() == null ? "" : messageInfoParam.getMessageParam().getRechargeBalance().toString());
            content = content.replace("{5}", messageInfoParam.getMessageParam().getPoint() == null ? "" : messageInfoParam.getMessageParam().getPoint().toString());
            content = content.replace("{6}", messageInfoParam.getMessageParam().getExpendAmount() == null ? "" : messageInfoParam.getMessageParam().getExpendAmount().toString());
            content = content.replace("{7}", messageInfoParam.getMessageParam().getFavoredAmount() == null ? "" : messageInfoParam.getMessageParam().getFavoredAmount().toString());
            content = content.replace("{8}", messageInfoParam.getMessageParam().getRefundNum() == null ? "" : messageInfoParam.getMessageParam().getRefundNum());
            content = content.replace("{9}", messageInfoParam.getMessageParam().getProductName() == null ? "" : messageInfoParam.getMessageParam().getProductName());
            content = content.replace("{10}", messageInfoParam.getMessageParam().getEarningAmount() == null ? "" : messageInfoParam.getMessageParam().getEarningAmount().toString());
            content = content.replace("{11}", messageInfoParam.getMessageParam().getEarningPoint() == null ? "" : messageInfoParam.getMessageParam().getEarningPoint().toString());
            content = content.replace("{12}", messageInfoParam.getMessageParam().getMerchantName() == null ? "" : messageInfoParam.getMessageParam().getMerchantName());
            content = content.replace("{13}", messageInfoParam.getMessageParam().getAdName() == null ? "" : messageInfoParam.getMessageParam().getAdName());
            content = content.replace("{14}", messageInfoParam.getMessageParam().getStoreName() == null ? "" : messageInfoParam.getMessageParam().getStoreName());
            content = content.replace("{15}", messageInfoParam.getMessageParam().getExpendPoint() == null ? "" : messageInfoParam.getMessageParam().getExpendPoint().toString());
            content = content.replace("{16}", messageInfoParam.getMessageParam().getRechargeNum() == null ? "" : messageInfoParam.getMessageParam().getRechargeNum());
            content = content.replace("{17}", messageInfoParam.getMessageParam().getAdTypeName() == null ? "" : messageInfoParam.getMessageParam().getAdTypeName());
            content = content.replace("{18}", messageInfoParam.getMessageParam().getFailReason() == null ? "" : messageInfoParam.getMessageParam().getFailReason());
            content = content.replace("{19}", messageInfoParam.getMessageParam().getProductCount() == null ? "" : messageInfoParam.getMessageParam().getProductCount().toString());
            content = content.replace("{20}", messageInfoParam.getMessageParam().getExpressCompanyName() == null ? "" : messageInfoParam.getMessageParam().getExpressCompanyName());
            content = content.replace("{21}", messageInfoParam.getMessageParam().getRefundAmount() == null ? "" : messageInfoParam.getMessageParam().getRefundAmount().toString());
            content = content.replace("{22}", messageInfoParam.getMessageParam().getOrderAmount() == null ? "" : messageInfoParam.getMessageParam().getOrderAmount().toString());
            content = content.replace("{23}", messageInfoParam.getMessageParam().getWorkOrderContent() == null ? "" : messageInfoParam.getMessageParam().getWorkOrderContent());
            content = content.replace("{24}", messageInfoParam.getMessageParam().getReplyWorkOrderContent() == null ? "" : messageInfoParam.getMessageParam().getReplyWorkOrderContent());
            content = content.replace("{25}", messageInfoParam.getMessageParam().getRechargePoint() == null ? "" : messageInfoParam.getMessageParam().getRechargePoint().toString());
            content = content.replace("{26}", messageInfoParam.getMessageParam().getActivityName() == null ? "" : messageInfoParam.getMessageParam().getActivityName());
            content = content.replace("{27}", messageInfoParam.getMessageParam().getInviteBounty() == null ? "" : messageInfoParam.getMessageParam().getInviteBounty().toString());
            content = content.replace("{28}", messageInfoParam.getMessageParam().getActualAmount() == null ? "" : messageInfoParam.getMessageParam().getActualAmount().toString());
            content = content.replace("{29}", messageInfoParam.getMessageParam().getRefundPoints() == null ? "" : messageInfoParam.getMessageParam().getRefundPoints().toString());
            content = content.replace("{30}", messageInfoParam.getMessageParam().getShareUserName() == null ? "" : messageInfoParam.getMessageParam().getShareUserName());
            content = content.replace("{31}", messageInfoParam.getMessageParam().getGameTypeName() == null ? "" : messageInfoParam.getMessageParam().getGameTypeName());
        }
        messageDO.setContent(content);
        messageDO.setTitle(dos.get(0).getTitle());
        if (messageInfoParam.getRelateId() != null && messageInfoParam.getRelateId() > 0) {
            messageDO.setRelateId(messageInfoParam.getRelateId());
        }else{
            messageDO.setRelateId(0L);
        }
        messageDO.setGmtModified(new Date());
        messageDO.setGmtCreate(new Date());
        Integer id = messageDOMapper.insert(messageDO);
        //发送推送
        MessagePushInfo pushInfo = new MessagePushInfo();
        pushInfo.setTitle(dos.get(0).getTitle());
        pushInfo.setContent(content);
        pushInfo.setMessageId(messageDO.getId());
        pushInfo.setUserNum(userNum);
        pushInfo.setMessageType(messageInfoParam.getTypeEnum().getVal());
        if(messageInfoParam.getRelateId() == null){
            pushInfo.setRelateId(0L);
        }else{
            pushInfo.setRelateId(messageInfoParam.getRelateId());
        }
        messageProducerService.sendMessage(MqConstant.TOPIC_MALL_SRV, MqConstant.TAG_GTPUSH, pushInfo);
        return id;
    }

    @Override
    public MessageTemplateBO getTemplateByType(MessageTypeEnum typeEnum) {
        MessageTemplateDOExample example = new MessageTemplateDOExample();
        example.createCriteria().andTypeEqualTo(typeEnum.getVal());
        List<MessageTemplateDO> dos = messageTemplateDOMapper.selectByExample(example);
        if (dos.isEmpty()) {
            return null;
        }
        return MessageConverter.coverTemplateBO(dos.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveMessageOperator(String userNum, OperatorMessageInfoParam messageInfoParam) {
        MessageDO messageDO = new MessageDO();
        messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
        messageDO.setUserNum(userNum);
        messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageDO.setContent(messageInfoParam.getContent());
        messageDO.setGmtCreate(new Date());
        messageDO.setGmtModified(new Date());
        messageDO.setRelateId(0L);
        messageDO.setTitle(messageInfoParam.getTitle());
        int row = messageDOMapper.insert(messageDO);
        //发送推送
        MessagePushInfo pushInfo = new MessagePushInfo();
        pushInfo.setTitle(messageInfoParam.getTitle());
        pushInfo.setContent(messageInfoParam.getContent());
        pushInfo.setMessageId(messageDO.getId());
        pushInfo.setUserNum(userNum);
        pushInfo.setMessageType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
        messageProducerService.sendMessage(MqConstant.TOPIC_MALL_SRV, MqConstant.TAG_GTPUSH, pushInfo);
        return row;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMessageToAll(OperatorMessageParam param) {
        // 发送异步事件批量新增消息记录
        eventPublisher.publishBatchAddMessages(param);
    }

    @Override
    public Page<MessageBO> getOperatorMessageList(MessageQueryParam param) {
        MessageDOExample example = new MessageDOExample();
        if (StringUtils.isNotEmpty(param.getUserNum())) {
            example.createCriteria().andUserNumEqualTo(param.getUserNum()).andStatusNotEqualTo(MessageStatusEnum.MESSAGE_STATUS_DELETE.val);
        } else {
            example.createCriteria().andStatusNotEqualTo(MessageStatusEnum.MESSAGE_STATUS_DELETE.val);
        }
        example.setOrderByClause("id desc");

        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        int total = (int)messageDOMapper.countByExample(example);

        List<MessageDO> messageDOS = messageDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (messageDOS.isEmpty()) {
            return null;
        }
        Page<MessageBO> page = new Page<>();
        List<MessageBO> messageBOS = new ArrayList<>();
        for (MessageDO messageDO : messageDOS) {
            MessageBO messageBO = MessageConverter.coverBO(messageDO);
            messageBOS.add(messageBO);
        }
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount(total);
        page.setRecords(messageBOS);
        return page;
    }

    @Override
    public MessageBO selectMessageId(Long id) {
        MessageDO messageDO = messageDOMapper.selectByPrimaryKey(id);
        return MessageConverter.coverBO(messageDO);
    }

	@Override
	public void pushMessageBySetCid(String userNum,MessageTypeEnum typeEnum) {
		MessageDOExample example = new MessageDOExample();
		example.createCriteria().andIsPushEqualTo(false).andTypeEqualTo(typeEnum.getVal()).andUserNumEqualTo(userNum);
		List<MessageDO> list = messageDOMapper.selectByExample(example);
		for (MessageDO messageDO : list) {
			//发送推送
	        MessagePushInfo pushInfo = new MessagePushInfo();
	        pushInfo.setTitle(messageDO.getTitle());
	        pushInfo.setContent(messageDO.getContent());
	        pushInfo.setMessageId(messageDO.getId());
	        pushInfo.setUserNum(userNum);
	        pushInfo.setMessageType(messageDO.getType());
	        if(messageDO.getRelateId() == null){
	            pushInfo.setRelateId(0L);
	        }else{
	            pushInfo.setRelateId(messageDO.getRelateId());
	        }
	        messageProducerService.sendMessage(MqConstant.TOPIC_MALL_SRV, MqConstant.TAG_GTPUSH, pushInfo);
	        
	        MessageDO record = new MessageDO();
	        record.setIsPush(true);
	        record.setGmtModified(new Date());
	        record.setId(messageDO.getId());
	        
	        messageDOMapper.updateByPrimaryKeySelective(record);
	        
		}
	}

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchMessages(String title, String content, List<PushParam> params) {
        for (PushParam param : params) {
            MessageDO messageDO = new MessageDO();
            messageDO.setStatus(MessageStatusEnum.MESSAGE_STATUS_UNREAD.val);
            messageDO.setUserNum(param.getUserNum());
            messageDO.setType(MessageTypeEnum.MESSAGE_TYPE_PLATFORM_NOTICE.getVal());
            messageDO.setTitle(title);
            messageDO.setRelateId(0L);
            messageDO.setContent(content);
            messageDO.setGmtCreate(new Date());
            messageDO.setGmtModified(new Date());
            messageDOMapper.insert(messageDO);
        }
    }
}
