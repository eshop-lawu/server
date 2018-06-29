package com.lawu.eshop.cache.srv.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.eshop.cache.srv.CacheSrvApplicationTest;
import com.lawu.eshop.cache.srv.EmbeddedRedis;
import com.lawu.eshop.cache.srv.constants.ConcurrencyControlConstant;
import com.lawu.eshop.cache.srv.service.ConcurrencyControlService;
import com.lawu.eshop.cache.srv.service.GameConfigCacheService;
import com.lawu.eshop.cache.srv.service.GameRoomService;

/**
 * 游戏房间接口单元测试
 * @author jiangxinjun
 * @createDate 2018年3月30日
 * @updateDate 2018年3月30日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheSrvApplicationTest.class)
public class GameRoomServiceImplTest extends EmbeddedRedis {
    
    @Autowired
    private GameRoomService gameRoomService;
    
    @Autowired
    private ConcurrencyControlService concurrencyControlService;
    
    @Autowired
    private GameConfigCacheService gameConfigCacheService;
    
    @Test
    public void createGameRoom() {
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        GameRoomOperationParam param = new GameRoomOperationParam();
        param.setCurrentUserNum("M0001");
        param.setGameType(GameTypeEnum.MIND);
        param.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(param.getCurrentUserNum());
        param.setPlayerInfo(gameRoomPlayerInfoDTO);
        
        // 房主创建房间
        GameRoomDetailsDTO gameRoomDetailsDTO = gameRoomService.createGameRoom(param);
        Assert.assertEquals(gameRoomDetailsDTO.getIsDissolution(), false);
        for (GameRoomPlayerInfoDTO item : gameRoomDetailsDTO.getPlayerInfos()) {
            if (gameRoomPlayerInfoDTO.getUserNum().equals(item.getUserNum())) {
                Assert.assertEquals(gameRoomPlayerInfoDTO.getHeadImg(), item.getHeadImg());
                Assert.assertEquals(gameRoomPlayerInfoDTO.getIsRoomHost(), item.getIsRoomHost());
                Assert.assertEquals(gameRoomPlayerInfoDTO.getJoinTime(), item.getJoinTime());
                Assert.assertEquals(gameRoomPlayerInfoDTO.getNickName(), item.getNickName());
                Assert.assertEquals(gameRoomPlayerInfoDTO.getRegionName(), item.getRegionName());
                Assert.assertEquals(gameRoomPlayerInfoDTO.getRegionPath(), item.getRegionPath());
                Assert.assertEquals(gameRoomPlayerInfoDTO.getStatusEnum(), item.getStatusEnum());
            }
        }
        String numKey = param.getGameType().toString().concat(ConcurrencyControlConstant.GAME_ROOM_PEOPLE_NUMBER).concat(param.getRoomNum());
        Long num = concurrencyControlService.get(numKey);
        Assert.assertEquals(1L, num.longValue());
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        GameRoomOperationParam param2 = new GameRoomOperationParam();
        param2.setCurrentUserNum("M0002");
        param2.setGameType(GameTypeEnum.MIND);
        param2.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(param2.getCurrentUserNum());
        param2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        // 用户加入到房间
        gameRoomService.joinGameRoom(param2);
        
        // 用户准备
        GameRoomOperationParam param3 = new GameRoomOperationParam();
        param3.setCurrentUserNum(param2.getCurrentUserNum());
        param3.setGameType(GameTypeEnum.MIND);
        param3.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO3  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO3.setUserNum(param3.getCurrentUserNum());;
        gameRoomPlayerInfoDTO3.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        param3.setPlayerInfo(gameRoomPlayerInfoDTO3);
        gameRoomService.updateGameRoomPlayerReadyStatus(param3);
        
        // 房主再次挑战
        GameRoomDetailsDTO gameRoomDetailsDTO2 = gameRoomService.createGameRoom(param);
        Assert.assertEquals(gameRoomDetailsDTO2.getIsDissolution(), false);
        for (GameRoomPlayerInfoDTO item : gameRoomDetailsDTO2.getPlayerInfos()) {
            if (item.getIsRoomHost()) {
                Assert.assertEquals(item.getStatusEnum(), GameRoomPlayerStatusEnum.READY);
            } else {
                Assert.assertEquals(item.getStatusEnum(), GameRoomPlayerStatusEnum.NOT_READY);
            }
        }
    }
    
    @Test
    public void joinGameRoom() {
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        GameRoomOperationParam param = new GameRoomOperationParam();
        param.setCurrentUserNum("M0001");
        param.setGameType(GameTypeEnum.MIND);
        param.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(param.getCurrentUserNum());
        param.setPlayerInfo(gameRoomPlayerInfoDTO);
        
        // 房主创建房间
        gameRoomService.createGameRoom(param);
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        GameRoomOperationParam param2 = new GameRoomOperationParam();
        param2.setCurrentUserNum("M0002");
        param2.setGameType(GameTypeEnum.MIND);
        param2.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(param2.getCurrentUserNum());
        param2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        // 用户加入到房间
        GameRoomDetailsDTO gameRoomDetailsDTO =  gameRoomService.joinGameRoom(param2);
        Assert.assertEquals(gameRoomDetailsDTO.getIsDissolution(), false);
        for (GameRoomPlayerInfoDTO item : gameRoomDetailsDTO.getPlayerInfos()) {
            if (gameRoomPlayerInfoDTO2.getUserNum().equals(item.getUserNum())) {
                Assert.assertEquals(gameRoomPlayerInfoDTO2.getHeadImg(), item.getHeadImg());
                Assert.assertEquals(gameRoomPlayerInfoDTO2.getIsRoomHost(), item.getIsRoomHost());
                Assert.assertEquals(gameRoomPlayerInfoDTO2.getJoinTime(), item.getJoinTime());
                Assert.assertEquals(gameRoomPlayerInfoDTO2.getNickName(), item.getNickName());
                Assert.assertEquals(gameRoomPlayerInfoDTO2.getRegionName(), item.getRegionName());
                Assert.assertEquals(gameRoomPlayerInfoDTO2.getRegionPath(), item.getRegionPath());
                Assert.assertEquals(gameRoomPlayerInfoDTO2.getStatusEnum(), item.getStatusEnum());
            }
        }
        
        
        // 用户准备
        GameRoomOperationParam param3 = new GameRoomOperationParam();
        param3.setCurrentUserNum(param2.getCurrentUserNum());
        param3.setGameType(GameTypeEnum.MIND);
        param3.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO3  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO3.setUserNum(param3.getCurrentUserNum());;
        gameRoomPlayerInfoDTO3.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        param3.setPlayerInfo(gameRoomPlayerInfoDTO3);
        gameRoomService.updateGameRoomPlayerReadyStatus(param3);
        
        // 用户再次挑战
        GameRoomDetailsDTO gameRoomDetailsDTO2 = gameRoomService.joinGameRoom(param);
        Assert.assertEquals(gameRoomDetailsDTO2.getIsDissolution(), false);
        for (GameRoomPlayerInfoDTO item : gameRoomDetailsDTO2.getPlayerInfos()) {
            Assert.assertEquals(item.getStatusEnum(), GameRoomPlayerStatusEnum.READY);
        }
    }
    
    @Test
    public void updateGameRoomPlayerReadyStatus() {
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        GameRoomOperationParam param = new GameRoomOperationParam();
        param.setCurrentUserNum("M0001");
        param.setGameType(GameTypeEnum.MIND);
        param.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(param.getCurrentUserNum());
        param.setPlayerInfo(gameRoomPlayerInfoDTO);
        
        // 房主创建房间
        gameRoomService.createGameRoom(param);
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        // 用户加入到房间
        GameRoomOperationParam param2 = new GameRoomOperationParam();
        param2.setCurrentUserNum("M0002");
        param2.setGameType(GameTypeEnum.MIND);
        param2.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(param2.getCurrentUserNum());
        param2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        gameRoomService.joinGameRoom(param2);
        
        // 用户准备
        GameRoomOperationParam param3 = new GameRoomOperationParam();
        param3.setCurrentUserNum(param2.getCurrentUserNum());
        param3.setGameType(GameTypeEnum.MIND);
        param3.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO3  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO3.setUserNum(param3.getCurrentUserNum());;
        gameRoomPlayerInfoDTO3.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        param3.setPlayerInfo(gameRoomPlayerInfoDTO3);
        GameRoomDetailsDTO gameRoomDetailsDTO = gameRoomService.updateGameRoomPlayerReadyStatus(param3);
        for (GameRoomPlayerInfoDTO item : gameRoomDetailsDTO.getPlayerInfos()) {
            if (item.getUserNum().equals(param3.getCurrentUserNum())) {
                Assert.assertEquals(item.getStatusEnum(), GameRoomPlayerStatusEnum.READY);
            }
        }
    }
    
    @Test
    public void exitGameRoom() {
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        GameRoomOperationParam param = new GameRoomOperationParam();
        param.setCurrentUserNum("M0001");
        param.setGameType(GameTypeEnum.MIND);
        param.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(param.getCurrentUserNum());
        param.setPlayerInfo(gameRoomPlayerInfoDTO);
        
        // 房主创建房间
        gameRoomService.createGameRoom(param);
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        // 用户1加入到房间
        GameRoomOperationParam param2 = new GameRoomOperationParam();
        param2.setCurrentUserNum("M0002");
        param2.setGameType(GameTypeEnum.MIND);
        param2.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(param2.getCurrentUserNum());
        param2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        gameRoomService.joinGameRoom(param2);
        
        // 用户2加入到房间
        GameRoomOperationParam param3 = new GameRoomOperationParam();
        param3.setCurrentUserNum("M0003");
        param3.setGameType(GameTypeEnum.MIND);
        param3.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO3  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO3.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO3.setIsRoomHost(false);
        gameRoomPlayerInfoDTO3.setJoinTime(new Date());
        gameRoomPlayerInfoDTO3.setNickName("John");
        gameRoomPlayerInfoDTO3.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO3.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO3.setUserNum(param3.getCurrentUserNum());
        param3.setPlayerInfo(gameRoomPlayerInfoDTO3);
        gameRoomService.joinGameRoom(param3);
        
        // 房主踢出一个用户
        GameRoomOperationParam param4 = new GameRoomOperationParam();
        param4.setCurrentUserNum(param.getCurrentUserNum());
        param4.setGameType(GameTypeEnum.MIND);
        param4.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO4  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO4.setUserNum(param3.getCurrentUserNum());
        param4.setPlayerInfo(gameRoomPlayerInfoDTO4);
        GameRoomDetailsDTO gameRoomDetailsDTO = gameRoomService.exitGameRoom(param4);
        boolean isExist = false;
        for (GameRoomPlayerInfoDTO item : gameRoomDetailsDTO.getPlayerInfos()) {
            if (item.getUserNum().equals(gameRoomPlayerInfoDTO4.getUserNum())) {
                isExist = true;
            }
        }
        Assert.assertEquals(isExist, false);
        Assert.assertEquals(gameRoomDetailsDTO.getIsDissolution(), false);
        
        // 用户主动退出
        GameRoomOperationParam param5 = new GameRoomOperationParam();
        param5.setGameType(GameTypeEnum.MIND);
        param5.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO5 = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO5.setUserNum(param2.getCurrentUserNum());
        param5.setPlayerInfo(gameRoomPlayerInfoDTO5);
        GameRoomDetailsDTO gameRoomDetailsDTO2 = gameRoomService.exitGameRoom(param5);
        isExist = false;
        for (GameRoomPlayerInfoDTO item : gameRoomDetailsDTO2.getPlayerInfos()) {
            if (item.getUserNum().equals(gameRoomPlayerInfoDTO5.getUserNum())) {
                isExist = true;
            }
        }
        Assert.assertEquals(isExist, false);
        Assert.assertEquals(gameRoomDetailsDTO2.getIsDissolution(), false);
        
        // 房主退出
        GameRoomOperationParam param6 = new GameRoomOperationParam();
        param6.setGameType(GameTypeEnum.MIND);
        param6.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO6 = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO6.setUserNum(param.getCurrentUserNum());
        param6.setPlayerInfo(gameRoomPlayerInfoDTO6);
        GameRoomDetailsDTO gameRoomDetailsDTO3 = gameRoomService.exitGameRoom(param6);
        Assert.assertNull(gameRoomDetailsDTO3.getPlayerInfos());
        Assert.assertEquals(gameRoomDetailsDTO3.getIsDissolution(), true);
    }
    
    @Test
    public void getGameRoomAllPlayer() {
        Map<String, GameRoomPlayerInfoDTO> players = new HashMap<>();
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        // 房主创建房间
        GameRoomOperationParam param = new GameRoomOperationParam();
        param.setCurrentUserNum("M0001");
        param.setGameType(GameTypeEnum.MIND);
        param.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(param.getCurrentUserNum());
        param.setPlayerInfo(gameRoomPlayerInfoDTO);
        players.put(gameRoomPlayerInfoDTO.getUserNum(), gameRoomPlayerInfoDTO);
        gameRoomService.createGameRoom(param);
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        // 用户1加入到房间
        GameRoomOperationParam param2 = new GameRoomOperationParam();
        param2.setCurrentUserNum("M0002");
        param2.setGameType(GameTypeEnum.MIND);
        param2.setRoomNum(param.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(param2.getCurrentUserNum());
        param2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        players.put(gameRoomPlayerInfoDTO2.getUserNum(), gameRoomPlayerInfoDTO2);
        gameRoomService.joinGameRoom(param2);
        
        GameRoomDetailsDTO gameRoomDetailsDTO = gameRoomService.getGameRoomAllPlayer(param);
        for (GameRoomPlayerInfoDTO item : gameRoomDetailsDTO.getPlayerInfos()) {
            GameRoomPlayerInfoDTO expectedPlayerInfo = players.get(item.getUserNum());
            Assert.assertEquals(expectedPlayerInfo.getHeadImg(), item.getHeadImg());
            Assert.assertEquals(expectedPlayerInfo.getIsRoomHost(), item.getIsRoomHost());
            Assert.assertEquals(expectedPlayerInfo.getJoinTime(), item.getJoinTime());
            Assert.assertEquals(expectedPlayerInfo.getNickName(), item.getNickName());
            Assert.assertEquals(expectedPlayerInfo.getRegionName(), item.getRegionName());
            Assert.assertEquals(expectedPlayerInfo.getRegionPath(), item.getRegionPath());
            Assert.assertEquals(expectedPlayerInfo.getStatusEnum(), item.getStatusEnum());
        }
    }
}
