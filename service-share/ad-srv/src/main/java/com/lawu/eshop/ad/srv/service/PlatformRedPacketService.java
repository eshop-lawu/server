package com.lawu.eshop.ad.srv.service;

import com.lawu.eshop.ad.param.PlatformRedPacketParam;
import com.lawu.eshop.ad.param.PlatformRedPacketQueryParam;
import com.lawu.eshop.ad.srv.bo.GetPlatformRedPacketBO;
import com.lawu.eshop.ad.srv.bo.PlatformRedPacketBO;
import com.lawu.framework.core.page.Page;

/**
 * 平台红包接口
 * 
 * @author zhangrc
 * @date 2017/10/18
 *
 */
public interface PlatformRedPacketService {
	
	/**
	 * 设置平台红包
	 * @param param
	 */
	void saveRedPacket(PlatformRedPacketParam param);
	
	/**
	 * 禁用平台红包
	 * @param id
	 * @param auditorId
	 */
	void setRedPacket(Long id,Long auditorId);
	
	/**
	 * 平台列表查询
	 * @param query
	 * @return
	 */
	Page<PlatformRedPacketBO> queryRedPacket(PlatformRedPacketQueryParam query);

	/**
	 * 领取平台红包
	 * @param userNum
	 * @return
	 */
	GetPlatformRedPacketBO getRedPacket(String userNum);
	
	

}
