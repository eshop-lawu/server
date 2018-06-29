package com.lawu.eshop.cache.dto;

import java.util.List;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月4日
 */
public class PowerTasksDTO {
	
	private List<PowerTaskConfigBaseCacheDTO> list;

	private List<MerchantPowerTaskConfigCacheDTO> merchantList;

	public List<PowerTaskConfigBaseCacheDTO> getList() {
		return list;
	}

	public void setList(List<PowerTaskConfigBaseCacheDTO> list) {
		this.list = list;
	}

	public List<MerchantPowerTaskConfigCacheDTO> getMerchantList() {
		return merchantList;
	}

	public void setMerchantList(List<MerchantPowerTaskConfigCacheDTO> merchantList) {
		this.merchantList = merchantList;
	}
}
