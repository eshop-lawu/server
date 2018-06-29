package com.lawu.eshop.game.robot;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;
import com.lawu.eshop.game.robot.handle.WebSocketClientChannelInitializer;
import com.lawu.eshop.game.robot.processor.GameRevProcessorDispatch;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * Netty-Webscoket客户端
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
public class NettyClient {

    private static Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private Channel channel;

    private EventLoopGroup workerGroup;
    
    /**
     *  内核为此套接口排队的最大连接个数，对于给定的监听套接口，内核要维护两个队列，未链接队列和已连接队列大小总和最大值
     */
    private int ioThreadNum;

    /**
     * websocket地址
     */
    private String address;
    
    private GameRevProcessorDispatch gameRevProcessorDispatch;
    
    private ChannelFutureListener listener;
    
    public NettyClient(String address, int ioThreadNum, GameRevProcessorDispatch gameRevProcessorDispatch) {
        this.ioThreadNum = ioThreadNum;
        this.address = address;
        this.gameRevProcessorDispatch = gameRevProcessorDispatch;
    }
    
    public int getIoThreadNum() {
        return ioThreadNum;
    }

    public void setIoThreadNum(int ioThreadNum) {
        this.ioThreadNum = ioThreadNum;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Channel getChannel() {
        return channel;
    }
    
    public void addListener(ChannelFutureListener listener) {
        this.listener = listener;
    }
    
    @SuppressWarnings("unchecked")
    public void open() throws Exception {
        logger.info("begin to start netty client");
        workerGroup = new NioEventLoopGroup(getIoThreadNum());
        Bootstrap bootstrap = new Bootstrap();
        URI webSocketURI = URI.create(getAddress());
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(
                webSocketURI, WebSocketVersion.V13, null, false, new DefaultHttpHeaders());
        SslContext sslCtx = null;
        if ("wss".equals(webSocketURI.getScheme())) {
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        }
        bootstrap.group(workerGroup)
                .channel(GameNioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new WebSocketClientChannelInitializer(handshaker, gameRevProcessorDispatch, sslCtx));
        bootstrap.connect(webSocketURI.getHost(), webSocketURI.getPort()).addListeners(listener, (new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                channel = future.channel();
                handshaker.handshake(channel);
            }
        }));
        
        logger.info("netty client connect on address {} and ready for connections...", getAddress());
    }

    public void close() {
        logger.info("destroy netty client resources");
        if (null == channel) {
            logger.error("client netty client is null");
        }
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
        workerGroup = null;
        channel = null;
    }

}
