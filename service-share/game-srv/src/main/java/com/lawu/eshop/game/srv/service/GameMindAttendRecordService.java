package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.game.param.GameMindAnswerQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.game.param.ParticipateGameMindParam;
import com.lawu.eshop.game.srv.bo.GameMindAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GameMindParticipateGameBO;
import com.lawu.eshop.game.srv.bo.GameMindResultBO;

/**
 * @author zhangyong
 * @date 2018/3/12.
 */
public interface GameMindAttendRecordService {

	/**
     * 生成参与记录并且扣除积分
     * 
     * @param param
     * @author jiangxinjun
     * @createDate 2018年3月13日
     * @updateDate 2018年3月13日
     */
    Long addAttendRecords(MindAttendParam param);

    GameAttendRecordStatusEnum getGameAttendStatus(Long attendId, String userNum);

    GameMindResultBO answerQuestion(GameMindAnswerQuestionParam param, List<String> idList,List<GameMindAttendRecordResultParam> record);
    
    /**
     * 两人或多人参与游戏生成参与记录,并且扣除积分
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    GameMindParticipateGameBO participateGame(ParticipateGameMindParam param);

    GameMindAttendRecordBO queryAttendRecordByIdAndNum(Long attendId, String userNum);
    
    /**
     * 同步缓存中的数据到数据库
     * 
     * @param groupNum
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月24日
     */
    void synchronizeDataFormCache(String groupNum);
    
    /**
     * 用户退出, 清除数据库中和缓存中的数据<p>
     * 1.用户主动退出<p>
     * 2.用户杀掉进程<p>
     * 不要在此接口加入事务注解, 因为在解散房间的时候会抛出异常
     * 
     * @param userNum
     * @param groupNum
     * @author jiangxinjun
     * @createDate 2018年3月20日
     * @updateDate 2018年3月20日
     */
    GameRoomDetailsDTO quit(String userNum, String groupNum);
    
    /**
     * 扣除积分回调
     * 
     * @param gameMindAttendRecordId
     * @param attendRecordStatus
     * @author jiangxinjun
     * @createDate 2018年3月27日
     * @updateDate 2018年3月27日
     */
    void deductionPointsCallback(Long gameMindAttendRecordId, GameAttendRecordStatusEnum attendRecordStatus, boolean isRobot);

    String initRobotAttendRecordResult(String groupNum);

    /**
     * 根据分组编号查询参与游戏的用户编号(完成动力任务)
     *
     * @param groupNum
     * @return
     * @author meishuquan
     */
    List<String> getAttendRecordUserNums(String groupNum);

}
