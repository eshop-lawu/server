package com.lawu.eshop.mall.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.dto.WorkOrderDTO;
import com.lawu.eshop.mall.param.DealWorkOrderParam;
import com.lawu.eshop.mall.param.WorkOrderParam;
import com.lawu.eshop.mall.query.WorkOrderQuery;
import com.lawu.eshop.mall.srv.bo.WorkOrderBO;
import com.lawu.eshop.mall.srv.converter.WorkOrderConverter;
import com.lawu.eshop.mall.srv.service.WorkOrderService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "workOrder/")
public class WorkOrderController extends BaseController {

	@Autowired
    private WorkOrderService workOrderService;
	
	/**
     * 提交工单
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "saveWorkOrder", method = RequestMethod.POST)
    public Result<Integer> saveWorkOrder(@RequestBody WorkOrderParam param) {
        Integer result = workOrderService.saveWorkOrder(param);
        if (result != ResultCode.SUCCESS) {
			return successCreated(result);
		}
		return successCreated();
    }
    
    /**
     * 处理工单
     * 
     * @param dealWorkOrderParam
     * @return
     */
    @RequestMapping(value = "updateWorkOrder", method = RequestMethod.PUT)
    public Result<Integer> updateWorkOrder(@RequestBody DealWorkOrderParam dealWorkOrderParam) {
    	Integer result = workOrderService.updateWorkOrder(dealWorkOrderParam);
    	 if (result != ResultCode.SUCCESS) {
 			return successCreated(result);
 		}
 		return successCreated();
    }
    
    /**
     * 查询工单
     * 
     * @param workOrderQuery
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "selectWorkOrder")
    public Result<Page<WorkOrderDTO>> selectWorkOrder(@RequestBody WorkOrderQuery workOrderQuery) {
    	Page<WorkOrderBO> page = workOrderService.selectWorkOrder(workOrderQuery);
    	List<WorkOrderDTO> list = WorkOrderConverter.convertBoToDto(page.getRecords());
    	Page<WorkOrderDTO> result = new Page<WorkOrderDTO>();
    	result.setRecords(list);
    	result.setTotalCount(page.getTotalCount());
    	result.setCurrentPage(page.getCurrentPage());
    	return successCreated(result);
    }
}
