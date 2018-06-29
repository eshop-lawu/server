package com.lawu.eshop.game.robot.param;

import com.lawu.eshop.game.robot.constants.ConnType;
import com.lawu.eshop.game.robot.constants.GameMsgType;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
public class RevMsg<T> {

    /**
     * 连接类型
     */
    private ConnType type;

    /**
     * 游戏消息类型
     */
    private GameMsgType msgType;

    /**
     * 登录token
     */
    private String token;

    /**
     * 主题内容
     */
    private T content;

    public ConnType getType() {
        return type;
    }

    public void setType(ConnType type) {
        this.type = type;
    }

    public GameMsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(GameMsgType msgType) {
        this.msgType = msgType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
    
}
