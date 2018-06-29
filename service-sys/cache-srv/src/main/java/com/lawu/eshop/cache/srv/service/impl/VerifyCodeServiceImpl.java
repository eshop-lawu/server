package com.lawu.eshop.cache.srv.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.srv.constants.KeyConstant;
import com.lawu.eshop.cache.srv.service.VerifyCodeService;

/**
 * @author meishuquan
 * @date 2018/3/12.
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Long getVisitFrequency(String tag, Integer expireTime) {
        String key = KeyConstant.REDIS_KEY_SMS_VISIT_FREQUENCY + tag;
        String oldVal = stringRedisTemplate.opsForValue().get(key);
        Long newVal = 1L;
        if (StringUtils.isEmpty(oldVal)) {
            //如果不存在放1
            stringRedisTemplate.opsForValue().set(key, "1", expireTime, TimeUnit.MINUTES);
        } else {
            newVal = stringRedisTemplate.boundValueOps(key).increment(1);//val +1
        }
        return newVal;
    }

    @Override
    public Boolean getPicCodeFlag(String tag, Integer frequencyExpireTime, Integer flagExpireTime, Integer frequencyLimit) {
        String picFlag = stringRedisTemplate.opsForValue().get(KeyConstant.REDIS_KEY_PIC_CODE_IS_OPEN);
        if ("1".equals(picFlag)) {
            return true;
        }

        Long freequencyVal = getVisitFrequency(tag, frequencyExpireTime);
        String key = KeyConstant.REDIS_KEY_PIC_CODE_FLAG + tag;
        if (freequencyVal.intValue() > frequencyLimit) {
            stringRedisTemplate.opsForValue().set(key, "1", flagExpireTime, TimeUnit.MINUTES);
        }

        String flag = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(flag)) {
            stringRedisTemplate.opsForValue().set(key, "0");
            return false;
        } else {
            return flag.equals("1");
        }
    }

    @Override
    public Boolean verifyPicCode(String key, String picCode) {
        String code = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        stringRedisTemplate.delete(key);
        return code.equalsIgnoreCase(picCode);
    }

    @Override
    public void savePicCode(String key, String picCode, Integer expireTime) {
        stringRedisTemplate.opsForValue().set(key, picCode, expireTime, TimeUnit.MINUTES);
    }

    @Override
    public void updatePicCodeFlag(Boolean isOpen) {
        String key = KeyConstant.REDIS_KEY_PIC_CODE_IS_OPEN;
        stringRedisTemplate.opsForValue().set(key, isOpen ? "1" : "0");
    }

}
