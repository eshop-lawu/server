package com.lawu.eshop.cache.srv.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.cache.constants.CacheGameQuestionEnum;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.dto.CheckPointsDeductionStatusDTO;
import com.lawu.eshop.cache.dto.GameMindAnswerResultCacheDTO;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GameMindRankDTO;
import com.lawu.eshop.cache.dto.GameMindRoomDetailsCacheDTO;
import com.lawu.eshop.cache.dto.GameMindUserDetailsCacheDTO;
import com.lawu.eshop.cache.dto.MultiplayerSubmitScoresDTO;
import com.lawu.eshop.cache.dto.StartTheGameUserDTO;
import com.lawu.eshop.cache.dto.VerifyAnswerDTO;
import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameQuestionParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameRecordParam;
import com.lawu.eshop.cache.param.ReadyStartGameParam;
import com.lawu.eshop.cache.param.VerifyAnswerParam;
import com.lawu.eshop.cache.srv.constants.ConcurrencyControlConstant;
import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.ConcurrencyControlService;
import com.lawu.eshop.cache.srv.service.GameConfigCacheService;
import com.lawu.eshop.cache.srv.service.GameMindCacheService;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.common.param.GamePointAllotParam;
import com.lawu.eshop.common.param.SecScoreParam;
import com.lawu.utils.NumberUtil;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
@Service
public class GameMindCacheServiceImpl implements GameMindCacheService {
    
    /**
     * 头脑PK游戏缓存超时时间(单位:秒)
     */
    private final static int GAME_MIND_CACHE_TIMEOUT = 30 * 60;
    
    /**
     * 出题时间
     */
    @Value("${game-mind.question-time:1000}")
    private int questionTime;
    
    /**
     * 显示正确答案时间
     */
    @Value("${game-mind.show-correct-answer-time:2000}")
    private int showCorrectAnswerTime;
    
    /**
     * 隐藏题目时间
     */
    @Value("${game-mind.hide-time:500}")
    private int hideTime;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private GameConfigCacheService gameConfigCacheService;
    
    @Autowired
    private ConcurrencyControlService concurrencyControlService;
    
    /**
     * 设置开始游戏时间
     * @param attendId
     * @param times
     */
    @Override
    public void setGameTime(Long attendId, Long times) {
        String key = KeyConstant.REDIS_KEY_GAME_MIND_ATTEND_START_TIME.concat(attendId.toString());
        stringRedisTemplate.opsForValue().set(key, times.toString(),600, TimeUnit.SECONDS);
    }

    /**
     * 获取上一次的开始时间
     * @param attendId
     * @return
     */
    @Override
    public Long getLastGameTime(Long attendId) {
        String key = KeyConstant.REDIS_KEY_GAME_MIND_ATTEND_START_TIME.concat(attendId.toString());
        String value = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(value)) {
            return 0L;
        }
        return Long.valueOf(value);
    }

    @Override
    public void setGameMindQuestionCache(String attendIds, String questionIds) {
        String[] attendId = attendIds.split(",");
        for (int i = 0; i < attendId.length; i++) {
            String key = KeyConstant.REDIS_KEY_GAME_MIND_ATTEND_QUESTION.concat(attendId[i]);
            stringRedisTemplate.opsForValue().set(key, questionIds,600, TimeUnit.SECONDS);
        }
    }

    @Override
    public String getMindQuestions(String attendId) {
        String key = KeyConstant.REDIS_KEY_GAME_MIND_ATTEND_QUESTION.concat(attendId);
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void addAttendRecordResult(Long attendId, String record) {
        String key = KeyConstant.REDIS_KEY_GAME_MIND_ANSWER_QUESTION.concat(attendId.toString());
        stringRedisTemplate.opsForValue().set(key, record,600, TimeUnit.SECONDS);
    }

    @Override
    public List<GameMindAttendRecordResultParam> getAttendRecordResult(Long attendId) {
        String pattern = KeyConstant.REDIS_KEY_GAME_MIND_ANSWER_QUESTION.concat(attendId.toString());
        String val = stringRedisTemplate.opsForValue().get(pattern);
        List<GameMindAttendRecordResultParam> list = JSON.parseArray(val, GameMindAttendRecordResultParam.class);
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public void addAnswerQuestionPoint(Long attendId, Integer point) {
        String key = KeyConstant.REDIS_KEY_GAME_MIND_ANSWER_QUESTION_SCORE.concat(attendId.toString());
        String oldVal = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(oldVal)) {
            stringRedisTemplate.opsForValue().set(key, point.toString(),600, TimeUnit.SECONDS);
        } else {
            stringRedisTemplate.boundValueOps(key).increment(point);
        }

    }

    @Override
    public Integer getAnswerQuestionPoint(Long attendId) {
        String key = KeyConstant.REDIS_KEY_GAME_MIND_ANSWER_QUESTION_SCORE.concat(attendId.toString());
        String oldVal = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(oldVal)) {
            return 0;
        } else {
            return Integer.valueOf(oldVal);
        }
    }

    @Override
    public void delSingleGameMindCache(Long attendId) {

        //删除头脑PK开始时间
        String timeKey = KeyConstant.REDIS_KEY_GAME_MIND_ATTEND_START_TIME.concat(attendId.toString());
        stringRedisTemplate.delete(timeKey);
        //删除头脑PK问题ID缓存
        String questionKey = KeyConstant.REDIS_KEY_GAME_MIND_ATTEND_QUESTION.concat(attendId.toString());
        stringRedisTemplate.delete(questionKey);

        //删除参与ID对应的答题列表
        String questionPattern = KeyConstant.REDIS_KEY_GAME_MIND_ANSWER_QUESTION.concat(attendId+ "*");
        Set<String> questionKeys = stringRedisTemplate.keys(questionPattern);
        stringRedisTemplate.delete(questionKeys);

        //删除单机分数
        String scoreKey = KeyConstant.REDIS_KEY_GAME_MIND_ANSWER_QUESTION_SCORE.concat(attendId.toString());
        stringRedisTemplate.delete(scoreKey);


    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void participateGame(GameMindParticipateGameParam param) {
        // 无论是随机对战还是好友对战, 全量更新用户信息与房间信息
        List<String> userNums = new ArrayList<>();
        for (GameMindParticipateGameRecordParam item : param.getRecords()) {
            GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = new GameMindUserDetailsCacheDTO();
            gameMindUserDetailsCacheDTO.setAccumulatedGameScore(0);
            gameMindUserDetailsCacheDTO.setUserNum(item.getUserNum());
            gameMindUserDetailsCacheDTO.setAttendNum(param.getAttendNum());
            gameMindUserDetailsCacheDTO.setRoomNum(param.getRoomNum());
            gameMindUserDetailsCacheDTO.setRecordId(item.getRecordId());
            gameMindUserDetailsCacheDTO.setAttendStatus(GameAttendRecordStatusEnum.INITSTATUS);
            // 设置有效时间为10分钟
            redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(item.getUserNum()), gameMindUserDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
            userNums.add(item.getUserNum());
        }
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = new GameMindRoomDetailsCacheDTO();
        gameMindRoomDetailsCacheDTO.setAttendNum(param.getAttendNum());
        gameMindRoomDetailsCacheDTO.setRoomNum(param.getRoomNum());
        gameMindRoomDetailsCacheDTO.setPoint(param.getPoint());
        gameMindRoomDetailsCacheDTO.setUserNums(userNums);
        gameMindRoomDetailsCacheDTO.setInitialUserNums(new ArrayList<>(userNums));
        gameMindRoomDetailsCacheDTO.setQuestions(param.getQuestions());
        /*
         *  以参与编号为key, 保存当前用户以及匹配对手
         */
        redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(gameMindRoomDetailsCacheDTO.getGroupNum()), gameMindRoomDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
    }

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<String> readyStartGame(ReadyStartGameParam param) {
        String userKey = KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(param.getUserNum());
        BoundValueOperations<String, GameMindUserDetailsCacheDTO> userDetailsBoundValueOperations = redisTemplate.boundValueOps(userKey);
        String roomDetailsKey = KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(param.getAttendNum());
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations = redisTemplate.boundValueOps(roomDetailsKey);
        // 保存当前用户和匹配对手到缓存, 初始化游戏状态
        GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = new GameMindUserDetailsCacheDTO();
        gameMindUserDetailsCacheDTO.setUserNum(param.getUserNum());
        gameMindUserDetailsCacheDTO.setIsHomeowner(param.getIsHomeowner());
        gameMindUserDetailsCacheDTO.setAttendNum(param.getAttendNum());
        // 设置有效时间为10分钟
        userDetailsBoundValueOperations.set(gameMindUserDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = new GameMindRoomDetailsCacheDTO();
        gameMindRoomDetailsCacheDTO.setAttendNum(param.getAttendNum());
        gameMindRoomDetailsCacheDTO.setUserNums(new ArrayList<>());
        gameMindRoomDetailsCacheDTO.getUserNums().add(param.getUserNum());
        // 以参与编号为key, 保存当前用户以及匹配对手
        boolean ifAbsent = roomDetailsBoundValueOperations.setIfAbsent(gameMindRoomDetailsCacheDTO);
        // 表明key已经存在, 更新房间详情信息
        if (!ifAbsent) {
            gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) roomDetailsBoundValueOperations.get();
            // 如果用户编号已经存在, 不再更新
            if (gameMindRoomDetailsCacheDTO.getUserNums().indexOf(param.getUserNum()) < 0) {
                gameMindRoomDetailsCacheDTO.getUserNums().add(param.getUserNum());
                roomDetailsBoundValueOperations.set(gameMindRoomDetailsCacheDTO);
            }
        }
        // 设置有效时间为10分钟
        roomDetailsBoundValueOperations.expire(GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
        return gameMindRoomDetailsCacheDTO.getUserNums();
    }

    @Override
    public List<StartTheGameUserDTO> getGroupUserNums(String attendNum) {
        List<StartTheGameUserDTO> rtn = new ArrayList<>();
        // 根据参与编号, 查询所有参与的用户
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(attendNum));
        for (String userNum : gameMindRoomDetailsCacheDTO.getUserNums()) {
            String userKey = KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum);
            GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(userKey);
            StartTheGameUserDTO item = new StartTheGameUserDTO();
            item.setUserNum(userNum);
            item.setIsHomeowner(gameMindUserDetailsCacheDTO.getIsHomeowner());
            rtn.add(item);
        }
        return rtn;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long changeAttendStatus(String userNum, GameAttendRecordStatusEnum attendStatus) {
        Long rtn = null;
        String userKey = KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum);
        BoundValueOperations<String, GameMindUserDetailsCacheDTO> userDetailsBoundValueOperations = redisTemplate.boundValueOps(userKey);
        GameMindUserDetailsCacheDTO currentGameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) userDetailsBoundValueOperations.get();
        /*
         *  如果查询为空, 则认为缓存中的数据已经清除, 或者是单机游戏
         */
        if (currentGameMindUserDetailsCacheDTO == null) { return rtn; }
        currentGameMindUserDetailsCacheDTO.setAttendStatus(attendStatus);
        userDetailsBoundValueOperations.set(currentGameMindUserDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
        
        // 根据参与编号, 查询所有参与的用户
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations = redisTemplate.boundValueOps(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(currentGameMindUserDetailsCacheDTO.getGroupNum()));
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) roomDetailsBoundValueOperations.get();
        
        /*
         *  添加计数标记
         *  当最后一个人积分回调时, 判断当前还有几个用户
         */
        Long counter = concurrencyControlService.incrementAndGet(ConcurrencyControlConstant.MINDPK_DEDUCTION_POINTS_CALLBACK.concat(currentGameMindUserDetailsCacheDTO.getGroupNum()), 60, TimeUnit.SECONDS);
        if (counter.intValue() == gameMindRoomDetailsCacheDTO.getInitialUserNums().size()) {
            /*
             *  遍历所有用户, 判断参与成功的用户有多少人
             *  1.如果参与成功的人数只有一个人, 返回当前用户参与记录的id
             *  2.如果参与成功人数大于1, 不做其他处理
             */
            Map<Long, GameMindUserDetailsCacheDTO> userDeatilsMap = new HashMap<>();
            int successSize = 0;
            for (String item : gameMindRoomDetailsCacheDTO.getUserNums()) {
                GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = null;
                if (item.equals(userNum)) {
                    gameMindUserDetailsCacheDTO = currentGameMindUserDetailsCacheDTO;
                } else {
                    gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(item));
                }
                // 如果从缓存查询为空, 认定该用户参与失败
                if (gameMindUserDetailsCacheDTO == null) continue;
                userDeatilsMap.put(gameMindUserDetailsCacheDTO.getRecordId(), gameMindUserDetailsCacheDTO);
                if (GameAttendRecordStatusEnum.ATTENDSUCCESS == gameMindUserDetailsCacheDTO.getAttendStatus()) {
                    rtn = gameMindUserDetailsCacheDTO.getRecordId();
                    successSize++;
                }
            }
            // 如果成功人数只有一个, 返回成功的那个用户编号
            if (successSize == 1) {
                GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = userDeatilsMap.get(rtn);
                // 设置参与状态为退还
                gameMindUserDetailsCacheDTO.setAttendStatus(GameAttendRecordStatusEnum.REFUND);
                redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(gameMindUserDetailsCacheDTO.getUserNum()), gameMindUserDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
                return rtn;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public CheckPointsDeductionStatusDTO checkPointsDeductionStatus(String userNum) {
        CheckPointsDeductionStatusDTO rtn = new CheckPointsDeductionStatusDTO();
        // 查询当前用户详情, 如果查询用户为空说明用户参与失败已经被移除
        GameMindUserDetailsCacheDTO currentGameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum));
        if (currentGameMindUserDetailsCacheDTO == null) {
            rtn.setIsAllComplete(false);
            rtn.setIsDeductedSuccess(false);
            return rtn;
        }
        // 根据参与编号, 查询所有参与的用户
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations = redisTemplate.boundValueOps(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(currentGameMindUserDetailsCacheDTO.getGroupNum()));
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) roomDetailsBoundValueOperations.get();
        // 如果房间信息在缓存中查询不到, 说明游戏未开始, 或者游戏已经结束
        if (gameMindRoomDetailsCacheDTO == null) {
            throw new WrongOperationException("游戏未在进行中");
        }
        // 
        Boolean isAllComplete = true;
        // 遍历所有用户查询
        for (String item : gameMindRoomDetailsCacheDTO.getUserNums()) {
            GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(item));
            if (gameMindUserDetailsCacheDTO == null) {
                rtn.setIsAllComplete(false);
                rtn.setIsDeductedSuccess(false);
                return rtn;
            }
            if (GameAttendRecordStatusEnum.ATTENDSUCCESS != gameMindUserDetailsCacheDTO.getAttendStatus()) {
                isAllComplete = false;
            }
            // 当前用户积分是否扣除成功
            if (item.equals(userNum)) {
                if (GameAttendRecordStatusEnum.ATTENDSUCCESS == gameMindUserDetailsCacheDTO.getAttendStatus()) {
                    rtn.setIsDeductedSuccess(true);
                } else if (GameAttendRecordStatusEnum.ATTENDFAIL == gameMindUserDetailsCacheDTO.getAttendStatus() || GameAttendRecordStatusEnum.REFUND == gameMindUserDetailsCacheDTO.getAttendStatus()) {
                    rtn.setIsDeductedSuccess(false);
                }
            }
        }
        rtn.setIsAllComplete(isAllComplete);
        // 当所有积分扣除完成
        if (rtn.getIsAllComplete()) {
            Long deductionMark = concurrencyControlService.incrementAndGet(ConcurrencyControlConstant.MINDPK_CHECK_DEDUCTION_POINTS.concat(currentGameMindUserDetailsCacheDTO.getGroupNum()), 10, TimeUnit.SECONDS);
            /*
             *  如果标记大于1, 说明已经返回了, 保证这条消息只发送一次
             *  设置为isAllComplete为false
             *  设置isDissolution为false
             */
            if (deductionMark > 1) {
                rtn.setIsAllComplete(false);
            }
        }
        // 设置第一题开始答题时间
        List<Date> answerStartTimes = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        // 添加延迟的时间
        now.add(Calendar.MILLISECOND, questionTime);
        answerStartTimes.add(now.getTime());
        gameMindRoomDetailsCacheDTO.setAnswerStartTimes(answerStartTimes);
        redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(currentGameMindUserDetailsCacheDTO.getGroupNum()), gameMindRoomDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
        return rtn;
    }

    @SuppressWarnings("unchecked")
    @Override
    public VerifyAnswerDTO verifyAnswer(VerifyAnswerParam param) {
        VerifyAnswerDTO rtn = new VerifyAnswerDTO();
        String userkey = KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(param.getUserNum());
        BoundValueOperations<String, GameMindUserDetailsCacheDTO> userDetailsBoundValueOperations = redisTemplate.boundValueOps(userkey);
        GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) userDetailsBoundValueOperations.get();
        /*
         *  如果缓存被清除了, 说明游戏已经超时
         *  如果参与状态为游戏失败, 说明已经退出
         */
        if (gameMindUserDetailsCacheDTO == null || gameMindUserDetailsCacheDTO.getGroupNum() == null) {
            throw new WrongOperationException("您已经不在游戏中");
        }
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations = redisTemplate.boundValueOps(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(gameMindUserDetailsCacheDTO.getGroupNum()));
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) roomDetailsBoundValueOperations.get();
        /*
         *  如果缓存被清除了, 说明游戏已经超时
         */
        if (gameMindRoomDetailsCacheDTO == null) {
            throw new WrongOperationException("游戏已经结束");
        }
        if (gameMindRoomDetailsCacheDTO.getAnswerStartTimes() == null || gameMindRoomDetailsCacheDTO.getAnswerStartTimes().isEmpty()) {
            throw new WrongOperationException("请先检查积分是否扣除成功");
        }
        
        int currentQuestionIdx = gameMindRoomDetailsCacheDTO.getAnswerStartTimes().size() - 1;
        Date currentAnswerStartTime = gameMindRoomDetailsCacheDTO.getAnswerStartTimes().get(currentQuestionIdx);
        GameMindParticipateGameQuestionParam currentQuestion = gameMindRoomDetailsCacheDTO.getQuestions().get(currentQuestionIdx);
        
        // 从缓存获取配置
        GameMindConfigDTO gameMindConfig = gameConfigCacheService.getGameMindConfig();
        // 如果是一道答题
        boolean isLastQuestion = gameMindRoomDetailsCacheDTO.getAnswerStartTimes().size() == gameMindConfig.getQuestionCount();
        rtn.setIsLastQuestion(isLastQuestion);
        
        /*
         *  如果当前用户的状态是游戏失败, 说明是在游戏中退出的用户
         *  所以当用户退出, 自动触发答题结果并返回
         */
        if (GameAttendRecordStatusEnum.GAMEPLAYFAIL == gameMindUserDetailsCacheDTO.getAttendStatus()) {
            // 清除当前用户的缓存
            redisTemplate.delete(userkey);
            rtn.setAccumulatedGameScore(gameMindUserDetailsCacheDTO.getAccumulatedGameScore() < 0 ? 0 : gameMindUserDetailsCacheDTO.getAccumulatedGameScore());
            // 校验是否已经答完, 或者只剩最后一个人
            rtn = verifyEveryoneFinishedAnswering(rtn, gameMindRoomDetailsCacheDTO, gameMindConfig);
            // 如果当前这道题已经回答完毕, 直接返回
            if (rtn.getIsLastAnswer()) {
                return rtn;
            }
            throw new WrongOperationException("您已经不在游戏中");
        }
        /*
         *  1.校验用户是否已经答过这道题目了
         *  2.玩家回答的这道题目是否与缓存中记录的一致
         */
        if (gameMindUserDetailsCacheDTO.getAnswerResults() == null) {
            gameMindUserDetailsCacheDTO.setAnswerResults(new ArrayList<>());
        }
        // 用户已经答过这道题目了
        if ((currentQuestionIdx + 1) == gameMindUserDetailsCacheDTO.getAnswerResults().size()) {
            if (currentQuestion.getId().equals(param.getQuestionId())) {
                throw new WrongOperationException("您已经答过这道题目了");
            } else {
                throw new WrongOperationException("下场答题还未开始");
            }
        } else if (currentQuestionIdx == gameMindUserDetailsCacheDTO.getAnswerResults().size()) {
            if (!currentQuestion.getId().equals(param.getQuestionId())) {
                throw new WrongOperationException("当前作答的不是这道题目");
            }
        }
        GameMindAnswerResultCacheDTO gameMindAnswerResultCacheDTO = new GameMindAnswerResultCacheDTO();
        gameMindAnswerResultCacheDTO.setQuestionId(param.getQuestionId());
        gameMindAnswerResultCacheDTO.setRightAnswer(param.getRightAnswer());
        
        int useTime = (int) ((new Date().getTime() - currentAnswerStartTime.getTime()) / 1000);
        /*
         *  考虑延迟可能上下偏差
         *  如果使用时间小于0,则使用时间为0
         *  如果使用时间大于配置的倒计时时间,只使用时间为倒计时时间
         */
        if (useTime < 0) {
            useTime = 0;
        } else if (useTime > gameMindConfig.getCountDown()) {
            useTime = gameMindConfig.getCountDown();
        }
        gameMindAnswerResultCacheDTO.setUseTime(useTime);
        if (currentQuestion.getRightAnswer().equals(param.getRightAnswer())) {
            gameMindAnswerResultCacheDTO.setFlag(true);
            gameMindAnswerResultCacheDTO.setPoint(getPoint(gameMindConfig, gameMindAnswerResultCacheDTO.getUseTime(), rtn.getIsLastQuestion()));
        } else {
            gameMindAnswerResultCacheDTO.setFlag(false);
            gameMindAnswerResultCacheDTO.setPoint(0);
        }
        gameMindUserDetailsCacheDTO.getAnswerResults().add(gameMindAnswerResultCacheDTO);
        if (gameMindUserDetailsCacheDTO.getAccumulatedGameScore() == null) {
            gameMindUserDetailsCacheDTO.setAccumulatedGameScore(0);
        }
        gameMindUserDetailsCacheDTO.setAccumulatedGameScore(gameMindUserDetailsCacheDTO.getAccumulatedGameScore() + gameMindAnswerResultCacheDTO.getPoint());
        redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(param.getUserNum()), gameMindUserDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
        rtn.setAccumulatedGameScore(gameMindUserDetailsCacheDTO.getAccumulatedGameScore());
        rtn.setFlag(gameMindAnswerResultCacheDTO.getFlag());
        rtn.setRightAnswer(currentQuestion.getRightAnswer());
        rtn = verifyEveryoneFinishedAnswering(rtn, gameMindRoomDetailsCacheDTO, gameMindConfig);
        return rtn;
    }

    @Override
    public List<GameMindUserDetailsCacheDTO> allInformation(String groupNum) {
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(groupNum));
        if (gameMindRoomDetailsCacheDTO == null) {
            return new ArrayList<>();
        }
        List<GameMindUserDetailsCacheDTO> allUserInfos = new ArrayList<>();
        for (String userNum : gameMindRoomDetailsCacheDTO.getUserNums()) {
            GameMindUserDetailsCacheDTO item = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum));
            if (item != null) {
                allUserInfos.add(item);
            }
        }
        // 放入退出用户信息
        if (gameMindRoomDetailsCacheDTO.getExitUserInformations() != null) {
            allUserInfos.addAll(gameMindRoomDetailsCacheDTO.getExitUserInformations());
        }
        return allUserInfos;
    }
    

    @SuppressWarnings("unchecked")
    @Override
    public void clearAllCache(String groupNum) {
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(groupNum));
        if (gameMindRoomDetailsCacheDTO == null) { return; }
        for (String userNum : gameMindRoomDetailsCacheDTO.getUserNums()) {
            redisTemplate.delete(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum));
        }
        redisTemplate.delete(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(groupNum));
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public void quit(String userNum) {
        String userKey = KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum);
        BoundValueOperations<String, GameMindUserDetailsCacheDTO> userDeatilsBoundValueOperations = redisTemplate.boundValueOps(userKey);
        GameMindUserDetailsCacheDTO gameMindUserDetailsCacheDTO = (GameMindUserDetailsCacheDTO) userDeatilsBoundValueOperations.get();
        if (gameMindUserDetailsCacheDTO == null) {
            return;
        }
        if (gameMindUserDetailsCacheDTO.getGroupNum() == null) {
            return;
        }
        String roomKey = KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(gameMindUserDetailsCacheDTO.getGroupNum());
        // 把退出的用户信息放入房间信息中, 不再做改动
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDeatilsBoundValueOperations = redisTemplate.boundValueOps(roomKey);
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) roomDeatilsBoundValueOperations.get();
        if (gameMindRoomDetailsCacheDTO == null) { return; }
        /*
         *  参与成功退出, 更改参与状态为参与失败
         *  如果在参与失败时推出, 在缓存中删除该删除用户
         */
        if (GameAttendRecordStatusEnum.ATTENDSUCCESS == gameMindUserDetailsCacheDTO.getAttendStatus()) {
            // 设置累计积分为0, 参与状态为游戏失败
            gameMindUserDetailsCacheDTO.setAccumulatedGameScore(-1);
            gameMindUserDetailsCacheDTO.setAttendStatus(GameAttendRecordStatusEnum.GAMEPLAYFAIL);
            userDeatilsBoundValueOperations.set(gameMindUserDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
            // 把退出用户放入退出用户集合
            if (gameMindRoomDetailsCacheDTO.getExitUserInformations() == null) {
                gameMindRoomDetailsCacheDTO.setExitUserInformations(new ArrayList<>(gameMindRoomDetailsCacheDTO.getInitialUserNums().size()));
            }
            gameMindRoomDetailsCacheDTO.getExitUserInformations().add(gameMindUserDetailsCacheDTO);
            roomDeatilsBoundValueOperations.set(gameMindRoomDetailsCacheDTO);
        } else if (GameAttendRecordStatusEnum.ATTENDFAIL == gameMindUserDetailsCacheDTO.getAttendStatus() || GameAttendRecordStatusEnum.REFUND == gameMindUserDetailsCacheDTO.getAttendStatus()) {
            redisTemplate.delete(userKey);
        }
        
        // 如果在人数为空时,清除缓存中的所有key
        if (gameMindRoomDetailsCacheDTO.getUserNums() != null && !gameMindRoomDetailsCacheDTO.getUserNums().isEmpty()) {
            gameMindRoomDetailsCacheDTO.getUserNums().remove(userNum);
            roomDeatilsBoundValueOperations.set(gameMindRoomDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
            // 当房间内的游戏人数为0时, 清空缓存信息, 并且返回
            if (gameMindRoomDetailsCacheDTO.getUserNums().size() == 0) {
                clearAllCache(gameMindUserDetailsCacheDTO.getGroupNum());
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public GameMindUserDetailsCacheDTO getSingleUserInformation(String userNum) {
        String userKey = KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum);
        BoundValueOperations<String, GameMindUserDetailsCacheDTO> userDeatilsBoundValueOperations = redisTemplate.boundValueOps(userKey);
        return (GameMindUserDetailsCacheDTO) userDeatilsBoundValueOperations.get();
    }
    
    /*************************************************************************
     * private method
    *************************************************************************/
    /**
     * 根据使用时间获取获得的分数
     * 
     * @param secScores
     * @param useTime
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月21日
     * @updateDate 2018年3月21日
     */
    private Integer getPoint(GameMindConfigDTO gameMindConfig, Integer useTime, boolean isLastQuestion) {
        List<SecScoreParam> secScores = gameMindConfig.getSecScore();
        for (SecScoreParam item : secScores) {
            if (useTime <= item.getSecond()) {
                if (isLastQuestion) {
                    return item.getPoint() * gameMindConfig.getLastScoreMultiple();
                } else {
                    return item.getPoint();
                }
            }
        }
        return 0;
    }
    
    /**
     * 获取排行榜的信息
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月23日
     * @updateDate 2018年3月23日
     */
    @SuppressWarnings("unchecked")
    private List<GameMindRankDTO> getRankingInformation(boolean isLastQuestion, GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO, GameMindConfigDTO gameMindConfig) {
        /*
         *  判断是否是最后一个用户
         */
        if (!isLastQuestion && gameMindRoomDetailsCacheDTO.getUserNums().size() > 1) {
            return null;
        }
        // 获取参与的玩家人数
        int playerNum = gameMindRoomDetailsCacheDTO.getInitialUserNums().size();
        List<GameMindRankDTO> rtn = new ArrayList<>(playerNum);
        List<GameMindUserDetailsCacheDTO> roomGameMindUserDetailsCacheDTO = new ArrayList<>(playerNum);
        // 分数对应人数map
        Map<Integer, Integer> rankNumMap = new HashMap<>();
        // 获取所有用户, 游戏中的或者退出的用户
        for (String userNum : gameMindRoomDetailsCacheDTO.getUserNums()) {
            GameMindUserDetailsCacheDTO itemDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(userNum));
            if (itemDTO == null) { continue; } 
            roomGameMindUserDetailsCacheDTO.add(itemDTO);
        }
        if (gameMindRoomDetailsCacheDTO.getExitUserInformations() != null) {
            roomGameMindUserDetailsCacheDTO.addAll(gameMindRoomDetailsCacheDTO.getExitUserInformations());
        }
        for (GameMindUserDetailsCacheDTO item : roomGameMindUserDetailsCacheDTO) {
            if (rankNumMap.containsKey(item.getAccumulatedGameScore())) {
                rankNumMap.put(item.getAccumulatedGameScore(), rankNumMap.get(item.getAccumulatedGameScore()) + 1);
            } else {
                rankNumMap.put(item.getAccumulatedGameScore(), 1);
            }
        }
        List<Integer> rankList = new ArrayList<>(rankNumMap.keySet());
        // 将分数排序
        rankList.sort((o1, o2) -> {
            return o2.compareTo(o1);
        });
        // 分数对应排名map
        int currentRank = 1;
        Map<Integer, Integer> rankMap = new HashMap<>(rankList.size());
        for (Integer item : rankList) {
            rankMap.put(item, currentRank);
            // 当前排名
            currentRank = currentRank + rankNumMap.get(item);
        }
        GamePointAllotParam currentAllotConfig = null;
        // 找到当前人数的积分分配配置
        for (GamePointAllotParam item : gameMindConfig.getAllotList()) {
            if (item.getAttendCount().equals(playerNum)) {
                currentAllotConfig = item;
            }
        }
        BigDecimal totalRewardpPoints = new BigDecimal(gameMindRoomDetailsCacheDTO.getPoint()).multiply(new BigDecimal(playerNum));
        // 计算游戏结果
        for (GameMindUserDetailsCacheDTO item : roomGameMindUserDetailsCacheDTO) {
            // 更新房间内用户的排名和奖励积分数,以及星星数
            item.setGameRank(rankMap.get(item.getAccumulatedGameScore()));
            // 当前分值的总人数
            Integer num = rankNumMap.get(item.getAccumulatedGameScore());
            // 获取当前分值的总占比
            BigDecimal totalProportion = new BigDecimal(0);
            for (int i = 0; i < num; i++) {
                totalProportion = totalProportion.add(new BigDecimal(currentAllotConfig.getRankPoint().get((item.getGameRank() + i) - 1)));
            }
            BigDecimal rewardPoint = totalRewardpPoints.multiply(totalProportion).divide(new BigDecimal(num), 0, RoundingMode.DOWN);;
            item.setRewardPoint(rewardPoint);
            item.setRewardStar(Integer.valueOf(currentAllotConfig.getRankStar().get(item.getGameRank() - 1)));
            // 获取之前的参与状态
            GameAttendRecordStatusEnum beforeStatus = item.getAttendStatus();
            if (item.getRewardStar().intValue() > 0) {
                item.setAttendStatus(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS);
            } else {
                item.setAttendStatus(GameAttendRecordStatusEnum.GAMEPLAYFAIL);
            }
            GameMindRankDTO gameMindRankDTO = new GameMindRankDTO();
            // 更新更新累计积分, 把-1改为0
            item.setAccumulatedGameScore(item.getAccumulatedGameScore() < 0 ? 0 : item.getAccumulatedGameScore());
            gameMindRankDTO.setAccumulatedGameScore(item.getAccumulatedGameScore());
            gameMindRankDTO.setAttendStatus(item.getAttendStatus());
            gameMindRankDTO.setGameRank(item.getGameRank());
            gameMindRankDTO.setRewardPoint(item.getRewardPoint());
            gameMindRankDTO.setRewardStar(item.getRewardStar());
            gameMindRankDTO.setUserNum(item.getUserNum());
            rtn.add(gameMindRankDTO);
            if (GameAttendRecordStatusEnum.ATTENDSUCCESS == beforeStatus) {
                redisTemplate.opsForValue().set(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(item.getUserNum()), item, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
            }
        }
        // 保存退出用户的排名信息
        BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations = redisTemplate.boundValueOps(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(gameMindRoomDetailsCacheDTO.getGroupNum()));
        roomDetailsBoundValueOperations.set(gameMindRoomDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
        return rtn;
    }
    
    /**
     * 防止有玩家中途退出, 校验所有玩家是否都已经答完题目了
     * 如果是最后一个人答完题目直接返回单题答题结束
     * 
     * @author jiangxinjun
     * @createDate 2018年3月28日
     * @updateDate 2018年3月28日
     */
    @SuppressWarnings("unchecked")
    private VerifyAnswerDTO verifyEveryoneFinishedAnswering(VerifyAnswerDTO rtn, GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO, GameMindConfigDTO gameMindConfig) {
        // 校验是否是最后一个人, 如果是最后一个人返回排行信息
        if (gameMindRoomDetailsCacheDTO.getUserNums().size() == 1) {
            rtn.setIsLastAnswer(true);
            rtn.setIsLastQuestion(true);
        }
        List<MultiplayerSubmitScoresDTO> submitScores = new ArrayList<>(gameMindRoomDetailsCacheDTO.getUserNums().size());
        // 当前回答的是第几道题目
        int currentQuestionNum = gameMindRoomDetailsCacheDTO.getAnswerStartTimes().size();
        // 校验是否所有玩家答题完成
        Boolean isLastAnswer = true;
        for (String item : gameMindRoomDetailsCacheDTO.getUserNums()) {
            GameMindUserDetailsCacheDTO itemDTO = (GameMindUserDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_USER_DETAILS.concat(item));
            if (itemDTO == null) { continue; }
            if (GameAttendRecordStatusEnum.ATTENDSUCCESS.equals(itemDTO.getAttendStatus())) {
                MultiplayerSubmitScoresDTO multiplayerSubmitScoresDTO = new MultiplayerSubmitScoresDTO();
                multiplayerSubmitScoresDTO.setUserNum(item);
                multiplayerSubmitScoresDTO.setAccumulatedGameScore(itemDTO.getAccumulatedGameScore());
                int selectedAnswer = -1;
                if (itemDTO.getAnswerResults() != null && itemDTO.getAnswerResults().size() == currentQuestionNum) {
                    selectedAnswer = itemDTO.getAnswerResults().get(currentQuestionNum - 1).getRightAnswer();
                }
                multiplayerSubmitScoresDTO.setSelectedAnswer(selectedAnswer);
                submitScores.add(multiplayerSubmitScoresDTO);
                int itemAnswerCount = itemDTO.getAnswerResults() == null ? 0 : itemDTO.getAnswerResults().size();
                if (currentQuestionNum != itemAnswerCount) {
                    isLastAnswer = false;
                    break;
                }
            }
        }
        if (rtn.getIsLastAnswer() == null || !rtn.getIsLastAnswer()) {
            rtn.setIsLastAnswer(isLastAnswer);
        }
        /*
         *  如果是答最后一道题目, 更新下一道题的答题开始时间
         *  判断是否是最后一道题目
         */
        if (rtn.getIsLastAnswer()) {
            // 保证当前这道题目结束时, 所有玩家只会接收到一个结束标记
            String endKey = ConcurrencyControlConstant.MINDPK_QUESTION_ANSWER_END.concat(gameMindRoomDetailsCacheDTO.getGroupNum()).concat("_" + currentQuestionNum);
            Long count = concurrencyControlService.incrementAndGet(endKey, 10, TimeUnit.SECONDS);
            if (count > 1) {
                rtn.setIsLastAnswer(false);
            } else {
                // 如果是最后回的, 返回所有人答题的选择的答案和积分
                rtn.setSubmitScores(submitScores);
                if (!rtn.getIsLastQuestion()) {
                    Calendar now = Calendar.getInstance();
                    // 添加延迟的时间
                    now.add(Calendar.MILLISECOND, showCorrectAnswerTime + hideTime + questionTime);
                    gameMindRoomDetailsCacheDTO.getAnswerStartTimes().add(now.getTime());
                    BoundValueOperations<String, GameMindRoomDetailsCacheDTO> roomDetailsBoundValueOperations = redisTemplate.boundValueOps(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(gameMindRoomDetailsCacheDTO.getGroupNum()));
                    roomDetailsBoundValueOperations.set(gameMindRoomDetailsCacheDTO, GAME_MIND_CACHE_TIMEOUT, TimeUnit.SECONDS);
                }
            }
        }
        if (!rtn.getIsLastQuestion() || !rtn.getIsLastAnswer()) {
            return rtn;
        }
        List<GameMindRankDTO> rankList = getRankingInformation(rtn.getIsLastQuestion(), gameMindRoomDetailsCacheDTO, gameMindConfig);
        rtn.setRanks(rankList);
        return rtn;
    }

    
	@Override
	public void setGameSimpleMindQuestionIds(List<String> list) {
		String tempKey =KeyConstant.GAME_MIND_QUESTION_SIMPLE_TEMP_CACHE;
		//如果list为空，说明同步完成，将使用中的缓存key替换
		if(list.isEmpty()){
			Long size =stringRedisTemplate.opsForList().size(tempKey);
			List<String> strs = stringRedisTemplate.opsForList().range(tempKey, 0, size);
			String useKey =KeyConstant.GAME_MIND_QUESTION_SIMPLE_USE_CACHE;
			stringRedisTemplate.delete(useKey);
			stringRedisTemplate.opsForList().leftPushAll(useKey, strs);
			return;
		}
		stringRedisTemplate.opsForList().leftPushAll(tempKey, list);
		
	}
	
	
	@Override
	public void setGameDifficultyMindQuestionIds(List<String> list) {
		String tempKey =KeyConstant.GAME_MIND_QUESTION_DIFFICULTY_TEMP_CACHE;
		//如果list为空，说明同步完成，将使用中的缓存key替换
		if(list.isEmpty()){
			Long size =stringRedisTemplate.opsForList().size(tempKey);
			List<String> strs = stringRedisTemplate.opsForList().range(tempKey, 0, size);
			String useKey =KeyConstant.GAME_MIND_QUESTION_DIFFICULTY_USE_CACHE;
			stringRedisTemplate.delete(useKey);
			stringRedisTemplate.opsForList().leftPushAll(useKey, strs);
			return;
		}
		stringRedisTemplate.opsForList().leftPushAll(tempKey, list);
		
	}

	@Override
	public void removeMindQuestion() {
		stringRedisTemplate.delete(KeyConstant.GAME_MIND_QUESTION_SIMPLE_TEMP_CACHE);
		stringRedisTemplate.delete(KeyConstant.GAME_MIND_QUESTION_DIFFICULTY_TEMP_CACHE);
	}

    @Override
    public List<String> getMindCacheQuestionIds(Integer configCount, Integer easyCount) {
        CacheGameQuestionEnum questionEnum;
        if (configCount.equals(easyCount)) {
            questionEnum = CacheGameQuestionEnum.SIMPLE;
        } else if (0 == easyCount) {
            // 全部困难的题目
            questionEnum = CacheGameQuestionEnum.DIFFICULT;
        } else {
            questionEnum = CacheGameQuestionEnum.OTHER;
        }

        return getRandomQuestionIds(configCount, easyCount, questionEnum);
    }

    @Override
    public GameMindRoomDetailsCacheDTO getRobotMindQuestions(String groupNum) {

        return (GameMindRoomDetailsCacheDTO) redisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_GAME_MIND_ROOM_DETAILS.concat(groupNum));
    }


    /**
     * 随机获取题目idList
     *
     * @param configCount  取题数量
     * @param easyCount    简单题目数量
     * @param questionEnum 题目 类型
     * @return 题目idList
     */
    private List<String> getRandomQuestionIds(Integer configCount,
                                              Integer easyCount, CacheGameQuestionEnum questionEnum) {
        String simpleKey = KeyConstant.GAME_MIND_QUESTION_SIMPLE_USE_CACHE;
        String difficultyKey = KeyConstant.GAME_MIND_QUESTION_DIFFICULTY_USE_CACHE;
        Integer totalSize = 0;
        Integer sizeSimple = 0;
        Integer sizeDiff = 0;
        List<String> list = new ArrayList<>();
        if (CacheGameQuestionEnum.OTHER == questionEnum) {
            sizeSimple = stringRedisTemplate.opsForList().size(simpleKey).intValue();
            sizeDiff = stringRedisTemplate.opsForList().size(difficultyKey).intValue();
            totalSize = sizeSimple + sizeDiff;
            if (totalSize <= configCount) {
                list = stringRedisTemplate.opsForList().range(simpleKey, 0, sizeSimple);
                List<String> diffList = stringRedisTemplate.opsForList().range(difficultyKey, 0, sizeDiff);
                list.addAll(diffList);
                return list;
            }
            Integer[] randomSimpleIndex = NumberUtil.randomArray(0, sizeSimple - 1, easyCount);
            Integer[] randomDiffIndex = NumberUtil.randomArray(0, sizeDiff - 1, configCount - easyCount);
            if (randomSimpleIndex == null || randomDiffIndex == null) {
                return list;
            }
            for (int i = 0; i < randomSimpleIndex.length; i++) {
                list.add(stringRedisTemplate.opsForList().index(simpleKey, randomSimpleIndex[i]));
            }
            for (int i = 0; i < randomDiffIndex.length; i++) {
                list.add(stringRedisTemplate.opsForList().index(difficultyKey, randomDiffIndex[i]));
            }
            return list;
        } else if (CacheGameQuestionEnum.SIMPLE == questionEnum) {
            //不存在困难的题目
            totalSize = stringRedisTemplate.opsForList().size(simpleKey).intValue();
            if (totalSize <= configCount) {
                list = stringRedisTemplate.opsForList().range(simpleKey, 0, totalSize);
                return list;
            }
            Integer[] randomIndex = NumberUtil.randomArray(0, totalSize - 1, configCount);
            if (randomIndex == null) {
                return list;
            }
            for (int i = 0; i < randomIndex.length; i++) {
                String questionId = stringRedisTemplate.opsForList().index(simpleKey, randomIndex[i]);
                list.add(questionId);
            }
            return list;
        } else if (CacheGameQuestionEnum.DIFFICULT == questionEnum) {
            //困难的题目
            totalSize = stringRedisTemplate.opsForList().size(difficultyKey).intValue();
            if (totalSize <= configCount) {
                list = stringRedisTemplate.opsForList().range(difficultyKey, 0, totalSize);
                return list;
            }
            Integer[] randomIndex = NumberUtil.randomArray(0, totalSize - 1, configCount);
            if (randomIndex == null) {
                return list;
            }
            for (int i = 0; i < randomIndex.length; i++) {
                String questionId = stringRedisTemplate.opsForList().index(difficultyKey, randomIndex[i]);
                list.add(questionId);
            }
            return list;
        }
        return new ArrayList<>();
    }
}
