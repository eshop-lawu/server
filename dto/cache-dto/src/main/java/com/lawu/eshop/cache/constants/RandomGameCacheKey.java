package com.lawu.eshop.cache.constants;

public enum RandomGameCacheKey {

	READY("READY", "准备"),
	START("START", "开始");
	private String val;
	private String name;

	RandomGameCacheKey(String val, String name) {
		this.val = val;
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
