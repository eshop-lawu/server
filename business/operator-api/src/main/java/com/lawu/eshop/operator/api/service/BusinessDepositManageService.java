package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.property.dto.BusinessDepositQueryDTO;
import com.lawu.eshop.property.param.BusinessDepositOperDataParam;
import com.lawu.eshop.property.param.BusinessDepositQueryDataParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年4月15日 下午3:16:31
 *
 */
@FeignClient(value = "property-srv")
public interface BusinessDepositManageService {

	/**
	 * 保证金列表查询
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "businessDeposit/selectDepositList",method = RequestMethod.POST)
	Result<Page<BusinessDepositQueryDTO>> selectDepositList(@RequestBody BusinessDepositQueryDataParam param);

	/**
	 * 保证金操作
	 * @param param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "businessDeposit/updateBusinessDeposit",method = RequestMethod.POST)
	Result updateBusinessDeposit(@RequestBody BusinessDepositOperDataParam param);


}
