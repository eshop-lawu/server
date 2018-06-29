package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.service.ShoppingRefundDetailService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 退款中-等待买家退货
 * 买家超时操作
 * 否则自动撤销退款申请
 * 
 * @author jiangxinjun
 * @createDate 2017年4月17日
 * @createDate 2017年11月15日
 */
public class ShoppingRefundToBeReturnJob extends AbstractTxPageJob<ShoppingOrderItemDO> {

    @Autowired
    private ShoppingRefundDetailService shoppingRefundDetailService;
    
    @Override
    public List<ShoppingOrderItemDO> queryPage(int offset, int pageSize) {
        return shoppingRefundDetailService.selectAutoRevokeToBeReturn(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderItemDO entity) {
        shoppingRefundDetailService.executeAutoRevokeRefundRequest(entity);
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
