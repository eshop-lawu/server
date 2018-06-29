package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.param.DiamondConfigParam;
import com.lawu.eshop.activity.param.MerchantPowerTaskBaseConfigParam;
import com.lawu.eshop.activity.param.MerchantPowerTaskConfigParam;
import com.lawu.eshop.activity.param.PowerTaskBaseConfigParam;
import com.lawu.eshop.activity.param.PowerTaskConfigParam;
import com.lawu.eshop.activity.srv.bo.DiamondConfigBO;
import com.lawu.eshop.activity.srv.bo.PowerTaskConfigBO;
import com.lawu.eshop.activity.srv.bo.RichConfigBO;
import com.lawu.eshop.activity.srv.converter.RichConfigConverter;
import com.lawu.eshop.activity.srv.domain.RichConfigDO;
import com.lawu.eshop.activity.srv.domain.RichConfigDOExample;
import com.lawu.eshop.activity.srv.mapper.RichConfigDOMapper;
import com.lawu.eshop.activity.srv.servcie.RichConfigCacheService;
import com.lawu.eshop.activity.srv.servcie.RichConfigService;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigCacheParam;
import com.lawu.utils.DateUtil;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
@Service
public class RichConfigServiceImpl implements RichConfigService {
	
	@Autowired
	private RichConfigDOMapper richConfigDOMapper;
	
	@Autowired
	private RichConfigCacheService richConfigCacheService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void savePowerTaskConfig(PowerTaskConfigParam param) {
		
		List<RichConfigDO> list = richConfigDOMapper.selectByExampleWithBLOBs(null);
		RichConfigDO record = new RichConfigDO();
		record.setGmtModified(new Date());
		record.setPowerEffectiveTime(param.getPowerEffectiveTime());
		String rangelandConfig = JSON.toJSONString(param.getTasks());
		record.setPowerTaskConfig(rangelandConfig);
		if(list.isEmpty()){
			record.setGmtCreate(new Date());
			richConfigDOMapper.insertSelective(record);
			return;
		}
		record.setId(list.get(0).getId());
		richConfigDOMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public void effectiveJob() {
		List<RichConfigDO> list = richConfigDOMapper.selectByExampleWithBLOBs(null);
		if(list.isEmpty()){
			return;
		}
		int deviationVal=2000*60*60;
		RichConfigDO config =list.get(0);
		if (null != config.getDiamondEffectiveTime() && config.getDiamondEffectiveTime().getTime() <= System.currentTimeMillis()&& System.currentTimeMillis()<= config.getDiamondEffectiveTime().getTime()+deviationVal) {
			DiamondConfigBO configBO = JSONObject.parseObject(config.getDiamondConfig(), DiamondConfigBO.class);
			DiamondConfigCacheParam cacheParam = RichConfigConverter.convertCacheParam(configBO);
			richConfigCacheService.saveCacheDiamondConfig(cacheParam);
		}
	}
	
	
	@Override
	public void effectivePowerJob() {
		List<RichConfigDO> list = richConfigDOMapper.selectByExampleWithBLOBs(null);
		if (list.isEmpty()) {
			return;
		}
		RichConfigDO config = list.get(0);
		List<PowerTaskBaseConfigParam> params = new ArrayList<>();
		if (null != config.getPowerEffectiveTime() && config.getPowerEffectiveTime().getTime() == DateUtil.getNowDate().getTime()) {
			params = JSON.parseArray(config.getPowerTaskConfig(), PowerTaskBaseConfigParam.class);
		}
		List<MerchantPowerTaskBaseConfigParam> merchantParams = new ArrayList<>();
		if (null != config.getMerchantPowerEffectiveTime() && config.getMerchantPowerEffectiveTime().getTime() == DateUtil.getNowDate().getTime()) {
			merchantParams = JSON.parseArray(config.getMerchantPowerTaskConfig(), MerchantPowerTaskBaseConfigParam.class);
		}
		PowerTaskConfigCacheParam param = RichConfigConverter.converterCacheParam(params, merchantParams);
		richConfigCacheService.setPowerTaskConfigCache(param);

	}

	@Override
	public PowerTaskConfigBO getPowerTaskConfig() {
		List<RichConfigDO> list = richConfigDOMapper.selectByExampleWithBLOBs(null);
		if(list.isEmpty()){
			return new PowerTaskConfigBO();
		}
		PowerTaskConfigBO config = RichConfigConverter.converterPowerTaskBO(list.get(0));
		return config;
	}

	@Override
	public RichConfigBO getRichConfig() {
		RichConfigDOExample example =new RichConfigDOExample();
		example.createCriteria();
		List<RichConfigDO> list =richConfigDOMapper.selectByExample(example);
		return RichConfigConverter.convertRichConfigBO(list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDiamondConfig(DiamondConfigParam param) {
		RichConfigDO configDO = new RichConfigDO();
		DiamondConfigBO configBO = RichConfigConverter.convertDiamondConfigBO(param);
		configDO.setDiamondConfig(JSON.toJSONString(configBO));
		String date = DateUtil.getDateFormat(param.getDiamondEffectiveTime(),"yyyy-MM-dd HH");
		date = date+":00:00";
		configDO.setDiamondEffectiveTime(DateUtil.stringToDate(date));
		configDO.setNotice(param.getNotice());
		configDO.setGmtModified(new Date());
		configDO.setMerchantNotice(param.getMerchantNotice());
		long count = richConfigDOMapper.countByExample(null);
		if (count == 0) {
			configDO.setGmtCreate(new Date());
			richConfigDOMapper.insertSelective(configDO);
		} else {
			RichConfigDOExample example = new RichConfigDOExample();
			example.createCriteria();
			richConfigDOMapper.updateByExampleSelective(configDO, example);
		}
		if (param.getImmediatelyCache() == 1) {
			DiamondConfigCacheParam cacheParam = RichConfigConverter.convertCacheParam(configBO);
			richConfigCacheService.saveCacheDiamondConfig(cacheParam);
		}
	}

	@Override
	public DiamondConfigBO getDiamondConfig() {
		List<RichConfigDO> configDOS = richConfigDOMapper.selectByExampleWithBLOBs(null);
		if (configDOS.isEmpty() || StringUtils.isEmpty(configDOS.get(0).getDiamondConfig())) {
			return null;
		}

		RichConfigDO configDO = configDOS.get(0);
		DiamondConfigBO configBO = JSONObject.parseObject(configDO.getDiamondConfig(), DiamondConfigBO.class);
		configBO.setDiamondEffectiveTime(configDO.getDiamondEffectiveTime());
		configBO.setNotice(configDO.getNotice());
		return configBO;
	}

	@Override
	public void saveMerchantPowerTaskConfig(MerchantPowerTaskConfigParam param) {
		List<RichConfigDO> list = richConfigDOMapper.selectByExampleWithBLOBs(null);
		RichConfigDO record = new RichConfigDO();
		record.setGmtModified(new Date());
		record.setMerchantPowerEffectiveTime(param.getPowerEffectiveTime());
		String Config = JSON.toJSONString(param.getTasks());
		record.setMerchantPowerTaskConfig(Config);
		if (list.isEmpty()) {
			record.setGmtCreate(new Date());
			richConfigDOMapper.insertSelective(record);
		} else {
			record.setId(list.get(0).getId());
			richConfigDOMapper.updateByPrimaryKeySelective(record);
		}
	}

}
