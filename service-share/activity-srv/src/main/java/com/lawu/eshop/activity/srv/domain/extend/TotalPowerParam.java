package com.lawu.eshop.activity.srv.domain.extend;

import java.util.List;

/**
 * 获取总共动力值参数
 * @author jiangxinjun
 * @createDate 2018年5月3日
 * @updateDate 2018年5月3日
 */
public class TotalPowerParam {
    
    /**
     * 动力值
     */
    private Integer power;
    
    /**
     * id集合
     */
    private List<Long> ids;

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
    
}
