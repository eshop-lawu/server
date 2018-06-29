package com.lawu.eshop.cache.srv.converter;

import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.eshop.cache.dto.MerchantPowerTaskConfigCacheDTO;
import com.lawu.eshop.cache.dto.PowerTaskConfigBaseCacheDTO;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.MerchantPowerTaskConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigBaseCacheParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月3日
 */
public class RichConfigConverter {
	
	
	public static PowerTaskConfigBaseCacheDTO converterPowerTaskDTO(PowerTaskConfigBaseCacheParam param){
		
		PowerTaskConfigBaseCacheDTO dto = new PowerTaskConfigBaseCacheDTO();
		dto.setPowerCount(param.getPowerCount());
		dto.setTaskCount(param.getTaskCount());
		dto.setStatus(param.getStatus());
		dto.setType(param.getType());
		dto.setBeginTime(param.getBeginTime());
		dto.setEndTime(param.getEndTime());
		return dto;
		
	}

	public static DiamondConfigCacheDTO convertDiamondConfigCacheDTO(DiamondConfigCacheParam cacheParam) {
		if (cacheParam == null) {
			return null;
		}

		DiamondConfigCacheDTO cacheDTO = new DiamondConfigCacheDTO();
		cacheDTO.setRichPopulation(cacheParam.getRichPopulation());
		cacheDTO.setCreationPeople(cacheParam.getCreationPeople());
		cacheDTO.setDailyDiamond(cacheParam.getDailyDiamond());
		cacheDTO.setOnceDiamond(cacheParam.getOnceDiamond());
		cacheDTO.setDailyDiamondLucky(cacheParam.getDailyDiamondLucky());
		cacheDTO.setOnceDiamondLucky(cacheParam.getOnceDiamondLucky());
		cacheDTO.setGainDiamondLuckyPower(cacheParam.getGainDiamondLuckyPower());
		cacheDTO.setGainDiamondLuckyConsumePower(cacheParam.getGainDiamondLuckyConsumePower());
		cacheDTO.setInitPower(cacheParam.getInitPower());
		cacheDTO.setGainDiamondLuckyScale(cacheParam.getGainDiamondLuckyScale());
		cacheDTO.setInitReleaseTime(cacheParam.getInitReleaseTime());
		cacheDTO.setReleaseInterval(cacheParam.getReleaseInterval());
		cacheDTO.setIsOpen(cacheParam.getIsOpen());
		cacheDTO.setDiamondGrowTime(cacheParam.getDiamondGrowTime());
		return cacheDTO;
	}

	public static MerchantPowerTaskConfigCacheDTO converterMerchantPowerTaskDTO(MerchantPowerTaskConfigCacheParam cacheParam) {
		MerchantPowerTaskConfigCacheDTO dto = new MerchantPowerTaskConfigCacheDTO();
		dto.setPowerCount(cacheParam.getPowerCount());
		dto.setTaskCount(cacheParam.getTaskCount());
		dto.setStatus(cacheParam.getStatus());
		dto.setType(cacheParam.getType());
		dto.setBeginTime(cacheParam.getBeginTime());
		dto.setEndTime(cacheParam.getEndTime());
		return dto;
	}
}
