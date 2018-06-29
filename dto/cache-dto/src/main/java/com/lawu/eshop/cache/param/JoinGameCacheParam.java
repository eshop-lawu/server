package com.lawu.eshop.cache.param;

import com.lawu.eshop.cache.constants.CacheGameTypeEnum;

public class JoinGameCacheParam {

	private CacheGameTypeEnum type;
	private String key;
	private String val;

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

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}
