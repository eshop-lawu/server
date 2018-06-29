package com.lawu.eshop.mall.srv.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.mall.constants.BusinessDepositOperEnum;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.mall.srv.MallSrvConfig;
import com.lawu.eshop.mall.srv.service.MessageService;
import com.lawu.eshop.mq.dto.order.AdDownnNoticeNotification;
import com.lawu.eshop.mq.dto.order.RevokeRefundRequestNoticeNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderNoPaymentNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderOrdersTradingIncomeNoticeNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderRemindShipmentsNotification;
import com.lawu.eshop.mq.dto.order.ShoppingOrderpaymentSuccessfulNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundFillReturnAddressRemindNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundRefuseRefundRemindNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundToBeConfirmedForRefundRemindNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundToBeRefundRemindNotification;
import com.lawu.eshop.mq.dto.order.ShoppingRefundToBeReturnRemindNotification;
import com.lawu.eshop.mq.dto.product.SeckillActivityAboutStartNoticeNotification;
import com.lawu.eshop.mq.dto.property.RefundDepositDoSuccessOrFailureNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.mq.message.impl.AbstractMessageConsumerListener;

/**
 * 
 * @author Sunny
 * @date 2017年4月17日
 */
@Service
public class MessageConsumerListener extends AbstractMessageConsumerListener {

	@Autowired
	private MessageService messageService;
	
	
	@Autowired
	private MallSrvConfig mallSrvConfig;

	@Override
	public void consumeMessage(String topic, String tags, Object message) {
		if (MqConstant.TOPIC_ORDER_SRV.equals(topic)) {
			/*
			 * 用户付款成功提示买家新增一个订单
			 * 发送推送消息提醒买家支付成功
			 */
			if (MqConstant.TAG_PAYMENT_SUCCESSFUL_PUSH.equals(tags)) {
				ShoppingOrderpaymentSuccessfulNotification notification = (ShoppingOrderpaymentSuccessfulNotification) message;
				/*
				 * 发送站内信给商家
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getId());
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_NEW_ORDER);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setOrderNum(notification.getOrderNum());
				messageInfoParam.setImgUrl(notification.getImgUrl());

				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMerchantNum(), messageInfoParam);
				
				/*
				 * 发送站内信以及推送给用户
				 */
				messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getId());
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_PAYMENT_SUCCESS);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setProductName(notification.getProductName());
				messageInfoParam.setImgUrl(notification.getImgUrl());

				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMemberNum(), messageInfoParam);
				return;
			}
			// 用户付款成功提示买家新增一个订单
			if (MqConstant.TAG_ORDER_NO_PAYMENT_PUSH_TO_MEMBER.equals(tags)) {
				ShoppingOrderNoPaymentNotification notification = (ShoppingOrderNoPaymentNotification) message;
				/*
				 * 发送站内信
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getId());
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_ORDER_PAY);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setOrderNum(notification.getOrderNum());
				messageInfoParam.setImgUrl(notification.getImgUrl());
				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMemberNum(), messageInfoParam);
				return;
			}
			
			// 用户申请退款，提示商家处理
			if (MqConstant.TAG_TO_BE_CONFIRMED_FOR_REFUND_REMIND.equals(tags)) {
				ShoppingRefundToBeConfirmedForRefundRemindNotification notification = (ShoppingRefundToBeConfirmedForRefundRemindNotification) message;
				/*
				 * 发送站内信
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getShoppingOrderItemId());
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_REFUND_APPLY);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setUserName(notification.getMemberNickname());
				messageInfoParam.setImgUrl(notification.getImgUrl());
				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMerchantNum(), messageInfoParam);
				return;
			}
			
			// 商家拒绝退款提醒买家
			if (MqConstant.TAG_REFUSE_REFUND_REMIND.equals(tags)) {
				ShoppingRefundRefuseRefundRemindNotification notification = (ShoppingRefundRefuseRefundRemindNotification) message;
				/*
				 * 发送站内信
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getShoppingOrderItemId());
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_REFUND_REFUSE);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setUserName(notification.getMemberNum());
				messageInfoParam.setImgUrl(notification.getImgUrl());
				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMemberNum(), messageInfoParam);
				return;
			}
			
			// 商家填写退货地址，提醒买家退货
			if (MqConstant.TAG_FILL_RETURN_ADDRESS_REMIND.equals(tags)) {
				ShoppingRefundFillReturnAddressRemindNotification notification = (ShoppingRefundFillReturnAddressRemindNotification) message;
				/*
				 * 发送站内信
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getShoppingOrderItemId());
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_RETURN);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setUserName(notification.getMemberNum());
				messageInfoParam.setImgUrl(notification.getImgUrl());
				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMemberNum(), messageInfoParam);
				return;
			}

            // 商家退款成功，提醒买家
            if (MqConstant.TAG_TO_BE_REFUND_REMIND.equals(tags)) {
                ShoppingRefundToBeRefundRemindNotification notification = (ShoppingRefundToBeRefundRemindNotification) message;
                /*
                 * 发送站内信
                 */
                // 组装信息
                MessageInfoParam messageInfoParam = new MessageInfoParam();
                messageInfoParam.setRelateId(notification.getShoppingOrderItemId());
                messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_REFUND_SUCCESS);
                messageInfoParam.setMessageParam(new MessageTempParam());
                messageInfoParam.getMessageParam().setUserName(notification.getMemberNum());
                messageInfoParam.getMessageParam().setRefundAmount(notification.getRefundAmount());
                messageInfoParam.getMessageParam().setRefundPoints(notification.getPoint());
                messageInfoParam.getMessageParam().setActualAmount(notification.getActualAmount());
                messageInfoParam.setImgUrl(notification.getImgUrl());
                // 保存站内信，并且发送个推
                messageService.saveMessage(notification.getMemberNum(), messageInfoParam);
                return;
            }

			// 用户填写退货物流，提醒买家退款
			if (MqConstant.TAG_TO_BE_RETURN_REMIND.equals(tags)) {
				ShoppingRefundToBeReturnRemindNotification notification = (ShoppingRefundToBeReturnRemindNotification) message;
				/*
				 * 发送站内信
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getShoppingOrderItemId());
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_USER_SEND);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setUserName(notification.getMemberNickname());
				messageInfoParam.getMessageParam().setWaybillNum(notification.getWaybillNum());
				messageInfoParam.getMessageParam().setOrderNum(notification.getOrderNum());
				messageInfoParam.setImgUrl(notification.getImgUrl());
				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMerchantNum(), messageInfoParam);
				return;
			}
			
			// 用户付款，商家没有发货，如果超过五天，每隔一天提醒一次
			if (MqConstant.TAG_REMIND_SHIPMENTS.equals(tags)) {
				ShoppingOrderRemindShipmentsNotification notification = (ShoppingOrderRemindShipmentsNotification) message;
				/*
				 * 发送站内信
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_SEND_NOTICE);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setProductCount(notification.getQuantity().intValue());
				messageInfoParam.setImgUrl(mallSrvConfig.getMessageSendNoticeImg());
				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMerchantNum(), messageInfoParam);
				return;
			}
			
			/*
			 *  提醒商家新增一笔订单交易收入
			 *  1、订单自动收货
			 *  2、订单超过退款时间
			 */
			if (MqConstant.TAG_ORDERS_TRADING_INCOME_NOTICE.equals(tags)) {
				ShoppingOrderOrdersTradingIncomeNoticeNotification notification = (ShoppingOrderOrdersTradingIncomeNoticeNotification) message;
				/*
				 * 发送站内信
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getShoppingOrderId());
				messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_ORDER_SUCCESS);

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setOrderAmount(notification.getActualAmount());
				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMerchantNum(), messageInfoParam);
				return;
			}
			
	         /*
             *  提醒商家退款申请已经撤销
             */
            if (MqConstant.TAG_REVOKE_REFUND_REQUEST_NOTICE.equals(tags)) {
                RevokeRefundRequestNoticeNotification notification = (RevokeRefundRequestNoticeNotification) message;
                /*
                 * 发送站内信
                 */
                // 组装信息
                MessageInfoParam messageInfoParam = new MessageInfoParam();
                messageInfoParam.setRelateId(notification.getShoppingOrderItemId());
                messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_REVOKE_REFUND_REQUEST);
                messageInfoParam.setMessageParam(new MessageTempParam());
                messageInfoParam.getMessageParam().setUserName(notification.getMemberNickname());
                messageInfoParam.getMessageParam().setOrderNum(notification.getOrderNum());
                messageInfoParam.setImgUrl(notification.getImgUrl());
                // 保存站内信，并且发送个推
                messageService.saveMessage(notification.getMerchantNum(), messageInfoParam);
                return;
            }
		} else if (MqConstant.TOPIC_PROPERTY_SRV.equals(topic)) {
			/*
			 *  提醒商家申请退款保证金结果通知
			 *
			 */
			if (MqConstant.TAG_PROPERTY_DEPOSIT_DO_RESULT.equals(tags)) {
				RefundDepositDoSuccessOrFailureNotification notification = (RefundDepositDoSuccessOrFailureNotification) message;
				/*
				 * 发送站内信
				 */
				// 组装信息
				MessageInfoParam messageInfoParam = new MessageInfoParam();
				messageInfoParam.setRelateId(notification.getDepositId());
				if (BusinessDepositOperEnum.REFUND_SUCCESS.getVal().equals(notification.getDepositOperEnumVal())) {
					messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_CHECK_DEPOSIT_SUCCESS);
				} else  if(BusinessDepositOperEnum.REFUND_FAILURE.getVal().equals(notification.getDepositOperEnumVal())){
					messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_CHECK_DEPOSIT_FAIL);
				}

				messageInfoParam.setMessageParam(new MessageTempParam());
				messageInfoParam.getMessageParam().setFailReason(notification.getFailureReason());

				// 保存站内信，并且发送个推
				messageService.saveMessage(notification.getMerchantNum(), messageInfoParam);
				return;
			}
		} else if (MqConstant.TOPIC_PRODUCT_SRV.equals(topic)) {
            /*
             *  提醒用户活动即将开始
             *
             */
            if (MqConstant.TAG_SECKILL_ACTIVITY_ABOUT_START_NOTICE.equals(tags)) {
                SeckillActivityAboutStartNoticeNotification notification = (SeckillActivityAboutStartNoticeNotification) message;
                /*
                 * 发送站内信
                 */
                // 组装信息
                MessageInfoParam messageInfoParam = new MessageInfoParam();
                messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_SECKILL_ACTIVITY_ABOUT_START);
                messageInfoParam.setRelateId(notification.getSeckillActivityAttentionId());
                messageInfoParam.setMessageParam(new MessageTempParam());
                messageInfoParam.getMessageParam().setProductName(notification.getProductName());
                
                // 保存站内信，并且发送个推
                messageService.saveMessage(notification.getMemberNum(), messageInfoParam);
                return;
            }
        }else if (MqConstant.TOPIC_AD_SRV.equals(topic)) {
            /*
             * 广告自动下架提醒
             *
             */
            if (MqConstant.TAG_TO_AD_DOWN_NOTICE.equals(tags)) {
            	AdDownnNoticeNotification notification = (AdDownnNoticeNotification) message;
                /*
                 * 发送站内信
                 */
                // 组装信息
                MessageInfoParam messageInfoParam = new MessageInfoParam();
                messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_AD_DOWN);
                messageInfoParam.setRelateId(notification.getAdId());
                messageInfoParam.setMessageParam(new MessageTempParam());
                messageInfoParam.getMessageParam().setAdName(notification.getAdTitle());
                messageInfoParam.getMessageParam().setAdTypeName(notification.getAdType());
                
                // 保存站内信，并且发送个推
                messageService.saveMessage(notification.getMerchantNum(), messageInfoParam);
                return;
            }
        }
	}
}
