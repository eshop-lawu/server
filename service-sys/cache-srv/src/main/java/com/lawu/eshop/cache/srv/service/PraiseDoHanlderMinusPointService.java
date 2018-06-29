package com.lawu.eshop.cache.srv.service;

public interface PraiseDoHanlderMinusPointService {
	
	/**
	 * 存入记录
	 * @param key
	 */
	void setAdPraiseIsDoPointRecord(String key);
	
	/**
	 * 获取记录
	 * @param id
	 * @return
	 */
	boolean getAdPraiseIsDoPointRecord(String key);

}
