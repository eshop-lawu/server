package com.lawu.eshop.game.robot.util;

import com.alibaba.fastjson.JSON;

/**
 * @author Leach
 * @date 2018/3/14
 */
public class JsonUtil {

    /**
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T read(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     *
     * @param obj
     * @return
     */
    public static String write(Object obj){
        return JSON.toJSONString(obj);
    }
}
