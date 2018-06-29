package com.lawu.eshop.order.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDOExample;

/**
 * 订单项扩展Mapper
 * 
 * @author Sunny
 * @date 2017年4月21日
 */
public interface ShoppingOrderItemExtendDOMapper {
	
	/**
	 * 根据查询条件查询
	 * 订单项、退款详情、订单
	 * 
	 * @param example
	 * @return
	 * @author Sunny
	 */
    List<ShoppingOrderItemExtendDO> selectByExample(ShoppingOrderItemExtendDOExample example);
    
    /**
     * 根据查询条件查询总数
     * 
     * @param example
     * @return
     * @author Sunny
     */
    long countByExample(ShoppingOrderItemExtendDOExample example);
    
    /**
     * 分页查询
     */
    List<ShoppingOrderItemExtendDO> selectByExampleWithRowbounds(ShoppingOrderItemExtendDOExample example, RowBounds rowBounds);
}