package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.ad.AdPointNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.LoveTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户点E咻广告 - 从事务
 * @author zhangrc
 * @date 2017/4/12
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_USER_ADD_POINT)
public class AdUserAddPointTransactionFollowServiceImpl extends AbstractTransactionFollowService<AdPointNotification, Reply> {

	@Autowired
	private PropertyInfoDataService propertyInfoDataService;

	@Override
	public void execute(AdPointNotification notification) {
		PropertyInfoDataParam param = new PropertyInfoDataParam();
		param.setPoint(notification.getPoint().toString());
		param.setUserNum(notification.getUserNum());
		param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.AD_QZ);
		param.setLoveTypeEnum(LoveTypeEnum.AD_QZ);
		param.setTempBizId(notification.getAdId() == null ? "0" : notification.getAdId().toString());
		param.setTitle(notification.getTitle());
		param.setTransactionDesc(MemberTransactionTypeEnum.AD_QZ.getDescPrefix()+notification.getTitle());
		param.setGmtExecute(notification.getGmtExecute());
		propertyInfoDataService.doHanlderBalanceIncome(param);
	}
}
