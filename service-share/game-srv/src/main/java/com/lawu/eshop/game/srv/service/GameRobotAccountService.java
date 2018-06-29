package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.game.srv.bo.GameUserInfoBO;

/**
 * 机器人表
 * 
 * @author lihj
 * @date 2018年5月14日
 */
public interface GameRobotAccountService {

    /**
     * 判断用户是否是机器人
     * 
     * @param num
     * @return
     */
    boolean checkMemberIsRobot(String num);
    
    /**
     * 
     * @param userNums
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月22日
     * @updateDate 2018年5月22日
     */
    List<GameUserInfoBO> findRobotUserInfo(List<String> userNums);
}
