package com.lawu.eshop.property.srv.service.impl.transaction;

import com.lawu.eshop.property.param.PropertyInfoDataQueryPointDetailParam;
import com.lawu.eshop.property.srv.service.PointDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.ad.AdPointNotification;
import com.lawu.eshop.mq.dto.ad.reply.AdPointReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 商家投放广告扣除积分 -- 从事务
 * @author zhangrc
 * @date 2017/4/12
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_ME_CUT_POINT)
public class AdMerchantCutPointTransactionFollowServiceImpl extends AbstractTransactionFollowService<AdPointNotification, AdPointReply> {

	@Autowired
	private PropertyInfoDataService propertyInfoDataService;

	@Autowired
	private PointDetailService pointDetailService;

	@Override
	public void execute(AdPointNotification notification) {
		PropertyInfoDataParam param = new PropertyInfoDataParam();
		param.setPoint(notification.getPoint().toString());
		param.setUserNum(notification.getUserNum());
		param.setBizId(notification.getAdId() == null ? "" : notification.getAdId().toString());
		param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.ADD_AD);
		param.setRegionPath(notification.getRegionPath());
		param.setGmtExecute(notification.getGmtExecute());
		propertyInfoDataService.doHanlderMinusPoint(param);

	}
	
	@Override
	public AdPointReply getReply(AdPointNotification notification) {
		AdPointReply  reply = new AdPointReply();
		//校验投放广告是否有扣除积分，查询积分明细记录是否存在
		PropertyInfoDataQueryPointDetailParam param = new PropertyInfoDataQueryPointDetailParam();
		param.setBizId(notification.getAdId() == null ? "" : notification.getAdId().toString());
		param.setUserNum(notification.getUserNum());
		param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.ADD_AD);
		boolean flag = pointDetailService.getPointDetailByUserNumAndBizIdAndType(param);
		reply.setFlag(flag);
		return reply;
	}
}
