package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.mall.param.WorkOrderParam;
import com.lawu.eshop.merchant.api.service.WorkOrderService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author meishuquan
 * @date 2017/8/3.
 */
@Service
public class MockWorkOrderService extends BaseController implements WorkOrderService {
    @Override
    public Result saveWorkOrder(@RequestBody WorkOrderParam workOrderParam) {
        return successCreated();
    }
}
