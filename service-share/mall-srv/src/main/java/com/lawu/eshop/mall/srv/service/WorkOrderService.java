package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.param.DealWorkOrderParam;
import com.lawu.eshop.mall.param.WorkOrderParam;
import com.lawu.eshop.mall.query.WorkOrderQuery;
import com.lawu.eshop.mall.srv.bo.WorkOrderBO;
import com.lawu.framework.core.page.Page;

public interface WorkOrderService {
	
	Integer saveWorkOrder(WorkOrderParam param);
	
	Integer updateWorkOrder(DealWorkOrderParam dealWorkOrderParam);

	Page<WorkOrderBO> selectWorkOrder(WorkOrderQuery workOrderQuery);
}
