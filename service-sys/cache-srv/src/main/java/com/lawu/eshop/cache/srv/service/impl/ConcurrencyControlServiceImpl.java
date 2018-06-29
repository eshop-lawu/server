package com.lawu.eshop.cache.srv.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.ConcurrencyControlService;

/**
 * 并发控制接口实现
 * @author jiangxinjun
 * @createDate 2018年3月24日
 * @updateDate 2018年3月24日
 */
@Service
public class ConcurrencyControlServiceImpl implements ConcurrencyControlService {
    
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;
    
    private RedisOperations<String, Long> longRedisOperations;
    
    @Autowired
    public void longRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Long> longRedisTemplate = new RedisTemplate<String, Long>();
        longRedisTemplate.setKeySerializer(new StringRedisSerializer());
        longRedisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
        longRedisTemplate.setExposeConnection(true);
        longRedisTemplate.setConnectionFactory(redisConnectionFactory);
        longRedisTemplate.afterPropertiesSet();
        longRedisOperations = longRedisTemplate.opsForValue().getOperations();
    }
    
    @Override
    public Long incrementAndGet(String key, long timeout, TimeUnit unit) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(KeyConstant.REDIS_KEY_CONCURRENCY_CONTROL_MARK.concat(key), longRedisOperations);
        Long increment = redisAtomicLong.incrementAndGet();
        redisAtomicLong.expire(timeout, unit);
        return increment;
    }

    @Override
    public Long decrementAndGet(String key, long timeout, TimeUnit unit) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(KeyConstant.REDIS_KEY_CONCURRENCY_CONTROL_MARK.concat(key), longRedisOperations);
        Long decrement = redisAtomicLong.decrementAndGet();
        redisAtomicLong.expire(timeout, unit);
        return decrement;
    }

    @Override
    public void set(String key, long newValue, long timeout, TimeUnit unit) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(KeyConstant.REDIS_KEY_CONCURRENCY_CONTROL_MARK.concat(key), longRedisOperations);
        redisAtomicLong.set(newValue);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(String key) {
        redisTemplate.delete(KeyConstant.REDIS_KEY_CONCURRENCY_CONTROL_MARK.concat(key));
    }

    @Override
    public Long get(String key) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(KeyConstant.REDIS_KEY_CONCURRENCY_CONTROL_MARK.concat(key), longRedisOperations);
        return redisAtomicLong.get();
    }
}
