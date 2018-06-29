package com.lawu.eshop.game.robot.processor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.game.robot.GameRobotConfig;
import com.lawu.eshop.game.robot.channel.ChannelManager;
import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;
import com.lawu.eshop.game.robot.constants.AttendTypeEnum;
import com.lawu.eshop.game.robot.constants.GameMsgType;
import com.lawu.eshop.game.robot.dto.GameMindRobotAnswerDTO;
import com.lawu.eshop.game.robot.dto.Result;
import com.lawu.eshop.game.robot.param.Message;
import com.lawu.eshop.game.robot.param.MindPkOnlineParam;
import com.lawu.eshop.game.robot.param.VerifyAnswerParam;
import com.lawu.eshop.game.robot.processor.GameRevProcessor;
import com.lawu.eshop.game.robot.processor.SendProcessor;
import com.lawu.eshop.game.robot.util.HttpUtil;
import com.lawu.eshop.game.robot.util.JsonUtil;

@Service("mindPKGameRevProcessorImpl")
public class MindPKGameRevProcessorImpl implements GameRevProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MindPKGameRevProcessorImpl.class);

    @Autowired
    private SendProcessor sendProcessor;

    @Autowired
    private GameRobotConfig gameRobotConfig;
    
    @Override
    public void online(String userNum) {
        MindPkOnlineParam content = new MindPkOnlineParam();
        content.setAttendType(AttendTypeEnum.RANDOM);
        content.setIsHomeowner(false);
        Message<MindPkOnlineParam> message = new Message<>();
        message.setMsgType(GameMsgType.ONLINE);
        message.setContent(content);
        sendProcessor.send(message, userNum);
    }
    
    @Override
    public void start(String userNum, Object model) {
        //拿题目 ，生成答题时间和答案
        GameNioSocketChannel channel = (GameNioSocketChannel) ChannelManager.getClient(userNum).getChannel();
        String roomNum = channel.getGroupNum();
        Map<String, String> params = new HashMap<>();
       // params.put("userNum", userNum);
        params.put("roomNum", roomNum);
        String resultStr = HttpUtil.doGet(gameRobotConfig.getMemberApiAddress() + "gameRobotMind/startGame", params);
        Result<String> result = JsonUtil.read(resultStr, Result.class);
        if (result.getRet() != 1000) {
            logger.debug("游戏账号登录失败,{}", result.getMsg());
        }else{
            channel.setAnswerIndex(0);
            channel.setContent(result.getModel());
        }

    }

    @Override
    public void submit(String userNum, Object model) {
        GameNioSocketChannel channel = (GameNioSocketChannel) ChannelManager.getClient(userNum).getChannel();
        int index = channel.getAnswerIndex();
        List<GameMindRobotAnswerDTO> answerDTOS = JSONObject.parseArray(channel.getContent(), GameMindRobotAnswerDTO.class);
        VerifyAnswerParam param = new VerifyAnswerParam();
        param.setQuestionId(answerDTOS.get(index).getQuestionId());
        param.setRightAnswer(answerDTOS.get(index).getRightAnswer());

        Message<VerifyAnswerParam> message = new Message<>();
        message.setContent(param);
        message.setMsgType(GameMsgType.SUBMIT);
        sendProcessor.sendAsync(message, userNum, answerDTOS.get(index).getUseTime());

        channel.setAnswerIndex(index + 1);

    }

}
