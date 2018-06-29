package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 取消未付款的抢购订单定时任务
 * 
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
public class CancelSeckillActivityOrderJob extends AbstractTxPageJob<ShoppingOrderBO> {

    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Override
    public List<ShoppingOrderBO> queryPage(int offset, int pageSize) {
        return shoppingOrderService.selectCancelSeckillActivityOrder(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderBO entity) {
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
