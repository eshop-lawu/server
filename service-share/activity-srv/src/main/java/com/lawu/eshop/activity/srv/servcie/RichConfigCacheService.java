package com.lawu.eshop.activity.srv.servcie;

import java.util.Date;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.eshop.cache.dto.MerchantPowerTaskConfigCacheDTO;
import com.lawu.eshop.cache.dto.PowerTaskConfigBaseCacheDTO;
import com.lawu.eshop.cache.dto.PowerTasksDTO;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigCacheParam;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
@FeignClient(value = "cache-srv",path="richConfig/")
public interface RichConfigCacheService {
	
	/**
	 * 保存动力任务到缓存
	 * @return
	 */
	@RequestMapping(value = "setPowerTaskConfigCache", method = RequestMethod.POST)
	Result setPowerTaskConfigCache(@RequestBody PowerTaskConfigCacheParam param);


	/**
	 * 获取动力任务配置
	 * @param type 任务类型
	 * @return
	 */
	@RequestMapping(value = "getPowerTaskConfigCache", method = RequestMethod.POST)
    Result<PowerTaskConfigBaseCacheDTO> getPowerTaskConfigCache(@RequestParam("type") PowerTaskTypeEnum type);

	/**
	 * 保存E钻配置
	 *
	 * @param param
	 * @author meishuquan
	 */
	@RequestMapping(value = "saveCacheDiamondConfig", method = RequestMethod.POST)
	Result saveCacheDiamondConfig(@RequestBody DiamondConfigCacheParam param);

	/**
	 * 获取E钻配置
	 *
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "getCacheDiamondConfig", method = RequestMethod.GET)
	Result<DiamondConfigCacheDTO> getCacheDiamondConfig();
	
	
	/**
	 * 获取所有动力任务
	 * 
	 * @return
	 */
	@RequestMapping(value = "getPowerTaskConfigCaches", method = RequestMethod.POST)
    Result<PowerTasksDTO> getPowerTaskConfigCaches();

	/**
	 * 开通E钻账户自动递增序列
	 * @return
	 */
	@RequestMapping(value="increaseMemberNum",method=RequestMethod.GET)
	Result<Integer> increaseMemberNum();
	
	/**
	 * 邀请E友总数
	 * 
	 * @return
	 */
	@RequestMapping(value="inviteNumberTask",method=RequestMethod.GET)
	Result<Integer> inviteNumberTask(@RequestParam("userNum") String userNum,@RequestParam("effectTime") Date effectTime);


	/**
	 * 今日是否购物获得动力任务
	 * 
	 * @param memberNum
	 * @return
	 */
	@RequestMapping(value="isShoppingTask",method=RequestMethod.GET)
	Result<Boolean> isShoppingTask(@RequestParam("userNum") String userNum);
	
	/**
	 * 今日是否获得动力任务
	 * 
	 * @param userNum
	 * @return
	 */
	@RequestMapping(value="isGetTask",method=RequestMethod.GET)
	Result<Boolean> isGetTask(@RequestParam("userNum") String userNum , @RequestParam("typeEnum") MerchantPowerTaskTypeEnum typeEnum);
	
	/**
	 * 获取商家动力任务配置
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "getMerchantPowerTaskConfigCache", method = RequestMethod.POST)
	Result<MerchantPowerTaskConfigCacheDTO> getMerchantPowerTaskConfigCache(@RequestParam("type") MerchantPowerTaskTypeEnum type);

	@RequestMapping(value = "getMerchantPowerTaskConfigCaches", method = RequestMethod.POST)
	Result<PowerTasksDTO> getMerchantPowerTaskConfigCaches();
	
}
