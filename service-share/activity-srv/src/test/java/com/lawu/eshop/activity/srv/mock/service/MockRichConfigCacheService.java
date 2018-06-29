package com.lawu.eshop.activity.srv.mock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.srv.servcie.RichConfigCacheService;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.eshop.cache.dto.MerchantPowerTaskConfigCacheDTO;
import com.lawu.eshop.cache.dto.PowerTaskConfigBaseCacheDTO;
import com.lawu.eshop.cache.dto.PowerTasksDTO;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigCacheParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/23.
 */
@Service
public class MockRichConfigCacheService extends BaseController implements RichConfigCacheService {
    
    public static DiamondConfigCacheDTO diamondConfig;
    
    static {
        diamondConfig = new DiamondConfigCacheDTO();
        diamondConfig.setRichPopulation(100);
        diamondConfig.setCreationPeople(100);
        diamondConfig.setDailyDiamond(BigDecimal.TEN);
        diamondConfig.setOnceDiamond(BigDecimal.valueOf(0.25));
        diamondConfig.setDailyDiamondLucky(BigDecimal.valueOf(100));
        diamondConfig.setOnceDiamondLucky(BigDecimal.valueOf(0.25));
        diamondConfig.setGainDiamondLuckyPower(45);
        diamondConfig.setGainDiamondLuckyConsumePower(5);
        diamondConfig.setInitPower(10);
        diamondConfig.setGainDiamondLuckyScale(new BigDecimal(0.1));
        diamondConfig.setInitReleaseTime("08:00");
        diamondConfig.setReleaseInterval(2);
        diamondConfig.setIsOpen(true);
        diamondConfig.setDiamondGrowTime(2);
    }
    
	@Override
	public Result setPowerTaskConfigCache(PowerTaskConfigCacheParam param) {
		return null;
	}

	@Override
	public Result<PowerTaskConfigBaseCacheDTO> getPowerTaskConfigCache(PowerTaskTypeEnum type) {
		PowerTaskConfigBaseCacheDTO dto  = new PowerTaskConfigBaseCacheDTO();
		dto.setPowerCount(5);
		dto.setStatus(PowerTaskStatusEnum.ENABLED);
		dto.setTaskCount(5);
		dto.setType(PowerTaskTypeEnum.AD);
		return successGet(dto);
	}

	@Override
	public Result saveCacheDiamondConfig(DiamondConfigCacheParam param) {
		return null;
	}

	@Override
	public Result<DiamondConfigCacheDTO> getCacheDiamondConfig() {
		return successGet(diamondConfig);
	}

	@Override
	public Result<PowerTasksDTO> getPowerTaskConfigCaches() {
		PowerTasksDTO dto = new PowerTasksDTO();
		List<PowerTaskConfigBaseCacheDTO> list = new ArrayList<>();
		for (Integer i = 1; i < 8; i++) {
			PowerTaskConfigBaseCacheDTO cache = new PowerTaskConfigBaseCacheDTO();
			cache.setPowerCount(5);
			cache.setStatus(PowerTaskStatusEnum.ENABLED);
			cache.setTaskCount(5);
			cache.setType(PowerTaskTypeEnum.getEnum(Byte.parseByte(i.toString())));
			list.add(cache);
		}
		dto.setList(list);
		return successGet(dto);
	}

	@Override
	public Result<Integer> increaseMemberNum() {
		return successGet(1);
	}

	@Override
	public Result<Integer> inviteNumberTask(String userNum, Date effectTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Boolean> isShoppingTask(String userNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Boolean> isGetTask(String userNum, MerchantPowerTaskTypeEnum typeEnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<MerchantPowerTaskConfigCacheDTO> getMerchantPowerTaskConfigCache(MerchantPowerTaskTypeEnum type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<PowerTasksDTO> getMerchantPowerTaskConfigCaches() {
		return null;
	}


}
