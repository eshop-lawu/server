package com.lawu.eshop.game.robot.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawu.eshop.game.robot.channel.ChannelManager;
import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;
import com.lawu.eshop.game.robot.constants.ResultCode;
import com.lawu.eshop.game.robot.dto.Result;
import com.lawu.eshop.game.robot.processor.GameRevProcessorDispatch;
import com.lawu.eshop.game.robot.util.JsonUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

    private Logger logger = LoggerFactory.getLogger(WebSocketClientHandler.class);

    private final WebSocketClientHandshaker handshaker;
    
    private GameRevProcessorDispatch gameRevProcessorDispatch;
    
    public WebSocketClientHandler(WebSocketClientHandshaker handshaker, GameRevProcessorDispatch gameRevProcessorDispatch) {
        this.handshaker = handshaker;
        this.gameRevProcessorDispatch = gameRevProcessorDispatch;
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("{}-active", ctx.channel().id());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        GameNioSocketChannel gameChannel = (GameNioSocketChannel) ctx.channel();
        gameRevProcessorDispatch.exit(gameChannel);
        ChannelManager.removeChannel(gameChannel.getUserNum());
        logger.info("{}_{}_{}_{}-inactive", gameChannel.getConnType(), gameChannel.getGroupNum(), gameChannel.id(), gameChannel.getUserNum());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        if (!handshaker.isHandshakeComplete()) {
            // web socket client connected
            if (msg instanceof HttpResponse) {
                ChannelFuture channelFuture = handshaker.processHandshake(channel, ( HttpResponse ) msg);
                GameNioSocketChannel gameChannel = (GameNioSocketChannel) ctx.channel();
                channelFuture.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        logger.debug("{}_{}_{}_{}-processHandshake", gameChannel.getConnType(), gameChannel.getGroupNum(), gameChannel.id(), gameChannel.getUserNum());
                        // 上线
                        gameRevProcessorDispatch.online(gameChannel.getConnType(), gameChannel.getUserNum());
                        logger.debug("{}_{}_{}_{}-online", gameChannel.getConnType(), gameChannel.getGroupNum(), gameChannel.id(), gameChannel.getUserNum());
                    }
                });
            }
            return;
        }
        if (msg instanceof WebSocketFrame) {
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    @SuppressWarnings("rawtypes")
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // the frame of close the webSocket
        if (frame instanceof CloseWebSocketFrame) {
            closeChannel(ctx);
            return;
        }
        // the frame of pong
        if (frame instanceof PongWebSocketFrame) {
            return;
        }
        
        // only text message, binary message is not supported
        if (!(frame instanceof TextWebSocketFrame)) {
            logger.warn("{}-Binary message is not supported", ctx.channel().id());
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }

        GameNioSocketChannel gameChannel = (GameNioSocketChannel) ctx.channel();
        
        String result = ((TextWebSocketFrame) frame).text();

        // 将接收到的json消息进行转换
        try {
            Result resultObj = JsonUtil.read(result, Result.class);
            if (ResultCode.GAME_WS_HEARTBEAT != resultObj.getRet()) {
                logger.debug("{}-result msg {}", ctx.channel().id(), result);
            }
            gameRevProcessorDispatch.receive(gameChannel, resultObj);
        } catch (Exception e) {
            logger.error("{}-{}", ctx.channel().id(), e.getMessage());
            logger.error("Fail to deal msg ", e);
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
