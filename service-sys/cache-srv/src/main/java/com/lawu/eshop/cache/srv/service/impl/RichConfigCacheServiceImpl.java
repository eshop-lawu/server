package com.lawu.eshop.cache.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.param.DiamondConfigCacheParam;
import com.lawu.eshop.cache.param.MerchantPowerTaskConfigCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigBaseCacheParam;
import com.lawu.eshop.cache.param.PowerTaskConfigCacheParam;
import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.RichConfigCacheService;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
@Service
public class RichConfigCacheServiceImpl implements RichConfigCacheService {
	
	@Autowired
    private RedisTemplate redisTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void setPowerTaskConfigCache(PowerTaskConfigCacheParam params) {
		if (params == null) {
            return;
        }
		BoundHashOperations<String, String, PowerTaskConfigBaseCacheParam> hashOperations = redisTemplate.boundHashOps(KeyConstant.RICH_POWER_TASK_CONFIG_MAIN_CACHE);
		for (PowerTaskConfigBaseCacheParam param : params.getTasks()) {
			hashOperations.put(KeyConstant.RICH_POWER_TASK_CONFIG_DETAIL_CACHE.concat(param.getType().toString()), param);
		}

		BoundHashOperations<String, String, MerchantPowerTaskConfigCacheParam> merchantOperations = redisTemplate.boundHashOps(KeyConstant.RICH_POWER_TASK_CONFIG_MAIN_CACHE_MERCHANT);
		for (MerchantPowerTaskConfigCacheParam merchantParam : params.getMerchantTasks()) {
			merchantOperations.put(KeyConstant.RICH_POWER_TASK_CONFIG_DETAIL_CACHE_MERCHANT.concat(merchantParam.getType().toString()), merchantParam);
		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public PowerTaskConfigBaseCacheParam getPowerTaskConfigCache(PowerTaskTypeEnum type) {
		BoundHashOperations<String, String, PowerTaskConfigBaseCacheParam> hashOperations = redisTemplate.boundHashOps(KeyConstant.RICH_POWER_TASK_CONFIG_MAIN_CACHE);
		PowerTaskConfigBaseCacheParam param = hashOperations.get(KeyConstant.RICH_POWER_TASK_CONFIG_DETAIL_CACHE.concat(type.toString()));
		/*Map<String, PowerTaskConfigBaseCacheParam> map = hashOperations.entries();
		for (String key : map.keySet()) {
			PowerTaskConfigBaseCacheParam param2 = map.get(key);
			System.out.println(param2.getPowerCount());
		}*/
		return param;
	}

	@Override
	public void saveCacheDiamondConfig(DiamondConfigCacheParam param) {
		if (param == null) {
			return;
		}
		redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_RICH_DIAMOND_CONFIG, param);
	}

	@Override
	public DiamondConfigCacheParam getCacheDiamondConfig() {
		return (DiamondConfigCacheParam) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_RICH_DIAMOND_CONFIG);
	}


	@Override
	public List<PowerTaskConfigBaseCacheParam> getPowerTaskConfigCaches() {
		BoundHashOperations<String, String, PowerTaskConfigBaseCacheParam> hashOperations = redisTemplate.boundHashOps(KeyConstant.RICH_POWER_TASK_CONFIG_MAIN_CACHE);
		Map<String, PowerTaskConfigBaseCacheParam> map = hashOperations.entries();
		List<PowerTaskConfigBaseCacheParam> list = new ArrayList<PowerTaskConfigBaseCacheParam>();
		for (String key : map.keySet()) {
			list.add(map.get(key));
		}
		return list;
	}
	
	@Override
	public Integer increaseMemberNum() {
		return stringRedisTemplate.boundValueOps(KeyConstant.REDIS_KEY_INCREASEMEMBERNUM_COUNT).increment(1).intValue();
	}


	@Override
	public Integer inviteNumberTask(String userNum ,Date effectTime) {
		
		String key = KeyConstant.REDIS_INVITE_TASKS_COUNT.concat(userNum);
		
		Long valAfterInc = stringRedisTemplate.boundValueOps(key).increment(1);

		Long expire = stringRedisTemplate.getExpire(key);
		if (expire == null || expire == -1) {
			stringRedisTemplate.expireAt(key, effectTime);
		}

		return valAfterInc.intValue();
	}


	@Override
	public Boolean isShoppingTask(String userNum) {
		Boolean flag = stringRedisTemplate.hasKey(KeyConstant.REDIS_SHOPING_TASKS_COUNT.concat(userNum));
		if(flag){
			return flag;
		}
		stringRedisTemplate.opsForValue().set(KeyConstant.REDIS_SHOPING_TASKS_COUNT.concat(userNum), "1",1,TimeUnit.DAYS);
		return false;
	}


	@Override
	public Boolean isGetTask(String userNum, MerchantPowerTaskTypeEnum typeEnum) {
		Boolean flag = stringRedisTemplate.hasKey(KeyConstant.REDIS_GET_TASKS_TODAY_COUNT.concat(typeEnum.toString()).concat(userNum));
		if(flag){
			return flag;
		}
		stringRedisTemplate.opsForValue().set(KeyConstant.REDIS_GET_TASKS_TODAY_COUNT.concat(typeEnum.toString()).concat(userNum), "1",1,TimeUnit.DAYS);
		return false;
	}

	@Override
	public MerchantPowerTaskConfigCacheParam getMerchantPowerTaskConfigCache(MerchantPowerTaskTypeEnum type) {
		BoundHashOperations<String, String, MerchantPowerTaskConfigCacheParam> hashOperations = redisTemplate.boundHashOps(KeyConstant.RICH_POWER_TASK_CONFIG_MAIN_CACHE_MERCHANT);
		return hashOperations.get(KeyConstant.RICH_POWER_TASK_CONFIG_DETAIL_CACHE_MERCHANT.concat(type.toString()));
	}

	@Override
	public List<MerchantPowerTaskConfigCacheParam> getMerchantPowerTaskConfigCaches() {
		BoundHashOperations<String, String, MerchantPowerTaskConfigCacheParam> hashOperations = redisTemplate.boundHashOps(KeyConstant.RICH_POWER_TASK_CONFIG_MAIN_CACHE_MERCHANT);
		Map<String, MerchantPowerTaskConfigCacheParam> map = hashOperations.entries();
		List<MerchantPowerTaskConfigCacheParam> list = new ArrayList<>();
		for (String key : map.keySet()) {
			list.add(map.get(key));
		}
		return list;
	}

}
