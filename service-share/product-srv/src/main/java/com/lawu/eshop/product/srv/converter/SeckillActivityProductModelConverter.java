package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.srv.bo.SeckillActivityProductModelBO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductModelDO;

/**
 * 抢购活动商品型号转换类
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
public class SeckillActivityProductModelConverter {
	
    /**
     * SeckillActivityProductModelDO List转SeckillActivityProductModelBO List
     * @param list
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
	public static List<SeckillActivityProductModelBO> convertSeckillActivityProductModelBOList(List<SeckillActivityProductModelDO> list) {
	    if (list == null || list.isEmpty()) {
	        return null;
	    }
	    List<SeckillActivityProductModelBO> rtn = new ArrayList<>();
	    for (SeckillActivityProductModelDO item : list) {
	        SeckillActivityProductModelBO entry = new SeckillActivityProductModelBO();
	        entry.setActivityProductId(item.getActivityProductId());
	        entry.setCount(item.getCount());
	        entry.setId(item.getId());
	        entry.setLeftCount(item.getLeftCount());
	        entry.setProductModelId(item.getProductModelId());
	        rtn.add(entry);
	    }
        return rtn;
    }
}
