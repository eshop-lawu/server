package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 自动取消未付款的订单定时任务
 * 
 * @author jiangxinjun
 * @createDate 2017年4月17日
 * @updateDate 2017年11月14日
 */
public class ShoppingOrderAutoCancelOrderJob extends AbstractTxPageJob<ShoppingOrderDO> {

    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Override
    public List<ShoppingOrderDO> queryPage(int offset, int pageSize) {
        return shoppingOrderService.selectAutoCancelOrder(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderDO entity) {
        shoppingOrderService.cancelOrder(null, entity.getId());
    }

    @Override
    public boolean isStatusData() {
        return true;
    }

    @Override
    public boolean continueWhenSinglePageFail() {
        return true;
    }
}
