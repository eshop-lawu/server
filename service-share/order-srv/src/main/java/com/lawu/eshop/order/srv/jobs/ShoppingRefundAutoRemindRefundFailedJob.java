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
 * 平台提示买家操作是否申请平台介入
 * 
 * @author jiangxinjun
 * @createDate 2017年11月15日
 * @createDate 2017年11月15日
 */
public class ShoppingRefundAutoRemindRefundFailedJob extends AbstractTxPageJob<ShoppingOrderItemDO> {

    @Autowired
    private ShoppingRefundDetailService shoppingRefundDetailService;
    
    @Override
    public List<ShoppingOrderItemDO> queryPage(int offset, int pageSize) {
        return shoppingRefundDetailService.selectAutoRemindRefundFailed(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderItemDO entity) {
        shoppingRefundDetailService.refundFailedRemind(entity);
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
