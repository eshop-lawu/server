package com.lawu.eshop.property.srv.service.impl.transaction;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.mq.dto.ad.AdPointNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.param.MerchantAdRefundDataParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.AdService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 广告下架 - 从事务
 * @author zhangrc
 * @date 2017/4/12
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_AD_SRV, tags = MqConstant.TAG_AD_ME_ADD_POINT)
public class AdMerchantAddPointTransactionFollowServiceImpl extends AbstractTransactionFollowService<AdPointNotification, Reply> {

	private Logger log = Logger.getLogger(AdMerchantAddPointTransactionFollowServiceImpl.class);

	@Autowired
	private PropertyInfoDataService propertyInfoDataService;
	@Autowired
	private AdService adService;

	@Override
	public void execute(AdPointNotification notification) {
		
		if(notification.getAdType()==AdTypeEnum.AD_TYPE_FLAT.getVal().byteValue() || notification.getAdType()==AdTypeEnum.AD_TYPE_VIDEO.getVal().byteValue()){
			PropertyInfoDataParam param = new PropertyInfoDataParam();
			param.setPoint(notification.getPoint().toString());
			param.setUserNum(notification.getUserNum());
			param.setBizId(notification.getAdId() == null ? "" : "adDown_"+notification.getAdId().toString());
			param.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.AD_RETURN_POINT);
			param.setGmtExecute(notification.getGmtExecute());
			propertyInfoDataService.doHanlderAddPoint(param);
		}else {
			//E咻 & 红包
			MerchantAdRefundDataParam param = new MerchantAdRefundDataParam();
			param.setAdId(notification.getAdId().toString());
			param.setRefundMoney(notification.getPoint().toString());
			param.setUserNum(notification.getUserNum());
			param.setTradeNo(notification.getTradeNo());
			param.setTransactionPayTypeEnum(TransactionPayTypeEnum.getEnum(notification.getPayType()));
			param.setClientType(notification.getClientType());
			param.setGmtExecute(notification.getGmtExecute());
			try {
				adService.doRefund(param);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}

	}
}
