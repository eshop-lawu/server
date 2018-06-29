package com.lawu.eshop.cache.srv.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.AdCountService;

@Service
public class AdCountServiceImpl implements AdCountService ,InitializingBean{
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	
	@Override
	public void setAdCountRecord(Long id,Integer count) {
		 String key = KeyConstant.REDIS_KEY_AD_COUNT.concat(id.toString());
		 stringRedisTemplate.opsForValue().set(key, count.toString());
	}

	@Override
	public Object getAdCountRecord(Long id) {
		
		String key = KeyConstant.REDIS_KEY_AD_COUNT.concat(id.toString());
	
		Long count = stringRedisTemplate.boundValueOps(key).increment(-1);
		
		return count<0?-1 :count;
	}
	
	@Override
	public Integer getSurplusCount(Long id) {

		 String key = KeyConstant.REDIS_KEY_BUSINESS_INVENTORY_SYN.concat(KeyConstant.AD_CLICK_KEY).concat(id.toString());
		 String count = stringRedisTemplate.opsForValue().get(key);
		 if(StringUtils.isEmpty(count)){
			 return 0;
		 }
		 return Integer.parseInt(count);
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		stringRedisTemplate.setEnableTransactionSupport(true);
	}

	
	

}
