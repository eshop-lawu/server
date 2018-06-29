package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.service.ShoppingRefundDetailService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 等待商家退款、类型为退款
 * 平台提示商家操作
 * 
 * @author jiangxinjun
 * @createDate 2017年11月15日
 * @updateDate 2017年11月15日
 */
public class ShoppingRefundAutoRemindToBeRefundWithRefundJob extends AbstractTxPageJob<ShoppingOrderItemExtendDO> {

    @Autowired
    private ShoppingRefundDetailService shoppingRefundDetailService;
    
    @Override
    public List<ShoppingOrderItemExtendDO> queryPage(int offset, int pageSize) {
        return shoppingRefundDetailService.selectAutoRemindToBeRefundWithRefund(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderItemExtendDO entity) {
        shoppingRefundDetailService.refundRemind(entity);
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
