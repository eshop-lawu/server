package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.service.ShoppingRefundDetailService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 退款中-待商家填写收货地址
 * 商家超时操作
 * 自动退款给买家
 * 
 * @author jiangxinjun
 * @createDate 2017年4月17日
 * @updateDate 2017年11月15日
 */
public class ShoppingRefundFillReturnAddressJob extends AbstractTxPageJob<ShoppingOrderItemDO> {
    
    @Autowired
    private ShoppingRefundDetailService shoppingRefundDetailService;
    
    @Override
    public List<ShoppingOrderItemDO> queryPage(int offset, int pageSize) {
        return shoppingRefundDetailService.selectAutoRefundFillReturnAddress(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderItemDO entity) {
        shoppingRefundDetailService.executeAutoRefund(entity);
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
