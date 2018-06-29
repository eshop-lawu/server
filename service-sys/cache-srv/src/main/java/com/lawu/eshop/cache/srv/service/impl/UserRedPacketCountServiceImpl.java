package com.lawu.eshop.cache.srv.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.UserRedPacketCountService;

@Service
public class UserRedPacketCountServiceImpl implements UserRedPacketCountService ,InitializingBean{
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public void setUserRedPacketCountRecord(Long id,Integer count) {
		 String key = KeyConstant.REDIS_KEY_USER_RED_PACKET_COUNT.concat(String.valueOf(id));
		 stringRedisTemplate.opsForValue().set(key, count.toString());
	}

	@Override
	public Object getUserRedPacketCountRecord(Long id) {
		
		String key = KeyConstant.REDIS_KEY_USER_RED_PACKET_COUNT.concat(String.valueOf(id));
		//总数减一
		return stringRedisTemplate.boundValueOps(key).increment(-1);
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		stringRedisTemplate.setEnableTransactionSupport(true);
	}
	

}
