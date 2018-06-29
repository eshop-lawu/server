package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.mall.dto.WorkOrderDTO;
import com.lawu.eshop.mall.param.DealWorkOrderParam;
import com.lawu.eshop.mall.query.WorkOrderQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

@FeignClient(value = "mall-srv")
public interface WorkOrderService {

	/**
	 * 处理工单
	 * 
	 * @param workOrderParam
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "workOrder/updateWorkOrder", method = RequestMethod.PUT)
	Result updateWorkOrder(@RequestBody DealWorkOrderParam dealWorkOrderParam);
	
	/**
	 * 查询工单
	 * 
	 * @param workOrderQuery
	 * @return
	 */
	@RequestMapping(value = "workOrder/selectWorkOrder", method = RequestMethod.POST)
	Result<Page<WorkOrderDTO>> selectWorkOrder(@RequestBody WorkOrderQuery workOrderQuery);
}
