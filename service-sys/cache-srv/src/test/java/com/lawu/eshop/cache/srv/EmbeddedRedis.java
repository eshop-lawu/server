package com.lawu.eshop.cache.srv;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import redis.embedded.RedisServer;

/**
 * 内嵌redis启动类
 * 需要redis服务的单元测试类可以继承该类
 * 类内共享一个redis，注意数据问题；类之间不共享redis
 *
 * @author Leach
 * @date 2017/10/11
 */
public abstract class EmbeddedRedis {
    private static RedisServer redisServer = null;

    @BeforeClass
    public static void setup() {
        try {
            redisServer = new RedisServer(6379);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
        // 启动时, 可能本地已经启动了redis服务
        try {
            redisServer.start();
        } catch (Exception e) {
        }
    }

    @AfterClass
    public static void finish() {
        redisServer.stop();
        redisServer = null;
    }
}
