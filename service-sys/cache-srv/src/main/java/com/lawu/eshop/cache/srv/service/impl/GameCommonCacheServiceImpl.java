package com.lawu.eshop.cache.srv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.cache.constants.CacheGameTypeEnum;
import com.lawu.eshop.cache.constants.RandomGameCacheKey;
import com.lawu.eshop.cache.dto.GameCommonCacheDTO;
import com.lawu.eshop.cache.dto.GameCommonNumDTO;
import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GamePuzzleCacheDetail;
import com.lawu.eshop.cache.dto.GamePuzzleCallBackCacheDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.ExitMatchQueueParam;
import com.lawu.eshop.cache.param.JoinGameCacheParam;
import com.lawu.eshop.cache.param.MatchingRobotParam;
import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.GameCommonCacheService;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;

/**
 * 游戏公用缓存
 *
 * @author lihj
 * @Date 2018年3月14日
 */
@Service
public class GameCommonCacheServiceImpl implements GameCommonCacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int joinCache(JoinGameCacheParam param) {
        // 记录参与匹配的时间
        stringRedisTemplate.opsForValue().set(KeyConstant.GAME_RANDOM_MATCH_TIME.concat(param.getKey()), String.valueOf(System.currentTimeMillis()), 60, TimeUnit.SECONDS);
        if (param.getType().equals(CacheGameTypeEnum.PUZZLE)) {
            List<String> listKey = stringRedisTemplate.opsForList().range(KeyConstant.GAME_PUZZLE_RANDOM.concat(RandomGameCacheKey.READY.getVal()), 0, -1);
            if(listKey.contains(param.getKey())){
                return ResultCode.GAME_ATTEND_REPEAT;
            }
            stringRedisTemplate.delete(KeyConstant.GAME_PUZZLE_RANDOM.concat(RandomGameCacheKey.START.getVal()).concat(param.getKey()));
            /*if(cons!=null){
            	return ResultCode.GAME_ATTEND_REPEAT;
            }*/
            stringRedisTemplate.opsForList().rightPush(KeyConstant.GAME_PUZZLE_RANDOM.concat(RandomGameCacheKey.READY.getVal()), param.getKey());
            return ResultCode.SUCCESS;
        } else {
            List<String> listKey = stringRedisTemplate.opsForList().range(KeyConstant.GAME_MIND_RANDOM.concat(RandomGameCacheKey.READY.getVal()), 0, -1);
            if(listKey.contains(param.getKey())){
                return ResultCode.GAME_ATTEND_REPEAT;
            }
            stringRedisTemplate.delete(KeyConstant.GAME_MIND_RANDOM.concat(RandomGameCacheKey.START.getVal()).concat(param.getKey()));
           /* if(cons!=null){
                return ResultCode.GAME_ATTEND_REPEAT;
            }*/
            stringRedisTemplate.opsForList().rightPush(KeyConstant.GAME_MIND_RANDOM.concat(RandomGameCacheKey.READY.getVal()), param.getKey());
            return ResultCode.SUCCESS;
        }
    }

    @Override
    public GameMatchResultDTO checkCacheMatchEatchother(CheckCacheMatchParam param) {
        GameMatchResultDTO matchDTO = new GameMatchResultDTO();
        List<GameCommonNumDTO> commonDTO = new ArrayList<GameCommonNumDTO>();
        GameCommonCacheDTO value = new GameCommonCacheDTO();
        if (param.getType().equals(CacheGameTypeEnum.PUZZLE)) {
        	String json =stringRedisTemplate.opsForValue().get(KeyConstant.GAME_PUZZLE_RANDOM.concat(RandomGameCacheKey.START.getVal()).concat(param.getKey())); 
            value = JSON.parseObject(json,GameCommonCacheDTO.class);
        } else {
            value = (GameCommonCacheDTO)redisTemplate.opsForValue().get(KeyConstant.GAME_MIND_RANDOM.concat(RandomGameCacheKey.START.getVal()).concat(param.getKey()));
        }
        if (null == value) {
            String matchStartTime = stringRedisTemplate.opsForValue().get(KeyConstant.GAME_RANDOM_MATCH_TIME.concat(param.getKey()));
            matchDTO.setMatchStartTime(matchStartTime != null ? Long.valueOf(matchStartTime) : null);
            return matchDTO;
        }
        GameCommonNumDTO dto =new GameCommonNumDTO();
        dto.setUserNum(value.getMasterUserNum());
        dto.setRoomMaster(true);
        dto.setRobot(value.getMasterUserNum().equals(value.getRobotUserNum()));
        commonDTO.add(dto);
        GameCommonNumDTO nomaster =new GameCommonNumDTO();
        nomaster.setUserNum(value.getOtherUserNum());
        nomaster.setRoomMaster(false);
        nomaster.setRobot(value.getMasterUserNum().equals(value.getRobotUserNum()));
        commonDTO.add(nomaster);
        matchDTO.setCommonInfo(commonDTO);
        matchDTO.setAttendNum(value.getAttendNum());
        return matchDTO;
    }

    @Override
    public void batchUpdateMatchEatchother(String key, String newKey) {
        List<String> keyPuzzle = stringRedisTemplate.opsForList().range(KeyConstant.GAME_PUZZLE_RANDOM.concat(key), 0, -1);
        List<String> keyMind = stringRedisTemplate.opsForList().range(KeyConstant.GAME_MIND_RANDOM.concat(key), 0, -1);
        while(keyPuzzle.size()>=2){
            String puzzleAttendNum =IdWorkerHelperImpl.generate(BizIdType.PUZZLE);
            List<String> ready = stringRedisTemplate.opsForList().range(KeyConstant.GAME_PUZZLE_RANDOM.concat(key),0,2);
            GameCommonCacheDTO commonDTO =initCommonDTO(ready,puzzleAttendNum);
            stringRedisTemplate.opsForValue().set(KeyConstant.GAME_PUZZLE_RANDOM.concat(newKey).concat(ready.get(0)), JSON.toJSON(commonDTO).toString(), 180, TimeUnit.SECONDS);
            stringRedisTemplate.opsForValue().set(KeyConstant.GAME_PUZZLE_RANDOM.concat(newKey).concat(ready.get(1)), JSON.toJSON(commonDTO).toString(), 180, TimeUnit.SECONDS);
            stringRedisTemplate.opsForList().remove(KeyConstant.GAME_PUZZLE_RANDOM.concat(key),1,ready.get(0));
            stringRedisTemplate.opsForList().remove(KeyConstant.GAME_PUZZLE_RANDOM.concat(key),1,ready.get(1));
            keyPuzzle=stringRedisTemplate.opsForList().range(KeyConstant.GAME_PUZZLE_RANDOM.concat(key), 0, -1);
        }
        while(keyMind.size()>=2){
            String mindAttendNum =IdWorkerHelperImpl.generate(BizIdType.MINDGAME);
            List<String> ready = stringRedisTemplate.opsForList().range(KeyConstant.GAME_MIND_RANDOM.concat(key),0,2);
            GameCommonCacheDTO commonDTO =initCommonDTO(ready,mindAttendNum);
            redisTemplate.opsForValue().set(KeyConstant.GAME_MIND_RANDOM.concat(newKey).concat(ready.get(0)), commonDTO, 180, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(KeyConstant.GAME_MIND_RANDOM.concat(newKey).concat(ready.get(1)), commonDTO, 180, TimeUnit.SECONDS);
            stringRedisTemplate.opsForList().remove(KeyConstant.GAME_MIND_RANDOM.concat(key),1,ready.get(0));
            stringRedisTemplate.opsForList().remove(KeyConstant.GAME_MIND_RANDOM.concat(key),1,ready.get(1));
            keyMind=stringRedisTemplate.opsForList().range(KeyConstant.GAME_MIND_RANDOM.concat(key), 0, -1);
        }
    }

	private GameCommonCacheDTO initCommonDTO(List<String> ready, String attendNum) {
		GameCommonCacheDTO dto =new GameCommonCacheDTO();
		dto.setMasterUserNum(ready.get(0));
		dto.setOtherUserNum(ready.get(1));
		dto.setAttendNum(attendNum);
		return dto;
	}

	@Override
	public void exitMatchQueue(ExitMatchQueueParam param) {
		if(param.getType().equals(CacheGameTypeEnum.PUZZLE)){
			stringRedisTemplate.opsForList().remove(KeyConstant.GAME_PUZZLE_RANDOM.concat(RandomGameCacheKey.READY.getVal()), -1, param.getKey());
		}else{
			stringRedisTemplate.opsForList().remove(KeyConstant.GAME_MIND_RANDOM.concat(RandomGameCacheKey.READY.getVal()), -1, param.getKey());
		}
	}

    @Override
    public GamePuzzleCallBackCacheDTO setCallBackCache(GamePuzzleCacheDetail param) {
        String value = stringRedisTemplate.opsForValue().get(KeyConstant.GAME_PUZZLE_CALLBACK_CACHE.concat(param.getAttendNum()));
        if(StringUtils.isEmpty(value)){
            GamePuzzleCallBackCacheDTO callBackCacheDTO =new GamePuzzleCallBackCacheDTO();
            List<GamePuzzleCacheDetail> detailList =new ArrayList<>();
            detailList.add(param);
            callBackCacheDTO.setDetailList(detailList);
            stringRedisTemplate.opsForValue().set(KeyConstant.GAME_PUZZLE_CALLBACK_CACHE.concat(param.getAttendNum()),JSON.toJSON(callBackCacheDTO).toString(),120,TimeUnit.SECONDS);
            return callBackCacheDTO;
        }else{
            GamePuzzleCallBackCacheDTO callDto = JSON.parseObject(value,GamePuzzleCallBackCacheDTO.class);
            callDto.getDetailList().add(param);
            stringRedisTemplate.opsForValue().set(KeyConstant.GAME_PUZZLE_CALLBACK_CACHE.concat(param.getAttendNum()),JSON.toJSON(callDto).toString(),120,TimeUnit.SECONDS);
            return callDto;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void matchingRobot(MatchingRobotParam param) {
        GameCommonCacheDTO commonDTO = initCommonDTO(param.getUserNums(), param.getAttendNum());
        // 最后一个为机器人用户编号
        commonDTO.setRobotUserNum(param.getUserNums().get(1));
        if (CacheGameTypeEnum.PUZZLE == param.getType()) {
            stringRedisTemplate.opsForValue().set(KeyConstant.GAME_PUZZLE_RANDOM.concat(RandomGameCacheKey.START.toString()).concat(param.getUserNums().get(0)), JSON.toJSON(commonDTO).toString(), 180, TimeUnit.SECONDS);
            stringRedisTemplate.opsForValue().set(KeyConstant.GAME_PUZZLE_RANDOM.concat(RandomGameCacheKey.START.toString()).concat(param.getUserNums().get(1)), JSON.toJSON(commonDTO).toString(), 180, TimeUnit.SECONDS);
        } else if (CacheGameTypeEnum.MIND == param.getType()) {
            redisTemplate.opsForValue().set(KeyConstant.GAME_MIND_RANDOM.concat(RandomGameCacheKey.START.toString()).concat(param.getUserNums().get(0)), commonDTO, 180, TimeUnit.SECONDS);
            redisTemplate.opsForValue().set(KeyConstant.GAME_MIND_RANDOM.concat(RandomGameCacheKey.START.toString()).concat(param.getUserNums().get(1)), commonDTO, 180, TimeUnit.SECONDS);
        }
    }
}
