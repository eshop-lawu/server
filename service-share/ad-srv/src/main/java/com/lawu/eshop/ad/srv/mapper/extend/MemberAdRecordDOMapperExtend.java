package com.lawu.eshop.ad.srv.mapper.extend;

import com.lawu.eshop.ad.srv.domain.MemberAdRecordDO;
import com.lawu.eshop.ad.srv.domain.extend.MemberAdRecordDOView;

public interface MemberAdRecordDOMapperExtend {
	
	MemberAdRecordDOView selectPointToday(MemberAdRecordDO marDO);
	
	MemberAdRecordDOView getTotlePoint(Long  adId);
  
}