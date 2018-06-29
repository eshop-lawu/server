package com.lawu.eshop.game.robot;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lawu.eshop.game.robot.channel.ChannelManager;
import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;
import com.lawu.eshop.game.robot.constants.AttendTypeEnum;
import com.lawu.eshop.game.robot.constants.ConnType;
import com.lawu.eshop.game.robot.constants.GameMsgType;
import com.lawu.eshop.game.robot.param.Message;
import com.lawu.eshop.game.robot.param.MindPkOnlineParam;
import com.lawu.eshop.game.robot.processor.GameRevProcessorDispatch;
import com.lawu.eshop.game.robot.processor.SendProcessor;
import com.lawu.eshop.game.robot.util.HttpUtil;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyClientTest {
    
    @Autowired
    private GameRobotConfig gameRobotConfig;
    
    private static NettyServer nettyServer = null;
    
    @Autowired
    private GameRevProcessorDispatch gameRevProcessorDispatch;
    
    @Autowired
    private SendProcessor sendProcessor;
    
    @BeforeClass
    public static void start() {
        nettyServer = new NettyServer();
        nettyServer.start();
    }
    
    @AfterClass
    public static void stop() {
        if (nettyServer != null) {
            nettyServer.stop();
        }
    }
    
    @Test
    public void connent() throws Exception {
        for (int i = 1; i <= 100; i++) {
            NettyClient nettyClient = new NettyClient("ws://localhost:8180/", gameRobotConfig.getIoThreadNum(), gameRevProcessorDispatch);
            String userNum = "M000" + i;
            nettyClient.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    GameNioSocketChannel channel = (GameNioSocketChannel) future.channel();
                    channel.setConnType(ConnType.MINDPK);
                    channel.setUserNum(userNum);
                    channel.setGroupNum("123");
                    channel.setToken("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJNRU1CRVIiLCJqdGkiOiJNOTQ5MjkzOTExNjk4ODM3NTIzIiwic3ViIjoiMTU2MTYxNzU5ODkiLCJhdWQiOiIzOTkiLCJpYXQiOjE1MjYyODUwNDN9.AjFfIznT8W4bin3z9uswVkHLDM_FzbeypnF_pUnJ17ETPY5FF0fQ6v-MQLz4k3nQzFryDBqhN9HGST5uKLFaLA");
                    ChannelManager.addClient(userNum, nettyClient);
                }
            });
            nettyClient.open();
            System.out.println("connent successful------------------" + i);
        }
        System.out.println("connent successful");
    }
    
    @Ignore
    @Test
    public void sendAsync() throws Exception {
        NettyClient nettyClient = new NettyClient("ws://localhost:8180/", gameRobotConfig.getIoThreadNum(), gameRevProcessorDispatch);
        nettyClient.open();
        GameNioSocketChannel channel = (GameNioSocketChannel) nettyClient.getChannel();
        Message<String> revMsg  = new Message<>();
        revMsg.setMsgType(GameMsgType.HEART);
        revMsg.setContent("test");
        System.out.println(channel.isActive());
        String userNum = "M0001";
        ChannelManager.addClient(userNum, nettyClient);
        sendProcessor.sendAsync(revMsg, userNum, 5L);
        sendProcessor.sendAsync(revMsg, userNum, 5L);
        System.out.println("game over");
    }
    
    @Ignore
    @Test
    public void httpsLogin() throws InterruptedException {
        Map<String, String> params = new HashMap<>();
        params.put("pwd", "dengni1314.");
        String resultStr = HttpUtil.doPost("https://api.edian.shop/member-api/login/15616175989", params);
        System.out.println(resultStr);
    }
    
    @Ignore
    @Test
    public void wssConnent() throws Exception {
        String wssUrl = "wss://ws.edian.shop:443/";
        String wsUrl = "ws://wk.lovelawu.com:80/";
        String wsUrl2 = "ws://test.lovelawu.com:12381/";
        String wsUrl3 = "ws://127.0.0.1:9999/";
        NettyClient nettyClient = new NettyClient(wssUrl + 123, gameRobotConfig.getIoThreadNum(), gameRevProcessorDispatch);
        nettyClient.open();
        String userNum = "M0001";
        GameNioSocketChannel channel = (GameNioSocketChannel) nettyClient.getChannel();
        channel.setConnType(ConnType.MINDPK);
        channel.setUserNum(userNum);
        channel.setGroupNum("123");
        channel.setToken("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJNRU1CRVIiLCJqdGkiOiJNOTQ5MjkzOTExNjk4ODM3NTIzIiwic3ViIjoiMTU2MTYxNzU5ODkiLCJhdWQiOiIzOTkiLCJpYXQiOjE1MjYyODUwNDN9.AjFfIznT8W4bin3z9uswVkHLDM_FzbeypnF_pUnJ17ETPY5FF0fQ6v-MQLz4k3nQzFryDBqhN9HGST5uKLFaLA");
        ChannelManager.addClient(userNum, nettyClient);
        MindPkOnlineParam content = new MindPkOnlineParam();
        content.setAttendType(AttendTypeEnum.RANDOM);
        content.setIsHomeowner(false);
        Message<MindPkOnlineParam> message = new Message<>();
        message.setMsgType(GameMsgType.ONLINE);
        message.setContent(content);
        sendProcessor.send(message, userNum);
        System.out.println("game over");
    }
}
