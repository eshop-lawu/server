package com.lawu.eshop.operator.api.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawu.eshop.order.param.foreign.ShoppingOrderUpdateInfomationForeignParam;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/04/15
 */
public interface ShoppingOrderExtendService {
	
	@SuppressWarnings("rawtypes")
	Result updateInformation(@PathVariable Long id , @RequestBody ShoppingOrderUpdateInfomationForeignParam param);
}
