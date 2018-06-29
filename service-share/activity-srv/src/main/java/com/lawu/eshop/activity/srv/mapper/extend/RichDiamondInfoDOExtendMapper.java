package com.lawu.eshop.activity.srv.mapper.extend;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

/**
 * 瑞奇岛E钻信息扩展Mapper
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public interface RichDiamondInfoDOExtendMapper {
    
    /**
     * 更新瑞奇岛E钻数量
     * @param diamond
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    void updateDiamondQuantity(@Param("diamond") BigDecimal diamond, @Param("luckyDiamond") BigDecimal luckDiamond);
    
}
