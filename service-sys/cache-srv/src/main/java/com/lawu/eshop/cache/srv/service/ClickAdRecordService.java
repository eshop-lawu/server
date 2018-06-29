package com.lawu.eshop.cache.srv.service;

/**
 * 
 * 广告点击记录
 * @author zhangrc
 * @data 2017/07/14
 *
 */
public interface ClickAdRecordService {

	/**
	 * 存入记录
	 * @param key (用户id+广告id+日期)
	 */
	void setClickAdRecord(String key);
	
	/**
	 * 验证key是否存在
	 * @param key
	 * @return
	 */
	boolean getClickAdRecord(String key);
}
