package com.lawu.eshop.member.ws.handle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.channel.GameNioSocketChannel;
import com.lawu.eshop.member.ws.param.GameMsgType;
import com.lawu.eshop.member.ws.param.RevMsg;
import com.lawu.eshop.member.ws.processor.GameRevProcessorDispatch;
import com.lawu.eshop.member.ws.processor.SendProcessor;
import com.lawu.eshop.member.ws.util.JsonUtil;
import com.lawu.eshop.member.ws.util.ResultUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * @author Leach
 * @date 2018/3/9
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private Logger logger = LoggerFactory.getLogger(WebSocketServerHandler.class);

    private WebSocketServerHandshaker handshaker;

    private GameRevProcessorDispatch gameRevProcessorDispatch;

    private SendProcessor sendProcessor;

    public WebSocketServerHandler(GameRevProcessorDispatch gameRevProcessorDispatch, SendProcessor sendProcessor) {
        this.gameRevProcessorDispatch = gameRevProcessorDispatch;
        this.sendProcessor = sendProcessor;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info("{}-active", ctx.channel().id());

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        // remove from group
        GameNioSocketChannel gameChannel = (GameNioSocketChannel) ctx.channel();
        logger.info("{}_{}_{}_{}-inactive", gameChannel.connTypeName(), gameChannel.getGroupNum(), ctx.channel().id(), gameChannel.getUserNum());
        ChannelManager.addOfflineChannel(gameChannel);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, ((FullHttpRequest) msg));
        } else if (msg instanceof WebSocketFrame) {
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        GameNioSocketChannel gameChannel = (GameNioSocketChannel) ctx.channel();
        String uri = req.uri();
        int separator = uri.lastIndexOf('/');
        if (separator != 0) {
            logger.warn("{}-Illegality uri path: {}", gameChannel.id(), uri);
            closeChannel(ctx);
            return;
        }
        String groupNum = uri.substring(separator + 1);
        if (StringUtils.isBlank(groupNum)) {
            logger.warn("{}-Uri path is null", gameChannel.id());
            closeChannel(ctx);
            return;
        }
        gameChannel.setGroupNum(groupNum);

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:7397/websocket", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // response to client
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // if not Keep-Alive，close the connetcion
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }


    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        Channel channel = ctx.channel();

        // the frame of close the webSocket
        if (frame instanceof CloseWebSocketFrame) {
            logger.info(frame.toString());
            handshaker.close(channel, (CloseWebSocketFrame) frame.retain());
            return;
        }
        // the frame of ping
        if (frame instanceof PingWebSocketFrame) {
            channel.write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        GameNioSocketChannel gameChannel = (GameNioSocketChannel) ctx.channel();

        // only text message, binary message is not supported
        if (!(frame instanceof TextWebSocketFrame)) {
            logger.warn("{}_{}_{}_{}-Binary message is not supported", gameChannel.connTypeName(), gameChannel.getGroupNum(), ctx.channel().id(), gameChannel.getUserNum());
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }


        String result = ((TextWebSocketFrame) frame).text();

        // 将接收到的json消息进行转换
        try {
            RevMsg revMsg = JsonUtil.read(result, RevMsg.class);
            if (GameMsgType.HEART != revMsg.getMsgType()) {
                logger.debug("{}_{}_{}_{}-rev msg {}", gameChannel.connTypeName(), gameChannel.getGroupNum(), ctx.channel().id(), gameChannel.getUserNum(), result);
            }
            gameRevProcessorDispatch.receive(gameChannel, revMsg);
            gameChannel.minusExceptinoTime();
        } catch (Exception e) {
            gameChannel.plusExceptinoTime();
            logger.error("{}_{}_{}_{}-{}", gameChannel.connTypeName(), gameChannel.getGroupNum(), ctx.channel().id(), gameChannel.getUserNum(), e.getMessage());
            logger.error("Fail to deal msg ", e);

            if (gameChannel.getExceptionTime() <= 3) {
                sendProcessor.send(ResultUtil.getFail(e.getMessage()), gameChannel);
            } else {
                // 超过3次异常，断开链接
                closeChannel(ctx);
            }
        }

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {

            IdleStateEvent event = (IdleStateEvent) evt;

            GameNioSocketChannel channel = (GameNioSocketChannel) ctx.channel();

            logger.info("{}_{}_{}_{}-eventState: {}", channel.connTypeName(), channel.getGroupNum(), ctx.channel().id(), channel.getUserNum(), event.state());
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
