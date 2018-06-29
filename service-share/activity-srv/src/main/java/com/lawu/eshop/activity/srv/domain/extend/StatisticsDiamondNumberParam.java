package com.lawu.eshop.activity.srv.domain.extend;

/**
 * 统计钻石分配数量
 * @author jiangxinjun
 * @createDate 2018年5月8日
 * @updateDate 2018年5月8日
 */
public class StatisticsDiamondNumberParam {
    
    /**
     * 类型
     */
    private Byte type;
    
    /**
     * 用户类型
     */
    private Byte userType;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }
    
}
