package com.lawu.eshop.mall.srv.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gexin.fastjson.JSONObject;
import com.lawu.eshop.mall.constants.WorkOrderStatusEnum;
import com.lawu.eshop.mall.constants.WorkOrderTypeEnum;
import com.lawu.eshop.mall.param.DealWorkOrderParam;
import com.lawu.eshop.mall.param.WorkOrderParam;
import com.lawu.eshop.mall.query.WorkOrderQuery;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.WorkOrderBO;
import com.lawu.eshop.mall.srv.domain.WorkOrderDO;
import com.lawu.eshop.mall.srv.mapper.WorkOrderDOMapper;
import com.lawu.eshop.mall.srv.service.WorkOrderService;
import com.lawu.framework.core.page.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class WorkOrderServiceImplTest {

	@Autowired
    private WorkOrderService workOrderService;
	
	@Autowired
    private WorkOrderDOMapper workOrderDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveWorkOrder() {
		WorkOrderParam param = new WorkOrderParam();
		param.setAccount("13025458808");
		param.setContent("工单");
		param.setName("hqm");
		param.setUserNum("M1302545");
		param.setWorkOrderTypeEnum(WorkOrderTypeEnum.MERCHANT);
		workOrderService.saveWorkOrder(param);
		List<WorkOrderDO> list = workOrderDOMapper.selectByExample(null);
		System.out.println(JSONObject.toJSON(list));
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateWorkOrder() {
		saveWorkOrder();
		List<WorkOrderDO> list = workOrderDOMapper.selectByExample(null);
		System.out.println(JSONObject.toJSON(list));
		DealWorkOrderParam dealWorkOrderParam = new DealWorkOrderParam();
		dealWorkOrderParam.setAuditorId(1);
		dealWorkOrderParam.setAuditorName("审核人员1");
		dealWorkOrderParam.setId(1L);
		dealWorkOrderParam.setWorkOrderStatusEnum(WorkOrderStatusEnum.REPLIED);
		dealWorkOrderParam.setReplyContent("已回复");
		workOrderService.updateWorkOrder(dealWorkOrderParam);
		list = workOrderDOMapper.selectByExample(null);
		System.out.println(JSONObject.toJSON(list));
    }
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectWorkOrder() {
		saveWorkOrder();
		saveWorkOrder();
		saveWorkOrder();
		saveWorkOrder();
		saveWorkOrder();
		
		WorkOrderQuery workOrderQuery = new WorkOrderQuery();
		workOrderQuery.setWorkOrderTypeEnum(WorkOrderTypeEnum.MERCHANT);
		workOrderQuery.setWorkOrderStatusEnum(WorkOrderStatusEnum.NOT_YET_DEAL);
		workOrderQuery.setCurrentPage(1);
		workOrderQuery.setPageSize(10);
		Page<WorkOrderBO> page = workOrderService.selectWorkOrder(workOrderQuery);
		System.out.println(JSONObject.toJSON(page));
    }
}
