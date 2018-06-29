package com.lawu.eshop.jobs.service;

import java.math.BigDecimal;
import java.util.Map;

public interface CommonPropertyService {

	/**
	 * 广告提成比例
	 * @return
	 */
	Map<String,BigDecimal> getAdCommissionPropertys_bak();

	Map<String,BigDecimal> getAdCommissionPropertys();

	/**
	 * 销售和营业额提成比例
	 * @return
	 */
	Map<String, BigDecimal> getSaleCommissionPropertys_bak();

	Map<String, BigDecimal> getSaleCommissionPropertys();

	/**
	 *
	 * @return
	 */
	Map<String, Object> getPropertys();
}
