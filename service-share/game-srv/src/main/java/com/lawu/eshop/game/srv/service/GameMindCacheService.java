package com.lawu.eshop.game.srv.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.dto.GameMindRoomDetailsCacheDTO;
import com.lawu.eshop.cache.dto.GameMindUserDetailsCacheDTO;
import com.lawu.eshop.cache.dto.StartTheGameUserDTO;
import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.cache.param.GameMindParticipateGameParam;
import com.lawu.eshop.cache.param.GameMindRandomMatchUserInformationParam;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
@FeignClient(value = "cache-srv", path = "gameMindCache/")
public interface GameMindCacheService {

    @RequestMapping(value = "setGameStartTime/{attendId}", method = RequestMethod.PUT)
    Result setGameTime(@PathVariable("attendId") Long attendId, @RequestParam("times") Long times);

    @RequestMapping(value = "setGameMindQuestionCache", method = RequestMethod.POST)
    Result setGameMindQuestionCache(@RequestParam("attendIds") String attendIds, @RequestParam("questionIds") String questionIds);


    @RequestMapping(value = "addAttendRecordResult", method = RequestMethod.POST)
    Result addAttendRecordResult(@RequestParam("attendId") Long attendId, @RequestParam("record") String record);

    @RequestMapping(value = "getAttendRecordResult", method = RequestMethod.GET)
    Result<List<GameMindAttendRecordResultParam>> getAttendRecordResult(@RequestParam("attendId") Long attendId);

    @RequestMapping(value = "addAnswerQuestionPoint", method = RequestMethod.PUT)
    Result addAnswerQuestionPoint(@RequestParam("attendId") Long attendId,@RequestParam("point") Integer point);

    @RequestMapping(value = "getAnswerQuestionPoint", method = RequestMethod.PUT)
    Result<Integer> getAnswerQuestionPoint(@RequestParam("attendId") Long attendId);

    @RequestMapping(value = "delSingleGameMindCache", method = RequestMethod.DELETE)
    Result delSingleGameMindCache(@RequestParam("attendId") Long attendId);
    
    /**
     * 寻找匹配对手<p>
     * 1.如果找到, 返回匹配成功
     * 2.如果没有找到,在匹配等待的缓存池放入当前用户编号
     * @param param 当前用户编号
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月13日
     * @updateDate 2018年3月13日
     */
    @RequestMapping(value = "findingMatchOpponents", method = RequestMethod.POST)
    Result<Boolean> findingMatchOpponents(@RequestBody GameMindRandomMatchUserInformationParam param);

    /**
     * 参与头脑PK, 保存参与记录id, 以及生成的问题
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "participateGame", method = RequestMethod.POST)
    Result participateGame(@RequestBody GameMindParticipateGameParam param);

    /**
     * 根据参与编号, 获取参与的用户编号集合
     *
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @RequestMapping(value = "getGroupUserNums", method = RequestMethod.GET)
    Result<List<StartTheGameUserDTO>> getGroupUserNums(@RequestParam("attendNum") String attendNum);

    /**
     * 更改用户参与状态
     *
     * @param userNum
     * @param attendStatus
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @RequestMapping(value = "changeAttendStatus", method = RequestMethod.PUT)
    Result<Long> changeAttendStatus(@RequestParam("userNum") String userNum, @RequestParam("attendStatus") GameAttendRecordStatusEnum attendStatus);
    
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
    Result<List<GameMindUserDetailsCacheDTO>> allInformation(@RequestParam("attendNum") String attendNum);
    
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
    Result clearAllCache(@RequestParam("attendNum") String attendNum);
    
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
    Result quit(@RequestParam("userNum") String userNum);
    
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
    Result<GameMindUserDetailsCacheDTO> getSingleUserInformation(@RequestParam("userNum") String userNum);
    
    /**
     * 简单题库list
     *  @author zhangrc
     */
    @RequestMapping(value = "setGameSimpleMindQuestionIds", method = RequestMethod.PUT)
    Result setGameSimpleMindQuestionIds (@RequestBody List<String> list);
    
    
    /**
     * 困难题库list
     *   @author zhangrc
     */
    @RequestMapping(value = "setGameDifficultyMindQuestionIds", method = RequestMethod.PUT)
    Result setGameDifficultyMindQuestionIds (@RequestBody List<String> list);

    /**
     * 清除缓存
     * @return
     */
    @RequestMapping(value = "removeMindQuestion", method = RequestMethod.PUT)
	Result removeMindQuestion();

    @RequestMapping(value = "getMindCacheQuestionIds", method = RequestMethod.GET)
    Result<List<Long>> getMindCacheQuestionIds(@RequestParam("configCount") Integer configCount, @RequestParam("easyCount") Integer easyCount);

    @RequestMapping(value = "getRobotMindQuestions", method = RequestMethod.GET)
    Result<GameMindRoomDetailsCacheDTO> getRobotMindQuestions(@RequestParam("groupNum") String groupNum);
}
