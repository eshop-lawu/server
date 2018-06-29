package com.lawu.eshop.cache.srv.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.cache.dto.GameMindRoomDetailsCacheDTO;
import com.lawu.eshop.cache.srv.CacheSrvApplicationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheSrvApplicationTest.class)
public class SerializerTest {
    
    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void serializerTest() {
        GameMindRoomDetailsCacheDTO gameMindRoomDetailsCacheDTO = (GameMindRoomDetailsCacheDTO) redisTemplate.opsForValue().get("GAME_MIND_RANDOM_ROOM_DETAILS_MG975542454541352961");
        System.out.println(gameMindRoomDetailsCacheDTO);
    }
    
}
