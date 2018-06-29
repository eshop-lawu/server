package com.lawu.eshop.game.robot;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.lawu.eshop.game.robot.channel.ChannelManager;
import com.lawu.eshop.game.robot.channel.GameNioSocketChannel;
import com.lawu.eshop.game.robot.processor.GameRevProcessorDispatch;

@EnableScheduling
@Configuration
public class GameRobotAutoConfiguration {
    
    @Autowired
    private GameRevProcessorDispatch gameRevProcessorDispatch;
    
    @Bean(initMethod = "initialize")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {  
        ThreadPoolTaskScheduler threadPool = new ThreadPoolTaskScheduler();
        threadPool.setPoolSize(10);
        return threadPool;  
    }
    
    @Scheduled(fixedDelay = 500, initialDelay=100)
    public void heartbeat() {
        Map<String, NettyClient> clients = ChannelManager.getClients();
        clients.forEach((userNum, item) -> {
            gameRevProcessorDispatch.heart(userNum);
        });
    }
    
    /**
     * 定时检查管道是否还在信息在接收
     * 如果没有，关闭这个客户端连接
     * 
     * @author jiangxinjun
     * @createDate 2018年5月29日
     * @updateDate 2018年5月29日
     */
    @Scheduled(fixedDelay = 500, initialDelay=100)
    public void offline() {
        Map<String, NettyClient> clients = ChannelManager.getClients();
        clients.forEach((userNum, item) -> {
            GameNioSocketChannel channel = (GameNioSocketChannel) item.getChannel();
            Long timeout = System.currentTimeMillis() - channel.getLastReceivedTime().getTime();
            // 如果上次接收消息时间超过2分钟，断开连接
            if (timeout > 2 * 60 * 1000) {
                if (channel.isActive()) {
                    channel.close();
                } else {
                    // 因为连接失效，不会触发请求释放资源连接，需要手动触发
                    gameRevProcessorDispatch.exit(channel);
                    ChannelManager.removeChannel(userNum);
                }
            }
        });
    }
    
}
