package com.lawu.eshop.game.robot.constants;

/**
 * 游戏消息类型
 * @author Leach
 * @date 2018/3/13
 */
public enum GameMsgType {
    HEART, // 心跳
    ONLINE, // 上线
    REONLINE, // 重连
    READY, // 准备游戏
    START, // 开始游戏
    CHECK, // 检查积分是否扣除成功
    SUBMIT, // 提交成绩
    REJECT, // 踢人
    ABANDON, // 放弃游戏
    TIMEOUT // 游戏超时

}
