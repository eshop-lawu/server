package com.lawu.eshop.user.srv.mq;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gexin.fastjson.JSONObject;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mq.dto.user.CommentInfo;
import com.lawu.eshop.mq.dto.user.FansInfo;
import com.lawu.eshop.mq.dto.user.MessagePushInfo;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.srv.UserSrvConfig;
import com.lawu.eshop.user.srv.bo.FansMerchantBO;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.bo.MessagePushBO;
import com.lawu.eshop.user.srv.service.FansMerchantService;
import com.lawu.eshop.user.srv.service.MemberService;
import com.lawu.eshop.user.srv.service.MerchantService;
import com.lawu.eshop.user.srv.service.MerchantStoreInfoService;
import com.lawu.mq.message.impl.AbstractMessageConsumerListener;
import com.lawu.utils.GtPush;

/**
 * @author Leach
 * @date 2017/4/11
 */
@Service
public class MessageConsumerListener extends AbstractMessageConsumerListener {
    private static Logger logger = LoggerFactory.getLogger(MessageConsumerListener.class);

    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String TYPE = "type";
    public static final String RELATE_ID = "relateId";
    
    @Autowired
    private MerchantStoreInfoService merchantStoreInfoService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserSrvConfig userSrvConfig;

    @Autowired
    private FansMerchantService fansMerchantService;

    @Override
    public void consumeMessage(String topic, String tags, Object message) {
        if (MqConstant.TOPIC_ORDER_SRV.equals(topic) && MqConstant.TAG_BUY_NUMBERS.equals(tags)) {
            // 增加买单笔数
            FansInfo fansInfo = (FansInfo) message;
            merchantStoreInfoService.updateMerchantStoreBuyNums(fansInfo.getMerchantId(), fansInfo.getPayOrderCount());
            // 成为粉丝
            FansMerchantBO fansMerchantBO = fansMerchantService.getFansMerchant(fansInfo.getMemberId(),
                    fansInfo.getMerchantId());
            if (fansMerchantBO == null) {
                // 买单增加粉丝
                fansMerchantService.saveFansMerchant(fansInfo.getMerchantId(), fansInfo.getMemberId(),
                        FansMerchantChannelEnum.PAY);
            }

        } else if (MqConstant.TOPIC_ORDER_SRV.equals(topic) && MqConstant.TAG_COMMENTS_COUNT.equals(tags)) {
            // 增加评论人数
            CommentInfo commentInfo = (CommentInfo) message;
            merchantStoreInfoService.addMerchantStoreCommentsCount(commentInfo.getMerchantId(), commentInfo.getCommentsCount());
        } else if (MqConstant.TOPIC_MALL_SRV.equals(topic) && MqConstant.TAG_GTPUSH.equals(tags)) {
            // 发送推送消息
            MessagePushInfo info = (MessagePushInfo) message;
            JSONObject contents = new JSONObject();
            contents.put(TITLE, info.getTitle());
            contents.put(CONTENT, info.getContent());
            contents.put(RELATE_ID, info.getRelateId());
            contents.put(TYPE, MessageTypeEnum.getEnum(info.getMessageType()));
            if (info.getUserNum().contains("M")) {
                MemberBO memberBO = memberService.findMemberByNum(info.getUserNum());
                // 会员单个推送
                GtPush push = new GtPush();
                push.sendMessageToCidCustoms(contents.toString(), memberBO.getGtCid(), info.getTitle(),
                        userSrvConfig.getGtHost(), userSrvConfig.getGtMemberAppKey(),
                        userSrvConfig.getGtMemberMasterSecret(), userSrvConfig.getGtMemberAppId());
            } else {
                // 商家单个推送
                MerchantBO merchantBO = merchantService.findMemberByNum(info.getUserNum());
                GtPush push = new GtPush();
                push.sendMessageToCid(contents.toString(), merchantBO.getGtCid(), info.getTitle(),
                        userSrvConfig.getGtHost(), userSrvConfig.getGtMerchantAppKey(),
                        userSrvConfig.getGtMerchantMasterSecret(), userSrvConfig.getGtMerchantAppId());
            }
        } else if (MqConstant.TOPIC_MALL_SRV.equals(topic) && MqConstant.TAG_GTPUSHALL.equals(tags)) {
            // 发送推送消息
            MessagePushInfo info = (MessagePushInfo) message;
            JSONObject contents = new JSONObject();
            contents.put(TITLE, info.getTitle());
            contents.put(RELATE_ID, info.getRelateId());
            contents.put(CONTENT, info.getContent());
            contents.put(TYPE, MessageTypeEnum.getEnum(info.getMessageType()));
            GtPush push = new GtPush();
            logger.info("gtpush-all-type type:result({}) flag:flag({})", info.getUserType(),
                    UserTypeEnum.MEMBER.getValue().equals(info.getUserType()));
            if (UserTypeEnum.MEMBER.getValue().equals(info.getUserType())) {
                // 推送所有
                push.pushToAllUser(info.getTitle(), contents.toString(), userSrvConfig.getGtHost(),
                        userSrvConfig.getGtMemberAppKey(), userSrvConfig.getGtMemberMasterSecret(),
                        userSrvConfig.getGtMemberAppId());
            } else {
                push.pushToAllCompany(info.getTitle(), contents.toString(), userSrvConfig.getGtHost(),
                        userSrvConfig.getGtMerchantAppKey(), userSrvConfig.getGtMerchantMasterSecret(),
                        userSrvConfig.getGtMerchantAppId());
            }
        } else if (MqConstant.TOPIC_MALL_SRV.equals(topic) && MqConstant.TAG_GTPUSH_AREA.equals(tags)) {
            // 发送推送消息
            MessagePushInfo info = (MessagePushInfo) message;
            JSONObject contents = new JSONObject();
            contents.put(TITLE, info.getTitle());
            contents.put(RELATE_ID, info.getRelateId());
            contents.put(CONTENT, info.getContent());
            contents.put(TYPE, MessageTypeEnum.getEnum(info.getMessageType()));
            GtPush push = new GtPush();
            List<MessagePushBO> messagePushBOS;
            logger.info("gtpush-area-type type:result({}) flag:flag({})", info.getUserType(),
                    UserTypeEnum.MEMBER.getValue().equals(info.getUserType()));
            if (UserTypeEnum.MEMBER.getValue().equals(info.getUserType())) {
                // 推送用户
                messagePushBOS = memberService.findMessagePushList(info.getArea());
                for (MessagePushBO messagePushBO : messagePushBOS) {
                    push.sendMessageToCidCustoms(contents.toString(), messagePushBO.getGtCid(), info.getTitle(),
                            userSrvConfig.getGtHost(), userSrvConfig.getGtMemberAppKey(),
                            userSrvConfig.getGtMemberMasterSecret(), userSrvConfig.getGtMemberAppId());
                }
            } else {
                messagePushBOS = merchantService.findMessagePushList(info.getArea());
                for (MessagePushBO messagePushBO : messagePushBOS) {
                    push.sendMessageToCid(contents.toString(), messagePushBO.getGtCid(), info.getTitle(),
                            userSrvConfig.getGtHost(), userSrvConfig.getGtMerchantAppKey(),
                            userSrvConfig.getGtMerchantMasterSecret(), userSrvConfig.getGtMerchantAppId());
                }
            }

        }
    }
}
