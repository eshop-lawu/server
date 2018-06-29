package com.lawu.eshop.activity.srv.bo;

import java.math.BigDecimal;

/**
 * 生成红包BO
 * 
 * @author jiangxinjun
 * @createDate 2017年12月29日
 * @updateDate 2017年12月29日
 */
public class GenerateRedpacketBO {

    /**
     * 助力红包活动参与详情id
     */
    private Long attendDetailId;
    
    /**
     * 自动生成红包方案index
     */
    private Integer generateLargeRedpacketIndex;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户图像
     */
    private String headimg;

    /**
     * 助力人数
     */
    private Integer helpCount;

    /**
     * 原始红包金额
     */
    private BigDecimal originalMoney;

    /**
     * 最终红包金额
     */
    private BigDecimal finalMoney;

    public Long getAttendDetailId() {
        return attendDetailId;
    }

    public void setAttendDetailId(Long attendDetailId) {
        this.attendDetailId = attendDetailId;
    }

    public Integer getGenerateLargeRedpacketIndex() {
        return generateLargeRedpacketIndex;
    }

    public void setGenerateLargeRedpacketIndex(Integer generateLargeRedpacketIndex) {
        this.generateLargeRedpacketIndex = generateLargeRedpacketIndex;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Integer getHelpCount() {
        return helpCount;
    }

    public void setHelpCount(Integer helpCount) {
        this.helpCount = helpCount;
    }

    public BigDecimal getOriginalMoney() {
        return originalMoney;
    }

    public void setOriginalMoney(BigDecimal originalMoney) {
        this.originalMoney = originalMoney;
    }

    public BigDecimal getFinalMoney() {
        return finalMoney;
    }

    public void setFinalMoney(BigDecimal finalMoney) {
        this.finalMoney = finalMoney;
    }

}
