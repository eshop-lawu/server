package com.lawu.eshop.cache.srv.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.GamePuzzleCacheService;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
@Service
public class GamePuzzleCacheServiceImpl implements GamePuzzleCacheService {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<String> readyStartGame(String userNum, String attendNum) {
        redisTemplate.opsForValue().set(KeyConstant.GAME_PUZZLE_ONLINE.concat(attendNum).concat(userNum),userNum,600, TimeUnit.SECONDS);
        Set<String> keys = redisTemplate.keys(KeyConstant.GAME_PUZZLE_ONLINE.concat(attendNum)+"*");
        Iterator<String> it =keys.iterator();
        List<String> lt =new ArrayList<String>();
        while(it.hasNext()){
            String key =it.next();
            lt.add(redisTemplate.opsForValue().get(key).toString());
        }
        return lt;
    }

	@Override
	public Long incrementCount(String attendNum) {
		return redisTemplate.boundValueOps(attendNum).increment(1);
	}
}
