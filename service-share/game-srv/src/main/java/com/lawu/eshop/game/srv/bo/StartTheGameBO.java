package com.lawu.eshop.game.srv.bo;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.cache.dto.StartTheGameUserDTO;

/**
 * 房间开始游戏BO
 * @author jiangxinjun
 * @createDate 2018年3月23日
 * @updateDate 2018年3月23日
 */
public class StartTheGameBO {
    
    /**
     * 参与积分
     */
    private BigDecimal attendPoint;
    
    /**
     * 房间内用户信息
     */
    private List<StartTheGameUserDTO> userInfos;
    
    public BigDecimal getAttendPoint() {
        return attendPoint;
    }

    public void setAttendPoint(BigDecimal attendPoint) {
        this.attendPoint = attendPoint;
    }

    public List<StartTheGameUserDTO> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<StartTheGameUserDTO> userInfos) {
        this.userInfos = userInfos;
    }
    
}
