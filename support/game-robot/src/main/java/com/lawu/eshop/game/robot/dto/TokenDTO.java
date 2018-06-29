package com.lawu.eshop.game.robot.dto;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public class TokenDTO {

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 用户token，登录后每次请求必须带上该标志
     */
    private String token;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}
