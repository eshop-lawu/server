package com.lawu.eshop.cache.param;

import com.lawu.eshop.cache.constants.CacheGameTypeEnum;

/**
 * 退出匹配队列
 * 
 * @author lihj
 * @Date 2018年3月21日
 */
public class ExitMatchQueueParam {
	private CacheGameTypeEnum type;
	private String key;

	public CacheGameTypeEnum getType() {
		return type;
	}

	public void setType(CacheGameTypeEnum type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
