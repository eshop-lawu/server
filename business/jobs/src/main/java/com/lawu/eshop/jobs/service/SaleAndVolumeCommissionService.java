package com.lawu.eshop.jobs.service;

import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.property.constants.CommissionEnum;

public interface SaleAndVolumeCommissionService {

	/**
	 *
	 * @param shoppingOrderCommissionDTO
	 * @param commissionEnum
	 * @param isTest
	 */
	void commission(ShoppingOrderCommissionDTO shoppingOrderCommissionDTO, CommissionEnum commissionEnum, boolean isTest);

}
