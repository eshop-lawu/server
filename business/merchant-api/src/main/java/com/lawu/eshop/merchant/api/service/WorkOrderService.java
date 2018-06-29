package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.param.WorkOrderParam;
import com.lawu.framework.web.Result;
/**
 * 工单接口
 * 
 * @author hongqm
 * @date 2017/07/27
 *
 */
@FeignClient(value = "mall-srv")
public interface WorkOrderService {

	
	/**
	 * 用户提交工单
	 * 
	 * @param workOrderParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "workOrder/saveWorkOrder", method = RequestMethod.POST)
	Result saveWorkOrder(@RequestBody WorkOrderParam workOrderParam);
	
}
