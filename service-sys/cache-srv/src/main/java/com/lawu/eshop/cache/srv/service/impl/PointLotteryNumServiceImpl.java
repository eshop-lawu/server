package com.lawu.eshop.cache.srv.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.PointLotteryNumService;

/**
 * @author meishuquan
 * @date 2018/2/23.
 */
@Service
public class PointLotteryNumServiceImpl implements PointLotteryNumService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Long getNewPointLotteryNum(Long pointLotteryActivityId) {
        String key = KeyConstant.REDIS_KEY_POINT_LOTTERY_ACTIVITY.concat(String.valueOf(pointLotteryActivityId));
        String oldVal = stringRedisTemplate.opsForValue().get(key);
        Long newVal = 1L;
        if (StringUtils.isEmpty(oldVal)) {
            //如果不存在放1
            stringRedisTemplate.opsForValue().set(key, "1");
        } else {
            newVal = stringRedisTemplate.boundValueOps(key).increment(1);//val +1
        }
        return newVal;
    }

    @Override
    public void delPointLotteryActivity(Long pointLotteryActivityId) {
        String key = KeyConstant.REDIS_KEY_POINT_LOTTERY_ACTIVITY.concat(String.valueOf(pointLotteryActivityId));
        stringRedisTemplate.delete(key);
    }

}
