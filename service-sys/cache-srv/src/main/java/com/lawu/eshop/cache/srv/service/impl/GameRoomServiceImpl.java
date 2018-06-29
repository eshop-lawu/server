package com.lawu.eshop.cache.srv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.eshop.cache.srv.constants.ConcurrencyControlConstant;
import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.ConcurrencyControlService;
import com.lawu.eshop.cache.srv.service.GameConfigCacheService;
import com.lawu.eshop.cache.srv.service.GameRoomService;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * @author meishuquan
 * @date 2018/3/13.
 */
@Service
public class GameRoomServiceImpl implements GameRoomService {
    
    /**
     * 头脑PK游戏缓存超时时间(单位:秒)
     */
    private final static int GAME_ROOM_CACHE_TIMEOUT = 60 * 60;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private GameConfigCacheService gameConfigCacheService;
    
    @Autowired
    private ConcurrencyControlService concurrencyControlService;

    private Logger logger = LoggerFactory.getLogger(GameRoomServiceImpl.class);
    @Override
    public String getGameRoomNum(String roomType, Integer roomTotalNum) {
        String usableKey = roomType + KeyConstant.REDIS_KEY_GAME_ROOM_USABLE_INDEX;
        String disableKey = roomType + KeyConstant.REDIS_KEY_GAME_ROOM_DISABLE_INDEX;
        String pageIndexKey = roomType + KeyConstant.REDIS_KEY_GAME_ROOM_PAGE_INDEX;
        String newRoomNum;
        while (true) {
            String pageIndex = stringRedisTemplate.opsForValue().get(pageIndexKey);
            if (StringUtils.isNotEmpty(pageIndex)) {
                int pageIndexNum = Integer.valueOf(pageIndex);
                int index = 0;
                while (index < pageIndexNum) {
                    index++;
                    newRoomNum = stringRedisTemplate.opsForList().rightPop(usableKey + index);
                    if (StringUtils.isNotEmpty(newRoomNum)) {
                        stringRedisTemplate.opsForList().leftPush(disableKey + index, newRoomNum);
                        return newRoomNum;
                    }
                }
            }

            int pageIndexNum = stringRedisTemplate.opsForValue().increment(pageIndexKey, 1).intValue();
            List<String> roomNumList = new ArrayList<>();
            for (int i = roomTotalNum; i > 0; i--) {
                int roomNum = (pageIndexNum - 1) * roomTotalNum + i;
                roomNumList.add(String.valueOf(roomNum));
            }
            stringRedisTemplate.opsForList().rightPushAll(usableKey + pageIndexNum, roomNumList);
        }
    }

    @Override
    public void recycleGameRoomNum(String roomType, String roomNum) {
        String usableKey = roomType + KeyConstant.REDIS_KEY_GAME_ROOM_USABLE_INDEX;
        String disableKey = roomType + KeyConstant.REDIS_KEY_GAME_ROOM_DISABLE_INDEX;
        String pageIndexKey = roomType + KeyConstant.REDIS_KEY_GAME_ROOM_PAGE_INDEX;
        String pageIndex = stringRedisTemplate.opsForValue().get(pageIndexKey);
        int pageIndexNum = Integer.valueOf(pageIndex);
        int index = 0;
        while (index < pageIndexNum) {
            index++;
            long delResult = stringRedisTemplate.opsForList().remove(disableKey + index, 0, roomNum);
            if (delResult > 0) {
                stringRedisTemplate.opsForList().leftPush(usableKey + index, roomNum);
                return;
            }
        }
    }

    @Override
    public GameRoomDetailsDTO createGameRoom(GameRoomOperationParam param) {
        String roomKey = param.getGameType().toString().concat(KeyConstant.REDIS_KEY_GAME_ROOM_READY).concat(param.getRoomNum());
        String numKey = param.getGameType().toString().concat(ConcurrencyControlConstant.GAME_ROOM_PEOPLE_NUMBER).concat(param.getRoomNum());
        BoundHashOperations<String, String, GameRoomPlayerInfoDTO> hashOperations = redisTemplate.boundHashOps(roomKey);
        List<GameRoomPlayerInfoDTO> playersInfo = hashOperations.values();
        GameRoomDetailsDTO rtn = new GameRoomDetailsDTO();
        rtn.setIsDissolution(false);
        /*
         * 根据key是否存在
         * 判断房主是否是重新挑战
         * 1.初次上线, 设置房间人数为1, 缓存房主的个人信息
         * 2.再次挑战, 更新房间内的所有用户的准备状态为未准备, 并更新房主的个人信息
         */
        if (playersInfo == null || playersInfo.isEmpty()) {
            GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = param.getPlayerInfo();
            hashOperations.put(gameRoomPlayerInfoDTO.getUserNum(), gameRoomPlayerInfoDTO);
            rtn.setPlayerInfos(Arrays.asList(gameRoomPlayerInfoDTO));
            concurrencyControlService.incrementAndGet(numKey, GAME_ROOM_CACHE_TIMEOUT, TimeUnit.SECONDS);
        } else {
            // 更新房主的个人资料
            GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = param.getPlayerInfo();
            hashOperations.put(gameRoomPlayerInfoDTO.getUserNum(), gameRoomPlayerInfoDTO);
            // 更新房间内的非房主用户为未准备
            for (GameRoomPlayerInfoDTO playerInfo : playersInfo) {
                if (!playerInfo.getIsRoomHost()) {
                    playerInfo.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
                }
                hashOperations.put(playerInfo.getUserNum(), playerInfo);
            }
            rtn.setPlayerInfos(playersInfo);
        }
        hashOperations.expire(GAME_ROOM_CACHE_TIMEOUT, TimeUnit.SECONDS);
        return rtn;
    }

    @Override
    public GameRoomDetailsDTO joinGameRoom(GameRoomOperationParam param) {
        String roomKey = param.getGameType().toString().concat(KeyConstant.REDIS_KEY_GAME_ROOM_READY).concat(param.getRoomNum());
        String numKey = param.getGameType().toString().concat(ConcurrencyControlConstant.GAME_ROOM_PEOPLE_NUMBER).concat(param.getRoomNum());
        BoundHashOperations<String, String, GameRoomPlayerInfoDTO> boundHashOperations = redisTemplate.boundHashOps(roomKey);
        List<GameRoomPlayerInfoDTO> playersInfo = boundHashOperations.values();
        if (playersInfo == null || playersInfo.isEmpty()) {
            logger.info("加入房间的时候房间不存在joinGameRoom");
            throw new DataNotExistException(ResultCode.GAME_ROOM_JOIN_FAIL, "房间不存在");
        }
        Integer roomMaxNum = 0;
        // 获取房间内的最大人数
        if (param.getGameType() == GameTypeEnum.MIND) {
            GameMindConfigDTO gameMindConfigDTO = gameConfigCacheService.getGameMindConfig();
            roomMaxNum = gameMindConfigDTO.getRoomMaxNum();
        } else if (param.getGameType() == GameTypeEnum.PUZZLE) {
            GamePuzzleConfigCacheDTO gamePuzzleConfigCacheDTO = gameConfigCacheService.getGamePuzzleConfig();
            roomMaxNum = gamePuzzleConfigCacheDTO.getRoomMaxNum();
        }
        if (roomMaxNum == 0) {
            throw new DataNotExistException(ResultCode.GAME_ROOM_JOIN_FAIL, "游戏配置信息不存在");
        }
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = param.getPlayerInfo();
        GameRoomPlayerInfoDTO cacheGameRoomPlayerInfoDTO = boundHashOperations.get(gameRoomPlayerInfoDTO.getUserNum());
        if (cacheGameRoomPlayerInfoDTO == null) {
            // 获取当前房间的人数
            Long playerNum = boundHashOperations.size();
            if (playerNum >= roomMaxNum) {
                throw new WrongOperationException(ResultCode.GAME_ROOM_FULL);
            }
            // 记录房间人数, 房间人数+1, 并返回递增后的数值
            playerNum = concurrencyControlService.incrementAndGet(numKey, GAME_ROOM_CACHE_TIMEOUT, TimeUnit.SECONDS);
            if (playerNum > roomMaxNum) {
                // 房间人数-1
                concurrencyControlService.decrementAndGet(numKey, GAME_ROOM_CACHE_TIMEOUT, TimeUnit.SECONDS);
                throw new WrongOperationException(ResultCode.GAME_ROOM_FULL);
            }
            playersInfo.add(gameRoomPlayerInfoDTO);
        } else {
            // 如果存在说明是再次挑战, 取缓存中的值
            gameRoomPlayerInfoDTO.setStatusEnum(cacheGameRoomPlayerInfoDTO.getStatusEnum());
        }
        boundHashOperations.put(gameRoomPlayerInfoDTO.getUserNum(), gameRoomPlayerInfoDTO);
        GameRoomDetailsDTO rtn = new GameRoomDetailsDTO();
        rtn.setIsDissolution(false);
        rtn.setPlayerInfos(playersInfo);
        return rtn;
    }

    @Override
    public GameRoomDetailsDTO exitGameRoom(GameRoomOperationParam param) {
        GameRoomDetailsDTO rtn = new GameRoomDetailsDTO();
        rtn.setIsDissolution(false);
        String roomKey = param.getGameType().toString().concat(KeyConstant.REDIS_KEY_GAME_ROOM_READY).concat(param.getRoomNum());
        String numKey = param.getGameType().toString().concat(ConcurrencyControlConstant.GAME_ROOM_PEOPLE_NUMBER).concat(param.getRoomNum());
        BoundHashOperations<String, String, GameRoomPlayerInfoDTO> boundHashOperations = redisTemplate.boundHashOps(roomKey);
        Boolean roomIsExist = redisTemplate.hasKey(roomKey);
        // 如果当房间不存在, 直接返回解散房间
        if (!roomIsExist) {
            return rtn;
        }
        /*
         *  校验当前用户编号跟被移除的的用户编号是否一致
         *  1.如果一致,认为用户是自主退出, 或者离线
         *  2.如果不一致, 需要校验当前用户是否是房主
         */
        String userNum = param.getPlayerInfo().getUserNum();
        if (param.getCurrentUserNum() != null && param.getCurrentUserNum().equals(userNum)) {
            GameRoomPlayerInfoDTO currentGameRoomPlayerInfoDTO = boundHashOperations.get(param.getCurrentUserNum());
            if (!currentGameRoomPlayerInfoDTO.getIsRoomHost()) {
                throw new WrongOperationException("您不是房主, 不能踢出成员");
            }
        }
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = boundHashOperations.get(userNum);
        if (gameRoomPlayerInfoDTO == null) {
            throw new WrongOperationException("您已不在该房间");
        }
        // 判断是否是房主, 房主退出, 清除所有相关key, 并回收房间号
        if (gameRoomPlayerInfoDTO.getIsRoomHost() != null && gameRoomPlayerInfoDTO.getIsRoomHost()) {
            concurrencyControlService.delete(numKey);
            boundHashOperations.getOperations().delete(roomKey);
            // 回收房间号
            recycleGameRoomNum(param.getGameType().toString(), param.getRoomNum());
            rtn.setIsDissolution(true);
            return rtn;
        }
        Long affectedQuantity = boundHashOperations.delete(userNum);
        if (affectedQuantity <= 0) {
            throw new WrongOperationException("您已不在该房间");
        }
        // 房间人数-1
        concurrencyControlService.decrementAndGet(numKey, GAME_ROOM_CACHE_TIMEOUT, TimeUnit.SECONDS);
        rtn.setPlayerInfos(boundHashOperations.values());
        return rtn;
    }

    @Override
    public GameRoomDetailsDTO updateGameRoomPlayerReadyStatus(GameRoomOperationParam param) {
        String roomKey = param.getGameType().toString().concat(KeyConstant.REDIS_KEY_GAME_ROOM_READY).concat(param.getRoomNum());
        BoundHashOperations<String, String, GameRoomPlayerInfoDTO> boundHashOperations = redisTemplate.boundHashOps(roomKey);
        Boolean roomIsExist = redisTemplate.hasKey(roomKey);
        if (!roomIsExist) {
            logger.info("更新房间信息的时候房间不存在updateGameRoomPlayerReadyStatus");
            throw new DataNotExistException("房间不存在");
        }
        String userNum = param.getPlayerInfo().getUserNum();
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO = boundHashOperations.get(userNum);
        if (gameRoomPlayerInfoDTO == null) {
            throw new WrongOperationException(ResultCode.GAME_ROOM_PLAYER_READY_FAIL, "您已不在该房间");
        }
        gameRoomPlayerInfoDTO.setStatusEnum(param.getPlayerInfo().getStatusEnum());
        boundHashOperations.put(userNum, gameRoomPlayerInfoDTO);
        GameRoomDetailsDTO rtn = new GameRoomDetailsDTO();
        rtn.setIsDissolution(false);
        rtn.setPlayerInfos(boundHashOperations.values());
        return rtn;
    }

    @Override
    public GameRoomDetailsDTO getGameRoomAllPlayer(GameRoomOperationParam param) {
        String roomKey = param.getGameType().toString().concat(KeyConstant.REDIS_KEY_GAME_ROOM_READY).concat(param.getRoomNum());
        BoundHashOperations<String, String, GameRoomPlayerInfoDTO> boundHashOperations = redisTemplate.boundHashOps(roomKey);
        Boolean roomIsExist = redisTemplate.hasKey(roomKey);
        if (!roomIsExist) {
            logger.info("获取房间信息的时候房间信息的时候房间不存在getGameRoomAllPlayer");
            throw new DataNotExistException("房间不存在");
        }
        GameRoomDetailsDTO rtn = new GameRoomDetailsDTO();
        rtn.setIsDissolution(false);
        rtn.setPlayerInfos(boundHashOperations.values());
        return rtn;
    }

}
