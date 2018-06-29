package com.lawu.eshop.cache.param;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author meishuquan
 * @date 2018/5/3.
 */
public class DiamondConfigCacheParam implements Serializable {

    private static final long serialVersionUID = -7164111256461524041L;

    /**
     * 瑞奇岛总人数
     */
    private Integer richPopulation;

    /**
     * 创世居民人数上限
     */
    private Integer creationPeople;

    /**
     * 每日发行E钻数量
     */
    private BigDecimal dailyDiamond;

    /**
     * 每次领取E钻数量
     */
    private BigDecimal onceDiamond;

    /**
     * 每日发行幸运钻钻数量
     */
    private BigDecimal dailyDiamondLucky;

    /**
     * 每次领取E钻数量
     */
    private BigDecimal onceDiamondLucky;

    /**
     * 获取幸运钻动力资格
     */
    private Integer gainDiamondLuckyPower;

    /**
     * 获取幸运钻消耗动力
     */
    private Integer gainDiamondLuckyConsumePower;

    /**
     * 挖矿初始动力值
     */
    private Integer initPower;

    /**
     * 抽取幸运用户概率
     */
    private BigDecimal gainDiamondLuckyScale;

    /**
     * 初始发行时间
     */
    private String initReleaseTime;

    /**
     * 发行间隔时间(小时)
     */
    private Integer releaseInterval;

    /**
     * 是否开启瑞奇岛
     */
    private Boolean isOpen;

    /**
     * E钻生长时间(小时)
     */
    private Integer diamondGrowTime;

    public Integer getRichPopulation() {
        return richPopulation;
    }

    public void setRichPopulation(Integer richPopulation) {
        this.richPopulation = richPopulation;
    }

    public Integer getCreationPeople() {
        return creationPeople;
    }

    public void setCreationPeople(Integer creationPeople) {
        this.creationPeople = creationPeople;
    }

    public BigDecimal getDailyDiamond() {
        return dailyDiamond;
    }

    public void setDailyDiamond(BigDecimal dailyDiamond) {
        this.dailyDiamond = dailyDiamond;
    }

    public BigDecimal getOnceDiamond() {
        return onceDiamond;
    }

    public void setOnceDiamond(BigDecimal onceDiamond) {
        this.onceDiamond = onceDiamond;
    }

    public BigDecimal getDailyDiamondLucky() {
        return dailyDiamondLucky;
    }

    public void setDailyDiamondLucky(BigDecimal dailyDiamondLucky) {
        this.dailyDiamondLucky = dailyDiamondLucky;
    }

    public BigDecimal getOnceDiamondLucky() {
        return onceDiamondLucky;
    }

    public void setOnceDiamondLucky(BigDecimal onceDiamondLucky) {
        this.onceDiamondLucky = onceDiamondLucky;
    }

    public Integer getGainDiamondLuckyPower() {
        return gainDiamondLuckyPower;
    }

    public void setGainDiamondLuckyPower(Integer gainDiamondLuckyPower) {
        this.gainDiamondLuckyPower = gainDiamondLuckyPower;
    }

    public Integer getGainDiamondLuckyConsumePower() {
        return gainDiamondLuckyConsumePower;
    }

    public void setGainDiamondLuckyConsumePower(Integer gainDiamondLuckyConsumePower) {
        this.gainDiamondLuckyConsumePower = gainDiamondLuckyConsumePower;
    }

    public Integer getInitPower() {
        return initPower;
    }

    public void setInitPower(Integer initPower) {
        this.initPower = initPower;
    }

    public BigDecimal getGainDiamondLuckyScale() {
        return gainDiamondLuckyScale;
    }

    public void setGainDiamondLuckyScale(BigDecimal gainDiamondLuckyScale) {
        this.gainDiamondLuckyScale = gainDiamondLuckyScale;
    }

    public String getInitReleaseTime() {
        return initReleaseTime;
    }

    public void setInitReleaseTime(String initReleaseTime) {
        this.initReleaseTime = initReleaseTime;
    }

    public Integer getReleaseInterval() {
        return releaseInterval;
    }

    public void setReleaseInterval(Integer releaseInterval) {
        this.releaseInterval = releaseInterval;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean open) {
        isOpen = open;
    }

    public Integer getDiamondGrowTime() {
        return diamondGrowTime;
    }

    public void setDiamondGrowTime(Integer diamondGrowTime) {
        this.diamondGrowTime = diamondGrowTime;
    }

}
