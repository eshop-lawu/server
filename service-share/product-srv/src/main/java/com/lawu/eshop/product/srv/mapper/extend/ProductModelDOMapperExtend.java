package com.lawu.eshop.product.srv.mapper.extend;

import com.lawu.eshop.product.srv.domain.extend.ProductModelDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductModelNumsView;

/**
 * ProductModelDOMapper扩展
 * 
 * @author Sunny
 * @date 2017年6月6日
 */
public interface ProductModelDOMapperExtend {

	void editInventory(ProductModelNumsView view);
	
	void editSaleVolume(ProductModelNumsView view);

	void updateByExampleSelective(ProductModelDOView modelDO);
   
}