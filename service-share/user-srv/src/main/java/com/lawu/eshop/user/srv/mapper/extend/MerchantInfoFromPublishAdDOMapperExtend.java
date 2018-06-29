package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.user.srv.domain.extend.MerchantInfoFromPublishAdDOView;

public interface MerchantInfoFromPublishAdDOMapperExtend {

	List<MerchantInfoFromPublishAdDOView> getMerchantInfoFromPublishAd(Long merchantId);
	
}
