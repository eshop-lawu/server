package com.lawu.eshop.cache.srv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.eshop.cache.dto.MerchantPowerTaskConfigCacheDTO;
import com.lawu.eshop.cache.dto.PowerTaskConfigBaseCacheDTO;
import com.lawu.eshop.cache.dto.PowerTasksDTO;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.MerchantPowerTaskConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigBaseCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigCacheParam;
import com.lawu.eshop.cache.srv.converter.RichConfigConverter;
import com.lawu.eshop.cache.srv.service.RichConfigCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
@RestController
@RequestMapping(value = "richConfig/")
public class RichConfigController extends BaseController{
	
	@Autowired
	private RichConfigCacheService richConfigCacheService;
	
	@RequestMapping(value = "setPowerTaskConfigCache", method = RequestMethod.POST)
    public Result setPowerTaskConfigCache(@RequestBody PowerTaskConfigCacheParam param) {
		richConfigCacheService.setPowerTaskConfigCache(param);
		return successCreated();
    }
	
	
	@RequestMapping(value = "getPowerTaskConfigCache", method = RequestMethod.POST)
    public Result<PowerTaskConfigBaseCacheDTO> getPowerTaskConfigCache(@RequestParam PowerTaskTypeEnum type) {
		PowerTaskConfigBaseCacheParam param = richConfigCacheService.getPowerTaskConfigCache(type);
		return successCreated(RichConfigConverter.converterPowerTaskDTO(param));
    }

	/**
	 * 保存E钻配置
	 *
	 * @param param
	 * @author meishuquan
	 */
	@RequestMapping(value = "saveCacheDiamondConfig", method = RequestMethod.POST)
	public Result saveCacheDiamondConfig(@RequestBody DiamondConfigCacheParam param) {
		richConfigCacheService.saveCacheDiamondConfig(param);
		return successCreated();
	}

	/**
	 * 获取E钻配置
	 *
	 * @return
	 * @author meishuquan
	 */
	@RequestMapping(value = "getCacheDiamondConfig", method = RequestMethod.GET)
	public Result<DiamondConfigCacheDTO> getCacheDiamondConfig() {
		DiamondConfigCacheParam cacheParam = richConfigCacheService.getCacheDiamondConfig();
		return successGet(RichConfigConverter.convertDiamondConfigCacheDTO(cacheParam));
	}
	
	
	/**
	 * 获取所有动力任务
	 * 
	 * @return
	 */
	@RequestMapping(value = "getPowerTaskConfigCaches", method = RequestMethod.POST)
    public Result<PowerTasksDTO> getPowerTaskConfigCaches() {
		List<PowerTaskConfigBaseCacheParam> params = richConfigCacheService.getPowerTaskConfigCaches();
		List<PowerTaskConfigBaseCacheDTO> list = new ArrayList<>();
		for (PowerTaskConfigBaseCacheParam powerTaskConfigBaseCacheParam : params) {
			list.add(RichConfigConverter.converterPowerTaskDTO(powerTaskConfigBaseCacheParam));
		}
		PowerTasksDTO dto = new PowerTasksDTO();
		dto.setList(list);
		return successCreated(dto);
    }
	
	/**
	 * 开通E钻账户自动递增序列
	 * @return
	 */
	@RequestMapping(value="increaseMemberNum",method=RequestMethod.GET)
	public Result<Integer> increaseMemberNum(){
		return successGet(richConfigCacheService.increaseMemberNum());
	}
	
	
	@RequestMapping(value="inviteNumberTask",method=RequestMethod.GET)
	public Result<Integer> inviteNumberTask(@RequestParam("userNum") String userNum ,@RequestParam("effectTime") Date effectTime){
		
		return successGet(richConfigCacheService.inviteNumberTask(userNum,effectTime));
		
	}
	
	
	@RequestMapping(value="isShoppingTask",method=RequestMethod.GET)
	public Result<Boolean> isShoppingTask(@RequestParam("userNum") String userNum){
		
		return successGet(richConfigCacheService.isShoppingTask(userNum));
		
	}
	

	@RequestMapping(value="isGetTask",method=RequestMethod.GET)
	public Result<Boolean> isGetTask(@RequestParam("userNum") String userNum , @RequestParam("typeEnum") MerchantPowerTaskTypeEnum typeEnum){
		return successGet(richConfigCacheService.isGetTask(userNum,typeEnum));

	}


	@RequestMapping(value = "getMerchantPowerTaskConfigCache", method = RequestMethod.POST)
	public Result<MerchantPowerTaskConfigCacheDTO> getMerchantPowerTaskConfigCache(@RequestParam MerchantPowerTaskTypeEnum type) {
		MerchantPowerTaskConfigCacheParam cacheParam = richConfigCacheService.getMerchantPowerTaskConfigCache(type);
		return successCreated(RichConfigConverter.converterMerchantPowerTaskDTO(cacheParam));
	}

	/**
	 * 获取商家所有动力任务
	 *
	 * @return
	 */
	@RequestMapping(value = "getMerchantPowerTaskConfigCaches", method = RequestMethod.POST)
	public Result<PowerTasksDTO> getMerchantPowerTaskConfigCaches() {
		List<MerchantPowerTaskConfigCacheParam> params = richConfigCacheService.getMerchantPowerTaskConfigCaches();
		List<MerchantPowerTaskConfigCacheDTO> list = new ArrayList<>();
		for (MerchantPowerTaskConfigCacheParam param : params) {
			list.add(RichConfigConverter.converterMerchantPowerTaskDTO(param));
		}
		PowerTasksDTO dto = new PowerTasksDTO();
		dto.setMerchantList(list);
		return successCreated(dto);
	}


}
