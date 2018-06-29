package com.lawu.eshop.cache.srv.service.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.AdViewService;

@Service
public class AdViewServiceImpl implements AdViewService {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	@Override
	public void setAdView(String adId, String memberId) {
		Boolean flag = stringRedisTemplate.opsForSet().isMember(KeyConstant.REDIS_KEY_AD.concat(adId), memberId);
		if (flag) {
			return ;
		}
		stringRedisTemplate.opsForSet().add(KeyConstant.REDIS_KEY_AD.concat(adId), memberId);
		return ;
	}

	@Override
	public Set<String> getAdviews(String adId) {
		String key = KeyConstant.REDIS_KEY_AD.concat(adId);
		return stringRedisTemplate.opsForSet().members(key);
	}

	
	@Override
	public Boolean setMemeberSeeDetail(String adId, String memberId) {
		Boolean flag = stringRedisTemplate.hasKey(KeyConstant.REDIS_KEY_MEMBER_SEE_AD_DETAIL.concat(memberId).concat("_"+adId));
		if(flag){
			return flag;
		}
		stringRedisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_MEMBER_SEE_AD_DETAIL.concat(memberId).concat("_"+adId), "1",1,TimeUnit.DAYS);
		
		return false;
	}
	
	
	
}
