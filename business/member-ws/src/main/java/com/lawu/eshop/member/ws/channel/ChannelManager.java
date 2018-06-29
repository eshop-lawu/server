package com.lawu.eshop.member.ws.channel;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lawu.eshop.game.constants.ConnType;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * netty-channel 缓存管理
 * @author Leach
 * @date 2018/3/13
 */
public class ChannelManager {

    /**
     * channel组管理
     * 一个用户对应一个channel，一个房间/一次参与对应一组channel
     * <ConnType+groupNum, channel>
     */
    private static Map<String, ChannelGroup> groups = new ConcurrentHashMap<>();

    /**
     * channel缓存
     * <用户编号, channel>
     */
    private static Map<String, GameNioSocketChannel> channels = new ConcurrentHashMap<>();

    /**
     * 通道离线时间
     */
    private static Map<String, OfflineChannel> offlineChannels = new ConcurrentHashMap<>();

    /**
     * 将channel添加到缓存
     * @param channel
     */
    public static void addChannel(GameNioSocketChannel channel) {
        String groupNum = channel.getGroupNum();
        String userNum = channel.getUserNum();
        if (groupNum == null || userNum == null) {
            return;
        }
        removeOfflineChannel(userNum);

        String groupKey = channel.getConnType().name() + groupNum;
        ChannelGroup channelGroup = groups.get(groupKey);
        if (channelGroup == null) {
            // 新建group
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            ChannelGroup existChannel = groups.putIfAbsent(groupKey, channelGroup);
            // 判断放入时是否已存在
            if (existChannel != null) {
                channelGroup = existChannel;
            }
        }
        GameNioSocketChannel oldChannel = channels.get(userNum);
        channels.put(userNum, channel);
        channelGroup.add(channel);

        if (oldChannel != null && oldChannel.id() != channel.id()) {
            channelGroup.remove(oldChannel);
        }
    }

    /**
     * 从缓存中移除指定用户的channel
     * @param userNum
     */
    public static void removeChannel(String userNum) {

        if (userNum == null) {
            return;
        }
        GameNioSocketChannel channel = channels.get(userNum);

        if (channel == null) {
            return;
        }

        channels.remove(userNum);
        String groupNum = channel.getGroupNum();
        if (groupNum == null) {
            return;
        }
        String groupKey = channel.getConnType().name() + groupNum;

        ChannelGroup channelGroup = groups.get(groupKey);
        if (channelGroup != null) {
            channelGroup.remove(channel);

            if (channelGroup.isEmpty()) {
                groups.remove(groupKey);
            }
        }

    }

    /**
     * 缓存channel组
     * @param type
     * @param groupNum
     * @return
     */
    public static ChannelGroup getGroup(ConnType type, String groupNum) {
        if (groupNum == null) {
            return null;
        }
        String groupKey = type.name() + groupNum;
        return groups.get(groupKey);
    }

    /**
     * 获取channel
     * @param userNum
     * @return
     */
    public static GameNioSocketChannel getChannel(String userNum) {
        if (userNum == null) {
            return null;
        }
        return channels.get(userNum);
    }

    /**
     * 获取用户所在组别
     */
    public static String getGroupNum(String userNum) {
        GameNioSocketChannel channel = ChannelManager.getChannel(userNum);
        if (channel != null) {
            return channel.getGroupNum();
        }
        return null;
    }

    public static Map<String, OfflineChannel> getOfflineChannels() {
        return offlineChannels;
    }

    public static void removeOfflineChannel(String userNum) {

        offlineChannels.remove(userNum);
    }

    public static void addOfflineChannel(GameNioSocketChannel channel) {

        if (channel.getUserNum() == null) {
            return;
        }

        OfflineChannel offlineChannel = new OfflineChannel(channel.getConnType(), new Date(), channel.isOfflineSelf());
        offlineChannels.put(channel.getUserNum(), offlineChannel);
    }
}
