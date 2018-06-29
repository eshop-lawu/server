package com.lawu.eshop.property.srv.service;

import com.lawu.eshop.property.param.MemberRedPacketRefundDataParam;
import com.lawu.eshop.property.param.NotifyCallBackParam;

/**
 * 
 * <p>
 * Description: 用户红包处理
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月13日 下午1:58:23
 *
 */
public interface UserRedPacketService {

	/**
	 * 红包回调处理
	 * @param param
	 * @return
	 */
	int doHandleMemberRedPacketNotify(NotifyCallBackParam param);

	/**
	 * 红包退款
	 * @param param
	 * @return
	 */
	int doRefund(MemberRedPacketRefundDataParam param) throws Exception;
}
