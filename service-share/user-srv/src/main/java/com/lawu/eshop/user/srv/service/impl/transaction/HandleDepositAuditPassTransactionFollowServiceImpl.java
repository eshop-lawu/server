package com.lawu.eshop.user.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.property.StoreStatusNotification;
import com.lawu.eshop.mq.dto.user.MerchantStatusEnum;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.srv.service.MerchantAuditService;
import com.lawu.eshop.user.srv.service.MerchantStoreInfoService;

/**
 * @author zhangyong
 * @date 2017/6/7.
 */
@Service("handleDepositAuditPassTransactionFollowServiceImpl")
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_PROPERTY_SRV, tags = MqConstant.TAG_HANDLE_DEPOSIT_AUDIT_PASS)
public class HandleDepositAuditPassTransactionFollowServiceImpl extends AbstractTransactionFollowService<StoreStatusNotification, Reply> {

	@Autowired
	private MerchantStoreInfoService merchantStoreInfoService;

	@Autowired
	private MerchantAuditService merchantAuditService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void execute(StoreStatusNotification notification) {
		merchantStoreInfoService.updateMerchantStoreStatus(notification.getMerchantId(), MerchantStatusEnum.MERCHANT_STATUS_UNCHECK.val);
		if (notification.getShow()) {
			merchantAuditService.setAuditInfoShow(notification.getMerchantId());
		}
	}
}
