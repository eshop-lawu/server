package com.lawu.eshop.cache.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.PraiseDoHanlderMinusPointService;

@Service
public class PraiseDoHanlderMinusPointServiceImpl implements PraiseDoHanlderMinusPointService {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	@Override
	public void setAdPraiseIsDoPointRecord(String adIdAndMemberId) {
		 String key = KeyConstant.REDIS_KEY_AD_PRAISE_POINT_RECORD_.concat(adIdAndMemberId);
		 stringRedisTemplate.opsForValue().set(key,String.valueOf(1));
	}

	@Override
	public boolean getAdPraiseIsDoPointRecord(String adIdAndMemberId) {
		 String key = KeyConstant.REDIS_KEY_AD_PRAISE_POINT_RECORD_.concat(adIdAndMemberId);
		return stringRedisTemplate.hasKey(key);
	}

}
