package com.lawu.eshop.activity.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

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
import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigCacheParam;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
public class RichConfigConverterTest {
	
	@Test
	public void  converterCacheParam() {

		List<PowerTaskBaseConfigParam> list = new ArrayList<>();
		
		PowerTaskBaseConfigParam param = new PowerTaskBaseConfigParam();
		param.setPowerCount(10);
		param.setStatus(PowerTaskStatusEnum.ENABLED);
		param.setTaskCount(5);
		param.setType(PowerTaskTypeEnum.AD);
		list.add(param);
		List<MerchantPowerTaskBaseConfigParam> merchantParams = new ArrayList<>();
		MerchantPowerTaskBaseConfigParam configParam = new MerchantPowerTaskBaseConfigParam();
		merchantParams.add(configParam);
		configParam.setPowerCount(10);
		configParam.setStatus(PowerTaskStatusEnum.ENABLED);
		configParam.setTaskCount(5);
		configParam.setType(MerchantPowerTaskTypeEnum.AD);
		merchantParams.add(configParam);
		PowerTaskConfigCacheParam cache = RichConfigConverter.converterCacheParam(list,merchantParams);
		Assert.assertNotNull(cache);
		
	}

	@Test
	public void converterPowerTaskBO() {
		RichConfigDO richConfigDO = new RichConfigDO();
		List<PowerTaskBaseConfigParam> tasks = new ArrayList<>();
		
		for (Integer i = 1; i < 8; i++) {
			PowerTaskBaseConfigParam param = new PowerTaskBaseConfigParam();
			param.setPowerCount(10);
			param.setStatus(PowerTaskStatusEnum.ENABLED);
			param.setType(PowerTaskTypeEnum.getEnum(Byte.parseByte(i.toString())));
			param.setTaskCount(5);
			tasks.add(param);
		}
		String rangelandConfig = JSON.toJSONString(tasks);
		
		richConfigDO.setPowerTaskConfig(rangelandConfig);
		richConfigDO.setPowerEffectiveTime(new Date());
		
		PowerTaskConfigBO config = RichConfigConverter.converterPowerTaskBO(richConfigDO);
		Assert.assertNotNull(config);
	}
	
	
	@Test
	public void converterPowerTaskDTO() {
		PowerTaskConfigBO richConfigBO = new PowerTaskConfigBO();
		List<PowerTaskBaseConfigParam> tasks = new ArrayList<>();
		
		for (Integer i = 1; i < 8; i++) {
			PowerTaskBaseConfigParam param = new PowerTaskBaseConfigParam();
			param.setPowerCount(10);
			param.setStatus(PowerTaskStatusEnum.ENABLED);
			param.setType(PowerTaskTypeEnum.getEnum(Byte.parseByte(i.toString())));
			param.setTaskCount(5);
			tasks.add(param);
		}
		richConfigBO.setTasks(tasks);
		richConfigBO.setPowerEffectiveTime(new Date());
		PowerTaskConfigDTO config = RichConfigConverter.converterPowerTaskDTO(richConfigBO);
		Assert.assertNotNull(config);
	}

	@Test
	public void convertRichConfigBO() {
		List<RichConfigDO> list = new ArrayList<>();
		
		RichConfigDO record = new RichConfigDO();
		
		List<PowerTaskBaseConfigParam> tasks = new ArrayList<>();
		
		for (Integer i = 1; i < 8; i++) {
			PowerTaskBaseConfigParam param = new PowerTaskBaseConfigParam();
			param.setPowerCount(10);
			param.setStatus(PowerTaskStatusEnum.ENABLED);
			param.setType(PowerTaskTypeEnum.getEnum(Byte.parseByte(i.toString())));
			param.setTaskCount(5);
			tasks.add(param);
		}
		String rangelandConfig = JSON.toJSONString(tasks);
		record.setPowerTaskConfig(rangelandConfig);
		record.setPowerEffectiveTime(new Date());
		list.add(record);
		RichConfigBO config = RichConfigConverter.convertRichConfigBO(list);
		Assert.assertNotNull(config);
		
	}

	@Test
	public void convertDiamondConfigBO() {
		DiamondConfigParam param = new DiamondConfigParam();
		
		param.setRichPopulation(10);
		param.setCreationPeople(10);
		param.setDailyDiamond(new BigDecimal("10"));
		param.setOnceDiamond(new BigDecimal("10"));
		param.setDailyDiamondLucky(new BigDecimal("10"));
		param.setOnceDiamondLucky(new BigDecimal("10"));
		param.setGainDiamondLuckyPower(10);
		param.setGainDiamondLuckyConsumePower(10);
		param.setInitPower(10);
		param.setGainDiamondLuckyScale(new BigDecimal("10"));
		param.setInitReleaseTime("3");
		param.setReleaseInterval(3);
		param.setIsOpen(true);
		param.setDiamondGrowTime(3);
		DiamondConfigBO diamondConfigBO = RichConfigConverter.convertDiamondConfigBO(param);
		Assert.assertNotNull(diamondConfigBO);
	}

	@Test
	public void convertDiamondConfigDTO() {
		DiamondConfigBO configBO = new DiamondConfigBO();

		configBO.setRichPopulation(10);
		configBO.setCreationPeople(10);
		configBO.setDailyDiamond(new BigDecimal("10"));
		configBO.setOnceDiamond(new BigDecimal("10"));
		configBO.setDailyDiamondLucky(new BigDecimal("10"));
		configBO.setOnceDiamondLucky(new BigDecimal("10"));
		configBO.setGainDiamondLuckyPower(10);
		configBO.setGainDiamondLuckyConsumePower(10);
		configBO.setInitPower(10);
		configBO.setGainDiamondLuckyScale(new BigDecimal("10"));
		configBO.setInitReleaseTime("3");
		configBO.setReleaseInterval(3);
		configBO.setIsOpen(true);
		configBO.setDiamondGrowTime(3);
		configBO.setDiamondEffectiveTime(new Date());
		configBO.setNotice("test");
		
		DiamondConfigDTO dto = RichConfigConverter.convertDiamondConfigDTO(configBO);
		Assert.assertNotNull(dto);
	}

	@Test
	public void convertCacheParam() {
		DiamondConfigBO configBO = new DiamondConfigBO();

		configBO.setRichPopulation(10);
		configBO.setCreationPeople(10);
		configBO.setDailyDiamond(new BigDecimal("10"));
		configBO.setOnceDiamond(new BigDecimal("10"));
		configBO.setDailyDiamondLucky(new BigDecimal("10"));
		configBO.setOnceDiamondLucky(new BigDecimal("10"));
		configBO.setGainDiamondLuckyPower(10);
		configBO.setGainDiamondLuckyConsumePower(10);
		configBO.setInitPower(10);
		configBO.setGainDiamondLuckyScale(new BigDecimal("10"));
		configBO.setInitReleaseTime("3");
		configBO.setReleaseInterval(3);
		configBO.setIsOpen(true);
		configBO.setDiamondGrowTime(3);
		configBO.setDiamondEffectiveTime(new Date());
		configBO.setNotice("test");
		
		DiamondConfigCacheParam param = RichConfigConverter.convertCacheParam(configBO);
		Assert.assertNotNull(param);
	}

}
