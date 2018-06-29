package com.lawu.eshop.member.ws.handle;

import java.util.concurrent.TimeUnit;

import com.lawu.eshop.member.ws.MemberWsConfig;
import com.lawu.eshop.member.ws.processor.GameRevProcessorDispatch;
import com.lawu.eshop.member.ws.processor.SendProcessor;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author Leach
 * @date 2018/3/9
 */
public class GameChildChannelHandler extends ChannelInitializer<SocketChannel> {

    private GameRevProcessorDispatch gameRevProcessorDispatch;

    private SendProcessor sendProcessor;

    private MemberWsConfig memberWsConfig;

    public GameChildChannelHandler(GameRevProcessorDispatch gameRevProcessorDispatch, SendProcessor sendProcessor, MemberWsConfig memberWsConfig) {
        this.gameRevProcessorDispatch = gameRevProcessorDispatch;
        this.sendProcessor = sendProcessor;
        this.memberWsConfig = memberWsConfig;
    }

    @Override
    protected void initChannel(SocketChannel e) throws Exception {

        e.pipeline().addLast("http-codec", new HttpServerCodec());
        e.pipeline().addLast("aggregator", new HttpObjectAggregator(64 * 1024));
        e.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        // 超时检查
        e.pipeline().addLast("ping", new IdleStateHandler(memberWsConfig.getReaderIdleTime(), memberWsConfig.getWriterIdleTime(), memberWsConfig.getAllIdleTime(), TimeUnit.SECONDS));
        e.pipeline().addLast("handler", new WebSocketServerHandler(gameRevProcessorDispatch, sendProcessor));
    }
}