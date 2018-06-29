package com.lawu.eshop.ad.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.domain.extend.PointPoolDOView;

public interface PointPoolDOMapperExtend {
	
	List<PointPoolDO> selectMember(Long adId);
	
	
	PointPoolDO selectPoint(Long adId);
	
	
	PointPoolDOView getTotlePoint(Long  adId);
	
	
	void updatePointOut(Long  adId);
}