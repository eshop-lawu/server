package com.lawu.eshop.order.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.order.srv.domain.ShoppingCartDOExample;
import com.lawu.eshop.order.srv.domain.extend.ShoppingCartUpdateQuantityDO;

/**
 * 购物车扩展Mapper
 * 
 * @author Sunny
 * @date 2017年6月28日
 */
public interface ShoppingCartExtendDOMapper {
	
	/**
	 * 根据id更新购物车商品数量
	 * 
	 * @param record
	 * @return
	 * @author Sunny
	 * @date 2017年6月28日
	 */
    int updateQuantityByPrimaryKey(ShoppingCartUpdateQuantityDO record);
    
    /**
     * 根据条件查询购物车id集合
     * 
     * @param example
     * @return
     * @author Sunny
     * @date 2017年6月28日
     */
    List<Long> selectIdByExample(ShoppingCartDOExample example);
}