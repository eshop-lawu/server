package com.lawu.eshop.member.ws.service;

import com.lawu.eshop.cache.dto.CheckPointsDeductionStatusDTO;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.cache.dto.VerifyAnswerDTO;
import com.lawu.eshop.cache.param.ReadyStartGameParam;
import com.lawu.eshop.cache.param.VerifyAnswerParam;
import com.lawu.eshop.game.dto.MindPkOnlineDTO;
import com.lawu.eshop.game.dto.MindPkStartDTO;
import com.lawu.eshop.game.param.MindPkStartParam;
import com.lawu.framework.web.Result;

/**
 * 头脑PK记录接口
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
public interface GameMindRecordService {
    
    /**
     * 准备开始游戏<p>
     * 把上线的用户加入返回, 再返回组中已在线的所有用户
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月16日
     * @updateDate 2018年3月24日
     */
    Result<MindPkOnlineDTO> readyStartGame(ReadyStartGameParam param);
    
    /**
     * 开始游戏(两人或者多人对战)
     * @param userNum
     * @param param
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    Result<MindPkStartDTO> participateGame(String userNum, MindPkStartParam param);
    
    /**
     * 校验所有参与用户积分扣除状态
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月19日
     */
    Result<CheckPointsDeductionStatusDTO> checkPointsDeductionStatus(String userNum);
    
    /**
     * 校验用户的答案是否正确
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    Result<VerifyAnswerDTO> verifyAnswer(VerifyAnswerParam param);
    
    /**
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月20日
     * @updateDate 2018年3月20日
     */
    Result<GameRoomDetailsDTO> quit(String userNum);
}
