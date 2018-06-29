package com.lawu.eshop.member.ws.processor;

import com.lawu.framework.web.Result;

/**
 * @author Leach
 * @date 2018/3/13
 */
public interface GameRevProcessor {

    /**
     * 用户上线
     * @param userNum
     * @param param
     */
    Result online(String userNum, Object param);

    /**
     * 准备游戏
     * @param userNum
     * @param param
     */
    Result ready(String userNum, Object param);

    /**
     * 开始游戏
     * @param userNum
     * @param param
     */
    Result start(String userNum, Object param);


    /**
     * 检查积分是否扣除成功
     * @param userNum
     * @param param
     */
    Result check(String userNum, Object param);

    /**
     * 提交成绩
     * @param userNum
     * @param param
     */
    Result submit(String userNum, Object param);

    /**
     * 踢人
     * @param userNum
     * @param param
     */
    Result reject(String userNum, Object param);

    /**
     * 放弃
     * @param userNum
     * @param param
     */
    Result abandon(String userNum, Object param);

    /**
     * 超时
     * @param userNum
     * @param param
     */
    Result timeout(String userNum, Object param);

    /**
     * 离线
     * @param userNum
     */
    Result offline(String userNum);

    /**
     * 判断用户是否有权继续游戏
     * @param userNum
     * @return
     */
    boolean isValid(String userNum);
}
