package com.lawu.eshop.cache.srv.service;

import java.util.List;

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

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public interface GameMindCacheService {
    void setGameTime(Long attendId, Long times);

    Long getLastGameTime(Long attendId);

    void setGameMindQuestionCache(String attendIds, String questionIds);

    String getMindQuestions(String attendId);

    void addAttendRecordResult(Long attendId, String record);

    List<GameMindAttendRecordResultParam> getAttendRecordResult(Long attendId);

    void addAnswerQuestionPoint(Long attendId, Integer point);

    Integer getAnswerQuestionPoint(Long attendId);

    void delSingleGameMindCache(Long attendId);
    
    /**
     * 参与头脑PK, 保存参与记录id, 以及生成的问题
     *
     * @param param
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    void participateGame(GameMindParticipateGameParam param);

    /**
     * 每次用户上线, 把当前用户放入缓存中, 并且返回, 目前已经在组内的用户
     *
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月16日
     */
    List<String> readyStartGame(ReadyStartGameParam param);

    /**
     * 根据参与编号, 获取参与的用户编号集合
     *
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    List<StartTheGameUserDTO> getGroupUserNums(String attendNum);

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
    Long changeAttendStatus(String userNum, GameAttendRecordStatusEnum attendStatus);

    /**
     * 校验所有参与用户积分扣除状态<p>
     * 如果所有参与用户的积分扣除状态都是正常的, 设置答题开始时间
     *
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    CheckPointsDeductionStatusDTO checkPointsDeductionStatus(String userNum);
    
    /**
     * 校验用户的答案是否正确
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    VerifyAnswerDTO verifyAnswer(VerifyAnswerParam param);
    
    /**
     * 返回缓存中的所有信息, 用户同步到数据库中
     * 
     * @param groupNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    List<GameMindUserDetailsCacheDTO> allInformation(String groupNum);
    
    /**
     * 清除缓存中的所有数据
     * 
     * @param groupNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    void clearAllCache(String groupNum);
    
    /**
     * 用户退出, 清除用户的缓存数据, 以及设置累计积分为0
     * 
     * @param userNum
     * @author jiangxinjun
     * @createDate 2018年3月20日
     * @updateDate 2018年3月20日
     */
    void quit(String userNum);
    
    /**
     * 根据用户编号获取单个用户信息
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月22日
     * @updateDate 2018年3月22日
     */
    GameMindUserDetailsCacheDTO getSingleUserInformation(String userNum);

    /**
     * 
     * @param list
     */
	void setGameSimpleMindQuestionIds(List<String> list);
	
	
	void setGameDifficultyMindQuestionIds(List<String> list);

	void removeMindQuestion();

    List<String> getMindCacheQuestionIds(Integer configCount, Integer easyCount);

    GameMindRoomDetailsCacheDTO getRobotMindQuestions(String groupNum);
}
