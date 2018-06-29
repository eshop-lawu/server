package com.lawu.eshop.member.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leach
 * @date 2018/3/15
 */
@Component
public class MemberWsConfig {

    // 内核为此套接口排队的最大连接个数，对于给定的监听套接口，内核要维护两个队列，未链接队列和已连接队列大小总和最大值
    @Value("${lawu.eshop.member.ws.netty.ioThreadNum:5}")
    private int ioThreadNum;

    @Value("${lawu.eshop.member.ws.netty.backlog:1024}")
    private int backlog;

    @Value("${lawu.eshop.member.ws.netty.port}")
    private int port;

    @Value("${lawu.eshop.member.ws.netty.readerIdleTime}")
    private int readerIdleTime;

    @Value("${lawu.eshop.member.ws.netty.writerIdleTime}")
    private int writerIdleTime;

    @Value("${lawu.eshop.member.ws.netty.allIdleTime}")
    private int allIdleTime;

    public int getIoThreadNum() {
        return ioThreadNum;
    }

    public void setIoThreadNum(int ioThreadNum) {
        this.ioThreadNum = ioThreadNum;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getReaderIdleTime() {
        return readerIdleTime;
    }

    public void setReaderIdleTime(int readerIdleTime) {
        this.readerIdleTime = readerIdleTime;
    }

    public int getWriterIdleTime() {
        return writerIdleTime;
    }

    public void setWriterIdleTime(int writerIdleTime) {
        this.writerIdleTime = writerIdleTime;
    }

    public int getAllIdleTime() {
        return allIdleTime;
    }

    public void setAllIdleTime(int allIdleTime) {
        this.allIdleTime = allIdleTime;
    }
}
