package com.lawu.eshop.user.srv.strategy;

import org.springframework.stereotype.Component;

import com.lawu.utils.MD5;

/**
 * @author Leach
 * @date 2017/3/27
 */
@Component
public class MD5PasswordStrategy implements PasswordStrategy {

    @Override
    public String encode(String originalPassword) {
        return MD5.MD5Encode(originalPassword);
    }
}
