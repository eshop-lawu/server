package com.lawu.eshop.mall.srv.service;

import java.util.List;

import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageQueryForeignParam;
import com.lawu.eshop.mall.srv.bo.DiscountPackageBO;
import com.lawu.eshop.mall.srv.bo.DiscountPackageExtendBO;

/**
 * 优惠套餐服务接口
 *
 * @author Sunny
 * @date 2017/06/26
 */
public interface DiscountPackageService {

	/**
	 * 根据商家id查询商家的所有优惠套餐<p>
	 * 用户端
	 * 
	 * @param merchantId
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	List<DiscountPackageBO> listForMember(Long merchantId);
	
	/**
	 * 根据商家id以及查询参数查询商家的所有优惠套餐<p>
	 * 商家端
	 * 
	 * @param merchantId 商家id
	 * @param param 查询参数
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	List<DiscountPackageBO> listForMerchant(Long merchantId, DiscountPackageQueryForeignParam param);

	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id 套餐详情id
	 * @param id 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	DiscountPackageExtendBO get(Long id, Long merchantId);
	
	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id 套餐详情id
	 * @param id 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	DiscountPackageExtendBO get(Long id);
	
	/**
	 * 保存优惠套餐
	 * 
	 * @param merchantId 商家id
	 * @param discountPackageSaveParam 保存参数
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	void save(Long merchantId, DiscountPackageSaveParam discountPackageSaveParam);
	
	/**
	 * 更新优惠套餐
	 * 
	 * @param id
	 * @param discountPackageUpdateParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	void update(Long merchantId, Long id, DiscountPackageUpdateParam discountPackageUpdateParam);
	
	/**
	 * 删除优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	void delete(Long merchantId, Long id);
	
	/**
	 * 上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	void up(Long merchantId, Long id);
	
	/**
	 * 下架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	void down(Long merchantId, Long id);
	
	/**
	 * 批量删除优惠套餐
	 * 
	 * @param merchantId
	 * @param idList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	void delete(Long merchantId, List<Long> idList);
	
	/**
	 * 批量上架优惠套餐
	 * 
	 * @param merchantId
	 * @param idList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	void up(Long merchantId, List<Long> idList);
	
	/**
	 * 批量下架优惠套餐
	 * 
	 * @param merchantId
	 * @param idList
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	void down(Long merchantId, List<Long> idList);

}
