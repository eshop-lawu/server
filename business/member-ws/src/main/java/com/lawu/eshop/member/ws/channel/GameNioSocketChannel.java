package com.lawu.eshop.member.ws.channel;

import java.nio.channels.SocketChannel;

import com.lawu.eshop.game.constants.ConnType;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 自定义channel
 * @author Leach
 * @date 2018/3/9
 */
public class GameNioSocketChannel extends NioSocketChannel {

    /**
     * 链接类型
     */
    private ConnType connType;

    /**
     * 当前用户编号
     */
    private String userNum;

    /**
     * 用户所属组编号，房间号/参与编号
     */
    private String groupNum;

    /**
     * 异常通信次数
     */
    private int exceptionTime;

    /**
     * 自主离线
     */
    private boolean offlineSelf;

    public void plusExceptinoTime() {
        this.exceptionTime++;
    }

    public void minusExceptinoTime() {
        if (this.exceptionTime > 0) {
            this.exceptionTime--;
        }
    }

    public GameNioSocketChannel(Channel parent, SocketChannel socket) {
        super(parent, socket);
    }

    public ConnType getConnType() {
        return connType;
    }

    public void setConnType(ConnType connType) {
        this.connType = connType;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String connTypeName() {
        return this.connType == null ? "" : this.connType.name();
    }

    public int getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(int exceptionTime) {
        this.exceptionTime = exceptionTime;
    }

    public boolean isOfflineSelf() {
        return offlineSelf;
    }

    public void setOfflineSelf(boolean offlineSelf) {
        this.offlineSelf = offlineSelf;
    }
}
