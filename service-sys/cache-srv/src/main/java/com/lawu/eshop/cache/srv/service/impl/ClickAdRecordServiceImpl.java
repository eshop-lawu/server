package com.lawu.eshop.cache.srv.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.ClickAdRecordService;

@Service
public class ClickAdRecordServiceImpl implements ClickAdRecordService{
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	@Override
	public void setClickAdRecord(String key) {
		String newKey = KeyConstant.REDIS_KEY_CLICK_AD.concat(key);
	    stringRedisTemplate.opsForValue().set(newKey, "true",1,TimeUnit.DAYS);
	}

	@Override
	public boolean getClickAdRecord(String key) {
		String newKey = KeyConstant.REDIS_KEY_CLICK_AD.concat(key);
		return stringRedisTemplate.hasKey(newKey);
	}
	
	

}
