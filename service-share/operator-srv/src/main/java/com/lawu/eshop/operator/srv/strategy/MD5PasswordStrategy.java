package com.lawu.eshop.operator.srv.strategy;

import org.springframework.stereotype.Service;

import com.lawu.utils.MD5;

/**
 * @author zhangyong
 * @date 2017/4/20.
 */
@Service
public class MD5PasswordStrategy implements PasswordStrategy {
    @Override
    public String encode(String originalPassword) {
        return MD5.MD5Encode(originalPassword);
    }
    
   
}
