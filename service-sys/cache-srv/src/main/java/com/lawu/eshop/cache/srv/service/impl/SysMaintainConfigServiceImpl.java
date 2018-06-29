package com.lawu.eshop.cache.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.SysMaintainConfigService;

/** 
 * 
 * @author lihj
 * @date 2018年5月10日
 */
@Service
public class SysMaintainConfigServiceImpl implements SysMaintainConfigService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
	@Override
	public void updateSysMaintainConfig(String content) {
		stringRedisTemplate.opsForValue().set(KeyConstant.REDIS_SYS_MAINTAIN_CONFIG,content);
	}

	@Override
	public String getSysMaintainConfig() {
		String content =stringRedisTemplate.opsForValue().get(KeyConstant.REDIS_SYS_MAINTAIN_CONFIG);
		return content;
	}

}
