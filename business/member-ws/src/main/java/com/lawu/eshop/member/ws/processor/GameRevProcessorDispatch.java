package com.lawu.eshop.member.ws.processor;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONException;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.game.constants.ConnType;
import com.lawu.eshop.game.param.AbstractPKRejectParam;
import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.channel.GameNioSocketChannel;
import com.lawu.eshop.member.ws.param.RevMsg;
import com.lawu.eshop.member.ws.processor.impl.ParamClazzFactory;
import com.lawu.eshop.member.ws.util.JsonUtil;
import com.lawu.eshop.member.ws.util.ResultUtil;
import com.lawu.framework.web.Result;

/**
 * 消息处理分发
 *
 * @author Leach
 * @date 2018/3/13
 */
@Service
public class GameRevProcessorDispatch {

    private Logger logger = LoggerFactory.getLogger(GameRevProcessorDispatch.class);

    @Qualifier("MindPkRevProcessorImpl")
    @Autowired
    private GameRevProcessor mindPkRevProcessor;

    @Qualifier("PuzzleRevProcessorImpl")
    @Autowired
    private GameRevProcessor puzzleRevProcessor;

    @Autowired
    private SendProcessor sendProcessor;
    
    @Autowired
    private Validator validator;
    
    private GameRevProcessor getGameRevProcessor(ConnType connType) {
        switch (connType) {
            case MINDPK:
                return mindPkRevProcessor;
            case PUZZLE:
                return puzzleRevProcessor;
        }
        return null;
    }

    /**
     * 处理分发接收到的消息
     *
     * @param channel
     * @param revMsg
     */
    public void receive(GameNioSocketChannel channel, RevMsg revMsg) {
        GameRevProcessor gameRevProcessor = getGameRevProcessor(revMsg.getType());

        String userNum = channel.getUserNum();

        String paramJson = revMsg.getContent();

        Class paramClass = ParamClazzFactory.getParamClass(revMsg.getType(), revMsg.getMsgType());
        Object param = null;

        // 根据消息类型转换参数
        try {
            param = paramClass == null ? null : JsonUtil.read(paramJson, paramClass);
            // 校验参数
            if (param != null) {
                Set<ConstraintViolation<Object>> errors =  validator.validate(param);
                if (errors != null && !errors.isEmpty()) {
                    StringBuilder errorStr = new StringBuilder();
                    errors.forEach(item -> {
                        if (errorStr.length() > 0) {
                            errorStr.append("|");
                        }
                        errorStr.append(item.getMessage());
                    });
                    sendProcessor.sendToGroupByUser(ResultUtil.get(ResultCode.REQUIRED_PARM_EMPTY, errorStr.toString()), userNum);
                    return;
                }
            }
        } catch (JSONException e) {
            logger.warn("{}_{}_{}_{}-{}", channel.connTypeName(), channel.getGroupNum(), channel.id(), channel.getUserNum(), e.getMessage());
        }
        if (userNum == null) {
            logger.info("{}_{}_{}_{}-init", channel.connTypeName(), channel.getGroupNum(), channel.id(), userNum);
            channel.setConnType(revMsg.getType());
            userNum = UserUtil.getCurrentUserNumByToken(revMsg.getToken());
            channel.setUserNum(userNum);
            ChannelManager.addChannel(channel);
        }

        Result result = null;
        switch (revMsg.getMsgType()) {
            case HEART:
                sendProcessor.send(ResultUtil.get(ResultCode.GAME_WS_HEARTBEAT), channel);
                break;
            case ONLINE:
                logger.info("{}_{}_{}_{}-online", channel.connTypeName(), channel.getGroupNum(), channel.id(), userNum);
                result = gameRevProcessor.online(userNum, param);
                break;
            case REONLINE:
                if (gameRevProcessor.isValid(userNum)) {
                    channel.setConnType(revMsg.getType());
                    userNum = UserUtil.getCurrentUserNumByToken(revMsg.getToken());
                    channel.setUserNum(userNum);
                    ChannelManager.addChannel(channel);
                    sendProcessor.send(ResultUtil.getSuccess(), userNum);
                    break;
                }
                sendProcessor.send(ResultUtil.getFail("Illegal user"), userNum);
                break;

            case READY:
                if (!checkChannelInfo(channel)) {
                    break;
                }
                result = gameRevProcessor.ready(userNum, param);
                break;

            case START:
                if (!checkChannelInfo(channel)) {
                    break;
                }
                result = gameRevProcessor.start(userNum, param);
                break;

            case CHECK:
                if (!checkChannelInfo(channel)) {
                    break;
                }
                result = gameRevProcessor.check(userNum, param);

                if (result != null && ResultCode.GAME_WS_USER_OFFLINE == result.getRet()) {
                    GameNioSocketChannel rejectChannel = ChannelManager.getChannel(userNum);
                    rejectChannel.setOfflineSelf(true);
                }
                break;

            case SUBMIT:
                if (!checkChannelInfo(channel)) {
                    break;
                }
                result = gameRevProcessor.submit(userNum, param);
                break;

            case REJECT:
                if (!checkChannelInfo(channel)) {
                    break;
                }
                AbstractPKRejectParam rejectParam = (AbstractPKRejectParam) param;
                result = gameRevProcessor.reject(userNum, param);
                GameNioSocketChannel rejectChannel = ChannelManager.getChannel(rejectParam.getKickedUserNum());
                if (rejectChannel != null) {
                    rejectChannel.setOfflineSelf(true);
                }
                break;

            case ABANDON:
                if (!checkChannelInfo(channel)) {
                    break;
                }
                result = gameRevProcessor.abandon(userNum, param);
                GameNioSocketChannel abandonChannel = ChannelManager.getChannel(userNum);
                abandonChannel.setOfflineSelf(true);
                break;

            case TIMEOUT:
                if (!checkChannelInfo(channel)) {
                    break;
                }
                result = gameRevProcessor.timeout(userNum, param);
                break;
        }

        if (result != null) {
            sendProcessor.sendToGroupByUser(result, userNum);
        }
    }

    /**
     * 检查channel信息完整性
     * @param channel
     * @return
     */
    private boolean checkChannelInfo(GameNioSocketChannel channel) {

        if (channel.getUserNum() == null || ChannelManager.getGroupNum(channel.getUserNum()) == null) {
            sendProcessor.send(ResultUtil.getFail("Illegal operation"), channel);
            return false;
        }
        return true;
    }

    /**
     * 离线处理分发
     * @param connType
     * @param userNum
     */
    public void offline(ConnType connType, String userNum) {

        if (ChannelManager.getGroupNum(userNum) == null) {
            return;
        }
        GameRevProcessor gameRevProcessor = getGameRevProcessor(connType);
        Result result = gameRevProcessor.offline(userNum);
        if (result != null) {
            sendProcessor.sendToGroupByUser(result, userNum);
        }
    }
}
