package com.lawu.eshop.game.robot.handle;

import com.lawu.eshop.game.robot.processor.GameRevProcessorDispatch;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Leach
 * @date 2018/3/9
 */
public class WebSocketClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private WebSocketClientHandshaker handshaker;
    
    private GameRevProcessorDispatch gameRevProcessorDispatch;
    
    private SslContext sslCtx;

    public WebSocketClientChannelInitializer(WebSocketClientHandshaker handshaker, 
            GameRevProcessorDispatch gameRevProcessorDispatch, 
            SslContext sslCtx) {
        this.gameRevProcessorDispatch = gameRevProcessorDispatch;
        this.handshaker = handshaker;
        this.sslCtx = sslCtx;
    }

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast("aggregator", new HttpObjectAggregator(64 * 1024));
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
        if (sslCtx != null) {
            pipeline.addLast("ssl", sslCtx.newHandler(sc.alloc()));
        }
        pipeline.addLast("http-codec", new HttpClientCodec());
        pipeline.addLast("ws-handler", new WebSocketClientHandler(handshaker, gameRevProcessorDispatch));
    }
}