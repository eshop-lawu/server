package com.lawu.eshop.ad.srv.service;

import java.math.BigDecimal;

import com.lawu.eshop.ad.param.InviterBountyParam;
import com.lawu.eshop.ad.param.InviterBountyQueryParam;
import com.lawu.eshop.ad.srv.bo.GetInviterBountyBO;
import com.lawu.eshop.ad.srv.bo.InviterBountyBO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.Page;

/**
 * 邀请奖励金接口
 * 
 * @author zhangrc
 * @date 2017/12/20
 *
 */
public interface InviterBountyService {
	
	/**
	 * 设置邀请奖励金
	 * @param param
	 */
	void saveInviterBounty(InviterBountyParam param);
	
	/**
	 * 禁用邀请奖励金
	 * @param id
	 * @param auditorId
	 */
	void setInviterBounty(Long id,Long auditorId);
	
	/**
	 * 邀请奖励金列表查询
	 * @param query
	 * @return
	 */
	Page<InviterBountyBO> queryInviterBounty(InviterBountyQueryParam query);

	/**
	 * 领取邀请奖励金
	 * @param userNum
	 * @return
	 */
	GetInviterBountyBO getInviterBounty(String userNum,String regNum,UserTypeEnum userType);
	
	
	/**
	 * 获取发送出去的总邀请奖励金
	 * @return
	 */
	BigDecimal queryInviterBountyTotalMoney();
	
	

}
