package com.lawu.eshop.cache.srv.service;

import java.util.Date;
import java.util.List;

import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.MerchantPowerTaskConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigBaseCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigCacheParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public interface RichConfigCacheService {
	
	/**
	 * 保存任务配置
	 * 
	 * @param param
	 */
	void setPowerTaskConfigCache(PowerTaskConfigCacheParam param);
	
	
	/**
	 * 获取动力任务
	 * 
	 * @param type
	 * @return
	 */
	PowerTaskConfigBaseCacheParam getPowerTaskConfigCache(PowerTaskTypeEnum type);

	/**
	 * 保存E钻配置
	 *
	 * @param param
	 * @author meishuquan
	 */
	void saveCacheDiamondConfig(DiamondConfigCacheParam param);

	/**
	 * 获取E钻配置
	 *
	 * @return
	 * @author meishuquan
	 */
	DiamondConfigCacheParam getCacheDiamondConfig();
	
	
	/**
	 * 获取动力任务
	 * 
	 * @return
	 */
	List<PowerTaskConfigBaseCacheParam> getPowerTaskConfigCaches();

	/**
	 * 开通E钻账户自动递增序列
	 * @return
	 */
	Integer increaseMemberNum();


	Integer inviteNumberTask(String userNum , Date effectTime);


	Boolean isShoppingTask(String userNum);


	Boolean isGetTask(String userNum, MerchantPowerTaskTypeEnum typeEnum);

	MerchantPowerTaskConfigCacheParam getMerchantPowerTaskConfigCache(MerchantPowerTaskTypeEnum type);

	List<MerchantPowerTaskConfigCacheParam> getMerchantPowerTaskConfigCaches();
}
