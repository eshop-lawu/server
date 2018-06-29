package com.lawu.eshop.product.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.product.srv.domain.extend.SeckillActivityDOView;

/**
 * 商家参入活动
 * @Description
 * @author zhangrc
 * @date 2017年11月30日
 */
public interface SeckillActivityDOMapperExtend {
	
	List<SeckillActivityDOView> queryManagePage(Long merchantId, RowBounds rowBounds);
	
	int countManage(Long merchantId);

	int selectJoinStatus(Long productId);

}
