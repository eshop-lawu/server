package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.eshop.property.constants.LoveTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.ad.UserTakePlatRedPacketNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 领取平台红包 - 从事务
 * 
 * @author zhangrc
 * @date 2017/10/19
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_TAKE_PLAT_RED)
public class UserTakePlatRedTransactionFollowServiceImpl extends AbstractTransactionFollowService<UserTakePlatRedPacketNotification, Reply> {

	@Autowired
	private PropertyInfoDataService propertyInfoDataService;

	@Override
	public void execute(UserTakePlatRedPacketNotification notification) {
		PropertyInfoDataParam param = new PropertyInfoDataParam();
		param.setPoint(notification.getMoney().toString());
		param.setUserNum(notification.getUserNum());
		param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.PLATFORM_RED_SWEEP);
		param.setLoveTypeEnum(LoveTypeEnum.RED_PACKAGE);
		param.setTempBizId(notification.getId() == null ? "0" : notification.getId().toString());
		param.setTransactionDesc(MemberTransactionTypeEnum.PLATFORM_RED_SWEEP.getDescPrefix());
		param.setGmtExecute(notification.getGmtExecute());
		propertyInfoDataService.doHanlderBalanceIncome(param);
	}
}
