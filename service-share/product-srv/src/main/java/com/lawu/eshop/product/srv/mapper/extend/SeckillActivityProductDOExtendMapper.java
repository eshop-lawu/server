package com.lawu.eshop.product.srv.mapper.extend;

import com.lawu.eshop.product.srv.domain.extend.ProductNumsView;

/**
 * ProductModelDOMapper扩展
 * 
 * @author Sunny
 * @date 2017年6月6日
 */
public interface SeckillActivityProductDOExtendMapper {

    /**
     * 根据id增加关注人数
     * 
     * @param id 主键
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
	void increaseAttentionCount(Long id);
	    
    /**
     * 修改商品剩余数量
     * @param view 参数
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    void editTotalInventory(ProductNumsView view);
}