package com.lawu.eshop.game.robot.processor;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.lawu.eshop.game.robot.NettyClient;
import com.lawu.eshop.game.robot.channel.ChannelManager;
import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;
import com.lawu.eshop.game.robot.constants.GameMsgType;
import com.lawu.eshop.game.robot.param.Message;
import com.lawu.eshop.game.robot.param.RevMsg;
import com.lawu.eshop.game.robot.util.JsonUtil;

import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
@Component
public class SendProcessor {

    private final static Logger logger = LoggerFactory.getLogger(SendProcessor.class);
    
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    
    /**
     * 推送消息给指定用户
     * @param revMsg
     * @param channel
     * @return
     */
    @SuppressWarnings("rawtypes")
    public ChannelFuture send(RevMsg revMsg, GameNioSocketChannel channel) {
        String msg = JsonUtil.write(revMsg);
        TextWebSocketFrame tws = new TextWebSocketFrame(msg);
        if (channel != null) {
            ChannelFuture channelFuture = channel.writeAndFlush(tws);
            if (GameMsgType.HEART != revMsg.getMsgType()) {
                logger.debug("{}-send msg: {}", channel.id(), msg);
            }
            return channelFuture;
        }
        return null;
    }
    
    /**
     * 推送消息给指定用户
     * @param message
     * @param channel
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private ChannelFuture send(Message message, GameNioSocketChannel channel) {
        RevMsg revMsg = new RevMsg();
        revMsg.setMsgType(message.getMsgType());
        revMsg.setToken(channel.getToken());
        revMsg.setType(channel.getConnType());
        revMsg.setContent(message.getContent());
        return send(revMsg, channel);
    }
    
    /**
     * 推送消息给指定用户
     * @param message
     * @param userNum
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public ChannelFuture send(Message message, String userNum) {
        NettyClient nettyClient = ChannelManager.getClient(userNum);
        // 判断NettyClient是否已经被移除
        if (nettyClient == null) {
            return null;
        }
        return send(message, (GameNioSocketChannel) nettyClient.getChannel());
    }

    /**
     * 异步推送消息给指定用户
     * @param message
     * @param userNum
     * @param seconds 秒
     */
    @SuppressWarnings({ "rawtypes" })
    public void sendAsync(Message message, String userNum, long seconds) {
        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.SECOND, (int) seconds);
        threadPoolTaskScheduler.schedule(() -> {
            send(message, userNum);
        }, (Date) startTime.getTime());
    }
}
