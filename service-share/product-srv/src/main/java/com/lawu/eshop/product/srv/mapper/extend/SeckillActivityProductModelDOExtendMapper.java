package com.lawu.eshop.product.srv.mapper.extend;

import com.lawu.eshop.product.srv.domain.extend.ProductModelNumsView;

/**
 * ProductModelDOMapper扩展
 * 
 * @author Sunny
 * @date 2017年6月6日
 */
public interface SeckillActivityProductModelDOExtendMapper {

    /**
     * 修改商品型号剩余数量
     * @param view
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    void editInventory(ProductModelNumsView view);
}