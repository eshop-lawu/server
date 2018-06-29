package com.lawu.eshop.cache.srv.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.lawu.eshop.cache.constants.RedisDataTypeEnum;
import com.lawu.eshop.cache.srv.service.SerializeUtilCacheService;

@Service
public class SerialzeUtilCacheServiceImpl implements SerializeUtilCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String get(String redisKey, RedisDataTypeEnum keyType,int serializeFlag) {
        String rtn = "";
        JdkSerializationRedisSerializer ze = new JdkSerializationRedisSerializer();
        if(RedisDataTypeEnum.VALUE.getName().equals(keyType.getName())){
            if(serializeFlag == 1){
                Object obj = redisTemplate.opsForValue().get(redisKey);
                Object o = ze.deserialize(toByteArray(obj));
                SerializeConfig mapping = new SerializeConfig();
                mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss.SSS"));
                rtn = JSONObject.toJSONString(o, mapping);
            } else {
                rtn = stringRedisTemplate.opsForValue().get(redisKey);
            }

        } else if(RedisDataTypeEnum.LIST.getName().equals(keyType.getName())){
            if(serializeFlag == 1){
                BoundListOperations<String, Object> listOperations = redisTemplate.boundListOps(redisKey);
                RedisOperations<String,Object> redisOperations = listOperations.getOperations();
                Object o = ze.deserialize(toByteArray(redisOperations));
                rtn = JSONObject.toJSONString(o);
            } else {
                RedisOperations<String,Object> redisOperations = redisTemplate.opsForList().getOperations();
                rtn = JSONObject.toJSONString(redisOperations);
            }

        } else if(RedisDataTypeEnum.MAP.getName().equals(keyType.getName())){
            BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps(redisKey);
            Map<String,Object> obj = boundHashOperations.entries();
            Object o = ze.deserialize(toByteArray(obj));
            rtn = JSONObject.toJSONString(o);
        }
        return rtn;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
