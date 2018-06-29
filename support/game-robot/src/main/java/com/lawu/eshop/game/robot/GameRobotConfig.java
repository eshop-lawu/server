package com.lawu.eshop.game.robot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
@Configuration
@ConfigurationProperties(prefix = "lawu.eshop.game.robot")
public class GameRobotConfig {

    /**
     *  内核为此套接口排队的最大连接个数，对于给定的监听套接口，内核要维护两个队列，未链接队列和已连接队列大小总和最大值
     */
    private int ioThreadNum = 2;

    /**
     * 会员端websocket地址
     */
    private String memberWsAddress;

    /**
     * 会员端api地址
     */
    private String memberApiAddress;
    
    public int getIoThreadNum() {
        return ioThreadNum;
    }

    public void setIoThreadNum(int ioThreadNum) {
        this.ioThreadNum = ioThreadNum;
    }

    public String getMemberWsAddress() {
        return memberWsAddress;
    }

    public void setMemberWsAddress(String memberWsAddress) {
        this.memberWsAddress = memberWsAddress;
    }

    public String getMemberApiAddress() {
        return memberApiAddress;
    }

    public void setMemberApiAddress(String memberApiAddress) {
        this.memberApiAddress = memberApiAddress;
    }
    
}
