package com.lawu.eshop.cache.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.RecommendStoreService;

/**
 * @author meishuquan
 * @date 2017/7/28.
 */
@Service
public class RecommendStoreServiceImpl implements RecommendStoreService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setNewMerchant(String regionPath, String storeInfo) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_NEW_STORE.concat(regionPath);
        stringRedisTemplate.opsForValue().set(key, storeInfo);
    }

    @Override
    public void setRecommendFoodConsume(String regionPath, String storeInfo) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_CONSUME_STORE.concat(regionPath);
        stringRedisTemplate.opsForValue().set(key, storeInfo);
    }

    @Override
    public void setRecommendFoodComment(String regionPath, String storeInfo) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_COMMENT_STORE.concat(regionPath);
        stringRedisTemplate.opsForValue().set(key, storeInfo);
    }

    @Override
    public String getNewMerchant(String regionPath) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_NEW_STORE.concat(regionPath);
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String getRecommendFoodConsume(String regionPath) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_CONSUME_STORE.concat(regionPath);
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String getRecommendFoodComment(String regionPath) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_COMMENT_STORE.concat(regionPath);
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void delNewMerchant(String regionPath) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_NEW_STORE.concat(regionPath);
        stringRedisTemplate.delete(key);
    }

    @Override
    public void delRecommendFoodConsume(String regionPath) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_CONSUME_STORE.concat(regionPath);
        stringRedisTemplate.delete(key);
    }

    @Override
    public void delRecommendFoodComment(String regionPath) {
        String key = KeyConstant.REDIS_KEY_MERCHANT_COMMENT_STORE.concat(regionPath);
        stringRedisTemplate.delete(key);
    }
}
