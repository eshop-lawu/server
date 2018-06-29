package com.lawu.eshop.activity.srv.domain.extend;

import java.util.Date;

/**
 * 根据下标获取幸运钻用户id
 * @author jiangxinjun
 * @createDate 2018年5月3日
 * @updateDate 2018年5月3日
 */
public class GetLuckyDiamondIdByIndexParam {
    
    /**
     * 动力值
     */
    private Integer power;
    
    /**
     * 钻石停止生长时间
     */
    private Date stopGrowingDate;
    
    /**
     * 下标
     */
    private Long idx;

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Date getStopGrowingDate() {
        return stopGrowingDate;
    }

    public void setStopGrowingDate(Date stopGrowingDate) {
        this.stopGrowingDate = stopGrowingDate;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }
    
}
