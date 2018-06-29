package com.lawu.eshop.cache.dto;

/**
 * 开始游戏返回的用户DTO
 * @author jiangxinjun
 * @createDate 2018年3月24日
 * @updateDate 2018年3月24日
 */
public class StartTheGameUserDTO {
    
    /**
     * 用户编号
     */
    private String userNum;
    
    /**
     * 是否是房主
     */
    private Boolean isHomeowner;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Boolean getIsHomeowner() {
        return isHomeowner;
    }

    public void setIsHomeowner(Boolean isHomeowner) {
        this.isHomeowner = isHomeowner;
    }
    
}
