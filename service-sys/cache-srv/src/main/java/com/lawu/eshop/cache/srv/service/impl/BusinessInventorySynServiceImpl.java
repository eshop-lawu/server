package com.lawu.eshop.cache.srv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.concurrentqueue.cache.InventoryCacheManager;
import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.BusinessInventorySynService;

/**
 * 秒杀库存同步缓存服务接口实现类
 * 
 * @author jiangxinjun
 * @createDate 2017年11月30日
 * @updateDate 2017年11月30日
 */
@Service
public class BusinessInventorySynServiceImpl implements BusinessInventorySynService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Integer getInventory(String businessKey, Object id) {
        return InventoryCacheManager.getInventoryFromCache(stringRedisTemplate, KeyConstant.REDIS_KEY_BUSINESS_INVENTORY_SYN, businessKey, id);
    }

    @Override
    public void setInventory(String businessKey, Object id, Integer inventory) {
        InventoryCacheManager.setInventoryToCache(stringRedisTemplate, KeyConstant.REDIS_KEY_BUSINESS_INVENTORY_SYN, businessKey, id, inventory);
    }

    @Override
    public Integer decreaseInventory(String businessKey, Object id) {
        return InventoryCacheManager.decreaseInventoryToCache(stringRedisTemplate, KeyConstant.REDIS_KEY_BUSINESS_INVENTORY_SYN, businessKey, id);
    }

    @Override
    public void increaseInventory(String businessKey, Object id) {
        InventoryCacheManager.increaseInventoryToCache(stringRedisTemplate, KeyConstant.REDIS_KEY_BUSINESS_INVENTORY_SYN, businessKey, id);
    }
    
    @Override
    public void clearInventory(String businessKey, Object id) {
        InventoryCacheManager.clearInventoryFromCache(stringRedisTemplate, KeyConstant.REDIS_KEY_BUSINESS_INVENTORY_SYN, businessKey, id);
    }
}
