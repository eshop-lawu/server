package com.lawu.eshop.mall.srv.mapper.extend;

import com.lawu.eshop.mall.srv.domain.extend.DiscountPackageExtendDO;

public interface DiscountPackageExtendDOMapper {
	
    /**
     * 根据优惠套餐id查询优惠套餐以及套餐内容和详情图片
     * 
     * @param id
     * @return
     * @author Sunny
     * @date 2017年6月26日
     */
    DiscountPackageExtendDO selectByPrimaryKey(Long id);

}