package com.lawu.eshop.cache.param;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public class PowerTaskConfigCacheParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7438436160317004302L;
	
	List<PowerTaskConfigBaseCacheParam> tasks;

	List<MerchantPowerTaskConfigCacheParam> merchantTasks;
	

	public List<PowerTaskConfigBaseCacheParam> getTasks() {
		return tasks;
	}

	public void setTasks(List<PowerTaskConfigBaseCacheParam> tasks) {
		this.tasks = tasks;
	}

	public List<MerchantPowerTaskConfigCacheParam> getMerchantTasks() {
		return merchantTasks;
	}

	public void setMerchantTasks(List<MerchantPowerTaskConfigCacheParam> merchantTasks) {
		this.merchantTasks = merchantTasks;
	}
}
