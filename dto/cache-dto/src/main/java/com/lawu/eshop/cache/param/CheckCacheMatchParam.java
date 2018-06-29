package com.lawu.eshop.cache.param;

import com.lawu.eshop.cache.constants.CacheGameTypeEnum;

public class CheckCacheMatchParam {

	private CacheGameTypeEnum type;
	private String key;
	private Object obj;

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

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
