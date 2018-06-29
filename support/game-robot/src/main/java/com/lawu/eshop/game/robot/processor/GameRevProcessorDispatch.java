package com.lawu.eshop.game.robot.processor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lawu.eshop.game.robot.GameRobotConfig;
import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;
import com.lawu.eshop.game.robot.constants.ConnType;
import com.lawu.eshop.game.robot.constants.GameMsgType;
import com.lawu.eshop.game.robot.constants.ResultCode;
import com.lawu.eshop.game.robot.dto.Result;
import com.lawu.eshop.game.robot.param.Message;
import com.lawu.eshop.game.robot.util.HttpUtil;

/**
 * 消息处理分发
 *
 * @author Leach
 * @date 2018/3/13
 */
@Component
public class GameRevProcessorDispatch {

    private Logger logger = LoggerFactory.getLogger(GameRevProcessorDispatch.class);

    @Qualifier("mindPKGameRevProcessorImpl")
    @Autowired
    private GameRevProcessor mindPkRevProcessor;

    @Qualifier("puzzleGameRevProcessorImpl")
    @Autowired
    private GameRevProcessor puzzleRevProcessor;
    
    @Autowired
    private GameRobotConfig gameRobotConfig;
    
    @Autowired
    private SendProcessor sendProcessor;
    
    private GameRevProcessor getGameRevProcessor(ConnType connType) {
        switch (connType) {
            case MINDPK:
                return mindPkRevProcessor;
            case PUZZLE:
                return puzzleRevProcessor;
        }
        return null;
    }
    
    @SuppressWarnings("rawtypes")
    public void heart(String userNum) {
        Message message = new Message();
        message.setMsgType(GameMsgType.HEART);
        sendProcessor.send(message, userNum);
    }
    
    public void online(ConnType connType, String userNum) {
        GameRevProcessor gameRevProcessor = getGameRevProcessor(connType);
        gameRevProcessor.online(userNum);
    }
    
    /**
     * 处理分发接收到的消息
     *
     * @param channel
     * @param result
     */
    @SuppressWarnings("rawtypes")
    public void receive(GameNioSocketChannel channel, Result result) {
        GameRevProcessor gameRevProcessor = getGameRevProcessor(channel.getConnType());
        String userNum = channel.getUserNum();
        Object model = result.getModel();
        if (result.getRet() == ResultCode.GAME_WS_HEARTBEAT) {
            return;
        }
        // 刷新上次消息接收时间
        channel.setLastReceivedTime(new Date());
        if (result.getRet() == ResultCode.GAME_WS_USER_ONLINE) {
            return;
        }
        
        if (result.getRet() == ResultCode.GAME_WS_USER_READY) {
            return;
        }
        
        if (result.getRet() == ResultCode.GAME_WS_USER_START) {
            gameRevProcessor.start(userNum, model);
            return;
        }
        
        if (result.getRet() == ResultCode.GAME_WS_USER_CHECK_DONE 
                || result.getRet() == ResultCode.GAME_WS_USER_SUBMIT_MULTI) {
            gameRevProcessor.submit(userNum, model);
            return;
        }

        if (result.getRet() == ResultCode.GAME_WS_USER_OFFLINE) {
            channel.close();
            return;
        }
    }
    
    public void exit(GameNioSocketChannel channel) {
        Map<String, String> header = new HashMap<>();
        header.put("authorization", channel.getToken());
        HttpUtil.doPut(gameRobotConfig.getMemberApiAddress() + "randomMatch/releaseRobotResources", null, header);
    }
}
