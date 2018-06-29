package com.lawu.eshop.cache.srv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.cache.constants.RandomGameCacheKey;
import com.lawu.eshop.cache.dto.GameCommonCacheDTO;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleCacheAnswerDetailInfo;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.cache.param.GamePuzzleConfigParam;
import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.converter.GameConverter;
import com.lawu.eshop.cache.srv.service.GameConfigCacheService;
import com.lawu.framework.web.Result;

/**
 * @author zhangrc
 * @Description
 * @date 2018年3月10日
 */
@Service
public class GameConfigCacheServiceImpl implements GameConfigCacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setGameMindConfig(GameMindConfigParam param) {
        GameMindConfigDTO configDTO = GameConverter.converterMindConfig(param);
        if (configDTO == null) {
            return;
        }
        redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_MIND_CONFIG, configDTO);
    }

    @Override
    public void setGamePuzzleConfig(GamePuzzleConfigParam param) {
        GamePuzzleConfigCacheDTO configCacheDTO = GameConverter.converterPuzzleConfig(param);
        redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_PUZZLE_CONFIG, configCacheDTO);
    }

    @Override
    public GameMindConfigDTO getGameMindConfig() {
        return (GameMindConfigDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_CONFIG);
    }

    @Override
    public GamePuzzleConfigCacheDTO getGamePuzzleConfig() {
        return (GamePuzzleConfigCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_PUZZLE_CONFIG);
    }

    @Override
    public Boolean setGamePuzzleUserKeyValue(String key, String value) {
        return stringRedisTemplate.getConnectionFactory().getConnection().setNX((KeyConstant.REDIS_KEY_GAME_PUZZLE_USER_KEY + key).getBytes(), value.getBytes());
    }

    @Override
    public String getGamePuzzleUserKey(String key) {
        return stringRedisTemplate.opsForValue().get((KeyConstant.REDIS_KEY_GAME_PUZZLE_USER_KEY + key));
    }

    @Override
    public Long delGamePuzzleUserKeyKey(String key) {
        return stringRedisTemplate.getConnectionFactory().getConnection().del((KeyConstant.REDIS_KEY_GAME_PUZZLE_USER_KEY + key).getBytes());
    }

    /**
     * 设置用户拼图开始时间
     *
     * @param date
     */
    @Override
    public void setGamePuzzleStartTimeValue(String userNum,String time) {
        stringRedisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TIME.concat(userNum), time,600,TimeUnit.SECONDS);
        
    }

    /**
     * 获取用户拼图开始时间
     */
    @Override
    public String getGamePuzzleStartTimeValue(String userNum) {
        return stringRedisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TIME.concat(userNum));
    }

    @Override
    public void setRedisKeyGamePuzzleStartType(String key, String date) {
        stringRedisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TYPE + key, date,600,TimeUnit.SECONDS);
    }

    @Override
    public String getRedisKeyGamePuzzleStartType(String key) {
        return stringRedisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TYPE + key);
    }

    @Override
    public List<String> getLikeRedisKeyGamePuzzleStartType(String key) {
        Set<String> keys =  stringRedisTemplate.keys(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TYPE+key+"*");
        List<String> ltkey =new ArrayList<String>();
        Iterator<String> iter =keys.iterator();
        while(iter.hasNext()){
        	ltkey.add(stringRedisTemplate.opsForValue().get(iter.next()));
        }
        return ltkey;
    }

    @Override
    public void delRedisKeyGamePuzzleStartType(String key) {
        stringRedisTemplate.getConnectionFactory().getConnection().del((KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TYPE+key).getBytes());
    }

    @Override
    public Result removeStartCacheData(String attendNum, String userNum) {
        stringRedisTemplate.delete(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TYPE+"PUZZLE_ANSWER_"+attendNum.concat(userNum));
        stringRedisTemplate.delete(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TYPE+"PUZZLE_JSON_"+attendNum);
        stringRedisTemplate.delete(KeyConstant.GAME_PUZZLE_RANDOM+ RandomGameCacheKey.START.getVal()+userNum);
        return null;
    }

    @Override
    public Result removeMyPuzzleCacheData(String userNum) {
        String json = stringRedisTemplate.opsForValue().get(KeyConstant.GAME_PUZZLE_RANDOM+ RandomGameCacheKey.START.getVal()+userNum);
        if(null!=json){
            GameCommonCacheDTO value = JSON.parseObject(json,GameCommonCacheDTO.class);
            String answer = stringRedisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TYPE+"PUZZLE_ANSWER_"+value.getAttendNum().concat(userNum));
            if(null ==answer){
                return null;
            }
            GamePuzzleCacheAnswerDetailInfo listSucKey =JSON.parseObject(answer,GamePuzzleCacheAnswerDetailInfo.class);
            listSucKey.setTmpAnswer(new ArrayList<String>());
            listSucKey.setSingleUseTime(Arrays.asList("1","1","1"));
            listSucKey.setTotalScore(-1);
            listSucKey.setTotalUseTime(0);
            stringRedisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_PUZZLE_START_TYPE+"PUZZLE_ANSWER_"+value.getAttendNum().concat(userNum),JSON.toJSON(listSucKey).toString(),300,TimeUnit.SECONDS);
        }
        return null;
    }


}
