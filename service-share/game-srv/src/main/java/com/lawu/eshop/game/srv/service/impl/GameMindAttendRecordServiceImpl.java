package com.lawu.eshop.game.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameMindAnswerResultCacheDTO;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GameMindRoomDetailsCacheDTO;
import com.lawu.eshop.cache.dto.GameMindUserDetailsCacheDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.StartTheGameUserDTO;
import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameQuestionParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameRecordParam;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.common.param.SecScoreParam;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.GameMindRobotAnswerDTO;
import com.lawu.eshop.game.dto.MindGameScoreDTO;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.GameAccountAllotParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.param.GameMindAnswerQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.game.param.MindAttendRecordParam;
import com.lawu.eshop.game.param.ParticipateGameMindParam;
import com.lawu.eshop.game.srv.GameSrvConfig;
import com.lawu.eshop.game.srv.bo.GameMindAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GameMindParticipateGameBO;
import com.lawu.eshop.game.srv.bo.GameMindParticipateGameQuestionBO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionBO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionListBO;
import com.lawu.eshop.game.srv.bo.GameMindResultBO;
import com.lawu.eshop.game.srv.bo.StartTheGameBO;
import com.lawu.eshop.game.srv.converter.GameMindAttendRecordConverter;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDO;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDOExample;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordResultDO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordResultDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindQuestionDOMapper;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.GameMindAttendRecordResultService;
import com.lawu.eshop.game.srv.service.GameMindAttendRecordService;
import com.lawu.eshop.game.srv.service.GameMindCacheService;
import com.lawu.eshop.game.srv.service.GameMindConfigService;
import com.lawu.eshop.game.srv.service.GameMindQuestionService;
import com.lawu.eshop.game.srv.service.GameRobotAccountService;
import com.lawu.eshop.game.srv.service.GameRoomService;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.mq.dto.game.reply.DeductionPointsReply;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.NumberUtil;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
@Service
public class GameMindAttendRecordServiceImpl extends BaseController implements GameMindAttendRecordService {

    @Autowired
    private GameMindAttendRecordDOMapper gameMindAttendRecordDOMapper;
    
    @Autowired
    private GameMindAttendRecordResultDOMapper gameMindAttendRecordResultDOMapper;

    @Autowired
    private GameMindCacheService gameMindCacheService;
    
    @Autowired
    private GameMindQuestionDOMapper gameMindQuestionDOMapper;

    @Autowired
    private  GameMindConfigService gameMindConfigService;
    
    @Autowired
    @Qualifier("gameMindDeductionPointsTransactionMainServiceImpl")
    private TransactionMainService<DeductionPointsReply> gameMindDeductionPointsTransactionMainServiceImpl;
    
    @Autowired
    private GameMindQuestionService gameMindQuestionService;

    @Autowired
    private GameMindAttendRecordResultService gameMindAttendRecordResultService;

    @Autowired
    private GameAccountService gameAccountService;

    @Autowired
    @Qualifier("gameMindRewardPointsTransactionMainServiceImpl")
    private TransactionMainService<Reply> gameMindRewardPointsTransactionMainServiceImpl;

    @Autowired
    private GameConfigCacheService gameConfigCacheService;

    @Autowired
    private GameRoomService gameRoomService;

    @Autowired
    private GameSrvConfig gameSrvConfig;
    
    @Autowired
    @Qualifier("gameMindRefundPointsTransactionMainServiceImpl")
    private TransactionMainService<Reply> gameMindRefundPointsTransactionMainServiceImpl;

    @Autowired
    private GameRobotAccountService gameRobotAccountService;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addAttendRecords(MindAttendParam param) {
        String attendNum = IdWorkerHelperImpl.generate(BizIdType.MINDGAME);

        GameMindAttendRecordDO recordDO = new GameMindAttendRecordDO();
        recordDO.setUserNum(param.getUserNum());
        recordDO.setAttendCount(1);
        recordDO.setAttendNum(attendNum);
        recordDO.setAttendPoint(param.getAttendPoint());
        recordDO.setAttendStar(param.getAttendStar());
        recordDO.setAttendType(param.getAttendType().getVal());
        recordDO.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
        recordDO.setQuestionIds(param.getQuestionIds());
        recordDO.setGmtCreate(new Date());
        recordDO.setRoomNum(param.getRoomNum());
        recordDO.setGmtModified(new Date());
        gameMindAttendRecordDOMapper.insertSelective(recordDO);

        //减星星
        GameAccountStarParam starParam = new GameAccountStarParam();
        starParam.setAttendNum(attendNum);
        starParam.setGameType(GameTypeEnum.MIND);
        List<GameAccountAllotParam> params = new ArrayList<>();
        GameAccountAllotParam allotParam = new GameAccountAllotParam();
        allotParam.setUserNum(param.getUserNum());
        allotParam.setStar(param.getAttendStar());
        params.add(allotParam);
        starParam.setList(params);
        starParam.setRecordEnum(GameAttendRecordStatusEnum.ATTENDSUCCESS);
        gameAccountService.dealStar(starParam);
        return recordDO.getId();
    }

    @Override
    public GameAttendRecordStatusEnum getGameAttendStatus(Long attendId, String userNum) {
        GameMindAttendRecordDOExample example = new GameMindAttendRecordDOExample();
        example.createCriteria().andIdEqualTo(attendId).andUserNumEqualTo(userNum);
        List<GameMindAttendRecordDO> list = gameMindAttendRecordDOMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return GameAttendRecordStatusEnum.getEnum(list.get(0).getStatus());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameMindResultBO answerQuestion(GameMindAnswerQuestionParam param, List<String> idList, List<GameMindAttendRecordResultParam> record) {
        Integer point = 0;
        Boolean isTrue = false;//是否正确
        Boolean isFinish = false;//是否完成
        Integer questionIndex = 0;//题目下标
        Integer totalScore = 0;//总分
        Integer rightAnswer = -1;//正确答案
        String attendNum = "";
        Result<GameMindConfigDTO> config = gameConfigCacheService.getGameMindConfig();
        GameMindConfigDTO configDTO = config.getModel();
        Integer maxCountDown = configDTO.getCountDown() + gameSrvConfig.getDelayTime();
        if (param.getUserTime() <= maxCountDown) {
            MindGameScoreDTO scoreDTO =  getMindPoint(configDTO.getSecScore(), param.getUserTime(),maxCountDown);
            point = scoreDTO.getPoint();
        }
        GameMindAttendRecordResultParam resultParam = new GameMindAttendRecordResultParam();
        GameMindQuestionBO questionBO = gameMindQuestionService.getQuestion(param.getQuestionId(),param.getRightAnswer());
        rightAnswer = questionBO.getRightAnswer();
        if (questionBO.getRightAnswer().equals(param.getRightAnswer()) && param.getUserTime() <= maxCountDown) {
            //答题正确
            isTrue = true;
        } else {
            point = 0;
        }
        if (idList.get(idList.size() - 1).equals(param.getQuestionId().toString())) {
            //最后一题
            isFinish = true;
            //最后一次得分*倍数

            point = point * configDTO.getLastScoreMultiple();
            Integer score = gameMindCacheService.getAnswerQuestionPoint(param.getAttendId()).getModel();
            //总分数
            totalScore = point + score;
        }


        resultParam.setFlag(isTrue);
        resultParam.setAnswer(questionBO.getAnswerName());
        resultParam.setQuestionId(param.getQuestionId());
        resultParam.setRightAnswer(param.getRightAnswer());
        resultParam.setPoint(point);
        resultParam.setUseTime(param.getUserTime());
        resultParam.setGmtCreate(new Date());
        resultParam.setGmtModified(new Date());
        resultParam.setMindAttendId(param.getAttendId());


        if (idList.get(0).equals(param.getQuestionId().toString())) {
            List<GameMindAttendRecordResultParam> recordResultParams = new ArrayList<>();
            recordResultParams.add(resultParam);
            String attendResultRecord = JSONArray.toJSONString(recordResultParams);
            //缓存答题记录
            gameMindCacheService.addAttendRecordResult(param.getAttendId(), attendResultRecord);
            //缓存答题所得分数
            gameMindCacheService.addAnswerQuestionPoint(param.getAttendId(), point);

        } else {
            //  List<GameMindAttendRecordResultParam> oldRecords = new ArrayList<>();
            for (int i = 0; i < idList.size(); i++) {
                if (idList.get(i).equals(param.getQuestionId().toString())) {
                    //题目==缓存题目一直获取下标
                    questionIndex = i;
                }

            }
            if (questionIndex != 0 && questionIndex != record.size()) {
                return null;
            }
            record.add(resultParam);
            String attendResultRecord = JSONArray.toJSONString(record);

            gameMindCacheService.addAttendRecordResult(param.getAttendId(), attendResultRecord);

            //增加缓存分数
            gameMindCacheService.addAnswerQuestionPoint(param.getAttendId(), point);
        }

        GameMindResultBO resultBO = new GameMindResultBO();
        resultBO.setIsTrue(isTrue);
        resultBO.setIsFinish(isFinish);
        resultBO.setPoint(point);
        resultBO.setRightAnswer(rightAnswer);
        resultBO.setTotalScore(totalScore);
        if (isFinish) {
            // -- 同步数据库答题记录
            gameMindAttendRecordResultService.addAttendRecordsResult(record);
            MindAttendRecordParam recordParam = new MindAttendRecordParam();
            if (totalScore >= configDTO.getSuccessScore()) {
                //挑战成功
                resultBO.setAwardPoint(configDTO.getAwardPoint());
                resultBO.setAwardStar(configDTO.getAwardStar());
                resultBO.setStatus(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS);
                recordParam.setRewardPoint(configDTO.getAwardPoint());//奖励积分
                recordParam.setRewardStar(configDTO.getAwardStar());//奖励星星

            } else {
                resultBO.setAwardPoint(configDTO.getAttendPoint());
                resultBO.setAwardStar(0);
                resultBO.setStatus(GameAttendRecordStatusEnum.GAMEPLAYFAIL);
                recordParam.setRewardPoint(0);//奖励积分
                recordParam.setRewardStar(0);//奖励星星
            }
            recordParam.setAttendId(param.getAttendId());
            recordParam.setGameScore(totalScore);
            recordParam.setStatus(resultBO.getStatus());

            //单机结果更新参与表记录
            GameAccountAllotParam allotParam = new GameAccountAllotParam();
            allotParam.setUserNum(param.getUserNum());
            allotParam.setStar(configDTO.getAwardStar());
            attendNum = updateAttendRecordStatus(recordParam, allotParam);
            // ---清楚所有相关缓存
            gameMindCacheService.delSingleGameMindCache(param.getAttendId());
        }
        resultBO.setAttendNum(attendNum);

        return resultBO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GameMindParticipateGameBO participateGame(ParticipateGameMindParam param) {
        // 生成参与编号
        String attendNum = null;
        String roomNum = null;
        List<StartTheGameUserDTO> userInfos = null;
        // 从缓存获取配置
        GameMindConfigDTO gameMindConfigParamDTO = gameMindConfigService.findGameMindConfigFormCache();
        Integer attendPoint = gameMindConfigParamDTO.getAttendPoint();
        // 根据参与类型, 获取参与的用户编号集合
        if (AttendTypeEnum.RANDOM.equals(param.getAttendType())) {
            attendNum = param.getGroupNum();
            Result<List<StartTheGameUserDTO>> getGroupUserNumsResult = gameMindCacheService.getGroupUserNums(attendNum);
            if (!isSuccess(getGroupUserNumsResult)) {
                throw new WrongOperationException(getGroupUserNumsResult.getRet(), getGroupUserNumsResult.getMsg());
            }
            // 判断是否两个人都已经上线了
            if (getGroupUserNumsResult.getModel().size() != 2) {
                throw new WrongOperationException("随机对战需要两人连接才能开始");
            }
            userInfos = getGroupUserNumsResult.getModel();
            // 随机对战考虑参与编号与用户编号重复, 添加校验
            GameMindAttendRecordDOExample gameMindAttendRecordDOExample = new GameMindAttendRecordDOExample();
            gameMindAttendRecordDOExample.createCriteria().andAttendNumEqualTo(attendNum);
            long recordCount = gameMindAttendRecordDOMapper.countByExample(gameMindAttendRecordDOExample);
            if (recordCount > 0) {
                throw new WrongOperationException("游戏已经开始");
            }
        } else if (AttendTypeEnum.MANYPEOPLE.equals(param.getAttendType())) {
            roomNum = param.getGroupNum();
            StartTheGameBO startTheGameBO = gameRoomService.startTheGame(roomNum, GameTypeEnum.MIND);
            userInfos = startTheGameBO.getUserInfos();
            attendPoint = startTheGameBO.getAttendPoint().intValue();
        }
        if (attendNum == null) {
            attendNum = IdWorkerHelperImpl.generate(BizIdType.MINDGAME);
        }
        List<String> userNums = new ArrayList<>(userInfos.size());
        GameMindQuestionListBO gameMindQuestionListBO = gameMindQuestionService.getMindQuestions(gameMindConfigParamDTO.getQuestionCount(),gameMindConfigParamDTO.getQuestionSimpleCount());
        List<GameMindParticipateGameRecordParam> records = new ArrayList<>();
        for (StartTheGameUserDTO userInfo : userInfos) {
            userNums.add(userInfo.getUserNum());
            GameMindAttendRecordDO recordDO = new GameMindAttendRecordDO();
            recordDO.setUserNum(userInfo.getUserNum());
            recordDO.setAttendCount(userInfos.size());
            recordDO.setAttendNum(attendNum);
            recordDO.setAttendPoint(attendPoint);
            recordDO.setAttendStar(gameMindConfigParamDTO.getDeductStar());
            recordDO.setAttendType(param.getAttendType().getVal());
            recordDO.setStatus(GameAttendRecordStatusEnum.INITSTATUS.getVal());
            recordDO.setQuestionIds(StringUtils.join(gameMindQuestionListBO.getQuestionIds(), ","));
            recordDO.setRoomNum(roomNum);
            recordDO.setGmtModified(new Date());
            recordDO.setGmtCreate(new Date());
            gameMindAttendRecordDOMapper.insertSelective(recordDO);
            GameMindParticipateGameRecordParam recordParam = new GameMindParticipateGameRecordParam();
            recordParam.setRecordId(recordDO.getId());
            recordParam.setUserNum(userInfo.getUserNum());
            records.add(recordParam);
        }
        GameMindParticipateGameParam gameMindParticipateGameParam = new GameMindParticipateGameParam();
        gameMindParticipateGameParam.setPoint(attendPoint);
        gameMindParticipateGameParam.setAttendNum(attendNum);
        gameMindParticipateGameParam.setRoomNum(roomNum);
        gameMindParticipateGameParam.setQuestions(new ArrayList<>());
        gameMindParticipateGameParam.setRecords(records);
        
        GameMindParticipateGameBO rtn = new GameMindParticipateGameBO();
        rtn.setUserNums(userNums);
        /*
         *  计算本轮答题最大分值
         *  单题最大积分 * 答题的出题数
         */
        Integer maximumScore = 0;
        if (gameMindConfigParamDTO.getSecScore() != null && !gameMindConfigParamDTO.getSecScore().isEmpty()) {
            Integer maxScore = gameMindConfigParamDTO.getSecScore().get(0).getPoint();
            // 最后一题分数翻倍
            maximumScore = maxScore * (gameMindConfigParamDTO.getQuestionCount() - 1) + maxScore * gameMindConfigParamDTO.getLastScoreMultiple();
        }
        rtn.setMaximumScore(maximumScore);
        rtn.setQuestions(new ArrayList<>());
        
        List<GameMindQuestionDO> gameMindQuestionDOs = gameMindQuestionListBO.getQuestionDOS();
        // 以参与编号为key, 把放入题目的id和答案放入缓存, 题目的详细信息返回出去
        for (GameMindQuestionDO item : gameMindQuestionDOs) {
            GameMindParticipateGameQuestionBO question = new GameMindParticipateGameQuestionBO();
            question.setAnswers(JSONObject.parseArray(item.getAnswers(), String.class));
            question.setCategoryName(item.getCategoryName());
            question.setId(item.getId());
            question.setTitle(item.getTitle());
            rtn.getQuestions().add(question);

            GameMindParticipateGameQuestionParam gameMindParticipateGameQuestionParam = new GameMindParticipateGameQuestionParam();
            gameMindParticipateGameQuestionParam.setId(item.getId());
            gameMindParticipateGameQuestionParam.setRightAnswer(item.getRightAnswer());
            gameMindParticipateGameParam.getQuestions().add(gameMindParticipateGameQuestionParam);
        }
        // 保存参与记录id, 以及生成的问题到缓存
        gameMindCacheService.participateGame(gameMindParticipateGameParam);

        // 最后再发送事务消息
        for (GameMindParticipateGameRecordParam item : records) {

            // 如果是随机匹配
            if (AttendTypeEnum.RANDOM == param.getAttendType()) {
                //判断是否机器人
               boolean isRobot = gameRobotAccountService.checkMemberIsRobot(item.getUserNum());
                if(isRobot){
                    deductionPointsCallback(item.getRecordId(), GameAttendRecordStatusEnum.ATTENDSUCCESS, isRobot);
                    continue;
                }else{
                    // 判断是否有免费次数, 如果有免费次数扣除免费次数
                    Boolean isHasFreeTimes = gameAccountService.getShareCount(item.getUserNum(), GameTypeEnum.MIND);
                    if (isHasFreeTimes) {
                        // 扣除免费次数
                        gameAccountService.reduceFreeCount(item.getUserNum(), GameTypeEnum.MIND);
                        // 调用扣除积分回调方法
                        deductionPointsCallback(item.getRecordId(), GameAttendRecordStatusEnum.ATTENDSUCCESS, false);
                        continue;
                    }
                }
            }
            gameMindDeductionPointsTransactionMainServiceImpl.sendNotice(item.getRecordId());
        }
        return rtn;
    }

    @Override
    public GameMindAttendRecordBO queryAttendRecordByIdAndNum(Long attendId, String userNum) {
        GameMindAttendRecordDOExample example = new GameMindAttendRecordDOExample();
        example.createCriteria().andIdEqualTo(attendId).andUserNumEqualTo(userNum);
        List<GameMindAttendRecordDO> list = gameMindAttendRecordDOMapper.selectByExample(example);
        return GameMindAttendRecordConverter.converterBO(list);
    }

    /**
     * 单机结果更新参与表记录
     * @param param
     * @return 返回参与编号
     */
    @Transactional(rollbackFor = Exception.class)
    private String updateAttendRecordStatus(MindAttendRecordParam param,GameAccountAllotParam allotParam){
        GameMindAttendRecordDO recordDO = new GameMindAttendRecordDO();
        recordDO.setGmtModified(new Date());
        recordDO.setStatus(param.getStatus().getVal());
        recordDO.setGameScore(param.getGameScore());
        recordDO.setRewardPoint(new BigDecimal(param.getRewardPoint()));
        recordDO.setRewardStar(param.getRewardStar());
        recordDO.setId(param.getAttendId());
        gameMindAttendRecordDOMapper.updateByPrimaryKeySelective(recordDO);
        GameMindAttendRecordDO oldRecord = gameMindAttendRecordDOMapper.selectByPrimaryKey(param.getAttendId());
        if(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS  == param.getStatus()){
            // --加积分异步
         //   gameMindRewardPointsTransactionMainServiceImpl.sendNotice(param.getAttendId());
            // 加星星
            GameAccountStarParam starParam = new GameAccountStarParam();
            starParam.setAttendNum(oldRecord.getAttendNum());
            starParam.setGameType(GameTypeEnum.MIND);
            List<GameAccountAllotParam> params = new ArrayList<>();
            params.add(allotParam);
            starParam.setList(params);
            starParam.setRecordEnum(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS);
            gameAccountService.dealStar(starParam);
        }

        return oldRecord.getAttendNum();
    }

    /**
     * 根据时长获取分数
     * @param params
     * @param userTime
     * @return
     */
    private MindGameScoreDTO getMindPoint(List<SecScoreParam> params, Integer userTime,Integer maxCountDown) {
        Collections.sort(params, new Comparator<SecScoreParam>() {
            @Override
            public int compare(SecScoreParam o1, SecScoreParam o2) {
                return o1.getSecond() - o2.getSecond();
            }
        });
        Integer point = 0;
        for (int i = 0; i < params.size(); i++) {
            if (userTime <= params.get(i).getSecond()) {
                point = params.get(i).getPoint();
                break;
            } else if (userTime > params.get(params.size() - 1).getSecond() && userTime <= maxCountDown) {
                //在延迟范围内返回最后的一个分数
                point = params.get(params.size() - 1).getPoint();
                //level = GameScoreLevelEnum.getEnum(params.get(params.size() - 1).getLevel());
                break;
            }
        }
        MindGameScoreDTO scoreDTO = new MindGameScoreDTO();
        scoreDTO.setPoint(point);
        return scoreDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void synchronizeDataFormCache(String groupNum) {
        boolean isRobot = false;
        Result<List<GameMindUserDetailsCacheDTO>> allInformationResult = gameMindCacheService.allInformation(groupNum);
        if (!isSuccess(allInformationResult)) {
            throw new WrongOperationException(allInformationResult.getRet(), allInformationResult.getMsg());
        }
        List<GameMindUserDetailsCacheDTO> allUser = allInformationResult.getModel();
        if (allUser == null || allUser.isEmpty()) {
            return;
        }
        List<GameAccountAllotParam> params = new ArrayList<>();
        // 用于缓存答案
        Map<String, String> questionAnswer = new HashMap<>();
        List<Long> rewardPointsRecordIds = new ArrayList<>();
        for (GameMindUserDetailsCacheDTO item : allUser) {
            // 星星为正数才加
            isRobot = gameRobotAccountService.checkMemberIsRobot(item.getUserNum());
            if (!isRobot && item.getRewardStar() > 0  ) {
                GameAccountAllotParam allotParam = new GameAccountAllotParam();
                allotParam.setUserNum(item.getUserNum());
                allotParam.setStar(item.getRewardStar());
                params.add(allotParam);
            }
            // 总共答题时间
            Integer questionUseTime = 0;
            if (item.getAnswerResults() != null && !item.getAnswerResults().isEmpty()) {
                // 保存答题过程
                for (GameMindAnswerResultCacheDTO answerItem : item.getAnswerResults()) {
                    if (!questionAnswer.containsKey(answerItem.getQuestionId() + "-" + answerItem.getRightAnswer())) {
                        // 获取问题的中文答案
                        GameMindQuestionDO gameMindQuestionDO = gameMindQuestionDOMapper.selectByPrimaryKey(answerItem.getQuestionId());
                        List<String> answers = JSONObject.parseArray(gameMindQuestionDO.getAnswers(), String.class);
                        for (int i = 0; i < answers.size(); i++) {
                            questionAnswer.put(answerItem.getQuestionId() + "-" + i, answers.get(i));
                        }
                    }
                    GameMindAttendRecordResultDO gameMindAttendRecordResultDO = new GameMindAttendRecordResultDO();
                    gameMindAttendRecordResultDO.setMindAttendId(item.getRecordId());
                    gameMindAttendRecordResultDO.setPoint(answerItem.getPoint());
                    gameMindAttendRecordResultDO.setQuestionId(answerItem.getQuestionId());
                    gameMindAttendRecordResultDO.setRightAnswer(answerItem.getRightAnswer());
                    gameMindAttendRecordResultDO.setAnswer(questionAnswer.get(answerItem.getQuestionId() + "-" + answerItem.getRightAnswer()));
                    questionUseTime += answerItem.getUseTime();
                    gameMindAttendRecordResultDO.setUseTime(answerItem.getUseTime());
                    gameMindAttendRecordResultDO.setFlag(answerItem.getFlag());
                    gameMindAttendRecordResultDO.setGmtModified(new Date());
                    gameMindAttendRecordResultDO.setGmtCreate(new Date());
                    gameMindAttendRecordResultDOMapper.insertSelective(gameMindAttendRecordResultDO);
                }
            }
            // 更新游戏参与记录
            GameMindAttendRecordDO record = new GameMindAttendRecordDO();
            record.setId(item.getRecordId());
            record.setGameRank(item.getGameRank());
            record.setGameScore(item.getAccumulatedGameScore());
            record.setQuestionUseTime(questionUseTime);
            record.setRewardPoint(item.getRewardPoint());
            record.setStatus(item.getAttendStatus().getVal());
            record.setGmtModified(new Date());
            gameMindAttendRecordDOMapper.updateByPrimaryKeySelective(record);
            
            // 当奖励积分大于0时, 加入奖励积分记录id集合 非机器人
            if (!isRobot &&  record.getRewardPoint().doubleValue() > 0D ) {
                rewardPointsRecordIds.add(record.getId());
            }

        }
        //减星星
        GameAccountStarParam starParam = new GameAccountStarParam();
        starParam.setAttendNum(allUser.get(0).getAttendNum());
        starParam.setGameType(GameTypeEnum.MIND);
        starParam.setList(params);
        starParam.setRecordEnum(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS);
        gameAccountService.dealStar(starParam);
        
        // 清除缓存中的所有数据
        gameMindCacheService.clearAllCache(groupNum);
        
        // 最后去添加积分
        for (Long id : rewardPointsRecordIds) {
            gameMindRewardPointsTransactionMainServiceImpl.sendNotice(id);
        }
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public GameRoomDetailsDTO quit(String userNum, String groupNum) {
        GameRoomDetailsDTO rtn = new GameRoomDetailsDTO();
        rtn.setIsDissolution(false);
        /*
         *  判断用户是否在游戏中, 通过用户编号和grouoNum查询参与记录
         *  1.在游戏中, 获取参与编号, 清除数据库以及缓存中的房间内数据, 以及清除游戏累计积分
         *  2.不在游戏中, 清除数据库以及缓存中的房间内的数据
         */
        Result<GameMindUserDetailsCacheDTO> getSingleUserInformationResult =  gameMindCacheService.getSingleUserInformation(userNum);
        if (!isSuccess(getSingleUserInformationResult)) {
            throw new WrongOperationException(getSingleUserInformationResult.getRet(), getSingleUserInformationResult.getMsg());
        }
        /*
         *  如果记录为空, 说明还未开始游戏
         *  默认房间编号为groupNum
         */
        String roomNum = groupNum;
        GameMindUserDetailsCacheDTO getSingleUserInformationModel = getSingleUserInformationResult.getModel();
        if (getSingleUserInformationModel != null) {
            // 参与成功退出更新为游戏失败
            if (GameAttendRecordStatusEnum.ATTENDSUCCESS == getSingleUserInformationModel.getAttendStatus()) {
                roomNum = getSingleUserInformationModel.getRoomNum();
                // 更新游戏参与记录为游戏失败
                GameMindAttendRecordDO record = new GameMindAttendRecordDO();
                record.setId(getSingleUserInformationModel.getRecordId());
                record.setStatus(GameAttendRecordStatusEnum.GAMEPLAYFAIL.getVal());
                record.setGmtModified(new Date());
                gameMindAttendRecordDOMapper.updateByPrimaryKeySelective(record);
            }
        }
        // 清除缓存中的用户数据
        Result quitResult = gameMindCacheService.quit(userNum);
        if (!isSuccess(quitResult)) {
            throw new WrongOperationException(quitResult.getRet(), quitResult.getMsg());
        }
        // 如果房间编号不为空
        if (StringUtils.isNotBlank(roomNum)) {
            ExitGameRoomParam param = new ExitGameRoomParam();
            param.setRoomNum(roomNum);
            param.setUserNum(userNum);
            param.setGameType(GameTypeEnum.MIND);
            // 考虑在执行退出房间方法, 房间找不到的异常
            try {
                rtn = gameRoomService.exitGameRoom(param);
            } catch (DataNotExistException e) {
            }  catch (WrongOperationException e) {
            }
        }
        return rtn;
    }

    @Override
    public void deductionPointsCallback(Long gameMindAttendRecordId, GameAttendRecordStatusEnum attendRecordStatus, boolean isRobot) {
        // 更改参与记录状态
        GameMindAttendRecordDO record = new GameMindAttendRecordDO();
        record.setId(gameMindAttendRecordId);
        record.setStatus(attendRecordStatus.getVal());
        record.setGmtModified(new Date());
        gameMindAttendRecordDOMapper.updateByPrimaryKeySelective(record);
        
        GameMindAttendRecordDO gameMindAttendRecordDO = gameMindAttendRecordDOMapper.selectByPrimaryKey(gameMindAttendRecordId);
        
        // 如果参与成功, 减星星
        if (!isRobot && GameAttendRecordStatusEnum.ATTENDSUCCESS == attendRecordStatus) {
            GameAccountStarParam starParam = new GameAccountStarParam();
            starParam.setAttendNum(gameMindAttendRecordDO.getAttendNum());
            starParam.setGameType(GameTypeEnum.MIND);
            List<GameAccountAllotParam> params = new ArrayList<>();
            GameAccountAllotParam allotParam = new GameAccountAllotParam();
            allotParam.setUserNum(gameMindAttendRecordDO.getUserNum());
            allotParam.setStar(gameMindAttendRecordDO.getAttendStar());
            params.add(allotParam);
            starParam.setList(params);
            starParam.setRecordEnum(GameAttendRecordStatusEnum.ATTENDSUCCESS);
            gameAccountService.dealStar(starParam);
        }
        
        // 如果不是单人, 需要其他的操作
        if (AttendTypeEnum.STANDALONE != AttendTypeEnum.getEnum(gameMindAttendRecordDO.getAttendType())) {
            // 更新缓存中的参与记录状态
            Result<Long> changeAttendStatusResult = gameMindCacheService.changeAttendStatus(gameMindAttendRecordDO.getUserNum(), attendRecordStatus);
            // 发送退还积分事务消息, 放在方法最后
            Long recoredId = changeAttendStatusResult.getModel();
            if (recoredId != null) {
                /*
                 *  更改参与记录状态为退还, 退还唯一参与成功的人的积分
                 */
                GameMindAttendRecordDO gameMindAttendRecordUpdateDO = new GameMindAttendRecordDO();
                gameMindAttendRecordUpdateDO.setId(recoredId);
                gameMindAttendRecordUpdateDO.setStatus(GameAttendRecordStatusEnum.REFUND.getVal());
                gameMindAttendRecordUpdateDO.setGmtModified(new Date());
                gameMindAttendRecordDOMapper.updateByPrimaryKeySelective(gameMindAttendRecordUpdateDO);
                
                GameMindAttendRecordDO refundGameMindAttendRecord = gameMindAttendRecordDOMapper.selectByPrimaryKey(recoredId);
                //判断退还积分和星星的账户是否机器人
                boolean refundIsRobot = gameRobotAccountService.checkMemberIsRobot(refundGameMindAttendRecord.getUserNum());
                if (refundIsRobot) {
                    return;
                }
                
                // 退还星星
                GameAccountStarParam starParam = new GameAccountStarParam();
                starParam.setAttendNum(refundGameMindAttendRecord.getAttendNum());
                starParam.setGameType(GameTypeEnum.MIND);
                List<GameAccountAllotParam> params = new ArrayList<>();
                GameAccountAllotParam allotParam = new GameAccountAllotParam();
                allotParam.setUserNum(refundGameMindAttendRecord.getUserNum());
                allotParam.setStar(refundGameMindAttendRecord.getAttendStar());
                params.add(allotParam);
                starParam.setList(params);
                starParam.setRecordEnum(GameAttendRecordStatusEnum.REFUND);
                gameAccountService.dealStar(starParam);
                
                // 发送事务消息退还积分
                gameMindRefundPointsTransactionMainServiceImpl.sendNotice(recoredId);
            }
        }        
    }

    @Override
    public String initRobotAttendRecordResult(String groupNum) {
        Result<GameMindRoomDetailsCacheDTO> cacheDTOResult = gameMindCacheService.getRobotMindQuestions(groupNum);
        List<GameMindParticipateGameQuestionParam> questionDOS = cacheDTOResult.getModel().getQuestions();
        GameMindConfigDTO config = gameMindConfigService.findGameMindConfigFormCache();

        Integer[] rightCount = NumberUtil.randomArray(config.getRobotMinRightCount(), config.getRobotMaxRightCount(), 1);
        if (rightCount == null || rightCount.length <= 0) {
            return null;
        }

        List<Object> rightQuestions = NumberUtil.getRandomList(questionDOS, rightCount[0]);
        List<GameMindRobotAnswerDTO> answerDTOS = new ArrayList<>();
        GameMindRobotAnswerDTO answerDTO;
        Integer[] oldAnswer = {0,1,2,3};
        int index =0;
        for (GameMindParticipateGameQuestionParam questionDO : questionDOS) {
            answerDTO = new GameMindRobotAnswerDTO();
            answerDTO.setQuestionId(questionDO.getId());
            for (int i = 0; i < oldAnswer.length; i++) {
                if (questionDO.getRightAnswer().intValue() != oldAnswer[i]) {
                    answerDTO.setRightAnswer(oldAnswer[i]);
                    break;
                }
            }
            List useTimeList = NumberUtil.getRandomList(config.getSecScore(), 1);
            SecScoreParam scoreParam = (SecScoreParam) useTimeList.get(0);
            if (index == 0) {
                answerDTO.setUseTime(scoreParam.getSecond() + 1);//1秒误差时间
            } else {
                answerDTO.setUseTime(scoreParam.getSecond() + 4);//4秒误差时间
            }
            index = index + 1;
            for (Object question : rightQuestions) {
                GameMindParticipateGameQuestionParam rightDO = (GameMindParticipateGameQuestionParam) question;
                if (questionDO.getId() == rightDO.getId().longValue()) {
                    answerDTO.setRightAnswer(questionDO.getRightAnswer());
                    break;
                }
            }
            answerDTOS.add(answerDTO);
        }
        return JSONObject.toJSONString(answerDTOS);
    }

    @Override
    public List<String> getAttendRecordUserNums(String groupNum) {
        GameMindAttendRecordDOExample example = new GameMindAttendRecordDOExample();
        example.createCriteria().andStatusEqualTo(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal()).andAttendNumEqualTo(groupNum);
        example.or().andStatusEqualTo(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal()).andRoomNumEqualTo(groupNum);
        List<GameMindAttendRecordDO> attendRecordDOS = gameMindAttendRecordDOMapper.selectByExample(example);
        List<String> userNumList = new ArrayList<>();
        if (attendRecordDOS.isEmpty()) {
            return userNumList;
        }
        for (GameMindAttendRecordDO attendRecordDO : attendRecordDOS) {
            userNumList.add(attendRecordDO.getUserNum());
        }
        return userNumList;
    }

}
