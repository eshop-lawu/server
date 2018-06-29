package com.lawu.eshop.game.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class NettyServer {
    
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    
    private EventLoopGroup bossGroup = new NioEventLoopGroup(0);
    
    private EventLoopGroup workerGroup = new NioEventLoopGroup(5);
    
    private Channel ch;

    public void start() {
        final ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 注意是childOption
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new WebSocketServerInitializer());
        try {
            ch = b.bind("localhost", 8180).sync().channel();
        } catch (InterruptedException e) {
        }
    }

    public void stop() {
        ch.closeFuture().syncUninterruptibly();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        bossGroup = null;
        workerGroup = null;
        ch = null;
    }
    
    private class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        public void initChannel(final SocketChannel ch) throws Exception {
            final ChannelPipeline pipeline = ch.pipeline();
            //pipeline.addLast("http-request-decoder", new HttpRequestDecoder());
            pipeline.addLast("http-codec", new HttpServerCodec());
            pipeline.addLast("aggregator", new HttpObjectAggregator(64 * 1024));
            //pipeline.addLast("http-response-encoder", new HttpResponseEncoder());
            //pipeline.addLast("http-chunked", new ChunkedWriteHandler());
            // 超时检查
            //pipeline.addLast("ping", new IdleStateHandler(5, 5, 5, TimeUnit.SECONDS));
            //pipeline.addLast("request-handler", new WebSocketServerProtocolHandler("/websocket"));
            pipeline.addLast("handler", new SomeHandler());
        }
    }

    public class SomeHandler extends SimpleChannelInboundHandler<Object> {
        
        private WebSocketServerHandshaker handshaker;
        
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            logger.info("{}-active", ctx.channel().id());

        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
            // remove from group
            logger.info("{}-inactive", ctx.channel().id());
        }
        
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object frame) throws Exception {
            Channel channel = ctx.channel();
            if (frame instanceof FullHttpRequest) {
                FullHttpRequest req = (FullHttpRequest) frame;
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8180", null, false);
                handshaker = wsFactory.newHandshaker(req);
                if (handshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                } else {
                    handshaker.handshake(ctx.channel(), req);
                }
                return;
            }
            
//            // the frame of close the webSocket
//            if (frame instanceof CloseWebSocketFrame) {
//                logger.info(frame.toString());
//                handshaker.close(channel, ((CloseWebSocketFrame) frame).retain());
//                return;
//            }
//            // the frame of ping
//            if (frame instanceof PingWebSocketFrame) {
//                channel.write(new PongWebSocketFrame(((PingWebSocketFrame)frame).content().retain()));
//                return;
//            }
            
            if (!(frame instanceof TextWebSocketFrame)) {
                return;
            }
            
            String result = ((TextWebSocketFrame) frame).text();
            // uncomment to print request
            logger.info("Request received: {}", result);
        }
        
        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            super.userEventTriggered(ctx, evt);
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent event = (IdleStateEvent) evt;
                logger.info("{}}-eventState: {}", ctx.channel().id(), event.state());
                if (event.state().equals(IdleState.READER_IDLE)) {
                    // 未进行读操作
                    closeChannel(ctx);
                } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                    // 未进行写操作
                    closeChannel(ctx);

                } else if (event.state().equals(IdleState.ALL_IDLE)) {
                    // 未进行读写
                    // 只需处理读超时、写超时即可，此处读写超时不处理
                }

            }
        }

        /**
         * 关闭channel，退出
         *
         * @param ctx
         */
        private void closeChannel(ChannelHandlerContext ctx) {
            // 关闭channel
            ctx.close();
        }
    }
}
