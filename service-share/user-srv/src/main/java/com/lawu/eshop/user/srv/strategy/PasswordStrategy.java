package com.lawu.eshop.user.srv.strategy;

/**
 * 密码加密策略
 *
 * @author Leach
 * @date 2017/3/27
 */
public interface PasswordStrategy {

    String encode(String originalPassword);
}
