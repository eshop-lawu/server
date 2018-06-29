package com.lawu.eshop.activity.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.activity.dto.DiamondConfigDTO;
import com.lawu.eshop.activity.dto.PowerTaskConfigDTO;
import com.lawu.eshop.activity.param.DiamondConfigParam;
import com.lawu.eshop.activity.param.MerchantPowerTaskBaseConfigParam;
import com.lawu.eshop.activity.param.PowerTaskBaseConfigParam;
import com.lawu.eshop.activity.srv.bo.DiamondConfigBO;
import com.lawu.eshop.activity.srv.bo.PowerTaskConfigBO;
import com.lawu.eshop.activity.srv.bo.RichConfigBO;
import com.lawu.eshop.activity.srv.domain.RichConfigDO;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.MerchantPowerTaskConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigBaseCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigCacheParam;
import com.lawu.utils.DateUtil;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public class RichConfigConverter {
	
	public static PowerTaskConfigCacheParam converterCacheParam(List<PowerTaskBaseConfigParam> params,List<MerchantPowerTaskBaseConfigParam> merchantParams ) {

		PowerTaskConfigCacheParam cache = new PowerTaskConfigCacheParam();
		List<PowerTaskConfigBaseCacheParam> list = new ArrayList<>();
		for (PowerTaskBaseConfigParam powerTaskBaseConfigParam : params) {
			PowerTaskConfigBaseCacheParam param = new PowerTaskConfigBaseCacheParam();
			param.setPowerCount(powerTaskBaseConfigParam.getPowerCount());
			param.setStatus(powerTaskBaseConfigParam.getStatus());
			param.setTaskCount(powerTaskBaseConfigParam.getTaskCount());
			param.setType(powerTaskBaseConfigParam.getType());
			param.setBeginTime(powerTaskBaseConfigParam.getBeginTime());
			param.setEndTime(powerTaskBaseConfigParam.getEndTime());
			list.add(param);
		}
		List<MerchantPowerTaskConfigCacheParam> merchantList = new ArrayList<>();
		for(MerchantPowerTaskBaseConfigParam configParam : merchantParams){
			MerchantPowerTaskConfigCacheParam cacheParam = new MerchantPowerTaskConfigCacheParam();
			cacheParam.setPowerCount(configParam.getPowerCount());
			cacheParam.setStatus(configParam.getStatus());
			cacheParam.setTaskCount(configParam.getTaskCount());
			cacheParam.setType(configParam.getType());
			cacheParam.setBeginTime(configParam.getBeginTime());
			cacheParam.setEndTime(configParam.getEndTime());
			merchantList.add(cacheParam);
		}
		cache.setTasks(list);
		cache.setMerchantTasks(merchantList);
		return cache;
	}

	public static PowerTaskConfigBO converterPowerTaskBO(RichConfigDO richConfigDO) {
		PowerTaskConfigBO config = new PowerTaskConfigBO();
		if(richConfigDO == null || richConfigDO.getPowerTaskConfig() == null){
			return config;
		}
		config.setPowerEffectiveTime(richConfigDO.getPowerEffectiveTime());
		config.setTasks(JSON.parseArray(richConfigDO.getPowerTaskConfig(),PowerTaskBaseConfigParam.class));
		config.setMerchantPowerEffectiveTime(richConfigDO.getMerchantPowerEffectiveTime());
		config.setMerchantTasks(richConfigDO.getMerchantPowerTaskConfig() == null ? new ArrayList<>() : JSON.parseArray(richConfigDO.getMerchantPowerTaskConfig(), MerchantPowerTaskBaseConfigParam.class));
		return config;
	}
	
	
	public static PowerTaskConfigDTO converterPowerTaskDTO(PowerTaskConfigBO richConfigBO) {
		PowerTaskConfigDTO config = new PowerTaskConfigDTO();
		if(richConfigBO == null){
			return config;
		}
		config.setPowerEffectiveTime(richConfigBO.getPowerEffectiveTime());
		config.setTasks(richConfigBO.getTasks());
		config.setMerchantPowerEffectiveTime(richConfigBO.getMerchantPowerEffectiveTime());
		config.setMerchantTasks(richConfigBO.getMerchantTasks());
		if(richConfigBO.getTasks() != null){
			for (PowerTaskBaseConfigParam powerTaskBaseConfigParam : richConfigBO.getTasks()) {
				if(powerTaskBaseConfigParam.getType() == PowerTaskTypeEnum.INVITE){
					config.setIsOpenInviteTime(DateUtil.belongCalendar(new Date(), powerTaskBaseConfigParam.getBeginTime(), powerTaskBaseConfigParam.getEndTime()));
				}
				
			}
		}
		if(!richConfigBO.getMerchantTasks().isEmpty()){
			for (MerchantPowerTaskBaseConfigParam param : richConfigBO.getMerchantTasks()) {
				if(param.getType() == MerchantPowerTaskTypeEnum.INVITE_FRIEND){
					config.setIsMerchantOpenInviteTime(DateUtil.belongCalendar(new Date(), param.getBeginTime(), param.getEndTime()));
				}
				if(param.getType() == MerchantPowerTaskTypeEnum.ACTIVITY){
					config.setIsActiveTime(DateUtil.belongCalendar(new Date(), param.getBeginTime(), param.getEndTime()));
				}

			}
		}
		
		return config;
	}

	public static RichConfigBO convertRichConfigBO(List<RichConfigDO> list) {
		if(list.size()==0){
			return null; 
		}
		RichConfigBO bo =new RichConfigBO();
		bo.setDiamondConfig(list.get(0).getDiamondConfig());
		bo.setDiamondEffectiveTime(list.get(0).getDiamondEffectiveTime());
		bo.setNotice(list.get(0).getNotice());
		bo.setPower_effective_time(list.get(0).getPowerEffectiveTime());
		bo.setPower_task_config(list.get(0).getPowerTaskConfig());
		return bo;
	}

	public static DiamondConfigBO convertDiamondConfigBO(DiamondConfigParam param) {
		DiamondConfigBO configBO = new DiamondConfigBO();
		if (param == null) {
			return configBO;
		}

		configBO.setRichPopulation(param.getRichPopulation());
		configBO.setCreationPeople(param.getCreationPeople());
		configBO.setDailyDiamond(param.getDailyDiamond());
		configBO.setOnceDiamond(param.getOnceDiamond());
		configBO.setDailyDiamondLucky(param.getDailyDiamondLucky());
		configBO.setOnceDiamondLucky(param.getOnceDiamondLucky());
		configBO.setGainDiamondLuckyPower(param.getGainDiamondLuckyPower());
		configBO.setGainDiamondLuckyConsumePower(param.getGainDiamondLuckyConsumePower());
		configBO.setInitPower(param.getInitPower());
		configBO.setGainDiamondLuckyScale(param.getGainDiamondLuckyScale());
		configBO.setInitReleaseTime(param.getInitReleaseTime());
		configBO.setReleaseInterval(param.getReleaseInterval());
		configBO.setIsOpen(param.getIsOpen());
		configBO.setDiamondGrowTime(param.getDiamondGrowTime());
		return configBO;
	}

	public static DiamondConfigDTO convertDiamondConfigDTO(DiamondConfigBO configBO) {
		DiamondConfigDTO configDTO = new DiamondConfigDTO();
		if (configBO == null) {
			return configDTO;
		}

		configDTO.setRichPopulation(configBO.getRichPopulation());
		configDTO.setCreationPeople(configBO.getCreationPeople());
		configDTO.setDailyDiamond(configBO.getDailyDiamond());
		configDTO.setOnceDiamond(configBO.getOnceDiamond());
		configDTO.setDailyDiamondLucky(configBO.getDailyDiamondLucky());
		configDTO.setOnceDiamondLucky(configBO.getOnceDiamondLucky());
		configDTO.setGainDiamondLuckyPower(configBO.getGainDiamondLuckyPower());
		configDTO.setGainDiamondLuckyConsumePower(configBO.getGainDiamondLuckyConsumePower());
		configDTO.setInitPower(configBO.getInitPower());
		configDTO.setGainDiamondLuckyScale(configBO.getGainDiamondLuckyScale());
		configDTO.setInitReleaseTime(configBO.getInitReleaseTime());
		configDTO.setReleaseInterval(configBO.getReleaseInterval());
		configDTO.setIsOpen(configBO.getIsOpen());
		configDTO.setDiamondGrowTime(configBO.getDiamondGrowTime());
		configDTO.setDiamondEffectiveTime(configBO.getDiamondEffectiveTime());
		configDTO.setNotice(configBO.getNotice());
		return configDTO;
	}

	public static DiamondConfigCacheParam convertCacheParam(DiamondConfigBO configBO) {
		if (configBO == null) {
			return null;
		}

		DiamondConfigCacheParam cacheParam = new DiamondConfigCacheParam();
		cacheParam.setRichPopulation(configBO.getRichPopulation());
		cacheParam.setCreationPeople(configBO.getCreationPeople());
		cacheParam.setDailyDiamond(configBO.getDailyDiamond());
		cacheParam.setOnceDiamond(configBO.getOnceDiamond());
		cacheParam.setDailyDiamondLucky(configBO.getDailyDiamondLucky());
		cacheParam.setOnceDiamondLucky(configBO.getOnceDiamondLucky());
		cacheParam.setGainDiamondLuckyPower(configBO.getGainDiamondLuckyPower());
		cacheParam.setGainDiamondLuckyConsumePower(configBO.getGainDiamondLuckyConsumePower());
		cacheParam.setInitPower(configBO.getInitPower());
		cacheParam.setGainDiamondLuckyScale(configBO.getGainDiamondLuckyScale());
		cacheParam.setInitReleaseTime(configBO.getInitReleaseTime());
		cacheParam.setReleaseInterval(configBO.getReleaseInterval());
		cacheParam.setIsOpen(configBO.getIsOpen());
		cacheParam.setDiamondGrowTime(configBO.getDiamondGrowTime());
		return cacheParam;
	}

}
