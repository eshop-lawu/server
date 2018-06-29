package com.lawu.eshop.game.robot.channel;

import java.nio.channels.SocketChannel;
import java.util.Date;

import com.lawu.eshop.game.robot.constants.ConnType;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 自定义channel
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public class GameNioSocketChannel extends NioSocketChannel {
    
    /**
     * 上一次接收消息的时间
     */
    private Date lastReceivedTime;
    
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
     * 用户token
     */
    private String token;

    /**
     * 缓存数据题目答案或者拼图答案
     */
    private String content;

    /**
     * 答题序列
     */
    private int answerIndex;
    
    /**
     * 拼图配置缓存数据
     */
    private String cacheInfo;
    
    public GameNioSocketChannel() {
        super();
    }
    
    public GameNioSocketChannel(SocketChannel socket) {
        super(socket);
    }
    
    public GameNioSocketChannel(Channel parent, SocketChannel socket) {
        super(parent, socket);
    }

    public Date getLastReceivedTime() {
        return lastReceivedTime;
    }

    public void setLastReceivedTime(Date lastReceivedTime) {
        this.lastReceivedTime = lastReceivedTime;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

	public String getCacheInfo() {
		return cacheInfo;
	}

	public void setCacheInfo(String cacheInfo) {
		this.cacheInfo = cacheInfo;
	}
    
}
