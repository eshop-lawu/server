package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.service.ShoppingRefundDetailService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 等待商家退款、类型为退货退款
 * 商家操作超时
 * 平台第二次提示商家操作
 * 
 * @author jiangxinjun
 * @createDate 2017年11月15日
 * @updateDate 2017年11月15日
 */
public class ShoppingRefundAutoSecondTimeRemindToBeRefundWithReturnRefundJob extends AbstractTxPageJob<ShoppingOrderItemExtendDO> {

    @Autowired
    private ShoppingRefundDetailService shoppingRefundDetailService;
    
    @Override
    public List<ShoppingOrderItemExtendDO> queryPage(int offset, int pageSize) {
        return shoppingRefundDetailService.selectAutoSecondTimeRemindToBeRefundWithReturnRefund(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderItemExtendDO entity) {
        shoppingRefundDetailService.refundwithReturnRefundRemind(entity);
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
