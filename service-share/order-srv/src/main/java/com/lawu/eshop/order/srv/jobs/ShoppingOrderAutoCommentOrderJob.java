package com.lawu.eshop.order.srv.jobs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.service.ShoppingOrderService;
import com.lawu.jobsextend.AbstractTxPageJob;

/**
 * 自动评论定时任务
 * 
 * @author jiangxinjun
 * @createDate 2017年4月15日
 * @updateDate 2017年11月14日
 */
public class ShoppingOrderAutoCommentOrderJob extends AbstractTxPageJob<ShoppingOrderItemExtendDO> {

    @Autowired
    private ShoppingOrderService shoppingOrderService;
    
    @Override
    public List<ShoppingOrderItemExtendDO> queryPage(int offset, int pageSize) {
        return shoppingOrderService.selectAutoCommentOrder(offset, pageSize);
    }

    @Override
    public void executeSingle(ShoppingOrderItemExtendDO entity) {
        shoppingOrderService.executeAutoCommentOrder(entity);
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
