package com.lawu.eshop.activity.srv.mapper.extend;

import com.lawu.eshop.activity.srv.domain.extend.UpdateAssignedQuantityParam;
import com.lawu.eshop.activity.srv.domain.extend.UpdateLuckyAssignedQuantityParam;

/**
 * 金蛋分配记录扩展Mapper
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
public interface RichDiamondDistributionRecordDOExtendMapper {
    
    /**
     * 更新分配的小鸡数量以及分配的金蛋数量
     * @param param
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    void updateAssignedQuantity(UpdateAssignedQuantityParam param);
    
    /**
     * 更新分配的小鸡数量以及分配的金蛋数量
     * @param param
     * @author jiangxinjun
     * @createDate 2018年5月7日
     * @updateDate 2018年5月7日
     */
    void updateLuckyAssignedQuantity(UpdateLuckyAssignedQuantityParam param);
    
}
