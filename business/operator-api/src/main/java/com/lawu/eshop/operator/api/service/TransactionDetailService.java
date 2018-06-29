package com.lawu.eshop.operator.api.service;

import com.lawu.eshop.property.dto.TransactionDetailBackageDTO;
import com.lawu.eshop.property.param.TransactionDetailQueryForBackageParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author meishuquan
 * @date 2017/5/22
 */
@FeignClient(value = "property-srv")
public interface TransactionDetailService {

	/**
	 * 查询运营后台充值记录
	 * 
	 * @param param
	 *            查询参数
	 * @return
	 */
	@RequestMapping(value = "transactionDetail/getBackageRechargePageList", method = RequestMethod.POST)
	Result<Page<TransactionDetailBackageDTO>> getBackageRechargePageList(@RequestBody TransactionDetailQueryForBackageParam param);

}
