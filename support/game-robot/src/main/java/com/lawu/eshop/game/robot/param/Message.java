package com.lawu.eshop.game.robot.param;

import com.lawu.eshop.game.robot.constants.GameMsgType;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
public class Message<T> {

    /**
     * 游戏消息类型
     */
    private GameMsgType msgType;

    /**
     * 主题内容
     */
    private T content;

    public GameMsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(GameMsgType msgType) {
        this.msgType = msgType;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
    
}
