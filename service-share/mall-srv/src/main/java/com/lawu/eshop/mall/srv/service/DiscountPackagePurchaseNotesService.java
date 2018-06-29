package com.lawu.eshop.mall.srv.service;

import java.util.List;

import com.lawu.eshop.mall.srv.bo.DiscountPackagePurchaseNotesBO;

/**
 * 优惠套餐购买须知服务接口
 *
 * @author Sunny
 * @date 2017/07/31
 */
public interface DiscountPackagePurchaseNotesService {

	/**
	 * 查询所有的购买须知
	 * 
	 * @return
	 * @author Sunny
	 * @date 2017年7月31日
	 */
	List<DiscountPackagePurchaseNotesBO> list();
	
	/**
	 * 根据id字符串查询购买须知
	 * 
	 * @return
	 * @author Sunny
	 * @date 2017年7月31日
	 */
	List<DiscountPackagePurchaseNotesBO> list(String idsStr);

}
