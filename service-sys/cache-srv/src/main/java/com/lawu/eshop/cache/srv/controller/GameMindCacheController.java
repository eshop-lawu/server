package com.lawu.eshop.cache.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.dto.CheckPointsDeductionStatusDTO;
import com.lawu.eshop.cache.dto.GameMindRoomDetailsCacheDTO;
import com.lawu.eshop.cache.dto.GameMindUserDetailsCacheDTO;
import com.lawu.eshop.cache.dto.StartTheGameUserDTO;
import com.lawu.eshop.cache.dto.VerifyAnswerDTO;
import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameParam;
import com.lawu.eshop.cache.param.ReadyStartGameParam;
import com.lawu.eshop.cache.param.VerifyAnswerParam;
import com.lawu.eshop.cache.srv.service.GameMindCacheService;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
@RestController
@RequestMapping(value = "gameMindCache/")
public class GameMindCacheController extends BaseController {

    @Autowired
    private GameMindCacheService gameMindCacheService;

    /**
     * 设置头脑PK每一题的开始时间
     *
     * @param attendId
     * @param times
     */
    @RequestMapping(value = "setGameStartTime/{attendId}", method = RequestMethod.PUT)
    public Result setGameTime(@PathVariable("attendId") Long attendId, @RequestParam("times") Long times) {
        gameMindCacheService.setGameTime(attendId, times);
        return successCreated();
    }

    /**
     * 获取上一题的答题开始时间
     *
     * @param attendId
     * @return
     */
    @RequestMapping(value = "getLastGameTime/{attendId}", method = RequestMethod.GET)
    public Result<Long> getLastGameTime(@PathVariable("attendId") Long attendId) {
        Long time = gameMindCacheService.getLastGameTime(attendId);
        return successGet(time);
    }

    /**
     * 设置用户的题目缓存
     *
     * @param attendIds
     * @param questionIds
     */
    @RequestMapping(value = "setGameMindQuestionCache", method = RequestMethod.POST)
    public Result setGameMindQuestionCache(@RequestParam("attendIds") String attendIds, @RequestParam("questionIds") String questionIds) {
        gameMindCacheService.setGameMindQuestionCache(attendIds, questionIds);
        return successCreated();
    }

    /**
     * 获取缓存的题目列表
     * @param attendId 参与id
     * @return 返回题目ids
     */
    @RequestMapping(value = "getMindQuestions", method = RequestMethod.GET)
    public Result<String> getMindQuestions(@RequestParam("attendId") String attendId) {
        String questionIds = gameMindCacheService.getMindQuestions(attendId);
        return successGet(questionIds);
    }

    /**
     * 添加答题结果记录
     * @param questionId
     * @param record
     * @return
     */
    @RequestMapping(value = "addAttendRecordResult", method = RequestMethod.POST)
    public Result addAttendRecordResult(@RequestParam("attendId") Long attendId, @RequestParam("record") String record) {
        gameMindCacheService.addAttendRecordResult(attendId, record);
        return successCreated();
    }

    /**
     * 获取答题结果记录
     * @param attendId
     * @return
     */
    @RequestMapping(value = "getAttendRecordResult", method = RequestMethod.GET)
    public Result<List<GameMindAttendRecordResultParam>> getAttendRecordResult(@RequestParam("attendId") Long attendId) {
        List<GameMindAttendRecordResultParam> record = gameMindCacheService.getAttendRecordResult(attendId);
        return successGet(record);
    }

    /**
     * 更新答题分数
     * @param attendId
     * @param point
     * @return
     */
    @RequestMapping(value = "addAnswerQuestionPoint", method = RequestMethod.PUT)
    public Result addAnswerQuestionPoint(@RequestParam("attendId") Long attendId,@RequestParam("point") Integer point){
        gameMindCacheService.addAnswerQuestionPoint(attendId,point);
        return successCreated();
    }

    /**
     * 获取答题分数
     * @param attendId
     * @return
     */
    @RequestMapping(value = "getAnswerQuestionPoint", method = RequestMethod.PUT)
    public Result<Integer> getAnswerQuestionPoint(@RequestParam("attendId") Long attendId){
        Integer score = gameMindCacheService.getAnswerQuestionPoint(attendId);
        return successGet(score);
    }

    @RequestMapping(value = "delSingleGameMindCache", method = RequestMethod.DELETE)
    public Result delSingleGameMindCache(@RequestParam("attendId") Long attendId){
        gameMindCacheService.delSingleGameMindCache(attendId);
        return successDelete();
    }
    
    /**
     * 参与头脑PK, 保存参与记录id, 以及生成的问题
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "participateGame", method = RequestMethod.POST)
    public Result participateGame(@RequestBody GameMindParticipateGameParam param) {
        gameMindCacheService.participateGame(param);
        return successCreated();
    }

    /**
     * 每次用户上线, 把当前用户放入缓存中, 并且返回, 目前已经在组内的用户
     * 
     * @param userNum
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月24日
     */
    @RequestMapping(value = "readyStartGame", method = RequestMethod.PUT)
    public Result<List<String>> readyStartGame(@RequestBody ReadyStartGameParam param) {
        return successCreated(gameMindCacheService.readyStartGame(param));
    }

    /**
     * 根据参与编号, 获取参与的用户编号集合
     *
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月24日
     */
    @RequestMapping(value = "getGroupUserNums", method = RequestMethod.GET)
    public Result<List<StartTheGameUserDTO>> getGroupUserNums(@RequestParam("attendNum") String attendNum) {
        return successGet(gameMindCacheService.getGroupUserNums(attendNum));
    }

    /**
     * 更改用户参与状态<p>
     * 在最后一个用户更改状态之后, 检查当前成功参与的用户有多少人<p>
     * 如果只有一个人参与成功, 返回参与成功的记录id
     *
     * @param userNum
     * @param attendStatus
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @RequestMapping(value = "changeAttendStatus", method = RequestMethod.PUT)
    public Result<Long> changeAttendStatus(@RequestParam("userNum") String userNum, @RequestParam("attendStatus") GameAttendRecordStatusEnum attendStatus) {
        return successCreated(gameMindCacheService.changeAttendStatus(userNum, attendStatus));
    }

    /**
     * 校验所有参与用户积分扣除状态
     *
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月19日
     */
    @RequestMapping(value = "checkPointsDeductionStatus", method = RequestMethod.PUT)
    public Result<CheckPointsDeductionStatusDTO> checkPointsDeductionStatus(@RequestParam("userNum") String userNum) {
        try {
            return successCreated(gameMindCacheService.checkPointsDeductionStatus(userNum));
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }
    
    /**
     * 验证答案是否正确
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    @RequestMapping(value = "verifyAnswer", method = RequestMethod.PUT)
    public Result<VerifyAnswerDTO> verifyAnswer(@RequestBody VerifyAnswerParam param) {
        try {
            return successCreated(gameMindCacheService.verifyAnswer(param));
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }
    
    /**
     * 返回缓存中的所有信息, 用户同步到数据库中
     * 
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    @RequestMapping(value = "allInformation", method = RequestMethod.PUT)
    public Result<List<GameMindUserDetailsCacheDTO>> allInformation(@RequestParam("attendNum") String attendNum) {
        return successCreated(gameMindCacheService.allInformation(attendNum));
    }
    
    /**
     * 清除缓存中的所有数据
     * 
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "clearAllCache", method = RequestMethod.PUT)
    public Result clearAllCache(@RequestParam("attendNum") String attendNum) {
        gameMindCacheService.clearAllCache(attendNum);
        return successCreated();
    }
    
    /**
     * 用户退出, 清除用户的缓存数据, 以及设置累计积分为0
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "quit", method = RequestMethod.PUT)
    public Result quit(@RequestParam("userNum") String userNum) {
        gameMindCacheService.quit(userNum);
        return successCreated();
    }
    
    /**
     * 根据用户编号获取单个用户信息
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月22日
     * @updateDate 2018年3月22日
     */
    @RequestMapping(value = "getSingleUserInformation", method = RequestMethod.PUT)
    public Result<GameMindUserDetailsCacheDTO> getSingleUserInformation(@RequestParam("userNum") String userNum) {
        try {
            return successCreated(gameMindCacheService.getSingleUserInformation(userNum));
        } catch (WrongOperationException e) {
            return successCreated(e.getRet(), e.getMessage());
        }
    }
    
    /**
     * 简单题库队列
     * @param list
     * @return
     */
    @RequestMapping(value = "setGameSimpleMindQuestionIds", method = RequestMethod.PUT)
    public Result setGameSimpleMindQuestionIds (@RequestBody List<String> list){
    	 gameMindCacheService.setGameSimpleMindQuestionIds(list);
         return successCreated();
    }
    
    /**
     * 困难题库队列
     * @param list
     * @return
     */
    @RequestMapping(value = "setGameDifficultyMindQuestionIds", method = RequestMethod.PUT)
    public Result setGameDifficultyMindQuestionIds (@RequestBody List<String> list){
    	 gameMindCacheService.setGameDifficultyMindQuestionIds(list);
         return successCreated();
    }
    
    
    @RequestMapping(value = "removeMindQuestion", method = RequestMethod.PUT)
    public Result removeMindQuestion (){
    	 gameMindCacheService.removeMindQuestion();
         return successCreated();
    }

    @RequestMapping(value = "getMindCacheQuestionIds", method = RequestMethod.GET)
    public Result<List<Long>> getMindCacheQuestionIds(@RequestParam("configCount") Integer configCount,
                                                      @RequestParam("easyCount") Integer easyCount) {
        List<String> idStrings = gameMindCacheService.getMindCacheQuestionIds(configCount, easyCount);
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < idStrings.size(); i++) {
            ids.add(Long.valueOf(idStrings.get(i)));
        }
        return successGet(ids);
    }

    @RequestMapping(value = "getRobotMindQuestions", method = RequestMethod.GET)
    public Result<GameMindRoomDetailsCacheDTO> getRobotMindQuestions(@RequestParam("groupNum") String groupNum){
        GameMindRoomDetailsCacheDTO cacheDTO = gameMindCacheService.getRobotMindQuestions(groupNum);
        return successGet(cacheDTO);
    }
}
