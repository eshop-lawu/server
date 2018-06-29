package com.lawu.eshop.cache.srv.service;

/**
 * 
 * 广告可领取数记录
 * @author zhangrc
 * @date 2017/09/12
 *
 */
public interface UserRedPacketCountService {
	
	/**
	 * 存入记录
	 * @param key
	 */
	void setUserRedPacketCountRecord(Long id , Integer count);
	
	/**
	 * 获取数量
	 * @param id
	 * @return
	 */
	Object getUserRedPacketCountRecord(Long id);
	

}
