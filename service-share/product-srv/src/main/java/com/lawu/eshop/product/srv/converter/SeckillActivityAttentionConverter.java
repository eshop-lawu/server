package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.srv.bo.SeckillActivityAttentionBO;
import com.lawu.eshop.product.srv.domain.SeckillActivityAttentionDO;

/**
 * 抢购活动关注转换类
 * @author jiangxinjun
 * @createDate 2017年11月27日
 * @updateDate 2017年11月27日
 */
public class SeckillActivityAttentionConverter {
	
    /**
     * SeckillActivityAttentionDO List转SeckillActivityAttentionBO List
     * @param list
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27   日
     * @updateDate 2017年11月27日
     */
	public static List<SeckillActivityAttentionBO> convertSeckillActivityAttentionBOList(List<SeckillActivityAttentionDO> list) {
	    if (list == null || list.isEmpty()) {
	        return null;
	    }
	    List<SeckillActivityAttentionBO> rtn = new ArrayList<>();
	    for (SeckillActivityAttentionDO item : list) {
	        SeckillActivityAttentionBO seckillActivityAttentionBO = new SeckillActivityAttentionBO();
	        seckillActivityAttentionBO.setId(item.getId());
	        seckillActivityAttentionBO.setActivityId(item.getActivityId());
	        seckillActivityAttentionBO.setMemberId(item.getMemberId());
	        seckillActivityAttentionBO.setProductId(item.getProductId());
	        seckillActivityAttentionBO.setActivityProductId(item.getActivityProductId());
	        seckillActivityAttentionBO.setMemberNum(item.getMemberNum());
	        rtn.add(seckillActivityAttentionBO);
	    }
        return rtn;
    }
}
