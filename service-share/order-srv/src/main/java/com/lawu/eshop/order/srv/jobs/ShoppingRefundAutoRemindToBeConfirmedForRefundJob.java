package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.service.ShoppingRefundDetailService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 退款中-待商家处理
 * 退款类型-退款
 * 商家处理超时定时任务
 * 发送站内信和推送给商家
 * 定时任务
 * 
 * @author jiangxinjun
 * @createDate 2017年11月14日
 * @updateDate 2017年11月14日
 */
public class ShoppingRefundAutoRemindToBeConfirmedForRefundJob extends AbstractTxPageJob<ShoppingOrderItemExtendDO> {
    
    @Autowired
    private ShoppingRefundDetailService shoppingRefundDetailService;
    
    @Override
    public List<ShoppingOrderItemExtendDO> queryPage(int offset, int pageSize) {
        return shoppingRefundDetailService.selectAutoRemindToBeConfirmedForRefund(offset, pageSize);
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
