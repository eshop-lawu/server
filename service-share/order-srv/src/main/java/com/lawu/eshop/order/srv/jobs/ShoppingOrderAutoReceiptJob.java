package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 商家发货，买家超时收货
 * 平台自动收货
 * 定时任务
 * 
 * @author Sunny
 * @createDate 2017年4月17日
 * @updateDate 2017年11月14日
 */
public class ShoppingOrderAutoReceiptJob extends AbstractTxPageJob<ShoppingOrderDO> {

    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Override
    public List<ShoppingOrderDO> queryPage(int offset, int pageSize) {
        return shoppingOrderService.selectAutoReceiptOrder(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderDO entity) {
        shoppingOrderService.tradingSuccess(entity.getId(), true);
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
