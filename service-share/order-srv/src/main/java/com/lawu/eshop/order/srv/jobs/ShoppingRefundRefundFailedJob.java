package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.ShoppingOrderItemDO;
import com.lawu.eshop.order.srv.service.ShoppingRefundDetailService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 退款中-退款失败
 * 商家拒绝退款
 * 买家超时操作
 * 否则自动撤销退款申请
 * 
 * @author Sunny
 * @createDate 2017年4月17日
 * @updateDate 2017年11月15日
 */
public class ShoppingRefundRefundFailedJob extends AbstractTxPageJob<ShoppingOrderItemDO> {
    
    @Autowired
    private ShoppingRefundDetailService shoppingRefundDetailService;
    
    @Override
    public List<ShoppingOrderItemDO> queryPage(int offset, int pageSize) {
        return shoppingRefundDetailService.selectAutoRevokeRefundFailed(offset, pageSize);
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
