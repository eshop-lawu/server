package com.lawu.eshop.cache.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameRoomPlayerStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameMindRoomDetailsCacheDTO;
import com.lawu.eshop.cache.dto.GameMindUserDetailsCacheDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.GameRoomPlayerInfoDTO;
import com.lawu.eshop.cache.dto.StartTheGameUserDTO;
import com.lawu.eshop.cache.param.GameMindConfigParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameQuestionParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameRecordParam;
import com.lawu.eshop.cache.param.GameRoomOperationParam;
import com.lawu.eshop.cache.param.ReadyStartGameParam;
import com.lawu.eshop.cache.srv.CacheSrvApplicationTest;
import com.lawu.eshop.cache.srv.EmbeddedRedis;
import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.ConcurrencyControlService;
import com.lawu.eshop.cache.srv.service.GameConfigCacheService;
import com.lawu.eshop.cache.srv.service.GameMindCacheService;
import com.lawu.eshop.cache.srv.service.GameRoomService;

/**
 * 头脑游戏缓存服务单元测试
 * @author jiangxinjun
 * @createDate 2018年4月2日
 * @updateDate 2018年4月2日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheSrvApplicationTest.class)
public class GameMindCacheServiceImplTest extends EmbeddedRedis{
    
    @Autowired
    private GameMindCacheService gameMindCacheService;
    
    @Autowired
    private GameConfigCacheService gameConfigCacheService;
    
    @Autowired
    private GameRoomService gameRoomService;
    
    @Autowired
    private ConcurrencyControlService concurrencyControlService;
    
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;
    
    @SuppressWarnings("unchecked")
    @Test
    public void readyStartGame() {
        Map<String, ReadyStartGameParam> readyStartGameParamMap = new HashMap<>();
        String attendNum = "MG0001";
        // 随机匹配,房主上线
        ReadyStartGameParam param = new ReadyStartGameParam();
        param.setUserNum("M0001");
        param.setAttendNum(attendNum); 
        param.setIsHomeowner(true);
        gameMindCacheService.readyStartGame(param);
        readyStartGameParamMap.put(param.getUserNum(), param);
        
        // 随机匹配,对战用户上线
        ReadyStartGameParam param2 = new ReadyStartGameParam();
        param2.setUserNum("M0002");
        param2.setAttendNum(attendNum);
        param2.setIsHomeowner(false);
        gameMindCacheService.readyStartGame(param2);
        readyStartGameParamMap.put(param2.getUserNum(), param2);
        
        List<StartTheGameUserDTO> groupUserInfos = gameMindCacheService.getGroupUserNums(attendNum);
        for (StartTheGameUserDTO item : groupUserInfos) {
            ReadyStartGameParam readyStartGameParam = readyStartGameParamMap.get(item.getUserNum());
            Assert.assertEquals(readyStartGameParam.getUserNum(), item.getUserNum());
            Assert.assertEquals(readyStartGameParam.getIsHomeowner(), item.getIsHomeowner());
        }
        
        String roomDetailsKey = KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(attendNum);
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations = redisTemplate.boundValueOps(roomDetailsKey);
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = roomDetailsBoundValueOperations.get();
        Assert.assertEquals(attendNum, gameMindRoomDetailsCacheDTO.getAttendNum());
        Assert.assertEquals(attendNum, gameMindRoomDetailsCacheDTO.getGroupNum());
        Assert.assertNull(gameMindRoomDetailsCacheDTO.getRoomNum());
        Assert.assertEquals(2, gameMindRoomDetailsCacheDTO.getUserNums().size());
        for (String userNum : gameMindRoomDetailsCacheDTO.getUserNums()) {
            GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum));
            ReadyStartGameParam readyStartGameParam = readyStartGameParamMap.get(userNum);
            Assert.assertEquals(attendNum, gameMindUserDetailsCacheDTO.getAttendNum());
            Assert.assertEquals(attendNum, gameMindUserDetailsCacheDTO.getGroupNum());
            Assert.assertNull(gameMindUserDetailsCacheDTO.getRoomNum());
            Assert.assertEquals(readyStartGameParam.getIsHomeowner(), gameMindUserDetailsCacheDTO.getIsHomeowner());
        }
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void participateGame() {
        // 随机匹配开始游戏
        String attendNum = "MG0002";
        // 随机匹配,房主上线
        ReadyStartGameParam param = new ReadyStartGameParam();
        param.setUserNum("M0001");
        param.setAttendNum(attendNum); 
        param.setIsHomeowner(true);
        gameMindCacheService.readyStartGame(param);
        
        // 随机匹配,对战用户上线
        ReadyStartGameParam param2 = new ReadyStartGameParam();
        param2.setUserNum("M0002");
        param2.setAttendNum(attendNum);
        param2.setIsHomeowner(false);
        gameMindCacheService.readyStartGame(param2);
        
        GameMindParticipateGameParam gameMindParticipateGameParam = new GameMindParticipateGameParam();
        gameMindParticipateGameParam.setAttendNum(attendNum);
        gameMindParticipateGameParam.setPoint(8);
        List<GameMindParticipateGameQuestionParam> questions = new ArrayList<>();
        Map<Long, GameMindParticipateGameQuestionParam> questionMap = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            GameMindParticipateGameQuestionParam questionParam = new GameMindParticipateGameQuestionParam();
            questionParam.setId(Long.valueOf(i));
            questionParam.setRightAnswer(0);
            questions.add(questionParam);
            questionMap.put(questionParam.getId(), questionParam);
        }
        gameMindParticipateGameParam.setQuestions(questions);
        Map<String, GameMindParticipateGameRecordParam> recordMap = new HashMap<>();
        List<GameMindParticipateGameRecordParam> records = new ArrayList<>();
        List<StartTheGameUserDTO> groupUserInfos = gameMindCacheService.getGroupUserNums(attendNum);
        for (int i = 0; i < groupUserInfos.size(); i++) {
            StartTheGameUserDTO startTheGameUserDTO = groupUserInfos.get(i);
            GameMindParticipateGameRecordParam record = new GameMindParticipateGameRecordParam();
            record.setRecordId(Long.valueOf(i+1));
            record.setUserNum(startTheGameUserDTO.getUserNum());
            records.add(record);
            recordMap.put(record.getUserNum(), record);
        }
        gameMindParticipateGameParam.setRecords(records);
        gameMindCacheService.participateGame(gameMindParticipateGameParam);
        
        String roomDetailsKey = KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(attendNum);
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations = redisTemplate.boundValueOps(roomDetailsKey);
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = roomDetailsBoundValueOperations.get();
        Assert.assertEquals(attendNum, gameMindRoomDetailsCacheDTO.getAttendNum());
        Assert.assertEquals(attendNum, gameMindRoomDetailsCacheDTO.getGroupNum());
        Assert.assertNull(gameMindRoomDetailsCacheDTO.getRoomNum());
        Assert.assertEquals(2, gameMindRoomDetailsCacheDTO.getUserNums().size());
        Assert.assertEquals(2, gameMindRoomDetailsCacheDTO.getInitialUserNums().size());
        for (int i = 0; i < gameMindRoomDetailsCacheDTO.getUserNums().size(); i++) {
            Assert.assertEquals(gameMindRoomDetailsCacheDTO.getUserNums().get(i), gameMindRoomDetailsCacheDTO.getInitialUserNums().get(i));
        }
        for (GameMindParticipateGameQuestionParam item : gameMindRoomDetailsCacheDTO.getQuestions()) {
            GameMindParticipateGameQuestionParam expected = questionMap.get(item.getId());
            Assert.assertEquals(expected.getId(), item.getId());
            Assert.assertEquals(expected.getRightAnswer(), item.getRightAnswer());
        }
        for (String userNum : gameMindRoomDetailsCacheDTO.getUserNums()) {
            GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum));
            GameMindParticipateGameRecordParam gameMindParticipateGameRecordParam = recordMap.get(userNum);
            Assert.assertEquals(attendNum, gameMindUserDetailsCacheDTO.getAttendNum());
            Assert.assertEquals(attendNum, gameMindUserDetailsCacheDTO.getGroupNum());
            Assert.assertNull(gameMindUserDetailsCacheDTO.getRoomNum());
            Assert.assertEquals(gameMindParticipateGameRecordParam.getRecordId(), gameMindUserDetailsCacheDTO.getRecordId());
            Assert.assertEquals(GameAttendRecordStatusEnum.INITSTATUS, gameMindUserDetailsCacheDTO.getAttendStatus());
        }
        
        // 多人房间开始游戏
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        // 房主创建房间
        GameRoomOperationParam gameRoomOperationParam = new GameRoomOperationParam();
        gameRoomOperationParam.setCurrentUserNum("M0001");
        gameRoomOperationParam.setGameType(GameTypeEnum.MIND);
        gameRoomOperationParam.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(gameRoomOperationParam.getCurrentUserNum());
        gameRoomOperationParam.setPlayerInfo(gameRoomPlayerInfoDTO);
        gameRoomService.createGameRoom(gameRoomOperationParam);
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        // 用户1加入到房间
        GameRoomOperationParam gameRoomOperationParam2 = new GameRoomOperationParam();
        gameRoomOperationParam2.setCurrentUserNum("M0002");
        gameRoomOperationParam2.setGameType(GameTypeEnum.MIND);
        gameRoomOperationParam2.setRoomNum(gameRoomOperationParam.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(gameRoomOperationParam2.getCurrentUserNum());
        gameRoomOperationParam2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        gameRoomService.joinGameRoom(gameRoomOperationParam2);
        
        GameMindParticipateGameParam gameMindParticipateGameParam1 = new GameMindParticipateGameParam();
        gameMindParticipateGameParam1.setRoomNum(roomNum);
        gameMindParticipateGameParam1.setPoint(8);
        List<GameMindParticipateGameQuestionParam> questions1 = new ArrayList<>();
        Map<Long, GameMindParticipateGameQuestionParam> questionMap1 = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            GameMindParticipateGameQuestionParam questionParam = new GameMindParticipateGameQuestionParam();
            questionParam.setId(Long.valueOf(i));
            questionParam.setRightAnswer(0);
            questions1.add(questionParam);
            questionMap1.put(questionParam.getId(), questionParam);
        }
        gameMindParticipateGameParam1.setQuestions(questions1);
        Map<String, GameMindParticipateGameRecordParam> recordMap1 = new HashMap<>();
        List<GameMindParticipateGameRecordParam> records1 = new ArrayList<>();
        GameRoomDetailsDTO gameRoomDetailsDTO = gameRoomService.getGameRoomAllPlayer(gameRoomOperationParam);
        for (int i = 0; i < gameRoomDetailsDTO.getPlayerInfos().size(); i++) {
            GameRoomPlayerInfoDTO gameRoomPlayerInfoDTOItem = gameRoomDetailsDTO.getPlayerInfos().get(i);
            GameMindParticipateGameRecordParam record = new GameMindParticipateGameRecordParam();
            record.setRecordId(Long.valueOf(i+1));
            record.setUserNum(gameRoomPlayerInfoDTOItem.getUserNum());
            records1.add(record);
            recordMap1.put(record.getUserNum(), record);
        }
        gameMindParticipateGameParam1.setRecords(records1);
        gameMindCacheService.participateGame(gameMindParticipateGameParam1);
        
        String roomDetailsKey1 = KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(roomNum);
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations1 = redisTemplate.boundValueOps(roomDetailsKey1);
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO1 = roomDetailsBoundValueOperations1.get();
        Assert.assertEquals(roomNum, gameMindRoomDetailsCacheDTO1.getRoomNum());
        Assert.assertEquals(roomNum, gameMindRoomDetailsCacheDTO1.getGroupNum());
        Assert.assertNull(gameMindRoomDetailsCacheDTO1.getAttendNum());
        Assert.assertEquals(2, gameMindRoomDetailsCacheDTO1.getUserNums().size());
        Assert.assertEquals(2, gameMindRoomDetailsCacheDTO1.getInitialUserNums().size());
        for (int i = 0; i < gameMindRoomDetailsCacheDTO1.getUserNums().size(); i++) {
            Assert.assertEquals(gameMindRoomDetailsCacheDTO1.getUserNums().get(i), gameMindRoomDetailsCacheDTO1.getInitialUserNums().get(i));
        }
        for (GameMindParticipateGameQuestionParam item : gameMindRoomDetailsCacheDTO1.getQuestions()) {
            GameMindParticipateGameQuestionParam expected = questionMap1.get(item.getId());
            Assert.assertEquals(expected.getId(), item.getId());
            Assert.assertEquals(expected.getRightAnswer(), item.getRightAnswer());
        }
        for (String userNum : gameMindRoomDetailsCacheDTO1.getUserNums()) {
            GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum));
            GameMindParticipateGameRecordParam gameMindParticipateGameRecordParam = recordMap1.get(userNum);
            Assert.assertEquals(roomNum, gameMindRoomDetailsCacheDTO1.getRoomNum());
            Assert.assertEquals(roomNum, gameMindRoomDetailsCacheDTO1.getGroupNum());
            Assert.assertNull(gameMindUserDetailsCacheDTO.getAttendNum());
            Assert.assertEquals(gameMindParticipateGameRecordParam.getRecordId(), gameMindUserDetailsCacheDTO.getRecordId());
            Assert.assertEquals(GameAttendRecordStatusEnum.INITSTATUS, gameMindUserDetailsCacheDTO.getAttendStatus());
        }
    }
    
    @Test
    public void changeAttendStatusSuccess() {
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        // 房主创建房间
        GameRoomOperationParam gameRoomOperationParam = new GameRoomOperationParam();
        gameRoomOperationParam.setCurrentUserNum("M0001");
        gameRoomOperationParam.setGameType(GameTypeEnum.MIND);
        gameRoomOperationParam.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(gameRoomOperationParam.getCurrentUserNum());
        gameRoomOperationParam.setPlayerInfo(gameRoomPlayerInfoDTO);
        gameRoomService.createGameRoom(gameRoomOperationParam);
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        // 用户1加入到房间
        GameRoomOperationParam gameRoomOperationParam2 = new GameRoomOperationParam();
        gameRoomOperationParam2.setCurrentUserNum("M0002");
        gameRoomOperationParam2.setGameType(GameTypeEnum.MIND);
        gameRoomOperationParam2.setRoomNum(gameRoomOperationParam.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(gameRoomOperationParam2.getCurrentUserNum());
        gameRoomOperationParam2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        gameRoomService.joinGameRoom(gameRoomOperationParam2);
        
        GameMindParticipateGameParam gameMindParticipateGameParam = new GameMindParticipateGameParam();
        gameMindParticipateGameParam.setRoomNum(roomNum);
        gameMindParticipateGameParam.setPoint(8);
        List<GameMindParticipateGameQuestionParam> questions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            GameMindParticipateGameQuestionParam questionParam = new GameMindParticipateGameQuestionParam();
            questionParam.setId(Long.valueOf(i));
            questionParam.setRightAnswer(0);
            questions.add(questionParam);
        }
        gameMindParticipateGameParam.setQuestions(questions);
        List<GameMindParticipateGameRecordParam> records = new ArrayList<>();
        GameRoomDetailsDTO gameRoomDetailsDTO = gameRoomService.getGameRoomAllPlayer(gameRoomOperationParam);
        for (int i = 0; i < gameRoomDetailsDTO.getPlayerInfos().size(); i++) {
            GameRoomPlayerInfoDTO gameRoomPlayerInfoDTOItem = gameRoomDetailsDTO.getPlayerInfos().get(i);
            GameMindParticipateGameRecordParam record = new GameMindParticipateGameRecordParam();
            record.setRecordId(Long.valueOf(i+1));
            record.setUserNum(gameRoomPlayerInfoDTOItem.getUserNum());
            records.add(record);
        }
        gameMindParticipateGameParam.setRecords(records);
        gameMindCacheService.participateGame(gameMindParticipateGameParam);
        
        // 两个记录都是回调成功
        Long recordId = gameMindCacheService.changeAttendStatus(records.get(0).getUserNum(), GameAttendRecordStatusEnum.ATTENDSUCCESS);
        Assert.assertNull(recordId);
        GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(records.get(0).getUserNum()));
        Assert.assertEquals(GameAttendRecordStatusEnum.ATTENDSUCCESS, gameMindUserDetailsCacheDTO.getAttendStatus());
        Long recordId2 = gameMindCacheService.changeAttendStatus(records.get(1).getUserNum(), GameAttendRecordStatusEnum.ATTENDSUCCESS);
        Assert.assertNull(recordId2);
        GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO2 = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(records.get(1).getUserNum()));
        Assert.assertEquals(GameAttendRecordStatusEnum.ATTENDSUCCESS, gameMindUserDetailsCacheDTO2.getAttendStatus());
    }
    
    @Test
    public void changeAttendStatusFail() {
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        // 房主创建房间
        GameRoomOperationParam gameRoomOperationParam = new GameRoomOperationParam();
        gameRoomOperationParam.setCurrentUserNum("M0001");
        gameRoomOperationParam.setGameType(GameTypeEnum.MIND);
        gameRoomOperationParam.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(gameRoomOperationParam.getCurrentUserNum());
        gameRoomOperationParam.setPlayerInfo(gameRoomPlayerInfoDTO);
        gameRoomService.createGameRoom(gameRoomOperationParam);
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        // 用户1加入到房间
        GameRoomOperationParam gameRoomOperationParam2 = new GameRoomOperationParam();
        gameRoomOperationParam2.setCurrentUserNum("M0002");
        gameRoomOperationParam2.setGameType(GameTypeEnum.MIND);
        gameRoomOperationParam2.setRoomNum(gameRoomOperationParam.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(gameRoomOperationParam2.getCurrentUserNum());
        gameRoomOperationParam2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        gameRoomService.joinGameRoom(gameRoomOperationParam2);
        
        GameMindParticipateGameParam gameMindParticipateGameParam = new GameMindParticipateGameParam();
        gameMindParticipateGameParam.setRoomNum(roomNum);
        gameMindParticipateGameParam.setPoint(8);
        List<GameMindParticipateGameQuestionParam> questions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            GameMindParticipateGameQuestionParam questionParam = new GameMindParticipateGameQuestionParam();
            questionParam.setId(Long.valueOf(i));
            questionParam.setRightAnswer(0);
            questions.add(questionParam);
        }
        gameMindParticipateGameParam.setQuestions(questions);
        List<GameMindParticipateGameRecordParam> records = new ArrayList<>();
        GameRoomDetailsDTO gameRoomDetailsDTO = gameRoomService.getGameRoomAllPlayer(gameRoomOperationParam);
        for (int i = 0; i < gameRoomDetailsDTO.getPlayerInfos().size(); i++) {
            GameRoomPlayerInfoDTO gameRoomPlayerInfoDTOItem = gameRoomDetailsDTO.getPlayerInfos().get(i);
            GameMindParticipateGameRecordParam record = new GameMindParticipateGameRecordParam();
            record.setRecordId(Long.valueOf(i+1));
            record.setUserNum(gameRoomPlayerInfoDTOItem.getUserNum());
            records.add(record);
        }
        gameMindParticipateGameParam.setRecords(records);
        gameMindCacheService.participateGame(gameMindParticipateGameParam);
        
        // 两个记录都是回调成功
        Long recordId = gameMindCacheService.changeAttendStatus(records.get(0).getUserNum(), GameAttendRecordStatusEnum.ATTENDSUCCESS);
        Assert.assertNull(recordId);
        GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(records.get(0).getUserNum()));
        Assert.assertEquals(GameAttendRecordStatusEnum.ATTENDSUCCESS, gameMindUserDetailsCacheDTO.getAttendStatus());
        Long recordId2 = gameMindCacheService.changeAttendStatus(records.get(1).getUserNum(), GameAttendRecordStatusEnum.ATTENDFAIL);
        Assert.assertEquals(records.get(0).getRecordId(), recordId2);
        GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO2 = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(records.get(1).getUserNum()));
        Assert.assertEquals(GameAttendRecordStatusEnum.ATTENDFAIL, gameMindUserDetailsCacheDTO2.getAttendStatus());
        gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(records.get(0).getUserNum()));
        Assert.assertEquals(GameAttendRecordStatusEnum.REFUND, gameMindUserDetailsCacheDTO.getAttendStatus());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void quitStartGameBefore() {
        // 获取房间编号
        String roomNum = gameRoomService.getGameRoomNum(GameTypeEnum.MIND.toString(), 500);
        GameTypeEnum gameType = GameTypeEnum.MIND;
        // 房主创建房间
        GameRoomOperationParam gameRoomOperationParam = new GameRoomOperationParam();
        gameRoomOperationParam.setCurrentUserNum("M0001");
        gameRoomOperationParam.setGameType(gameType);
        gameRoomOperationParam.setRoomNum(roomNum);
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO.setIsRoomHost(true);
        gameRoomPlayerInfoDTO.setJoinTime(new Date());
        gameRoomPlayerInfoDTO.setNickName("Sunny");
        gameRoomPlayerInfoDTO.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO.setStatusEnum(GameRoomPlayerStatusEnum.READY);
        gameRoomPlayerInfoDTO.setUserNum(gameRoomOperationParam.getCurrentUserNum());
        gameRoomOperationParam.setPlayerInfo(gameRoomPlayerInfoDTO);
        gameRoomService.createGameRoom(gameRoomOperationParam);
        
        // 初始化配置
        GameMindConfigParam gameMindConfigParam  = new GameMindConfigParam();
        gameMindConfigParam.setRoomMaxNum(5);
        gameConfigCacheService.setGameMindConfig(gameMindConfigParam);
        
        // 用户1加入到房间
        GameRoomOperationParam gameRoomOperationParam2 = new GameRoomOperationParam();
        gameRoomOperationParam2.setCurrentUserNum("M0002");
        gameRoomOperationParam2.setGameType(gameType);
        gameRoomOperationParam2.setRoomNum(gameRoomOperationParam.getRoomNum());
        GameRoomPlayerInfoDTO gameRoomPlayerInfoDTO2  = new GameRoomPlayerInfoDTO();
        gameRoomPlayerInfoDTO2.setHeadImg("test.jpg");
        gameRoomPlayerInfoDTO2.setIsRoomHost(false);
        gameRoomPlayerInfoDTO2.setJoinTime(new Date());
        gameRoomPlayerInfoDTO2.setNickName("John");
        gameRoomPlayerInfoDTO2.setRegionName("广东省深圳市南山区创维大厦1007");
        gameRoomPlayerInfoDTO2.setStatusEnum(GameRoomPlayerStatusEnum.NOT_READY);
        gameRoomPlayerInfoDTO2.setUserNum(gameRoomOperationParam2.getCurrentUserNum());
        gameRoomOperationParam2.setPlayerInfo(gameRoomPlayerInfoDTO2);
        gameRoomService.joinGameRoom(gameRoomOperationParam2);
        
        GameMindParticipateGameParam gameMindParticipateGameParam = new GameMindParticipateGameParam();
        gameMindParticipateGameParam.setRoomNum(roomNum);
        gameMindParticipateGameParam.setPoint(8);
        List<GameMindParticipateGameQuestionParam> questions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            GameMindParticipateGameQuestionParam questionParam = new GameMindParticipateGameQuestionParam();
            questionParam.setId(Long.valueOf(i));
            questionParam.setRightAnswer(0);
            questions.add(questionParam);
        }
        gameMindParticipateGameParam.setQuestions(questions);
        List<GameMindParticipateGameRecordParam> records = new ArrayList<>();
        GameRoomDetailsDTO gameRoomDetailsDTO = gameRoomService.getGameRoomAllPlayer(gameRoomOperationParam);
        for (int i = 0; i < gameRoomDetailsDTO.getPlayerInfos().size(); i++) {
            GameRoomPlayerInfoDTO gameRoomPlayerInfoDTOItem = gameRoomDetailsDTO.getPlayerInfos().get(i);
            GameMindParticipateGameRecordParam record = new GameMindParticipateGameRecordParam();
            record.setRecordId(Long.valueOf(i+1));
            record.setUserNum(gameRoomPlayerInfoDTOItem.getUserNum());
            records.add(record);
        }
        gameMindParticipateGameParam.setRecords(records);
        gameMindCacheService.participateGame(gameMindParticipateGameParam);
        
        gameMindCacheService.changeAttendStatus(records.get(0).getUserNum(), GameAttendRecordStatusEnum.ATTENDSUCCESS);
        gameMindCacheService.changeAttendStatus(records.get(1).getUserNum(), GameAttendRecordStatusEnum.ATTENDFAIL);
        
        // 退款用户退出
        gameMindCacheService.quit(records.get(0).getUserNum());
        String userKey = KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(records.get(0).getUserNum());
        Assert.assertEquals(false, redisTemplate.hasKey(userKey));
        // 参与失败用户退出
        gameMindCacheService.quit(records.get(1).getUserNum());
        String userKey2 = KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(records.get(1).getUserNum());
        Assert.assertEquals(false, redisTemplate.hasKey(userKey2));
    }
}