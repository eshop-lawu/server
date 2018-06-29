package com.lawu.eshop.game.robot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.game.robot.GameRobotConfig;
import com.lawu.eshop.game.robot.NettyClient;
import com.lawu.eshop.game.robot.channel.ChannelManager;
import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;
import com.lawu.eshop.game.robot.dto.Result;
import com.lawu.eshop.game.robot.dto.TokenDTO;
import com.lawu.eshop.game.robot.param.JoinGameParam;
import com.lawu.eshop.game.robot.processor.GameRevProcessorDispatch;
import com.lawu.eshop.game.robot.util.HttpUtil;
import com.lawu.eshop.game.robot.util.JsonUtil;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

@RestController
@RequestMapping(value = "gameRobot/")
public class GameRobotController {
    
    private static final Logger logger = LoggerFactory.getLogger(GameRobotController.class);

    
    @Autowired
    private GameRobotConfig gameRobotConfig;
    
    @Autowired
    private GameRevProcessorDispatch gameRevProcessorDispatch;
    
    @SuppressWarnings({ "rawtypes" })
    @RequestMapping(value = "joinGame", method = RequestMethod.POST)
    public void joinGame(@ModelAttribute JoinGameParam joinGameParam) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("pwd", joinGameParam.getPassword());
        String resultStr = HttpUtil.doPost(gameRobotConfig.getMemberApiAddress() + "login/" + joinGameParam.getAccount(), params);
        Result loginResult = JsonUtil.read(resultStr, Result.class);
        if (loginResult.getRet() != 1000) {
            logger.debug("游戏账号登录失败,{}", loginResult.getMsg());
        }
        TokenDTO tokenDTO = JsonUtil.read(loginResult.getModel().toString(), TokenDTO.class);
        NettyClient nettyClient = new NettyClient(gameRobotConfig.getMemberWsAddress() + joinGameParam.getGrouoNum(), gameRobotConfig.getIoThreadNum(), gameRevProcessorDispatch);
        nettyClient.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                GameNioSocketChannel gameNioSocketChannel = (GameNioSocketChannel) future.channel();
                gameNioSocketChannel.setConnType(joinGameParam.getConnType());
                gameNioSocketChannel.setGroupNum(joinGameParam.getGrouoNum());
                gameNioSocketChannel.setUserNum(tokenDTO.getUserNum());
                gameNioSocketChannel.setToken(tokenDTO.getToken());
                gameNioSocketChannel.setLastReceivedTime(new Date());
                ChannelManager.addClient(tokenDTO.getUserNum(), nettyClient);
            }
        });
        nettyClient.open();
    }
    
}
