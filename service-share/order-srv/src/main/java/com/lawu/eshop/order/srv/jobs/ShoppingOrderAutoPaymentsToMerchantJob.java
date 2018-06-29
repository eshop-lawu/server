package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 订单收货之后，如果超过退款申请时间，释放商家冻结资金
 * 
 * @author Sunny
 * @createDate 2017年4月17日
 * @updateDate 2017年11月14日
 */
public class ShoppingOrderAutoPaymentsToMerchantJob extends AbstractTxPageJob<ShoppingOrderDO> {

    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Override
    public List<ShoppingOrderDO> queryPage(int offset, int pageSize) {
        return shoppingOrderService.selectAutoReleaseFrozenFundsOrder(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderDO entity) {
        shoppingOrderService.executeAutoReleaseFrozenFundsOrder(entity);
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
