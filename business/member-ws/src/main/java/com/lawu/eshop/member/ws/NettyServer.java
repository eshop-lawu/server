package com.lawu.eshop.member.ws;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lawu.eshop.member.ws.handle.GameChildChannelHandler;
import com.lawu.eshop.member.ws.channel.MemberNioServerSocketChannel;
import com.lawu.eshop.member.ws.processor.GameRevProcessorDispatch;
import com.lawu.eshop.member.ws.processor.SendProcessor;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author Leach
 * @date 2018/3/13
 */
@Component
public class NettyServer {

    private static Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private Channel channel;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    @Autowired
    private MemberWsConfig memberWsConfig;

    @Autowired
    private GameRevProcessorDispatch gameRevProcessorDispatch;

    @Autowired
    private SendProcessor sendProcessor;

    @PostConstruct
    public void start() throws InterruptedException {
        logger.info("begin to start netty server");
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(memberWsConfig.getIoThreadNum());

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(MemberNioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, memberWsConfig.getBacklog())
                // 注意是childOption
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new GameChildChannelHandler(gameRevProcessorDispatch, sendProcessor, memberWsConfig));

        channel = serverBootstrap.bind(memberWsConfig.getPort()).sync().channel();

        logger.info("netty server listening on port {} and ready for connections...", memberWsConfig.getPort());
    }

    @PreDestroy
    public void stop() {
        logger.info("destroy netty server resources");
        if (null == channel) {
            logger.error("server netty channel is null");
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
        bossGroup = null;
        workerGroup = null;
        channel = null;
    }

}
