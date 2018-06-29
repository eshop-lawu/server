package com.lawu.eshop.game.robot.channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lawu.eshop.game.robot.NettyClient;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public class ChannelManager {

    /**
     * NettyClient缓存
     * <用户编号, NettyClient>
     */
    private static Map<String, NettyClient> clients = new ConcurrentHashMap<>();

    /**
     * 将NettyClient添加到缓存
     * @param client
     */
    public static void addClient(String userNum, NettyClient client) {
        clients.put(userNum, client);
    }

    /**
     * 从缓存中移除指定用户的NettyClient
     * @param userNum
     */
    public static void removeChannel(String userNum) {
        if (userNum == null) {
            return;
        }
        NettyClient client = clients.get(userNum);
        if (client == null) {
            return;
        }
        // 关闭,清除资源
        client.close();
        clients.remove(userNum);
    }

    /**
     * 获取NettyClient
     * @param userNum
     * @return
     */
    public static NettyClient getClient(String userNum) {
        if (userNum == null) {
            return null;
        }
        return clients.get(userNum);
    }

    public static Map<String, NettyClient> getClients() {
        return clients;
    }
    
}
