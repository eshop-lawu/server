package com.lawu.eshop.cache.srv.service.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.dto.AbnormalRedisCountDTO;
import com.lawu.eshop.cache.param.AbnormalInfoParam;
import com.lawu.eshop.cache.param.EarlyAbnormalParam;
import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.RegMachineService;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
@Service
public class RegMachineServiceImpl implements RegMachineService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addHfRegRedisRecord(AbnormalInfoParam param) {
        //短高频注册
        String key = KeyConstant.REDIS_KEY_SHORT_FREQUENCY_REG.concat(param.getUserNum());
        String oldVal = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(oldVal)) {
            //如果不存在放1
            stringRedisTemplate.opsForValue().set(key, "1", param.getShortTime(), TimeUnit.MINUTES);
        } else {
            if (Integer.valueOf(oldVal) > param.getShortCount()) {
                //短高频注册触发
                addManyShortFrequencyReg(param.getUserNum(), param.getManyShortTime());
            }
            stringRedisTemplate.boundValueOps(key).increment(1);//val +1
        }

        //长高频注册
        String longKey = KeyConstant.REDIS_KEY_LONG_FREQUENCY_REG.concat(param.getUserNum());
        String oldLongVal = stringRedisTemplate.opsForValue().get(longKey);
        if (StringUtils.isEmpty(oldLongVal)) {
            //如果不存在放1
            stringRedisTemplate.opsForValue().set(longKey, "1", param.getLongTime(), TimeUnit.HOURS);
        } else {
            if (Integer.valueOf(oldLongVal) > param.getLongCount()) {
                //长高频高频注册触发
                addManyLongFrequencyReg(param.getUserNum(), param.getManyLongTime());
            }
            stringRedisTemplate.boundValueOps(longKey).increment(1);//val +1
        }

        //一天下线总数
        String oneDayKey = KeyConstant.REDIS_KEY_ONE_DAY_FREQUENCY_REG.concat(param.getUserNum());
        String oldOneVal = stringRedisTemplate.opsForValue().get(oneDayKey);
        if (StringUtils.isEmpty(oldOneVal)) {
            //如果不存在放1
            stringRedisTemplate.opsForValue().set(oneDayKey, "1", param.getOneDayTime(), TimeUnit.DAYS);
        } else {

            stringRedisTemplate.boundValueOps(oneDayKey).increment(1);//val +1
        }

    }

    @Override
    public AbnormalRedisCountDTO getAbnormalCount(String userNum) {
        AbnormalRedisCountDTO countDTO = new AbnormalRedisCountDTO();
        //短高频
        String key = KeyConstant.REDIS_KEY_SHORT_FREQUENCY_REG.concat(userNum);
        String shortCount = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(shortCount)) {
            countDTO.setShortHfCount(0);
        }else{
            countDTO.setShortHfCount(Integer.valueOf(shortCount));
        }

        //长高频
        String longKey = KeyConstant.REDIS_KEY_LONG_FREQUENCY_REG.concat(userNum);
        String longCount = stringRedisTemplate.opsForValue().get(longKey);
        if (StringUtils.isEmpty(longCount)) {
            countDTO.setLongHfCount(0);
        }else{
            countDTO.setLongHfCount(Integer.valueOf(longCount));
        }

        //多次短高频
        String manyShortKey = KeyConstant.REDIS_KEY_MANY_SHORT_FREQUENCY_REG.concat(userNum);
        String manyShortCount = stringRedisTemplate.opsForValue().get(manyShortKey);
        if (StringUtils.isEmpty(manyShortCount)) {
            countDTO.setManyShortHfCount(0);
        }else{
            countDTO.setManyShortHfCount(Integer.valueOf(manyShortCount));
        }

        //多次长高频
        String manyLongKey = KeyConstant.REDIS_KEY_MANY_LONG_FREQUENCY_REG.concat(userNum);
        String manyLongCount = stringRedisTemplate.opsForValue().get(manyLongKey);
        if (StringUtils.isEmpty(manyLongCount)) {
            countDTO.setManyLongHfCount(0);
        }else{
            countDTO.setManyLongHfCount(Integer.valueOf(manyLongCount));
        }

        //一天高频
        String oneKey = KeyConstant.REDIS_KEY_ONE_DAY_FREQUENCY_REG.concat(userNum);
        String oneDayCount = stringRedisTemplate.opsForValue().get(oneKey);
        if (StringUtils.isEmpty(oneDayCount)) {
            countDTO.setOneDayCount(0);
        }else{
            countDTO.setOneDayCount(Integer.valueOf(oneDayCount));
        }

        //凌晨
        String earlyKey = KeyConstant.REDIS_KEY_EARLY_FREQUENCY_REG.concat(userNum);
        String earlyCount = stringRedisTemplate.opsForValue().get(earlyKey);
        if (StringUtils.isEmpty(earlyCount)) {
            countDTO.setEarlyCount(0);
        }else{
            countDTO.setEarlyCount(Integer.valueOf(earlyCount));
        }

        return countDTO;
    }

    @Override
    public void addEarlyHfRedisRecord(EarlyAbnormalParam param) {
        String key = KeyConstant.REDIS_KEY_EARLY_FREQUENCY_REG.concat(param.getUserNum());
        String oldVal = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(oldVal)) {
            //如果不存在放1
            long startTime = new Date().getTime();
            long endTime = param.getTime().getTime();
            long redTime = (endTime - startTime) / 1000;
            stringRedisTemplate.opsForValue().set(key, "1", redTime, TimeUnit.SECONDS);
        } else {
            stringRedisTemplate.boundValueOps(key).increment(1);//val +1
        }
    }

    private void addManyShortFrequencyReg(String userNum, Integer manyTime) {
        //短高频注册触发
        String manyKey = KeyConstant.REDIS_KEY_MANY_SHORT_FREQUENCY_REG.concat(userNum);
        String oldManyVal = stringRedisTemplate.opsForValue().get(manyKey);
        if (StringUtils.isEmpty(oldManyVal)) {
            //如果不存在放1
            stringRedisTemplate.opsForValue().set(manyKey, "1", manyTime, TimeUnit.DAYS);
        } else {
            stringRedisTemplate.boundValueOps(manyKey).increment(1);//val +1
        }
    }

    private void addManyLongFrequencyReg(String userNum, Integer manyTime) {
        //长高频注册触发
        String manyKey = KeyConstant.REDIS_KEY_MANY_LONG_FREQUENCY_REG.concat(userNum);
        String oldManyVal = stringRedisTemplate.opsForValue().get(manyKey);
        if (StringUtils.isEmpty(oldManyVal)) {
            //如果不存在放1
            stringRedisTemplate.opsForValue().set(manyKey, "1", manyTime, TimeUnit.DAYS);
        } else {
            stringRedisTemplate.boundValueOps(manyKey).increment(1);//val +1
        }
    }
}
